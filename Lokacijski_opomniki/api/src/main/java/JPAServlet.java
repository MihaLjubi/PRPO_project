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
    // private UporabnikiZrno uporabnikiZrno;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki());

        // izpis uporabnikov na spletno stran

    }
}