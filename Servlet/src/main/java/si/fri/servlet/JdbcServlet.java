package si.fri.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import jdbc.BaseDao;
import jdbc.Entiteta;
import jdbc.Uporabnik;
import jdbc.UporabnikDaoImpl;

@WebServlet("/servlet")
public class JdbcServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ime_storitve = ConfigurationUtil.getInstance().get("kumuluzee.name").get();
        String version = ConfigurationUtil.getInstance().get("kumuluzee.version").get();
        String ime_okolja = ConfigurationUtil.getInstance().get("kumuluzee.env.name").get();

        resp.getWriter().printf("<p>Ime storitve:  %s</p>", ime_storitve);
        resp.getWriter().printf("<p>Verzija: %s</p>", version);
        resp.getWriter().printf("<p>Ime izvajalnega okolja:  %s</p>", ime_okolja);

        BaseDao uporabnikDao = new UporabnikDaoImpl();

        Uporabnik newUser1 = new Uporabnik("Polde", "Zagorc", "poldek123");
        Uporabnik newUser2 = new Uporabnik("Janez", "Dolenc", "dolencJ");
        Uporabnik newUser3 = new Uporabnik("Rebeka", "Picek", "RP99");

        uporabnikDao.vstavi(newUser1);
        uporabnikDao.vstavi(newUser2);
        uporabnikDao.vstavi(newUser3);

        resp.getWriter().printf("Dodamo nekaj vnosov v podatkovno bazo<br/>");
        printUporabniki(uporabnikDao, resp);

        uporabnikDao.odstrani(88);
        resp.getWriter().printf("Odstranimo enega uporabnika<br/>");
        printUporabniki(uporabnikDao, resp);

        Uporabnik updateUser = (Uporabnik) uporabnikDao.vrni(89);
        updateUser.setUporabniskoIme("JD");
        resp.getWriter().printf("Izpise posodobljenega uporabnika:<br/>%d %s %s %s<br/><br/>", updateUser.getId(), updateUser.getIme(), updateUser.getPriimek(), updateUser.getUporabniskoIme());
        uporabnikDao.posodobi(updateUser);

        Uporabnik returnedUser = (Uporabnik) uporabnikDao.vrni(90);
        resp.getWriter().printf("Izpise samo vrnjenega uporabnika:<br/>%d %s %s %s<br/><br/>", returnedUser.getId(), returnedUser.getIme(), returnedUser.getPriimek(), returnedUser.getUporabniskoIme());
    }

    private void printUporabniki (BaseDao uporabnikDao, HttpServletResponse resp) throws IOException {
        List<Entiteta> vsiUporabniki = uporabnikDao.vrniVse();
        for (int i = 0; i < vsiUporabniki.size(); i++) {
            Uporabnik u = (Uporabnik) vsiUporabniki.get(i);
            resp.getWriter().printf("%d %s %s %s<br/>", u.getId(), u.getIme(), u.getPriimek(), u.getUporabniskoIme());
        }
        resp.getWriter().printf("<br/>");
    }

}
