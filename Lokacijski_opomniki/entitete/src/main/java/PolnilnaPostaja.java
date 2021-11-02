import javax.persistence.*;
import java.sql.Time;

enum Status {
    ACTIVE,
    DISABLED
}

@Entity(name = "polnilnaPostaja")
@NamedQueries(value =
        {
                @NamedQuery(name = "PolnilnaPostaja.getAll", query = "SELECT pp FROM polnilnaPostaja pp")
        })
public class PolnilnaPostaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_polnilna_postaja;

    private String lokacija;

    private Status status;

    private Time obratovanje_zacetek;

    private Time obratovanje_konec;

    private Integer cena;

    @OneToMany
    @JoinColumn(name = "id_rezervacija")
    // TODO 'One To Many' attribute type should be a container ERROR
    private Rezervacija rezervacija;

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

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }
}
