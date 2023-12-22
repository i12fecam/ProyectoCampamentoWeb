package Business.Servlets.asociar;

import Business.GestorCampamentos;
import Business.mensajeExcepcion;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AsociarMonitorCampamento", urlPatterns = "/AsociarMonitorCampamento")
public class asociarMonitorCampamentoServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException{
        String monitorIDString = request.getParameter("id_monitor");
        String campamentoIDString = request.getParameter("campamento");


        //validar formulario
        if (campamentoIDString == null || monitorIDString == null) {

            request.setAttribute("error_message", "Debe seleccionar una opción en ambas tablas antes de enviar el formulario.");
            RequestDispatcher disp = request.getRequestDispatcher("/error.jsp");
            disp.forward(request, response);
            return;
        }
        int monitorID = Integer.parseInt( request.getParameter("id_monitor") );
        int campamentoID = Integer.parseInt( request.getParameter("campamento") );

        try {
            GestorCampamentos gestor = new GestorCampamentos();

            boolean esEspecial = gestor.comprobar_monitor_especial(monitorID);

        if (esEspecial) {
            gestor.asociarMonitorEspecialCampamento(monitorID, campamentoID);
        } else {
            gestor.asociarMonitorResponsableCampamento(monitorID, campamentoID);
        }
            request.setAttribute("success_message", "Se asoció el monitor al campamento correctamente");
            RequestDispatcher disp = request.getRequestDispatcher("/exito.jsp");
            disp.forward(request, response);

        } catch (mensajeExcepcion e) {

            request.setAttribute("error_message", e.getMessage());
            RequestDispatcher disp = request.getRequestDispatcher("/error.jsp");
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
