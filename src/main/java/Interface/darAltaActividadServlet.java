package Interface;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class darAltaActividadServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML");
        out.println("<BODY>");
        out.println ("</BODY>");
        out.println ("</HTML>");
        out.close();
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {

    }
}
