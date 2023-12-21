package Business.Servlets.actividad;

import Business.GestorCampamentos;
import Data.DTO.Actividad;
import Data.Horario;
import Data.NivelEducativo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AltaActividad", urlPatterns = "/AltaActividad")
public class darAltaActividadServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Actividad actividad = new Actividad();
        actividad.setNombre(request.getParameterValues("name")[0]);
        String [] Snivel_educativo =  (String[]) request.getParameterValues("nivel_educativo");
        if(Snivel_educativo[0].equals("Infantil")){
            actividad.setNivelEducativo(NivelEducativo.Infantil);
        } else if (Snivel_educativo[0].equals("Juvenil")) {
            actividad.setNivelEducativo(NivelEducativo.Juvenil);
        } else if (Snivel_educativo[0].equals("Adolescente")) {
            actividad.setNivelEducativo(NivelEducativo.Adolescente);
        }
        String Shorario[] = request.getParameterValues("horario");
        if(Shorario[0].equals("Parcial")){
            actividad.setHorario(Horario.Parcial);
        } else if (Shorario[0].equals("Completa")) {
            actividad.setHorario(Horario.Completo);
        }

        actividad.setMaxParticipantes(Integer.parseInt( request.getParameterValues("maxParticipantes")[0] ) );
        actividad.setMonitoresNecesarios(Integer.parseInt(request.getParameterValues("monitoresNecesarios")[0]));
        System.out.println(actividad);
        RequestDispatcher disp;
        try {
            GestorCampamentos gestorCampamentos = new GestorCampamentos();
            gestorCampamentos.crearActividad(actividad);
        }catch(Exception e){
                disp = request.getRequestDispatcher("/error.jsp");
                disp.forward(request, response);
            }
        request.setAttribute("success_message", "Se dio de alta la actividad correctamente");
        disp = request.getRequestDispatcher("/exito.jsp");
        disp.forward(request, response);
    }
}
