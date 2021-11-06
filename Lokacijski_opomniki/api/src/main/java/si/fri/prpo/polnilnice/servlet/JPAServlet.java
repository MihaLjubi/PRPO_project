package si.fri.prpo.polnilnice.servlet;

import si.fri.prpo.polnilnice.entitete.Uporabnik;
import si.fri.prpo.polnilnice.zrna.UporabnikZrno;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.IOException;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Uporabnik> uporabniki = uporabnikZrno.getUporabniki();
        List<Uporabnik> userbyname = uporabnikZrno.getByUsername("doubleO7");
        List<Uporabnik> userbyid = uporabnikZrno.getById(00001);
        List<Uporabnik> userbysurname = uporabnikZrno.getBySurname("Teden");
        List<Uporabnik> usersCrit = uporabnikZrno.getUporabnikiCriteria();

        String format = "%s\t%s\t%s\n";

        resp.getWriter().printf("Uporabniki:\n");
        for(Uporabnik user : uporabniki){
            resp.getWriter().printf(format, user.getIme(), user.getPriimek(), user.getUporabnisko_ime());
        }
        resp.getWriter().printf("\n");

        resp.getWriter().printf("ById:\n");
        for(Uporabnik user : userbyid){
            resp.getWriter().printf(format, user.getIme(), user.getPriimek(), user.getUporabnisko_ime());
        }
        resp.getWriter().printf("\n");

        resp.getWriter().printf("ByUsername:\n");
        for(Uporabnik user : userbyname){
            resp.getWriter().printf(format, user.getIme(), user.getPriimek(), user.getUporabnisko_ime());
        }
        resp.getWriter().printf("\n");

        resp.getWriter().printf("BySurname:\n");
        for(Uporabnik user : userbysurname){
            resp.getWriter().printf(format, user.getIme(), user.getPriimek(), user.getUporabnisko_ime());
        }
        resp.getWriter().printf("\n");

        resp.getWriter().printf("users with criteria:\n");
        for(Uporabnik user : usersCrit){
            resp.getWriter().printf(format, user.getIme(), user.getPriimek(), user.getUporabnisko_ime());
        }
        // izpis uporabnikov na spletno stran

    }
}