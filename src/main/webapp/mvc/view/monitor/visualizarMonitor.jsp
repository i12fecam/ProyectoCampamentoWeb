<%@ page import="Business.GestorCampamentos" %>
<%@ page import="Data.DTO.Monitor" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Quinte01
  Date: 15/12/2023
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visualizar Monitores</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tables.css">
</head>
<%
    GestorCampamentos gestor = new GestorCampamentos();
    ArrayList<Monitor> monitores = gestor.listarMonitores();

%>
<body>
<table class="table">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Fecha Nacimiento</th>
        <th>Atención especial</th>
        <th></th>
    </tr>
    <%for (Monitor monitor: monitores) { %>
    <tr>
        <td><%=monitor.getIdentificador() %></td>
        <td><%=monitor.getNombre() %></td>
        <td><%=monitor.getApellidos() %></td>
        <td><%=monitor.getFechaNacimiento() %></td>
        <td><%=monitor.isEducadorEspecial() ? "Si" : "No" %></td>
        <td><a href="modificarMonitor.jsp?id=<%= monitor.getIdentificador() %>" class="btn-modificar btn">Modificar</a></td>

    </tr>
    <% } %>
</table>
</body>
</html>