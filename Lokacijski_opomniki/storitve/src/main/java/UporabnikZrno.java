import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import javax.persistence.criteria.*;

@ApplicationScoped
public class UporabnikZrno {

    @PersistenceContext(unitName = "elektricne-polnilnice-jpa")
    private EntityManager em;

    //named query metoda
    public List<Uporabnik> getUporabniki() {

        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getAll", Uporabnik.class);
        List<Uporabnik> results = query.getResultList();

        return results;
    }

    //jpa criteria api metode
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Uporabnik> q = cb.createQuery(Uporabnik.class);
    Root<Uporabnik> u = q.from(Uporabnik.class);
    q.select(u);

    TypedQuery<Uporabnik> query = em.createQuery(q);
    List<Uporabnik> vsiUporabniki = query.getResultList();

    //izpisi rezervacije
    CriteriaBuilder cb1 = em.getCriteriaBuilder();
    CriteriaQuery<Rezervacija> q1 = cb1.createQuery(Rezervacija.class);
    Root<Rezervacija> r = q1.from(Rezervacija.class);
    q.select(r);

    TypedQuery<Rezervacija> query1 = em.createQuery(q1);
    List<Rezervacija> vseRezervacije = query1.getResultList();

    //izpisi polnilne postaje
    CriteriaBuilder cb2 = em.getCriteriaBuilder();
    CriteriaQuery<PolnilnaPostaja> q2 = cb2.createQuery(PolnilnaPostaja.class);
    Root<PolnilnaPostaja> p = q2.from(PolnilnaPostaja.class);
    q.select(p);

    TypedQuery<PolnilnaPostaja> query2 = em.createQuery(q2);
    List<PolnilnaPostaja> vsePostaje = query2.getResultList();

    //zdruzi sezname in vrni - verjetno kot tabelo al neki
    public List<List> izpisiVse() {

        // implementacija

        return null;
    }
}
