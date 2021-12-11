package si.fri.prpo.polnilnice.zasedenost.DTO;

public class PolnilnaPostajaDTO {
    private String lokacija;

    public PolnilnaPostajaDTO() {

    }

    public PolnilnaPostajaDTO(String lokacija) {
        this.lokacija = lokacija;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().isAssignableFrom(PolnilnaPostajaDTO.class))
            return false;

        return this.getLokacija().equals(((PolnilnaPostajaDTO) obj).getLokacija());
    }

    @Override
    public int hashCode() {
        return this.lokacija.hashCode();
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }
}
