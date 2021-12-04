package si.fri.prpo.polnilnice.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
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

import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja;
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

    @Operation(description = "Returns list of users", summary="User list")
    @APIResponses({
        @APIResponse(responseCode = "200",
                description = "Success",
                content = @Content(
                        schema = @Schema(implementation = Uporabnik.class)
                ),
                headers = { @Header(name = "X-Total-Count", description = "Number of users") }
        ),
        @APIResponse(responseCode = "404", description = "Useres not found")
    })
    @GET
    public Response getAllUsers() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> users = uporabnikZrno.getUporabniki(query);
        Long uporabnikiCount = uporabnikZrno.getUporabnikiCount(query);
        return Response.status(Response.Status.OK).entity(users).header("X-Total-Count", uporabnikiCount).build();
    }


    @Operation(description = "Returns user", summary="User details")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(
                            schema = @Schema(implementation = Uporabnik.class)
                    )
            ),
            @APIResponse(responseCode = "404", description = "User not found")
    })
    @GET
    @Path("{id}")
    public Response getUser(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id) {
        Uporabnik user = uporabnikZrno.getById(id);
        if(user != null) {
            return Response.status(Response.Status.OK).entity(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Create user", summary="User creation")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "User added",
                    content = @Content(
                            schema = @Schema(implementation = Uporabnik.class)
                    )
            ),
            @APIResponse(responseCode = "405", description = "Validation error")
    })
    @POST
    public Response addUser(@Parameter(name="id", required = true, allowEmptyValue = false) Uporabnik user) {
        Uporabnik u = uporabnikZrno.createUser(user);
        if(user != null) {
            return Response.status(Response.Status.OK).entity(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Updates user", summary="User update")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "User updated"
            ),
            @APIResponse(responseCode = "404", description = "User not found")
    })
    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") Integer id, Uporabnik user) {
        Uporabnik u = uporabnikZrno.updateUser(id, user);
        if(u != null) {
            return Response.status(Response.Status.OK).entity(u).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Deletes user", summary="User deletion")
    @APIResponses({
            @APIResponse(responseCode = "204",
                    description = "User deleted"
            ),
            @APIResponse(responseCode = "404", description = "User not found")
    })
    @DELETE
    @Path("{id}")
    public Response deleteUser(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id) {
        var result = uporabnikZrno.deleteUser(id);
        if(result) {
            return Response.status(Response.Status.OK).entity(result).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
