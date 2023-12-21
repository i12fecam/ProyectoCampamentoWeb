package Business.Servlets.campamento;
import Business.GestorInscripciones;
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

@WebServlet(name = "Inscripcion", urlPatterns = "/Inscripcion")
public class inscripcionCampamentosServlet extends HttpServlet{

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        String campamentoId = request.getParameter("idCampamento");
        request.setAttribute("idCampamento", campamentoId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/InscripcionCampamentos.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if (customerBean != null) {

            //Parametros
            Integer idcampamento = Integer.parseInt(request.getParameter("campamento"));
            LocalDate fechaInscripcion = LocalDate.parse(request.getParameter("fecha"));
            int idAsistente = customerBean.getIdAsistente();
            Horario horario = Horario.valueOf(request.getParameter("horario"));


            try {
                GestorInscripciones gestorInscripciones = new GestorInscripciones();
                float precio = gestorInscripciones. calcularPrecio(idcampamento, horario);

                gestorInscripciones.crearInscripcion(idAsistente, idcampamento, fechaInscripcion, horario, precio);
                request.setAttribute("success_message", "Se inscribió el campamento correctamente");
                RequestDispatcher disp = request.getRequestDispatcher("/exito.jsp");
                disp.forward(request, response);

            } catch (Exception e) {

                request.setAttribute("error_message", "Hubo un problema al crear la inscripción: " + e.getMessage());

                RequestDispatcher disp = request.getRequestDispatcher("/error.jsp");
                disp.forward(request, response);
            }

        }
    }

}
