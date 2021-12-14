package si.fri.prpo.polnilnice.DTO;

public class PolnilnicaDTO {
    private String lokacija;

    public PolnilnicaDTO() {

    }

    public PolnilnicaDTO(String lokacija) {
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
