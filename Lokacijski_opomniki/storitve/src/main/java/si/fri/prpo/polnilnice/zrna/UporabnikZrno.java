package si.fri.prpo.polnilnice.zrna;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.jboss.logging.Logger;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@ApplicationScoped
public class UporabnikZrno {

    private Logger logger = Logger.getLogger(UporabnikZrno.class.getName());

    @PostConstruct
    private void init() {
        logger.info("Inicializacija zrna " + UporabnikZrno.class.getName());
        logger.info("Zrno id: " + UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        logger.info("Uničenje zrna " + UporabnikZrno.class.getSimpleName());
    }

    @PersistenceContext(unitName = "elektricne-polnilnice-jpa")
    private EntityManager em;

    // CRUD operations
    // CREATE
    @BeleziKlice
    @Transactional
    public Uporabnik createUser(Uporabnik user) {
        if(user != null) {
            em.persist(user);
        }
        return user;
    }

    // READ
    @BeleziKlice
    public List<Uporabnik> getUporabniki(QueryParameters query) {
       List<Uporabnik> results = JPAUtils.queryEntities(em, Uporabnik.class, query);

        return results;
    }

    public Long getUporabnikiCount(QueryParameters query) {
        return JPAUtils.queryEntitiesCount(em, Uporabnik.class, query);
    }

    @BeleziKlice
    public Uporabnik getById(Integer id) {
        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getById", Uporabnik.class);
        query.setParameter("id", id);
        Uporabnik results = query.getSingleResult();

        return results;
    }

    // UPDATE
    @BeleziKlice
    @Transactional
    public Uporabnik updateUser(int uporabnik_id, Uporabnik user) {
        Uporabnik u = em.find(Uporabnik.class, uporabnik_id);
        user.setId_uporabnik(u.getId_uporabnik());
        em.merge(user);
        return user;
    }

    // DELETE
    @BeleziKlice
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean deleteUser(int uporabnik_id) {
        Uporabnik user = em.find(Uporabnik.class, uporabnik_id);
        if (user != null) {
            em.remove(user);
            return true;
        }
        return false;
    }

    //namedquery metoda
    public Uporabnik getByUsername(String username) {
        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getByUsername", Uporabnik.class);
        query.setParameter("username", username);
        Uporabnik result = (Uporabnik) query.getResultList();

        return result;
    }

    public Uporabnik getBySurname(String surname) {
        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getBySurname", Uporabnik.class);
        query.setParameter("surname", surname);
        Uporabnik result = (Uporabnik) query.getResultList();

        return result;
    }

    public List<Uporabnik> getUporabnikiCriteria() {
        CriteriaBuilder cbt = em.getCriteriaBuilder();
        CriteriaQuery<Uporabnik> q = cbt.createQuery(Uporabnik.class);
        Root<Uporabnik> root = q.from(Uporabnik.class);
        List<Uporabnik> rez = em.createQuery(q).getResultList();

        return rez;
    }

}






