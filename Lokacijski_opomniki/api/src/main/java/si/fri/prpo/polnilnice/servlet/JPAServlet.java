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

        resp.getWriter().printf("<p>");
        resp.getWriter().printf("Uporabniki:");
        for(Uporabnik user : uporabniki){
            resp.getWriter().printf(user.getUporabnisko_ime() + " ");
        }
        resp.getWriter().printf("<br>");
        resp.getWriter().printf("Byusername:");
        for(Uporabnik user : userbyname){
            resp.getWriter().printf(user.getUporabnisko_ime() + " ");
        }
        resp.getWriter().printf("<br>");
        resp.getWriter().printf("ById:");
        for(Uporabnik user : userbyid){
            resp.getWriter().printf("%s", user.getUporabnisko_ime() + " ");
        }
        resp.getWriter().printf("</p>");
        // izpis uporabnikov na spletno stran

    }
}