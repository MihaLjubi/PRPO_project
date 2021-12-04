package si.fri.prpo.polnilnice.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import si.fri.prpo.polnilnice.entitete.Uporabnik;
import si.fri.prpo.polnilnice.zrna.UporabnikZrno;

import java.util.List;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("uporabniki")
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class UporabnikVir {

    private Logger logger = Logger.getLogger(UporabnikVir.class.getName());

    @Context
    protected UriInfo uriInfo;

    @Inject
    private UporabnikZrno uporabnikZrno;

    @GET
    @Operation(description = "Returns list of users", summary="User list")
    @APIResponses({
        @APIResponse(responseCode = "200",
                description = "Success"
        ),
        @APIResponse(responseCode = "404", description = "Useres not found")
    })
    public Response getAllUsers() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> users = uporabnikZrno.getUporabniki(query);
        Long countUporabniki = uporabnikZrno.getUporabnikiCount(query);
        return Response.status(Response.Status.OK).entity(users).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary="returns the user provided by id")
    @APIResponse(responseCode = "200", description = "Request was successful")
    public Response getUser(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id) {
        Uporabnik user = uporabnikZrno.getById(id);
        if(user != null) {
            return Response.status(Response.Status.OK).entity(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Operation(summary="creates a new user and saves to db")
    @APIResponse(responseCode = "200", description = "Request was successful")
    public Response addUser(@Parameter(name="id", required = true, allowEmptyValue = false) Uporabnik user) {
        Uporabnik u = uporabnikZrno.createUser(user);
        if(user != null) {
            return Response.status(Response.Status.OK).entity(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    @Operation(summary="updates info of the user provided by id")
    @APIResponse(responseCode = "200", description = "Request was successful")
    public Response updateUser(@PathParam("id") Integer id, Uporabnik user) {
        Uporabnik u = uporabnikZrno.updateUser(id, user);
        if(u != null) {
            return Response.status(Response.Status.OK).entity(u).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Operation(summary="delets teh user provided by id from db")
    @APIResponse(responseCode = "200", description = "Request was successful")
    public Response deleteUser(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id) {
        var result = uporabnikZrno.deleteUser(id);
        if(result) {
            return Response.status(Response.Status.OK).entity(result).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
