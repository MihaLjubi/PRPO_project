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
import si.fri.prpo.polnilnice.DTO.PolnilnaPostajaDTO;
import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja;
import si.fri.prpo.polnilnice.entitete.Uporabnik;
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

    @Operation(description = "Returns list of charging stations", summary="Charging station list")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(
                            schema = @Schema(implementation = PolnilnaPostaja.class)
                    ),
                    headers = { @Header(name = "X-Total-Count", description = "Number of charging stations") }
            ),
            @APIResponse(responseCode = "404", description = "Charging stations not found")
    })
    @GET
    public Response getAllChargingStations() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<PolnilnaPostaja> polnilnepostaje = polnilnaPostajaZrno.getPolnilnePostaje(query);
        Long polnilnePostajeCount = polnilnaPostajaZrno.getPolnilnePostajeCount(query);
        return Response.status(Response.Status.OK).entity(polnilnepostaje).header("X-Total-Count", polnilnePostajeCount).build();
    }

    @Operation(description = "Returns charging station", summary="Charging station details")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(
                            schema = @Schema(implementation = PolnilnaPostaja.class)
                    )
            ),
            @APIResponse(responseCode = "404", description = "Charging station not found")
    })
    @GET
    @Path("{id}")
    public Response getChargingStation(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id) {
        PolnilnaPostaja pp = polnilnaPostajaZrno.getById(id);
        if(pp != null) {
            return Response.status(Response.Status.OK).entity(pp).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Create charging station", summary="Charging station creation")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Charging station added",
                    content = @Content(
                            schema = @Schema(implementation = PolnilnaPostaja.class)
                    )
            ),
            @APIResponse(responseCode = "405", description = "Authentication error")
    })
    @POST
    public Response addChargingStation(@Parameter(name="id", required = true, allowEmptyValue = false) PolnilnaPostajaDTO polnilnaPostajaDTO) {
        PolnilnaPostaja pp = upravljanjePolnilnihPostajZrno.ustvariPolnilnoPostajo(polnilnaPostajaDTO);
        if(pp != null) {
            return Response.status(Response.Status.OK).entity(pp).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Updates charging station", summary="Charging station update")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Charging station updated"
            ),
            @APIResponse(responseCode = "404", description = "Charging station not found")
    })
    @PUT
    @Path("{id}")
    public Response updateChargingStation(@PathParam("id") Integer id, PolnilnaPostaja polnilnaPostaja) {
        PolnilnaPostaja pp = polnilnaPostajaZrno.updateChargingStation(id, polnilnaPostaja);
        if(pp != null) {
            return Response.status(Response.Status.OK).entity(pp).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Deletes charging station", summary="Charging station deletion")
    @APIResponses({
            @APIResponse(responseCode = "204",
                    description = "Charging station deleted"
            ),
            @APIResponse(responseCode = "404", description = "Charging station not found")
    })
    @DELETE
    @Path("{id}")
    public Response deleteChargingStation(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id) {
        boolean result = polnilnaPostajaZrno.deleteChargingStation(id);
        if(result) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}