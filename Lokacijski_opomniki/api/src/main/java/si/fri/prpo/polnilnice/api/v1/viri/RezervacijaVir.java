package si.fri.prpo.polnilnice.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.jboss.logging.Logger;
import si.fri.prpo.polnilnice.DTO.RezervacijaDTO;
import si.fri.prpo.polnilnice.entitete.Rezervacija;
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

    @GET
    public Response getAllReservations() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Rezervacija> rezervacije = rezervacijaZrno.getRezervacije(query);
        return Response.status(Response.Status.OK).entity(rezervacije).build();
    }

    @GET
    @Path("{id}")
    public Response getReservation(@PathParam("id") Integer id) {
        Rezervacija reservation = rezervacijaZrno.getById(id);
        if(reservation != null) {
            return Response.status(Response.Status.OK).entity(reservation).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response addReservation(RezervacijaDTO rezervacijaDTO) {
        Rezervacija r = upravljanjePolnilnihPostajZrno.rezervacijaPolnilnePostaje(rezervacijaDTO);
        if(r != null) {
            return Response.status(Response.Status.OK).entity(r).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response updateReservation(@PathParam("id") Integer id, Rezervacija reservation) {
        Rezervacija r = rezervacijaZrno.updateReservation(id, reservation);
        if(r != null) {
            return Response.status(Response.Status.OK).entity(r).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteReservation(@PathParam("id") Integer id) {
        var result = rezervacijaZrno.deleteReservation(id);
        if(result) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
