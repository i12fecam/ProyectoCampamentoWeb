package Business.Servlets.campamento;
import Business.GestorAsistentes;
import Business.GestorCampamentos;
import Business.GestorInscripciones;
import Data.DAO.InscripcionDAO;
import Data.DTO.Asistente;
import Data.DTO.Campamento;
import Interface.CustomerBean;
import Data.Horario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
@WebServlet(name = "CancelarCampamento", urlPatterns = "/CancelarCampamento")
public class cancelarCampamentoServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {

        //Recibir parametros
        String id= request.getParameter("id_campamento");
        int id_ = Integer.parseInt(id);
        String id2= request.getParameter("fk_asistente");
        int id2_ = Integer.parseInt(id);
        try {
            GestorInscripciones gestorInscripciones =new GestorInscripciones();
            gestorInscripciones.cancelarInscripcion(id2_,id_);
            RequestDispatcher disp = request.getRequestDispatcher("/exito.jsp");
            disp.forward(request, response);
        } catch (Exception e) {
            // Mensaje de error
            request.setAttribute("error_message", "Hubo un problema al cancelar el campamento " + e.getMessage());
            // Redirigir a error.jsp
            RequestDispatcher disp = request.getRequestDispatcher("/error.jsp");
            disp.forward(request, response);
        }
    }


}
