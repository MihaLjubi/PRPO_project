package si.fri.prpo.polnilnice.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;
import si.fri.prpo.polnilnice.DTO.RacunDTO;
import si.fri.prpo.polnilnice.entitete.Racun;
import si.fri.prpo.polnilnice.zrna.RacunZrno;
import si.fri.prpo.polnilnice.zrna.UpravljanjePolnilnihPostajZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("racuni")
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class RacunVir {
    private Logger logger = Logger.getLogger(RacunVir.class.getName());
    @Context
    protected UriInfo uriInfo;

    @Inject
    private RacunZrno racunZrno;

    @Inject
    private UpravljanjePolnilnihPostajZrno upravljanjePolnilnihPostajZrno;

    @GET
    @Operation(summary="returns all receipts")
    @APIResponse(responseCode = "200", description = "Request was successful")
    public Response getAllReceipts() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Racun> racuni = racunZrno.getRacuni(query);
        return Response.status(Response.Status.OK).entity(racuni).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary="returns the receipt by provided id")
    @APIResponse(responseCode = "200", description = "Request was successful")
    public Response getReceipt(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id) {
        Racun racun = racunZrno.getById(id);
        if(racun != null) {
            return Response.status(Response.Status.OK).entity(racun).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Operation(summary="creates a receipt and saves to db")
    @APIResponse(responseCode = "200", description = "Request was successful")
    public Response addRacun(@Parameter(name="id", required = true, allowEmptyValue = false) RacunDTO racunDTO) {
        Racun r = upravljanjePolnilnihPostajZrno.izdajRacun(racunDTO);
        if(r != null) {
            return Response.status(Response.Status.OK).entity(r).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    @Operation(summary="updates info of the receipt provided by id")
    @APIResponse(responseCode = "200", description = "Request was successful")
    public Response updateRacun(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id, Racun racun) {
        Racun r = racunZrno.updateRacun(id, racun);
        if(r != null) {
            return Response.status(Response.Status.OK).entity(r).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Operation(summary="deletes the receipt provided by id")
    @APIResponse(responseCode = "200", description = "Request was successful")
    public Response deleteRacun(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id) {
        var result = racunZrno.deleteRacun(id);
        if(result) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}