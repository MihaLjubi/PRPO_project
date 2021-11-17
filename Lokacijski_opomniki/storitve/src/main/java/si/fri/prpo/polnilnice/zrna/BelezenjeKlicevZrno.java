package si.fri.prpo.polnilnice.zrna;

import si.fri.prpo.polnilnice.entitete.Rezervacija;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
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

    int count = 0;

    public void inc() {
        count++;
        logger.info("Number of calls: " + count);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
