package si.fri.prpo.polnilnice.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
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
    public Response getAllReceipts() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Racun> racuni = racunZrno.getRacuni(query);
        return Response.status(Response.Status.OK).entity(racuni).build();
    }

    @GET
    @Path("{id}")
    public Response getReceipt(@PathParam("id") Integer id) {
        Racun racun = racunZrno.getById(id);
        if(racun != null) {
            return Response.status(Response.Status.OK).entity(racun).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response addRacun(RacunDTO racunDTO) {
        Racun r = upravljanjePolnilnihPostajZrno.izdajRacun(racunDTO);
        if(r != null) {
            return Response.status(Response.Status.OK).entity(r).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response updateRacun(@PathParam("id") Integer id, Racun racun) {
        Racun r = racunZrno.updateRacun(id, racun);
        if(r != null) {
            return Response.status(Response.Status.OK).entity(r).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteRacun(@PathParam("id") Integer id) {
        var result = racunZrno.deleteRacun(id);
        if(result) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}