package Business.Servlets.monitor;

import Business.GestorCampamentos;
import Data.DTO.Monitor;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.time.LocalDate;

@WebServlet(name = "ModificarMonitor", urlPatterns = "/ModificarMonitor")
public class modificarMonitorServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        Monitor monitor = new Monitor();

        //Recibir parametros
        String id= request.getParameter("idMonitor");
        int id_ = Integer.parseInt(id);
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        LocalDate fecha_nacimiento = LocalDate.parse(request.getParameter("fecha_nacimiento"));

        //Asignar parametros al campamento
        monitor.setIdentificador(id_);
        monitor.setNombre(nombre);
        monitor.setApellidos(apellidos);
        monitor.setFechaNacimiento(fecha_nacimiento);
        String monitorEspecial = request.getParameter("especial");
        if(monitorEspecial.equals("true")){
            monitor.setEducadorEspecial(true);
        } else if (monitorEspecial.equals("false")) {
            monitor.setEducadorEspecial(false);
        }

        try {
            GestorCampamentos gestor = new GestorCampamentos();
            gestor.ModificarMonitor(monitor);
            RequestDispatcher disp = request.getRequestDispatcher("/exito.jsp");
            disp.forward(request, response);
        } catch (Exception e) {
            // Mensaje de error
            request.setAttribute("error_message", "Hubo un problema al modificar el monitor: " + e.getMessage());
            // Redirigir a error.jsp
            RequestDispatcher disp = request.getRequestDispatcher("/error.jsp");
            disp.forward(request, response);
        }
    }
}
