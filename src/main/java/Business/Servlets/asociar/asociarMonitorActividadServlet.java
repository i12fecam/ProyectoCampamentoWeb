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
        String actividadIDString = request.getParameter("actividad");
        String monitorIDString = request.getParameter("monitor");

        //validar formulario
        if (actividadIDString == null || monitorIDString == null) {

            request.setAttribute("error_message", "Debe seleccionar una opción en ambas tablas antes de enviar el formulario.");
            RequestDispatcher disp = request.getRequestDispatcher("/error.jsp");
            disp.forward(request, response);
            return;
        }

        int actividadID = Integer.parseInt( actividadIDString );
        int monitorID = Integer.parseInt( monitorIDString);
        try{
            GestorCampamentos gestor = new GestorCampamentos();
            boolean asociacion = gestor.asociarMonitorActividad(monitorID,actividadID);
            if(asociacion) {
                request.setAttribute("success_message", "Se asoció el monitor a la actividad correctamente");
                RequestDispatcher disp = request.getRequestDispatcher("/exito.jsp");
                disp.forward(request, response);
            }else{
                request.setAttribute("error_message", "La actividad no acepta más monitores: ");
                RequestDispatcher disp = request.getRequestDispatcher("/error.jsp");
                disp.forward(request, response);
            }
        } catch (Exception e) {
            // Mensaje de error
            request.setAttribute("error_message", "Hubo un problema al asociar el monitor: " + e.getMessage());

            // Redirigir a /error.jsp
            RequestDispatcher disp = request.getRequestDispatcher("/error.jsp");
            disp.forward(request, response);
        }
    }
}
