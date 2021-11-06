package si.fri.prpo.polnilnice.entitete;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "rezervacija")
@NamedQueries(value =
        {
                @NamedQuery(name = "Rezervacija.getAll", query = "SELECT r FROM rezervacija r"),
                @NamedQuery(name = "Rezervacija.getById", query = "SELECT r FROM rezervacija r WHERE r.id_rezervacija = :id"),
                @NamedQuery(name = "Rezervacija.getByChargingStart", query = "SELECT r FROM rezervacija r WHERE r.polnjenje_zacetek = :chargingStart"),
                @NamedQuery(name = "Rezervacija.getByChargingEnd", query = "SELECT r FROM rezervacija r WHERE r.polnjenje_konec = :chargingEnd")
        })
public class Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rezervacija;

    private Timestamp polnjenje_zacetek;

    private Timestamp polnjenje_konec;


    @ManyToOne
    @JoinColumn(name = "id_polnilna_postaja")
    private PolnilnaPostaja polnilnaPostaja;

    @ManyToOne
    @JoinColumn(name = "id_uporabnik")
    private Uporabnik uporabnik;

    public Integer getId_rezervacija() {
        return id_rezervacija;
    }

    public void setId_rezervacija(Integer id_rezervacija) {
        this.id_rezervacija = id_rezervacija;
    }

    public Timestamp getPolnjenje_zacetek() {
        return polnjenje_zacetek;
    }

    public void setPolnjenje_zacetek(Timestamp polnjenje_zacetek) {
        this.polnjenje_zacetek = polnjenje_zacetek;
    }

    public Timestamp getPolnjenje_konec() {
        return polnjenje_konec;
    }

    public void setPolnjenje_konec(Timestamp polnjenje_konec) {
        this.polnjenje_konec = polnjenje_konec;
    }

    public PolnilnaPostaja getPolnilnaPostaja() {
        return polnilnaPostaja;
    }

    public void setPolnilnaPostaja(PolnilnaPostaja polnilnaPostaja) {
        this.polnilnaPostaja = polnilnaPostaja;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }
}
