package si.fri.prpo.polnilnice.zrna;

import si.fri.prpo.polnilnice.DTO.PolnilnaPostajaDTO;
import si.fri.prpo.polnilnice.DTO.RacunDTO;
import si.fri.prpo.polnilnice.DTO.RezervacijaDTO;
import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja;
import si.fri.prpo.polnilnice.entitete.Racun;
import si.fri.prpo.polnilnice.entitete.Rezervacija;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
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

    public void rezervacijaPolnilnePostaje(RezervacijaDTO rezervacijaDTO) {//ustvari novo rezervacijo
        if (!rezervacijaDTO.validate())
            return;

        Rezervacija reservation = new Rezervacija();
        reservation.setUporabnik(rezervacijaDTO.getUporabnik());
        reservation.setPolnilnaPostaja(rezervacijaDTO.getPolnilnaPostaja());
        reservation.setPolnjenje_zacetek(rezervacijaDTO.getPolnjenjeZacetek());
        reservation.setPolnjenje_konec(rezervacijaDTO.getPolnjenjeKonec());
        reservation.setCena(rezervacijaDTO.getCena());
        rezervacijaZrno.createReservation(reservation);

    }
    public int izracunajCeno(Racun racunDTO, PolnilnaPostajaDTO polnilnaPostajaDTO) {//izrcuna ceno za vsako rezervacijo
        Time start = racunDTO.getObratovanje_zacetek();
        Time end = racunDTO.getObratovanje_konec();
        long duration = start.getTime() - end.getTime();
        long rezLength = TimeUnit.MILLISECONDS.toMinutes(duration);
        int flatrate = 5;
        int cenaMinute = polnilnaPostajaDTO.getCena();
        long cena = (flatrate + rezLength * cenaMinute);

        return (int)cena;
    }
    public void ustvariPolnilnico(PolnilnaPostajaDTO polnilnaPostajaDTO){//ustvari novo polnilno postajo
        PolnilnaPostaja pp = new PolnilnaPostaja();
        pp.setLokacija(polnilnaPostajaDTO.getLokacija());
        pp.setStatus(polnilnaPostajaDTO.getStatus());
        pp.setObratovanje_zacetek(polnilnaPostajaDTO.getObratovanje_zacetek());
        pp.setObratovanje_konec(polnilnaPostajaDTO.getObratovanje_konec());
        pp.setCena(polnilnaPostajaDTO.getCena());
        polnilnaPostajaZrno.createChargingStation(pp);
    }
}