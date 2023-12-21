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
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet(name = "AltaMonitor", urlPatterns = "/monitor/AltaMonitor")
public class darAltaMonitorServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Monitor monitor = new Monitor();
        monitor.setNombre(request.getParameterValues("nombre")[0]);
        monitor.setApellidos(request.getParameterValues("apellidos")[0]);
        LocalDate fecha_nacimiento = LocalDate.parse(request.getParameter("fecha_nacimiento"));
        monitor.setFechaNacimiento(fecha_nacimiento);

        String monitorEspecial[] = request.getParameterValues("especial");
        if(monitorEspecial[0].equals("true")){
            monitor.setEducadorEspecial(true);
        } else if (monitorEspecial[0].equals("false")) {
            monitor.setEducadorEspecial(false);
        }

        System.out.println(monitor);
        RequestDispatcher disp;
        try {
            GestorCampamentos gestorCampamentos = new GestorCampamentos();
            gestorCampamentos.crearMonitor(monitor);
            request.setAttribute("success_message", "Se dio de alta el monitor correctamente");
            disp = request.getRequestDispatcher("/exito.jsp");


        } catch (Exception e) {
            disp = request.getRequestDispatcher("/mvc/view/monitor/AltaMonitor.jsp");
            disp = request.getRequestDispatcher("home.jsp");
        }
        disp.forward(request, response);
    }
}
