package si.fri.prpo.polnilnice.zrna;

import org.jboss.logging.Logger;
import si.fri.prpo.polnilnice.entitete.Uporabnik;

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
    @Transactional
    public void createUser(Uporabnik user) {
        if(user != null) {
            em.persist(user);
        }
    }

    // READ
    public List<Uporabnik> getUporabniki() {
        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getAll", Uporabnik.class);
        List<Uporabnik> results = query.getResultList();

        return results;
    }

    public Uporabnik getById(Integer id) {
        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getById", Uporabnik.class);
        query.setParameter("id", id);
        Uporabnik results = query.getSingleResult();

        return results;
    }

    // UPDATE
    @Transactional
    public void updateUser(int uporabnik_id, Uporabnik user) {
        Uporabnik u = em.find(Uporabnik.class, uporabnik_id);
        user.setId_uporabnik(u.getId_uporabnik());
        em.merge(user);
    }

    // DELETE
    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteUser(int uporabnik_id) {
        Uporabnik user = em.find(Uporabnik.class, uporabnik_id);
        if (user != null) {
            em.remove(user);
        }
    }

    //namedquery metoda
    public List<Uporabnik> getByUsername(String username) {
        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getByUsername", Uporabnik.class);
        query.setParameter("username", username);
        List<Uporabnik> results = query.getResultList();

        return results;
    }

    public List<Uporabnik> getBySurname(String surname) {
        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getBySurname", Uporabnik.class);
        query.setParameter("surname", surname);
        List<Uporabnik> results = query.getResultList();

        return results;
    }

    public List<Uporabnik> getUporabnikiCriteria() {
        CriteriaBuilder cbt = em.getCriteriaBuilder();
        CriteriaQuery<Uporabnik> q = cbt.createQuery(Uporabnik.class);
        Root<Uporabnik> root = q.from(Uporabnik.class);
        List<Uporabnik> rez = em.createQuery(q).getResultList();

        return rez;
    }

}






