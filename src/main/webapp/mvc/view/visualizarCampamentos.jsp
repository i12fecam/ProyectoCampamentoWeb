<%@ page import="Business.GestorCampamentos" %>
<%@ page import="Data.DTO.Campamento" %>
<%@ page import="java.util.ArrayList" %>

<%--
  Created by IntelliJ IDEA.
  User: Quinte01
  Date: 09/12/2023
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visualizar Campamentos</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table2.css">
</head>
<%
    GestorCampamentos gestor = new GestorCampamentos();
    ArrayList< Campamento > campamentos = gestor.listarCampamentos();

%>
<body>
<table class="table">
    <tr>
        <th>ID</th>
        <th>Fecha Inicio</th>
        <th>Fecha Final</th>
        <th>Nivel Educativo</th>
        <th>Nº Asistentes</th>
       <!-- <th>Monitor Responsable</th>
            <th>Monitor Especial</th>-->
    </tr>
    <%for (Campamento campamento: campamentos) { %>
    <tr>
        <td><%=campamento.getIdCampamento() %></td>
        <td><%=campamento.getFechaInicio() %></td>
        <td><%=campamento.getFechaFinal() %></td>
        <td><%=campamento.getNivelEducativo().toString() %></td>
        <td><%=campamento.getMaxAsistentes() %></td>

    </tr>
    <% } %>
</table>

</body>
</html>