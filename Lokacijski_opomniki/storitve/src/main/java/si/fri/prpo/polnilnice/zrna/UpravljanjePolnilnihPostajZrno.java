package si.fri.prpo.polnilnice.zrna;

import si.fri.prpo.polnilnice.DTO.RezervacijaDTO;
import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja;
import si.fri.prpo.polnilnice.entitete.Rezervacija;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjePolnilnihPostajZrno {
    @Inject
    private UporabnikZrno uporabnikZrno;
    @Inject
    private PolnilnaPostajaZrno polnilnaPostajaZrno;
    @Inject
    private RezervacijaZrno rezervacijaZrno;

    private Logger logger = Logger.getLogger(UpravljanjePolnilnihPostajZrno.class.getName());

    @PostConstruct
    private void init() {
        logger.info("Inicializacija zrna " + UpravljanjePolnilnihPostajZrno.class.getName());
        logger.info("Zrno id: " + UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        logger.info("Uničenje zrna " + UpravljanjePolnilnihPostajZrno.class.getSimpleName());
    }

    public void rezervacijaPolnilnePostaje(RezervacijaDTO rezervacijaDTO) {
        if (!rezervacijaDTO.validate())
            return;

        Rezervacija reservation = new Rezervacija();
        reservation.setUporabnik(rezervacijaDTO.getUporabnik());
        reservation.setPolnilnaPostaja(rezervacijaDTO.getPolnilnaPostaja());
        reservation.setPolnjenje_zacetek(rezervacijaDTO.getPolnjenjeZacetek());
        reservation.setPolnjenje_konec(rezervacijaDTO.getPolnjenjeKonec());

        rezervacijaZrno.createReservation(reservation);
    }
}
