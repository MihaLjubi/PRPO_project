import javax.persistence.*;

@Entity(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM uporabnik u")
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

        @OneToMany
        @JoinColumn(name = "id_rezervacija")
        // TODO 'One To Many' attribute type should be a container ERROR
        private Rezervacija rezervacija;

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

        public Rezervacija getRezervacija() {
                return rezervacija;
        }

        public void setRezervacija(Rezervacija rezervacija) {
                this.rezervacija = rezervacija;
        }
}
