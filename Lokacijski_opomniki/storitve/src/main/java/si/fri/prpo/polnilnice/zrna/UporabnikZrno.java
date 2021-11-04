package si.fri.prpo.polnilnice.zrna;

import si.fri.prpo.polnilnice.entitete.Uporabnik;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@ApplicationScoped
public class UporabnikZrno {

    @PersistenceContext(unitName = "elektricne-polnilnice-jpa")
    private EntityManager em;

    public List<Uporabnik> getUporabniki() {

        // implementacija

        return null;

    }

}
