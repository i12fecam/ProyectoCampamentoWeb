package Interface;

import Business.GestorCampamentos;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet(name = "Inscripcion", urlPatterns = "/Inscripcion")
public class inscripcionCampamentosServlet extends HttpServlet{

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        String campamentoId = request.getParameter("campamentoId");
        request.setAttribute("campamentoId", campamentoId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/InscripcionCampamentos.jsp");
        dispatcher.forward(request, response);
        HttpSession session = request.getSession();
        session.setAttribute("campamentosMostrados", true);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {


    }

}
