package Interface;

import Data.DTO.Actividad;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AltaActividad", urlPatterns = "/AltaActividad")
public class darAltaActividadServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        Actividad actividad = new Actividad();
        actividad.setNombre((String) request.getAttribute("name"));
        request.getAttribute("nivel_educativo");
        request.getAttribute("horario");
        actividad.setMaxParticipantes((Integer) request.getAttribute("maxParticipantes"));
        actividad.setMonitoresNecesarios((Integer)request.getAttribute("monitoresNecesarios"));
        System.out.println(actividad);

        RequestDispatcher disp = request.getRequestDispatcher("/mvc/view/AltaActividad.jsp");
        disp.forward(request, response);
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {

    }
}
