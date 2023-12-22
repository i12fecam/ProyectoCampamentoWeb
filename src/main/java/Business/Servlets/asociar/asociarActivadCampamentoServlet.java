package Business.Servlets.asociar;

import Business.GestorCampamentos;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "AsociarActividadCampamento", urlPatterns = "/AsociarActividadCampamento")
public class asociarActivadCampamentoServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException{
        String actividadIDString = request.getParameter("actividad");
        String campamentoIDString = request.getParameter("campamento");

        //validar formulario
        if (actividadIDString == null || campamentoIDString == null) {

            request.setAttribute("error_message", "Debe seleccionar una opción en ambas tablas antes de enviar el formulario.");
            RequestDispatcher disp = request.getRequestDispatcher("/error.jsp");
            disp.forward(request, response);
            return;
        }

        int actividadID = Integer.parseInt( request.getParameter("actividad") );
        int campamentoID = Integer.parseInt( request.getParameter("campamento"));

        GestorCampamentos gestor = new GestorCampamentos();
        RequestDispatcher disp;

        if (gestor.comprobar_duplicidad_camp_act(campamentoID, actividadID)>0) {
            request.setAttribute("error_message", "La actividad ya está asociada al campamento");
            disp = request.getRequestDispatcher("/error.jsp");
        } else if (gestor.asociarActividadCampamento(campamentoID, actividadID)) {
            request.setAttribute("success_message", "Se asoció la actividad al campamento correctamente");
            disp = request.getRequestDispatcher("/exito.jsp");
        } else {
            disp = request.getRequestDispatcher("/error.jsp");
        }

        disp.forward(request, response);

    }
}
