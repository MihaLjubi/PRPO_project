package si.fri.prpo.polnilnice.DTO;

import si.fri.prpo.polnilnice.DTO.BaseDTO;
import si.fri.prpo.polnilnice.entitete.Rezervacija;
import si.fri.prpo.polnilnice.entitete.Uporabnik;
import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PolnilnaPostajaDTO extends BaseDTO {

    private Integer id_polnilna_postaja;
    private String lokacija;
    private PolnilnaPostaja.Status status;
    private Time obratovanje_zacetek;
    private Time obratovanje_konec;
    private Integer cena;
    @Override
    public boolean validate(){
        //TODO: implement

        return true;
    }

    private List<Rezervacija> comments = new ArrayList<>();

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

    public PolnilnaPostaja.Status getStatus() {
        return status;
    }

    public void setStatus(PolnilnaPostaja.Status status) {
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

    public List<Rezervacija> getComments() {
        return comments;
    }

    public void setComments(List<Rezervacija> comments) {
        this.comments = comments;
    }

}