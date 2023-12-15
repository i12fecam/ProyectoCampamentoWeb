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
        int actividadID = Integer.parseInt( request.getParameterValues("actividad")[0] );
        int campamentoID = Integer.parseInt( request.getParameterValues("campamento")[0] );
        GestorCampamentos gestor = new GestorCampamentos();
        RequestDispatcher disp;
        if(gestor.asociarActividadCampamento(campamentoID,actividadID)){
            disp = request.getRequestDispatcher("/exito.jsp");
        }else {
            disp = request.getRequestDispatcher("/error.jsp");
        }

        disp.forward(request, response);
    }
}
