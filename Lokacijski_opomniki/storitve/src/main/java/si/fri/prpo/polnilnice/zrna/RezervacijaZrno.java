package si.fri.prpo.polnilnice.zrna;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.jboss.logging.Logger;
import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja;
import si.fri.prpo.polnilnice.entitete.Rezervacija;
import si.fri.prpo.polnilnice.entitete.Uporabnik;
import si.fri.prpo.polnilnice.interceptor.BeleziKlice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class RezervacijaZrno {

    private Logger logger = Logger.getLogger(RezervacijaZrno.class.getName());

    @PostConstruct
    private void init() {
        logger.info("Inicializacija zrna " + Rezervacija.class.getName());
        logger.info("Zrno id: " + UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        logger.info("Uničenje zrna " + RezervacijaZrno.class.getSimpleName());
    }

    @PersistenceContext(unitName = "elektricne-polnilnice-jpa")
    private EntityManager em;

    // CRUD operations
    // CREATE
    @BeleziKlice
    @Transactional
    public Rezervacija createReservation(Rezervacija reservation) {
        if(reservation != null) {
            em.persist(reservation);
        }
        return reservation;
    }

    // READ
    @BeleziKlice
    public List<Rezervacija> getRezervacije(QueryParameters query) {
       List<Rezervacija> results = JPAUtils.queryEntities(em, Rezervacija.class, query);

        return results;
    }

    public Long getRezervacijeCount(QueryParameters query) {
        return JPAUtils.queryEntitiesCount(em, Uporabnik.class, query);
    }

    @BeleziKlice
    public Rezervacija getById(Integer id) {
        TypedQuery<Rezervacija> query = em.createNamedQuery("Rezervacija.getById", Rezervacija.class);
        query.setParameter("id", id);
        Rezervacija results = query.getSingleResult();

        return results;
    }

    // UPDATE
    @BeleziKlice
    @Transactional
    public Rezervacija updateReservation(int id_rezervacija, Rezervacija reservation) {
        Rezervacija r = em.find(Rezervacija.class, id_rezervacija);
        reservation.setId_rezervacija(r.getId_rezervacija());
        em.merge(reservation);
        return reservation;
    }

    // DELETE
    @BeleziKlice
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean deleteReservation(int id_rezervacija) {
        Rezervacija reservation = em.find(Rezervacija.class, id_rezervacija);
        if (reservation != null) {
            em.remove(reservation);
            return true;
        }
        return false;
    }
}
