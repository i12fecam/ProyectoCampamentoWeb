package Business.Servlets.campamento;

import Business.GestorCampamentos;
import Business.GestorInscripciones;
import Data.DTO.Inscripcion;
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
        Inscripcion inscripcion = new Inscripcion();

        //Parametros
        Integer idcampamento = Integer.parseInt(request.getParameter("campamento"));
        LocalDate fechaInscripcion = LocalDate.parse(request.getParameter("fecha"));
        //falta el id
        Horario horario = Horario.valueOf(request.getParameter("horario"));

        try{
            GestorInscripciones gestorInscripciones = new GestorInscripciones();
            gestorInscripciones.crearInscripcion(1, idcampamento, fechaInscripcion, horario);

            RequestDispatcher disp = request.getRequestDispatcher("/exito.jsp");
            disp.forward(request, response);

        }catch (Exception e){

            request.setAttribute("error_message", "Hubo un problema al crear la inscripción: " + e.getMessage());

            RequestDispatcher disp = request.getRequestDispatcher("/mvc/view/altaCampamentoView.jsp");
            disp.forward(request, response);
        }

    }

}
