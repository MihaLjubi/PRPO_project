package si.fri.prpo.polnilnice.servlet;

import si.fri.prpo.polnilnice.entitete.Racun;
import si.fri.prpo.polnilnice.entitete.Rezervacija;
import si.fri.prpo.polnilnice.entitete.Uporabnik;
import si.fri.prpo.polnilnice.zrna.RacunZrno;
import si.fri.prpo.polnilnice.zrna.RezervacijaZrno;
import si.fri.prpo.polnilnice.zrna.UporabnikZrno;
import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja;
import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja.Status;
import si.fri.prpo.polnilnice.zrna.PolnilnaPostajaZrno;
import si.fri.prpo.polnilnice.zrna.UpravljanjePolnilnihPostajZrno;
import java.sql.Time;

import si.fri.prpo.polnilnice.DTO.RezervacijaDTO;
import si.fri.prpo.polnilnice.DTO.PolnilnaPostajaDTO;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import java.io.IOException;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Inject
    private UpravljanjePolnilnihPostajZrno upravljanjePolnilnihPostajZrno;

    @Inject
    private PolnilnaPostajaZrno polnilnaPostajaZrno;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*Uporabnik test = new Uporabnik();
        test.setIme("Cene");
        test.setPriimek("Brzina");
        test.setUporabnisko_ime("iAmSpeed");

        List<Uporabnik> uporabniki = uporabnikZrno.getUporabniki();
        test.setIme("Brzomir");
        uporabnikZrno.updateUser(3, test);
        List<Uporabnik> usersCrit = uporabnikZrno.getUporabnikiCriteria();

        String format = "%s\t%s\t%s\n";

        resp.getWriter().printf("Uporabniki:\n");
        for(Uporabnik user : uporabniki){
            resp.getWriter().printf(format, user.getIme(), user.getPriimek(), user.getUporabnisko_ime());
        }
        resp.getWriter().printf("\n");

        resp.getWriter().printf("ById:\n");
        resp.getWriter().printf(format, userbyid.getIme(), userbyid.getPriimek(), userbyid.getUporabnisko_ime());
        resp.getWriter().printf("\n");

        resp.getWriter().printf("users with criteria:\n");
        for(Uporabnik user : usersCrit){
            resp.getWriter().printf(format, user.getIme(), user.getPriimek(), user.getUporabnisko_ime());
        }
        resp.getWriter().printf("\n");

        Time startTime = new Time(12, 50, 0);
        Time endTime = new Time(20, 45, 0);
        Status status = Status.DISABLED;
        PolnilnaPostajaDTO test2 = new PolnilnaPostajaDTO();
        test2.setCena(69);
        test2.setLokacija("Gornje radgone 12, 5000 Nikje");
        test2.setObratovanje_zacetek(startTime);
        test2.setObratovanje_konec(endTime);
        test2.setStatus(status);

        upravljanjePolnilnihPostajZrno.ustvariPolnilnoPostajo(test2);
        */

        /*
        Uporabnik userbyid = uporabnikZrno.getById(1);
        Timestamp st = new Timestamp(2021, 5, 5, 12, 30, 0, 0);
        Timestamp se = new Timestamp(2021, 5, 5, 11, 30, 0, 0);
        RezervacijaDTO r = new RezervacijaDTO();
        r.setPolnjenjeZacetek(st);
        r.setPolnjenjeKonec(se);
        r.setUporabnik(userbyid);
        r.setPolnilnaPostaja(polnilnaPostajaZrno.getById(1));

        Rezervacija rr = upravljanjePolnilnihPostajZrno.rezervacijaPolnilnePostaje(r);
        System.out.printf("%d\t%s\t%d\t%d", rr.getId_rezervacija(), rr.getPolnjenje_zacetek(), rr.getPolnilnaPostaja().getId_polnilna_postaja(), rr.getUporabnik().getId_uporabnik());
        */



        /* List<PolnilnaPostaja> pps = polnilnaPostajaZrno.getPolnilnePostaje();
        PolnilnaPostaja ppbyid = polnilnaPostajaZrno.getById(3);
        test2.setLokacija("Brzomir");

        resp.getWriter().printf("Postaje:\n");
        for(PolnilnaPostaja pp : pps){
            resp.getWriter().printf("%d\t%s\n", pp.getId_polnilna_postaja(), pp.getLokacija());
        }
        resp.getWriter().printf("\n");

        List<Racun> racuni = racunZrno.getRacuni();
        resp.getWriter().printf("Racuni:\n");
        for(Racun rac : racuni){
            resp.getWriter().printf("%d\t%s\n", rac.getId_racun(), rac.getKoncnacena());
        }
        resp.getWriter().printf("\n");

        Racun racun = new Racun();
        racun.setKoncnacena(12.5);
        racun.setRezervacija(rezervacijaZrno.getById(1));
        racunZrno.deleteRacun(1);
        racunZrno.updateRacun(2, racun);

        polnilnaPostajaZrno.updateChargingStation(1, test2); */



    }
}