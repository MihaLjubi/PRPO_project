package si.fri.prpo.polnilnice.zrna;

import org.jboss.logging.Logger;
import si.fri.prpo.polnilnice.entitete.Racun;
import si.fri.prpo.polnilnice.entitete.Rezervacija;
import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja;
import si.fri.prpo.polnilnice.entitete.Rezervacija;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RacunZrno {
    private Logger logger = Logger.getLogger(RacunZrno.class.getName());

    @PostConstruct
    private void init() {
        logger.info("Inicializacija zrna " + Racun.class.getName());
        logger.info("Zrno id: " + UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        logger.info("Uničenje zrna " + RacunZrno.class.getSimpleName());
    }

    @PersistenceContext(unitName = "elektricne-polnilnice-jpa")
    private EntityManager em;

    // CRUD operations
    // CREATE
    @Transactional
    public void createRacun(Racun racun) {
        if(racun != null) {
            em.persist(racun);
        }
    }

    // READ
    public List<Racun> getRacuni() {
        TypedQuery<Racun> query = em.createNamedQuery("Racun.getAll", Racun.class);
        List<Racun> results = query.getResultList();

        return results;
    }

    public Racun getById(Integer id) {
        TypedQuery<Racun> query = em.createNamedQuery("Racun.getById", Racun.class);
        query.setParameter("id", id);
        Racun results = query.getSingleResult();

        return results;
    }

    // UPDATE
    @Transactional
    public void updateReservation(int id_racun, Racun racun) {
        Racun r = em.find(Racun.class, id_racun);
        racun.setId_racun(r.getId_racun());
        racun.setKoncnacena(r.getKoncnacena());
        racun.setLokacija(r.getLokacija());
        racun.setObratovanje_konec(r.getObratovanje_konec());
        racun.setObratovanje_zacetek(r.getObratovanje_zacetek());
        em.merge(racun);
    }

    // DELETE
    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteReservation(int id_racun) {
        Racun racun = em.find(Racun.class, id_racun);
        if (racun != null) {
            em.remove(racun);
        }
    }
}
