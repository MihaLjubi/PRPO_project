package si.fri.prpo.polnilnice.entitete;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "racun")
@NamedQueries(value =
        {
                @NamedQuery(name = "Racun.getAll", query = "SELECT r FROM racun r"),
                @NamedQuery(name = "Racun.getById", query = "SELECT r FROM racun r WHERE r.id_racun = :id"),
        })
public class Racun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_racun;
    private String lokacija;
    private Time obratovanje_zacetek;
    private Time obratovanje_konec;
    private Integer koncnacena;

    @OneToOne
    @JoinColumn(name = "id_rezervacija")
    private Rezervacija rezervacija;

    public Integer getId_racun() {
        return id_racun;
    }

    public void setId_racun(Integer id_racun) {
        this.id_racun = id_racun;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
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

    public Integer getKoncnacena() {
        return koncnacena;
    }

    public void setKoncnacena(Integer koncnacena) {
        this.koncnacena = koncnacena;
    }
}
