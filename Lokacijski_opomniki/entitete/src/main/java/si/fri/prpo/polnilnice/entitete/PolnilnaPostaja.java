package si.fri.prpo.polnilnice.entitete;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "polnilnaPostaja")
@NamedQueries(value =
        {
                @NamedQuery(name = "PolnilnaPostaja.getAll", query = "SELECT pp FROM polnilnaPostaja pp"),
                @NamedQuery(name = "PolnilnaPostaja.getById", query = "SELECT pp FROM polnilnaPostaja pp WHERE pp.id_polnilna_postaja = :id"),
                @NamedQuery(name = "PolnilnaPostaja.getByLocation", query = "SELECT pp FROM polnilnaPostaja pp WHERE pp.lokacija = :location"),
                @NamedQuery(name = "PolnilnaPostaja.getByStatus", query = "SELECT pp FROM polnilnaPostaja pp WHERE pp.status = :status")
        })
public class PolnilnaPostaja {

    public enum Status {
        ACTIVE,
        DISABLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_polnilna_postaja;

    private String lokacija;

    private Status status;

    private Time obratovanje_zacetek;

    private Time obratovanje_konec;

    private Integer cena;

    @JsonbTransient
    @OneToMany(mappedBy = "polnilnaPostaja", cascade = CascadeType.ALL)
    private List<Rezervacija>  rezervacije;

    public Integer getId_polnilna_postaja() {
        return id_polnilna_postaja;
    }

    public void setId_polnilna_postaja(Integer id_polnilna_postaja) {
        this.id_polnilna_postaja = id_polnilna_postaja;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Time getObratovanje_zacetek() {
        return obratovanje_zacetek;
    }

    public void setObratovanje_zacetek(Time obratovanje_zacetek) {
        this.obratovanje_zacetek = obratovanje_zacetek;
    }

    public Time getObratovanje_konec() {
        return obratovanje_konec;
    }

    public void setObratovanje_konec(Time obratovanje_konec) {
        this.obratovanje_konec = obratovanje_konec;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }
}
