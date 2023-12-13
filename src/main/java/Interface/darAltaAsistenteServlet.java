package Interface;

import java.time.LocalDate;

import Business.GestorAsistentes;
import Business.GestorCampamentos;
import Data.DTO.Asistente;
import Data.NivelEducativo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AltaAsistente", urlPatterns = "/AltaAsistente")
public class darAltaAsistenteServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {


    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        Asistente asistente = new Asistente();

        //Recibir parametros
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        LocalDate fecha_nacimiento = LocalDate.parse(request.getParameter("fecha_nacimiento"));

        //Asignar parametros al campamento
        asistente.setNombre(nombre);
        asistente.setApellidos(apellidos);
        asistente.setFechaNacimiento(fecha_nacimiento);
        String asistenteEspecial = request.getParameter("especial");
        if(asistenteEspecial.equals("true")){
            asistente.setAtencionEspecial(true);
        } else if (asistenteEspecial.equals("false")) {
            asistente.setAtencionEspecial(false);
        }

        try {
            GestorAsistentes gestorAsistentes = new GestorAsistentes();
            gestorAsistentes.darAlta(asistente);
            RequestDispatcher disp = request.getRequestDispatcher("/exito.jsp");
            disp.forward(request, response);
        } catch (Exception e) {
            // Mensaje de error
            request.setAttribute("error_message", "Hubo un problema al crear el asistente: " + e.getMessage());
            // Redirigir a error.jsp
            RequestDispatcher disp = request.getRequestDispatcher("/error.jsp");
            disp.forward(request, response);
        }
    }
}
