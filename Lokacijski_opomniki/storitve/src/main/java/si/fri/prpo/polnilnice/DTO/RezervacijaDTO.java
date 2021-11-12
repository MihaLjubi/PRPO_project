package si.fri.prpo.polnilnice.DTO;

import si.fri.prpo.polnilnice.entitete.Uporabnik;
import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja;

import java.sql.Timestamp;

public class RezervacijaDTO extends BaseDTO{
    private Uporabnik uporabnik;
    private PolnilnaPostaja polnilnaPostaja;
    private Timestamp polnjenjeZacetek;
    private Timestamp polnjenjeKonec;
    private Integer cena;

    @Override
    public boolean validate(){
        //TODO: implement

        return true;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }

    public PolnilnaPostaja getPolnilnaPostaja() {
        return polnilnaPostaja;
    }

    public void setPolnilnaPostaja(PolnilnaPostaja polnilnaPostaja) {
        this.polnilnaPostaja = polnilnaPostaja;
    }

    public Timestamp getPolnjenjeZacetek() {
        return polnjenjeZacetek;
    }

    public void setPolnjenjeZacetek(Timestamp polnjenjeZacetek) {
        this.polnjenjeZacetek = polnjenjeZacetek;
    }

    public Timestamp getPolnjenjeKonec() {
        return polnjenjeKonec;
    }

    public void setPolnjenjeKonec(Timestamp polnjenjeKonec) {
        this.polnjenjeKonec = polnjenjeKonec;
    }

    public Integer getCena() { return cena; }

    public void setCena(Integer cena) { this.cena = cena; }

}
