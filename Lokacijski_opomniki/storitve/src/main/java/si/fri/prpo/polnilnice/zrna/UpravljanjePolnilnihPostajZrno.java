package si.fri.prpo.polnilnice.zrna;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import si.fri.prpo.polnilnice.DTO.*;
import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja;
import si.fri.prpo.polnilnice.entitete.Racun;
import si.fri.prpo.polnilnice.entitete.Rezervacija;
import si.fri.prpo.polnilnice.entitete.Uporabnik;
import si.fri.prpo.polnilnice.interceptor.BeleziKlice;
import si.fri.prpo.polnilnice.DTO.PolnilnaPostajaDTO;
import si.fri.prpo.polnilnice.DTO.PolnilnicaDTO;
import si.fri.prpo.polnilnice.DTO.RacunDTO;
import si.fri.prpo.polnilnice.DTO.RezervacijaDTO;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
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
    private Client client;
    private String baseUrl;

    @PostConstruct
    private void init() {
        logger.info("Inicializacija zrna " + UpravljanjePolnilnihPostajZrno.class.getName());
        logger.info("Zrno id: " + UUID.randomUUID().toString());

        client = ClientBuilder.newClient();
        baseUrl = ConfigurationUtil.getInstance().get("integration.zasedenost.base-url")
                .orElse("http://localhost:8081/v1");
    }

    @PreDestroy
    private void destroy() {
        logger.info("Uničenje zrna " + UpravljanjePolnilnihPostajZrno.class.getSimpleName());
    }

    //ustvari novo rezervacijo
    @BeleziKlice
    public Rezervacija rezervacijaPolnilnePostaje(RezervacijaDTO rezervacijaDTO) {
        if (!rezervacijaDTO.validate())
            return null;

        Rezervacija reservation = new Rezervacija();
        reservation.setUporabnik(rezervacijaDTO.getUporabnik());
        reservation.setPolnilnaPostaja(rezervacijaDTO.getPolnilnaPostaja());
        reservation.setPolnjenje_zacetek(rezervacijaDTO.getPolnjenjeZacetek());
        reservation.setPolnjenje_konec(rezervacijaDTO.getPolnjenjeKonec());

        posodobiZasedenostPolnilnic(reservation.getPolnilnaPostaja());

        Rezervacija r = rezervacijaZrno.createReservation(reservation);
        pridobiRezervacijeZaUporabnika(r.getUporabnik());
        return r;
    }

    //izrcuna ceno za vsako rezervacijo
    @BeleziKlice
    public Racun izdajRacun(RacunDTO racunDTO) {
        long duration = racunDTO.getRezervacija().getPolnjenje_zacetek().getTime() - racunDTO.getRezervacija().getPolnjenje_konec().getTime();
        long rezLength = TimeUnit.MILLISECONDS.toMinutes(duration);
        int cenaMinute = racunDTO.getRezervacija().getPolnilnaPostaja().getCena();
        double cena = rezLength * cenaMinute;

        Racun racun = new Racun();
        racun.setKoncnacena(cena);
        racun.setRezervacija(racunDTO.getRezervacija());

        racunZrno.createRacun(racun);
        return racun;
    }

    //ustvari novo polnilno postajo
    @BeleziKlice
    public PolnilnaPostaja ustvariPolnilnoPostajo(PolnilnaPostajaDTO polnilnaPostajaDTO){

        PolnilnaPostaja pp = new PolnilnaPostaja();
        pp.setLokacija(polnilnaPostajaDTO.getLokacija());
        pp.setStatus(polnilnaPostajaDTO.getStatus());
        pp.setObratovanje_zacetek(polnilnaPostajaDTO.getObratovanje_zacetek());
        pp.setObratovanje_konec(polnilnaPostajaDTO.getObratovanje_konec());
        pp.setCena(polnilnaPostajaDTO.getCena());

        polnilnaPostajaZrno.createChargingStation(pp);
        return pp;
    }

    private void posodobiZasedenostPolnilnic(PolnilnaPostaja polnilnaPostaja) {
        try {
            var pol = new PolnilnicaDTO(polnilnaPostaja.getLokacija());
            System.out.println(pol);
            System.out.println(pol.getLokacija());
            client.target(baseUrl + "/zasedenost").request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(pol));
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }
    private void pridobiRezervacijeZaUporabnika(Uporabnik uporabnik){
        try{
            Integer userId = uporabnik.getId_uporabnik();
            client.target(baseUrl + "/upravljanje/" + userId).request(MediaType.APPLICATION_JSON).
                    get();
            System.out.println("klic 2. mikrostoritve uspesen");
            logger.info("klic 2. mikrostoritve uspesen");
        } catch(Exception e){
            logger.severe(e.getMessage());
        }
    }
}
