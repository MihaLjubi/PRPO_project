package si.fri.prpo.polnilnice.DTO;

import si.fri.prpo.polnilnice.entitete.Rezervacija;

import java.sql.Time;

public class RacunDTO extends BaseDTO {
    private double koncnacena;
    private Rezervacija rezervacija;

    @Override
    public boolean validate() {
        if(koncnacena == 0 || rezervacija == null){
            return false;
        }

        return true;
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
