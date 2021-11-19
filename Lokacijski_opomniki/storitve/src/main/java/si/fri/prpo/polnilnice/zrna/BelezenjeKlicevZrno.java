package si.fri.prpo.polnilnice.zrna;

import si.fri.prpo.polnilnice.entitete.Rezervacija;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.interceptor.InvocationContext;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@ApplicationScoped
public class BelezenjeKlicevZrno {

    private Logger logger = Logger.getLogger(BelezenjeKlicevZrno.class.getName());

    @PostConstruct
    private void init() {
        logger.info("Inicializacija zrna " + BelezenjeKlicevZrno.class.getName());
    }

    @PreDestroy
    private void destroy() {
        logger.info("Uničenje zrna " + BelezenjeKlicevZrno.class.getSimpleName());
    }

    Map<String, Integer> counters = new HashMap<String, Integer>();

    public void inc(InvocationContext ctx) {
        String klicatelj = ctx.getMethod().getDeclaringClass().getName() + "." + ctx.getMethod().getName();
        boolean obstaja = counters.containsKey(klicatelj);

        if(!obstaja){
            counters.put(klicatelj, 1);
        }
        else{
            counters.put(klicatelj, counters.get(klicatelj) + 1);
        }
        logger.info("Method: " + klicatelj);
        logger.info("Number of calls: " + counters.get(klicatelj));
    }
}
