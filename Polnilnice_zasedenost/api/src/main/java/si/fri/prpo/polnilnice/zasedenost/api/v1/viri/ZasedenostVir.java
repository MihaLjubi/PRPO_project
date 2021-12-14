package si.fri.prpo.polnilnice.zasedenost.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import si.fri.prpo.polnilnice.zasedenost.DTO.PolnilnaPostajaDTO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("zasedenost")
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class ZasedenostVir {

    private Logger logger = Logger.getLogger(ZasedenostVir.class.getName());

    private Map<PolnilnaPostajaDTO, Integer> zasedenost;

    @PostConstruct
    private void init() {
        zasedenost = new HashMap<>();
        zasedenost.put(new PolnilnaPostajaDTO("Ljubljana"), 13);
        zasedenost.put(new PolnilnaPostajaDTO("Maribor"), 6);
        zasedenost.put(new PolnilnaPostajaDTO("Velenje"), 7);

        System.out.println("Izpis");
        for (Map.Entry me : zasedenost.entrySet()) {
            System.out.println("key " + me.getKey() + " value " + me.getValue());
        }
    }

    @PreDestroy
    private void destroy() {
        logger.info("Unicenje " + ZasedenostVir.class.getSimpleName());
    }

    @GET
    public Response pridobiZasedenost() {
        return Response.ok(
                zasedenost.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)).keySet()
                ).build();
    }

    @POST
    public Response dodajVnos(PolnilnaPostajaDTO polnilnaPostaja) {
        System.out.println(polnilnaPostaja);
        System.out.println(polnilnaPostaja.getLokacija());
        if (!zasedenost.containsKey(polnilnaPostaja)) {
            logger.info("dodan nov vnos: " + polnilnaPostaja.getLokacija());
            zasedenost.put(polnilnaPostaja, 1);
        } else {
            logger.info("prej: " + zasedenost.get(polnilnaPostaja));
            zasedenost.put(polnilnaPostaja, zasedenost.get(polnilnaPostaja) + 1);
            logger.info("posodobljeno: " + zasedenost.get(polnilnaPostaja));
        }

        for (Map.Entry me : zasedenost.entrySet()) {
            System.out.println("key " + me.getKey() + " value " + me.getValue());
        }

        return Response.status(Response.Status.OK).build();
    }
}
