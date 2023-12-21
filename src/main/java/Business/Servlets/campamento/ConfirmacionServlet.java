package Business.Servlets.campamento;

import Business.GestorInscripciones;
import Business.InscripcionExcepcion;
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

@WebServlet(name = "Confirmacion", urlPatterns = "/Confirmacion")
public class ConfirmacionServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException, IOException{
        try{
            HttpSession session = request.getSession();
            Integer idCampamento = Integer.parseInt(request.getParameter("campamento"));
            LocalDate fechaInscripcion = LocalDate.now();
            Integer idAsistente = Integer.parseInt(request.getParameter("idAsistente"));
            Horario horario = Horario.valueOf(request.getParameter("horario"));
            Float precio = Float.parseFloat(request.getParameter("precio"));

            System.out.println("Atributos recuperados de la sesión:");
            System.out.println("idCampamento: " + idCampamento);
            System.out.println("fechaInscripcion: " + fechaInscripcion);
            System.out.println("idAsistente: " + idAsistente);
            System.out.println("horario: " + horario);
            System.out.println("precio: " + precio);

            GestorInscripciones gestorInscripciones =new GestorInscripciones();
            try {
                gestorInscripciones.crearInscripcion(idAsistente, idCampamento, fechaInscripcion, horario, precio);
            }catch (InscripcionExcepcion e){
                    request.setAttribute("error_message", "La inscripcion es demasiado tardia ");
                    RequestDispatcher disp = request.getRequestDispatcher("/error.jsp");
                    disp.forward(request, response);
            }
            request.setAttribute("success_message", "La inscripcion se ha realizado con éxito");
            RequestDispatcher Dispatcher = request.getRequestDispatcher("/exito.jsp");
            Dispatcher.forward(request, response);

        }catch (Exception e){

            request.setAttribute("error_message","Hubo un problema creando la inscripcion");
            RequestDispatcher disp = request.getRequestDispatcher("/error.jsp");
            disp.forward(request, response);
        }

    }
}
