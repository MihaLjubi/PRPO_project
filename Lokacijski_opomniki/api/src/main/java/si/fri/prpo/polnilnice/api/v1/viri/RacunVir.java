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
import si.fri.prpo.polnilnice.DTO.RacunDTO;
import si.fri.prpo.polnilnice.entitete.Racun;
import si.fri.prpo.polnilnice.entitete.Uporabnik;
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

    @Operation(description = "Returns list of receipts", summary="Receipts list")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(
                            schema = @Schema(implementation = Racun.class)
                    ),
                    headers = { @Header(name = "X-Total-Count", description = "Number of receipts") }
            ),
            @APIResponse(responseCode = "404", description = "Receipts not found")
    })
    @GET
    public Response getAllReceipts() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Racun> racuni = racunZrno.getRacuni(query);
        long racuniCount = racunZrno.getRacuniCount(query);
        return Response.status(Response.Status.OK).entity(racuni).header("X-Total-Count", racuniCount).build();
    }

    @Operation(description = "Returns receipt", summary="Receipt details")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(
                            schema = @Schema(implementation = Racun.class)
                    )
            ),
            @APIResponse(responseCode = "404", description = "Receipt not found")
    })
    @GET
    @Path("{id}")
    public Response getReceipt(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id) {
        Racun racun = racunZrno.getById(id);
        if(racun != null) {
            return Response.status(Response.Status.OK).entity(racun).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Create receipt", summary="Receipt creation")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Receipt added",
                    content = @Content(
                            schema = @Schema(implementation = Racun.class)
                    )
            ),
            @APIResponse(responseCode = "405", description = "Authentication error")
    })
    @POST
    public Response addRacun(@Parameter(name="id", required = true, allowEmptyValue = false) RacunDTO racunDTO) {
        Racun r = upravljanjePolnilnihPostajZrno.izdajRacun(racunDTO);
        if(r != null) {
            return Response.status(Response.Status.OK).entity(r).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Updates receipt", summary="Receipt update")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Receipt updated"
            ),
            @APIResponse(responseCode = "404", description = "Receipt not found")
    })
    @PUT
    @Path("{id}")
    public Response updateRacun(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id, Racun racun) {
        Racun r = racunZrno.updateRacun(id, racun);
        if(r != null) {
            return Response.status(Response.Status.OK).entity(r).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Deletes receipt", summary="Receipt delition")
    @APIResponses({
            @APIResponse(responseCode = "204",
                    description = "Receipt deleted"
            ),
            @APIResponse(responseCode = "404", description = "Receipt not found")
    })
    @DELETE
    @Path("{id}")
    public Response deleteRacun(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id) {
        var result = racunZrno.deleteRacun(id);
        if(result) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}