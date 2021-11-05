package si.fri.prpo.polnilnice.zrna;

import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja;
import si.fri.prpo.polnilnice.entitete.Rezervacija;
import si.fri.prpo.polnilnice.entitete.Uporabnik;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.*;

@ApplicationScoped
public class UporabnikZrno {

    @PersistenceContext(unitName = "elektricne-polnilnice-jpa")
    private EntityManager em;

    //namedquery metoda
    public List<Uporabnik> getUporabniki() {

        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getAll", Uporabnik.class);
        List<Uporabnik> results = query.getResultList();

        return results;

    }
    public List<Uporabnik> getByUsername(String username) {

        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getByUsername", Uporabnik.class);
        query.setParameter("username", username);
        List<Uporabnik> results = query.getResultList();

        return results;

    }
    public List<Uporabnik> getById(Integer id) {

        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getById", Uporabnik.class);
        query.setParameter("id", id);
        List<Uporabnik> results = query.getResultList();

        return results;

    }
    //test
    public List<Uporabnik> getUporabnikiCriteria() {
        CriteriaBuilder cbt = em.getCriteriaBuilder();
        CriteriaQuery<Uporabnik> q = cbt.createQuery(Uporabnik.class);
        Root<Uporabnik> root = q.from(Uporabnik.class);
        List<Uporabnik> rez = em.createQuery(q).getResultList();

        return rez;
    }
}
