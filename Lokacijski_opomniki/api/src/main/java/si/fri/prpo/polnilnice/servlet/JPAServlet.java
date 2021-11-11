package si.fri.prpo.polnilnice.servlet;

import si.fri.prpo.polnilnice.entitete.Rezervacija;
import si.fri.prpo.polnilnice.entitete.Uporabnik;
import si.fri.prpo.polnilnice.zrna.RezervacijaZrno;
import si.fri.prpo.polnilnice.zrna.UporabnikZrno;
import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja;
import si.fri.prpo.polnilnice.entitete.PolnilnaPostaja.Status;
import si.fri.prpo.polnilnice.zrna.PolnilnaPostajaZrno;
import java.sql.Time;

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
    private PolnilnaPostajaZrno polnilnaPostajaZrno;

    @Inject
    private RezervacijaZrno rezervacijaZrno;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Uporabnik test = new Uporabnik();
        test.setIme("Cene");
        test.setPriimek("Brzina");
        test.setUporabnisko_ime("iAmSpeed");

        List<Uporabnik> uporabniki = uporabnikZrno.getUporabniki();
        Uporabnik userbyid = uporabnikZrno.getById(1);
        uporabnikZrno.deleteUser(2);
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

        Time startTime = new Time(8, 50, 0);
        Time endTime = new Time(20, 0, 0);
        Status status = Status.ACTIVE;
        PolnilnaPostaja test2 = new PolnilnaPostaja();
        test2.setCena(10);
        test2.setLokacija("Gornje radgone 12, 5000 Nikje");
        test2.setObratovanje_zacetek(startTime);
        test2.setObratovanje_konec(endTime);
        test2.setStatus(status);

        List<PolnilnaPostaja> pps = polnilnaPostajaZrno.getPolnilnePostaje();
        PolnilnaPostaja ppbyid = polnilnaPostajaZrno.getById(3);
        polnilnaPostajaZrno.deleteChargingStation(2);
        test2.setLokacija("Brzomir");
        polnilnaPostajaZrno.updateChargingStation(1, test2);

        resp.getWriter().printf("Postaje:\n");
        for(PolnilnaPostaja pp : pps){
            resp.getWriter().printf("%d\t%s\n", pp.getId_polnilna_postaja(), pp.getLokacija());
        }
        resp.getWriter().printf("\n");

        Timestamp st = new Timestamp(2021, 10, 21, 18, 20, 0, 0);
        Timestamp se = new Timestamp(2021, 10, 21, 20, 20, 0, 0);
        Rezervacija r = new Rezervacija();
        r.setPolnjenje_konec(se);
        r.setPolnjenje_zacetek(st);
        r.setUporabnik(test);
        r.setPolnilnaPostaja(test2);
        rezervacijaZrno.createReservation(r);

    }
}