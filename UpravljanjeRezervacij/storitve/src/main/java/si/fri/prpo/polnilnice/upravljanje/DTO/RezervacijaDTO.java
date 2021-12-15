package si.fri.prpo.polnilnice.upravljanje.DTO;

public class RezervacijaDTO implements Comparable<RezervacijaDTO>{
    private Integer uporabnik;
    private Integer polnilnaPostaja;
    private String zacetek;
    private String konec;

    @Override
    public int compareTo(RezervacijaDTO rez){
        if(getZacetek() == null || rez.getZacetek() == null){
            return 0;
        }
        return getZacetek().compareTo(rez.getZacetek());
    }
    public RezervacijaDTO(Integer uporabnik, Integer polnilnaPostaja, String zacetek, String konec){
        this.uporabnik = uporabnik;
        this.polnilnaPostaja = polnilnaPostaja;
        this.zacetek = zacetek;
        this.konec = konec;
    }

    public Integer getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Integer uporabnik) {
        this.uporabnik = uporabnik;
    }

    public Integer getPolnilnaPostaja() {
        return polnilnaPostaja;
    }

    public void setPolnilnaPostaja(Integer polnilnaPostaja) {
        this.polnilnaPostaja = polnilnaPostaja;
    }

    public String getZacetek() {
        return zacetek;
    }

    public void setZacetek(String zacetek) {
        this.zacetek = zacetek;
    }

    public String getKonec() {
        return konec;
    }

    public void setKonec(String konec) {
        this.konec = konec;
    }
}
