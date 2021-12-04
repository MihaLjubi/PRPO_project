package si.fri.prpo.polnilnice.zrna;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.jboss.logging.Logger;
import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja;
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
public class PolnilnaPostajaZrno {

    private Logger logger = Logger.getLogger(PolnilnaPostajaZrno.class.getName());

    @PostConstruct
    private void init() {
        logger.info("Inicializacija zrna " + PolnilnaPostaja.class.getName());
        logger.info("Zrno id: " + UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        logger.info("Uničenje zrna " + PolnilnaPostajaZrno.class.getSimpleName());
    }

    @PersistenceContext(unitName = "elektricne-polnilnice-jpa")
    private EntityManager em;

    // CRUD operations
    // CREATE
    @BeleziKlice
    @Transactional
    public void createChargingStation(PolnilnaPostaja chargingStation) {
        if(chargingStation != null) {
            em.persist(chargingStation);
        }
    }

    // READ
    @BeleziKlice
    public List<PolnilnaPostaja> getPolnilnePostaje(QueryParameters query) {
       List<PolnilnaPostaja> results = JPAUtils.queryEntities(em, PolnilnaPostaja.class, query);

        return results;
    }

    public Long getPolnilnePostajeCount(QueryParameters query) {
        return JPAUtils.queryEntitiesCount(em, Uporabnik.class, query);
    }

    @BeleziKlice
    public PolnilnaPostaja getById(Integer id) {
        TypedQuery<PolnilnaPostaja> query = em.createNamedQuery("PolnilnaPostaja.getById", PolnilnaPostaja.class);
        query.setParameter("id", id);
        PolnilnaPostaja results = query.getSingleResult();

        return results;
    }

    // UPDATE
    @BeleziKlice
    @Transactional
    public PolnilnaPostaja updateChargingStation(int id_polnilna_postaja, PolnilnaPostaja chargingStation) {
        PolnilnaPostaja pp = em.find(PolnilnaPostaja.class, id_polnilna_postaja);
        chargingStation.setId_polnilna_postaja(pp.getId_polnilna_postaja());
        em.merge(chargingStation);
        return pp;
    }

    // DELETE
    @BeleziKlice
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean deleteChargingStation(int id_polnilna_postaja) {
        PolnilnaPostaja chargingStation = em.find(PolnilnaPostaja.class, id_polnilna_postaja);
        if (chargingStation != null) {
            em.remove(chargingStation);
            return true;
        }
        return false;
    }
}
