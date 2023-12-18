package Business.Servlets.asociar;
import Business.GestorCampamentos;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet(name = "AsociarMonitorActividad", urlPatterns = "/AsociarMonitorActividad")
public class asociarMonitorActividadServlet extends HttpServlet{
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        int actividadID = Integer.parseInt( request.getParameter("actividad") );
        int monitorID = Integer.parseInt( request.getParameter("monitor"));
        try{
            GestorCampamentos gestor = new GestorCampamentos();

            gestor.asociarMonitorActividad(monitorID,actividadID);
            RequestDispatcher disp = request.getRequestDispatcher("/exito.jsp");
            disp.forward(request, response);

        } catch (Exception e) {
            // Mensaje de error
            request.setAttribute("error_message", "Hubo un problema al asociar el monitor: " + e.getMessage());

            // Redirigir a /error.jsp
            RequestDispatcher disp = request.getRequestDispatcher("/error.jsp");
            disp.forward(request, response);
        }
    }
}
