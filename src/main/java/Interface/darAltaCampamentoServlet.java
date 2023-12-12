package Interface;

import java.time.LocalDate;

import Business.GestorCampamentos;
import Data.DTO.Campamento;
import Data.NivelEducativo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AltaCampamento", urlPatterns = "/AltaCampamento")
public class darAltaCampamentoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {


    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        Campamento campamento = new Campamento();

        //Recibir parametros
        LocalDate fecha_inicio = LocalDate.parse(request.getParameter("fecha_inicio"));
        LocalDate fecha_final = LocalDate.parse(request.getParameter("fecha_fin"));
        NivelEducativo nivel_educativo = NivelEducativo.valueOf(request.getParameter("nivel_educativo"));
        Integer maxAsistentes = Integer.parseInt(request.getParameter("max_asistentes"));

        //Asignar parametros al campamento
        campamento.setFechaInicio(fecha_inicio);
        campamento.setFechaFinal(fecha_final);
        campamento.setNivelEducativo(nivel_educativo);
        campamento.setMaxAsistentes(maxAsistentes);

        try {
            GestorCampamentos gestorCampamentos = new GestorCampamentos();
            gestorCampamentos.crearCampamento(campamento);

            RequestDispatcher disp = request.getRequestDispatcher("/home.jsp");
            disp.forward(request, response);
        } catch (Exception e) {
            // Mensaje de error
            request.setAttribute("error_message", "Hubo un problema al crear el campamento: " + e.getMessage());

            // Redirigir a /mvc/view/altaCampamentoView.jsp
            RequestDispatcher disp = request.getRequestDispatcher("/mvc/view/altaCampamentoView.jsp");
            disp.forward(request, response);
        }
    }
}
