package si.fri.prpo.polnilnice.zasedenost.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import si.fri.prpo.polnilnice.zasedenost.DTO.PolnilnaPostajaDTO;

import javax.annotation.PostConstruct;
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

    private Logger log = Logger.getLogger(ZasedenostVir.class.getName());

    private Map<PolnilnaPostajaDTO, Integer> zasedenost;

    @PostConstruct
    private void init() {
        zasedenost = new HashMap<>();
        zasedenost.put(new PolnilnaPostajaDTO("Ljubljana"), 13);
        zasedenost.put(new PolnilnaPostajaDTO("Maribor"), 6);
        zasedenost.put(new PolnilnaPostajaDTO("Velenje"), 5);
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
        if (!zasedenost.containsKey(polnilnaPostaja)) {
            zasedenost.put(polnilnaPostaja, 1);
        } else {
            zasedenost.put(polnilnaPostaja, zasedenost.get(polnilnaPostaja) + 1);
        }

        log.info(String.valueOf(zasedenost.get(polnilnaPostaja)));
        return Response.ok().build();
    }
}
