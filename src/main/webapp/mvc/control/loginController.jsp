<%@ page import="Business.GestorUsuarios" %>
<%@ page import="Data.TipoUsuario" %><%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 19/11/23
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%--<%@ page import =",es.uco.pw.data.dao.UserDAO" %>--%>
<jsp:useBean  id="customerBean" scope="session" class="Interface.CustomerBean"></jsp:useBean>
<%
/* Posibles flujos:
1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp
2) customerBean no está logado:
a) Hay parámetros en el request  -> procede de la vista
b) No hay parámetros en el request -> procede de otra funcionalidad o index.jsp
*/
//Caso 1: Por defecto, vuelve al index
String nextPage = "../../index.jsp";
String mensajeNextPage = "";
//Caso 2
if (customerBean == null || customerBean.getEmailUser().equals("")) {
        String emailUser = request.getParameter("email");
        String passwordUser = request.getParameter("password");

        //Caso 2.a: Hay parámetros -> procede de la VISTA
        if (emailUser != null) {
        //Se accede a bases de datos para obtener el usuario
            GestorUsuarios gestor = new GestorUsuarios();


        //Se realizan todas las comprobaciones necesarias del dominio
        //Aquí sólo comprobamos que exista el usuario
            TipoUsuario tipo = gestor.comprobarUsuario(emailUser,passwordUser);
                if( tipo  != null){
                    //usuario valido
%>
                    <jsp:setProperty property="emailUser" value="<%=emailUser%>" name="customerBean"/>
<%
                    nextPage = "../../home.jsp";
                    mensajeNextPage = "El usuario ha sido válido";
                } else {
                    // Usuario no válido
                    nextPage = "../view/loginView.jsp";
                    mensajeNextPage = "El usuario que ha indicado no existe o no es v&aacute;lido";
                }
        //Caso 2.b -> se debe ir a la vista por primera vez
        } else {
            nextPage = "../view/loginView.jsp";
        }
}
%>
<jsp:forward page="<%=nextPage%>">
    <jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>