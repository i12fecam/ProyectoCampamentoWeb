<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="Business.GestorUsuarios" %>
<%@ page import="Data.DTO.Asistente" %><%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 23/11/23
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean  id="customerBean" scope="session" class="Interface.CustomerBean"></jsp:useBean>
<%
String nextPage = "../../index.jsp";
String mensajeNextPage = "";
//Caso 2
if (customerBean != null && customerBean.getEmailUser().equals("")) {

        String emailUser = request.getParameter("email");
        System.out.println(emailUser);
        String passwordUser = request.getParameter("password");
        System.out.println(emailUser);
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String s_fecha_nacimiento = request.getParameter("fecha_nacimiento");
        LocalDate fecha_nacimiento = null;
    try {
             fecha_nacimiento = LocalDate.parse(s_fecha_nacimiento);
        }catch (Exception e){}

        Boolean especial = request.getParameter("especial") == "1";

    //Caso 2.a: Hay parámetros -> procede de la VISTA
    if (emailUser != null) {
    //Se accede a bases de datos para obtener el usuario

        GestorUsuarios gestor = new GestorUsuarios();
        Asistente asis = new Asistente(0,name,surname,fecha_nacimiento,especial);
        //Se realizan todas las comprobaciones necesarias del dominio
        //Aquí sólo comprobamos que exista el usuario
        if(gestor.ValidarUsuario(emailUser)){
            //usuario valido
            gestor.AñadirUsuarioAsistente(asis,emailUser,passwordUser);
            nextPage = "../control/loginController.jsp";
            mensajeNextPage = "El usuario ha sido registrado de forma válida";
            System.out.println("El usuario ha sido registrado de forma válida");

        } else {
            // Usuario no válido
            nextPage = "../view/registerView.jsp";
            mensajeNextPage = "El usuario que ha indicado ya existe o no es v&aacute;lido";
        }
    //Caso 2.b -> se debe ir a la vista por primera vez
    } else {
        nextPage = "../view/registerView.jsp";
    }
}
%>
<jsp:forward page="<%=nextPage%>">
    <jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>
