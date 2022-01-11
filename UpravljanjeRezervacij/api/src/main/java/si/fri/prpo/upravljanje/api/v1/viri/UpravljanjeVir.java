package si.fri.prpo.upravljanje.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import si.fri.prpo.polnilnice.upravljanje.DTO.RezervacijaDTO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.logging.Logger;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("upravljanje")
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class UpravljanjeVir {

    private Logger logger = Logger.getLogger(UpravljanjeVir.class.getName());

    private LinkedList<RezervacijaDTO> rezervacije;

    @PostConstruct
    private void init() {
        rezervacije = new LinkedList<>();
        rezervacije.add(new RezervacijaDTO(1, 1, "10:00", "11:00"));
        rezervacije.add(new RezervacijaDTO(3, 1, "12:00", "15:00"));
        rezervacije.add(new RezervacijaDTO(2, 1, "09:00", "11:00"));
        rezervacije.add(new RezervacijaDTO(1, 2, "08:00", "10:00"));
        rezervacije.add(new RezervacijaDTO(1, 3, "14:00", "16:00"));
        rezervacije.add(new RezervacijaDTO(2, 3, "10:30", "11:30"));
    }

    @GET
    @Path("{id}")
    public Response pridobiRezervacije(@PathParam("id") Integer userId){
        rezervacije.removeIf(rez -> !Objects.equals(rez.getUporabnik(), userId));
        Collections.sort(rezervacije);

        logger.info("pridobljene rezervacije za upobrabnika z id=" + userId);

        return Response.ok().entity(rezervacije).build();
    }
}
