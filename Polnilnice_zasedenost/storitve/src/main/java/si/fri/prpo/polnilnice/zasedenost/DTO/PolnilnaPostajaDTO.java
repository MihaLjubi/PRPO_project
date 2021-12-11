package si.fri.prpo.polnilnice.zasedenost.DTO;

public class PolnilnaPostajaDTO {
    private String lokacija;

    public PolnilnaPostajaDTO() {

    }

    public PolnilnaPostajaDTO(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }
}
