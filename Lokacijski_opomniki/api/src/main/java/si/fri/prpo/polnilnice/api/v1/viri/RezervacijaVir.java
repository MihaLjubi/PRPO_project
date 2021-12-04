package si.fri.prpo.polnilnice.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.logging.Logger;
import si.fri.prpo.polnilnice.DTO.RezervacijaDTO;
import si.fri.prpo.polnilnice.entitete.Rezervacija;
import si.fri.prpo.polnilnice.entitete.Uporabnik;
import si.fri.prpo.polnilnice.zrna.RezervacijaZrno;
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
@Path("rezervacije")
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class RezervacijaVir {

    private Logger logger = Logger.getLogger(RezervacijaVir.class.getName());
    @Context
    protected UriInfo uriInfo;

    @Inject
    private RezervacijaZrno rezervacijaZrno;

    @Inject
    private UpravljanjePolnilnihPostajZrno upravljanjePolnilnihPostajZrno;

    @Operation(description = "Returns list of reservations", summary="Reservation list")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(
                            schema = @Schema(implementation = Rezervacija.class)
                    ),
                    headers = { @Header(name = "X-Total-Count", description = "Number of reservations") }
            ),
            @APIResponse(responseCode = "404", description = "Reservations not found")
    })
    @GET
    public Response getAllReservations() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Rezervacija> rezervacije = rezervacijaZrno.getRezervacije(query);
        Long rezervacijeCount = rezervacijaZrno.getRezervacijeCount(query);
        return Response.status(Response.Status.OK).entity(rezervacije).header("X-Total-Count", rezervacijeCount).build();
    }

    @Operation(description = "Returns reservation", summary="Reservation details")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(
                            schema = @Schema(implementation = Rezervacija.class)
                    )
            ),
            @APIResponse(responseCode = "404", description = "Reservation not found")
    })
    @GET
    @Path("{id}")
    public Response getReservation(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id) {
        Rezervacija reservation = rezervacijaZrno.getById(id);
        if(reservation != null) {
            return Response.status(Response.Status.OK).entity(reservation).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Create reservation", summary="Reservation creation")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Reservation added",
                    content = @Content(
                            schema = @Schema(implementation = Rezervacija.class)
                    )
            ),
            @APIResponse(responseCode = "405", description = "Validation error")
    })
    @POST
    public Response addReservation(@Parameter(name="id", required = true, allowEmptyValue = false) RezervacijaDTO rezervacijaDTO) {
        Rezervacija r = upravljanjePolnilnihPostajZrno.rezervacijaPolnilnePostaje(rezervacijaDTO);
        if(r != null) {
            return Response.status(Response.Status.OK).entity(r).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Updates reservation", summary="Reservation update")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Reservation updated"
            ),
            @APIResponse(responseCode = "404", description = "Reservation not found")
    })
    @PUT
    @Path("{id}")
    public Response updateReservation(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id, Rezervacija reservation) {
        Rezervacija r = rezervacijaZrno.updateReservation(id, reservation);
        if(r != null) {
            return Response.status(Response.Status.OK).entity(r).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(description = "Deletes reservation", summary="Reservation deletion")
    @APIResponses({
            @APIResponse(responseCode = "204",
                    description = "Reservation deleted"
            ),
            @APIResponse(responseCode = "404", description = "Reservation not found")
    })
    @DELETE
    @Path("{id}")
    public Response deleteReservation(@Parameter(name="id", required = true, allowEmptyValue = false) @PathParam("id") Integer id) {
        var result = rezervacijaZrno.deleteReservation(id);
        if(result) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
