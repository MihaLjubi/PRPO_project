package si.fri.prpo.polnilnice.entitete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;

@Entity(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM uporabnik u"),
                @NamedQuery(name = "Uporabnik.getById", query = "SELECT u FROM uporabnik u WHERE u.id_uporabnik = :id"),
                @NamedQuery(name = "Uporabnik.getByUsername", query = "SELECT u FROM uporabnik u WHERE u.uporabnisko_ime = :username"),
                @NamedQuery(name = "Uporabnik.getBySurname", query = "SELECT u FROM uporabnik u WHERE u.priimek = :surname"),
        })
public class Uporabnik {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id_uporabnik;

        private String ime;

        private String priimek;

        private String uporabnisko_ime;

        @JsonbTransient
        @OneToMany(mappedBy = "uporabnik", cascade = CascadeType.ALL)
        private List<Rezervacija> rezervacije;

        public Integer getId_uporabnik() {
                return id_uporabnik;
        }

        public void setId_uporabnik(Integer id_uporabnik) {
                this.id_uporabnik = id_uporabnik;
        }

        public String getIme() {
                return ime;
        }

        public void setIme(String ime) {
                this.ime = ime;
        }

        public String getPriimek() {
                return priimek;
        }

        public void setPriimek(String priimek) {
                this.priimek = priimek;
        }

        public String getUporabnisko_ime() {
                return uporabnisko_ime;
        }

        public void setUporabnisko_ime(String uporabnisko_ime) {
                this.uporabnisko_ime = uporabnisko_ime;
        }

        public List<Rezervacija> getRezervacije() {
                return rezervacije;
        }

        public void setRezervacije(List<Rezervacija> rezervacije) {
                this.rezervacije = rezervacije;
        }
}
