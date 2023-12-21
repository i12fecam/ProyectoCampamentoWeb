package Business.Servlets;

import Business.GestorCampamentos;
import Business.GestorUsuarios;
import Interface.CustomerBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CambiarDatos", urlPatterns = "/CambiarDatosUsuarios")
public class cambiarDatosServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        HttpSession session = request.getSession();
        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newPassword2 = request.getParameterValues("newPassword2")[0];
        RequestDispatcher disp = null;
        if(gestorUsuarios.comprobarUsuario(customerBean.getEmailUser(), oldPassword)!=null){
            if(newPassword.equals(newPassword2)){
                //cambiar contraseña

                try {
                    gestorUsuarios.changePassword(customerBean.getEmailUser(),newPassword);
                    request.setAttribute("success_message", "Se cambió la contraseña correctamente");
                    disp = request.getRequestDispatcher("/exito.jsp");
                } catch (SQLException e) {
                    request.setAttribute("error_message", "Hubo un problema al cambiar la contraseña" + e.getMessage());
                    disp = request.getRequestDispatcher("/error.jsp");

                }


            }
            else{
                //error contraseña nueva no coincide
                request.setAttribute("error_message", "Hubo un error al cambiar la contraseña, las contraseñas nuevas no coinciden");
                disp = request.getRequestDispatcher("/error.jsp");
            }
        }
        else{
            //error de contraseña incorrecta
            request.setAttribute("error_message", "Hubo un error al cambiar la contraseña, las antigua contraseña no es correcta");
            disp = request.getRequestDispatcher("/error.jsp");

        }
        disp.forward(request, response);
    }
}
