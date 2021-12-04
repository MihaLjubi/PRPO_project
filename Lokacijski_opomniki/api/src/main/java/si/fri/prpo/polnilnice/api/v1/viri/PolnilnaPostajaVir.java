package si.fri.prpo.polnilnice.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;
import si.fri.prpo.polnilnice.DTO.PolnilnaPostajaDTO;
import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja;
import si.fri.prpo.polnilnice.zrna.PolnilnaPostajaZrno;
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
@Path("polnilnepostaje")
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class PolnilnaPostajaVir {
    private Logger logger = Logger.getLogger(PolnilnaPostajaVir.class.getName());
    @Context
    protected UriInfo uriInfo;

    @Inject
    private PolnilnaPostajaZrno polnilnaPostajaZrno;

    @Inject
    private UpravljanjePolnilnihPostajZrno upravljanjePolnilnihPostajZrno;

    @GET
    @Operation(summary="returns list of all charging stations")
    @APIResponse(responseCode = "200", description = "Request was successful")
    public Response getAllChargingStations() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<PolnilnaPostaja> polnilnepostaje = polnilnaPostajaZrno.getPolnilnePostaje(query);
        return Response.status(Response.Status.OK).entity(polnilnepostaje).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary="returns the charging station provided by id")
    @APIResponse(responseCode = "200", description = "Request was successful")
    public Response getChargingStation(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id) {
        PolnilnaPostaja pp = polnilnaPostajaZrno.getById(id);
        if(pp != null) {
            return Response.status(Response.Status.OK).entity(pp).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Operation(summary="adds new charging station to db")
    @APIResponse(responseCode = "200", description = "Request was successful")
    public Response addChargingStation(@Parameter(name="id", required = true, allowEmptyValue = false) PolnilnaPostajaDTO polnilnaPostajaDTO) {
        PolnilnaPostaja pp = upravljanjePolnilnihPostajZrno.ustvariPolnilnoPostajo(polnilnaPostajaDTO);
        if(pp != null) {
            return Response.status(Response.Status.OK).entity(pp).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    @Operation(summary="updates info of the charging station provided by id")
    @APIResponse(responseCode = "200", description = "Request was successful")
    public Response updateChargingStation(@PathParam("id") Integer id, PolnilnaPostaja polnilnaPostaja) {
        PolnilnaPostaja pp = polnilnaPostajaZrno.updateChargingStation(id, polnilnaPostaja);
        if(pp != null) {
            return Response.status(Response.Status.OK).entity(pp).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Operation(summary="deletes the charging station provided by id")
    @APIResponse(responseCode = "200", description = "Request was successful")
    public Response deleteChargingStation(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id) {
        boolean result = polnilnaPostajaZrno.deleteChargingStation(id);
        if(result) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}