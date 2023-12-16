package Business.Servlets.campamento;
import java.time.LocalDate;
import Data.DAO.CampamentoDAO;
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
import java.util.ArrayList;

@WebServlet(name = "CampamentosDisponibles", urlPatterns = "/CampamentosDisponibles")
public class consultarCampamentosDispServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        GestorCampamentos gestorCampamentos = new GestorCampamentos();
        LocalDate fecha_inicio = LocalDate.parse(request.getParameter("fecha_buscar"));
        LocalDate fecha_final = LocalDate.parse(request.getParameter("fecha_buscar_stop"));
        ArrayList<Campamento> listaCampamentos=gestorCampamentos.listarCampamentos();
        ArrayList<Campamento> campamentosFiltrados = new ArrayList<>();
        try {
            for (Campamento campamento : listaCampamentos) {
                if (campamento.getFechaInicio().compareTo(fecha_inicio) > 0 && campamento.getFechaInicio().compareTo(fecha_final) < 0) {
                    campamentosFiltrados.add(campamento);
                }

            }
            request.setAttribute("campamentosFiltrados", campamentosFiltrados);
            RequestDispatcher disp = request.getRequestDispatcher("/mvc/view/campamento/consultarCampamentosDisponiblesResult.jsp");
            disp.forward(request, response);
        }
        catch (Exception e) {
            // Mensaje de error
            request.setAttribute("error_message", "Hubo un problema al mostrar los campamentos disponibles: " + e.getMessage());

            // Redirigir a /mvc/view/consultarCampamentosDisponibles.jsp
            RequestDispatcher disp = request.getRequestDispatcher("/mvc/view/campamento/consultarCampamentosDisponibles.jsp");
            disp.forward(request, response);
        }

    }
}
