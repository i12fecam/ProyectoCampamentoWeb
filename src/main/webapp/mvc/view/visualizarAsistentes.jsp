<%@ page import="Business.GestorAsistentes" %>
<%@ page import="Data.DTO.Asistente" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Quinte01
  Date: 12/12/2023
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visualizar asistentes</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tables.css">
</head>
<%
    GestorAsistentes gestor = new GestorAsistentes();
    ArrayList< Asistente > asistentes = gestor.listarAsistentes();

%>
<body>
<table class="table">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Fecha Nacimiento</th>
        <th>¿Necesidad especial?</th>
        <th></th>
    </tr>
    <%for (Asistente asistente: asistentes) { %>
    <tr>
        <td><%=asistente.getIdentificador() %></td>
        <td><%=asistente.getNombre() %></td>
        <td><%=asistente.getApellidos() %></td>
        <td><%=asistente.getFechaNacimiento() %></td>
        <td><%=asistente.isAtencionEspecial() ? "Si" : "No" %></td>
        <td><a href="modificarAsistente.jsp?id=<%= asistente.getIdentificador() %>" class="btn-modificar btn">Modificar</a></td>
    </tr>
    <% } %>
</table>
</body>
</html>