package si.fri.prpo.polnilnice.entitete;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
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
    private double koncnacena;

    @JsonbTransient
    @OneToOne
    @JoinColumn(name = "id_rezervacija")
    private Rezervacija rezervacija;

    public Integer getId_racun() {
        return id_racun;
    }

    public void setId_racun(Integer id_racun) {
        this.id_racun = id_racun;
    }

    public double getKoncnacena() {
        return koncnacena;
    }

    public void setKoncnacena(double koncnacena) {
        this.koncnacena = koncnacena;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }
}
