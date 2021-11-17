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
import javax.enterprise.context.RequestScoped;
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
    @Inject
    private RacunZrno racunZrno;

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

    //ustvari novo rezervacijo
    public Rezervacija rezervacijaPolnilnePostaje(RezervacijaDTO rezervacijaDTO) {
        if (!rezervacijaDTO.validate())
            return null;

        Rezervacija reservation = new Rezervacija();
        reservation.setUporabnik(rezervacijaDTO.getUporabnik());
        reservation.setPolnilnaPostaja(rezervacijaDTO.getPolnilnaPostaja());
        reservation.setPolnjenje_zacetek(rezervacijaDTO.getPolnjenjeZacetek());
        reservation.setPolnjenje_konec(rezervacijaDTO.getPolnjenjeKonec());

        Rezervacija r = rezervacijaZrno.createReservation(reservation);
        return r;
    }

    //izrcuna ceno za vsako rezervacijo
    public void izdajRacun(RacunDTO racunDTO) {
        long duration = racunDTO.getRezervacija().getPolnjenje_zacetek().getTime() - racunDTO.getRezervacija().getPolnjenje_konec().getTime();
        long rezLength = TimeUnit.MILLISECONDS.toMinutes(duration);
        int cenaMinute = racunDTO.getRezervacija().getPolnilnaPostaja().getCena();
        double cena = rezLength * cenaMinute;

        Racun racun = new Racun();
        racun.setKoncnacena(cena);
        racun.setRezervacija(racunDTO.getRezervacija());

        racunZrno.createRacun(racun);
    }

    //ustvari novo polnilno postajo
    public void ustvariPolnilnoPostajo(PolnilnaPostajaDTO polnilnaPostajaDTO){

        PolnilnaPostaja pp = new PolnilnaPostaja();
        pp.setLokacija(polnilnaPostajaDTO.getLokacija());
        pp.setStatus(polnilnaPostajaDTO.getStatus());
        pp.setObratovanje_zacetek(polnilnaPostajaDTO.getObratovanje_zacetek());
        pp.setObratovanje_konec(polnilnaPostajaDTO.getObratovanje_konec());
        pp.setCena(polnilnaPostajaDTO.getCena());

        polnilnaPostajaZrno.createChargingStation(pp);
    }
}
