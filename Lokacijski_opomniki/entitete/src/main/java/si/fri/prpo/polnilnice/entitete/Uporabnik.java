package si.fri.prpo.polnilnice.entitete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM uporabnik u"),
                @NamedQuery(name = "Uporabnik.getByUsername", query = "SELECT u FROM uporabnik u WHERE u.uporabnisko_ime = :username"),
                @NamedQuery(name = "Uporabnik.getById", query = "SELECT u FROM uporabnik u WHERE u.id_uporabnik = :id")
        })
public class Uporabnik {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id_uporabnik;

        private String ime;

        private String priimek;

        private String uporabnisko_ime;

        public Integer getId_uporabnik() {
                return id_uporabnik;
        }

        @OneToMany(mappedBy = "Uporabnik", cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "id_rezervacija")
        private List<Rezervacija> comments = new ArrayList<>();

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

        public List<Rezervacija> getComments() {
                return comments;
        }

        public void setComments(List<Rezervacija> comments) {
                this.comments = comments;
        }
}
