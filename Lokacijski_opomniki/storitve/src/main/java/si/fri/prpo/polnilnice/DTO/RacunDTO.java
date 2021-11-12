package si.fri.prpo.polnilnice.DTO;

import si.fri.prpo.polnilnice.entitete.Rezervacija;

import java.sql.Time;

public class RacunDTO {
    private String lokacija;
    private Time obratovanje_zacetek;
    private Time obratovanje_konec;
    private Integer koncnacena;
    private Rezervacija rezervacija;

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

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }
}
