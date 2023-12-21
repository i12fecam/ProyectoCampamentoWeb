<%@ page import="Business.GestorCampamentos" %>
<%@ page import="Data.DTO.Actividad" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 9/12/23
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/exception.jsp"%>
<html>
<head>
    <title>Visualizar Actividades</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tables.css">
</head>
<%
    GestorCampamentos gestor = new GestorCampamentos();
    ArrayList< Actividad > actividades = gestor.listarActividades();

%>
<body>
    <table class="table">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Nivel Educativo</th>
            <th>Horario</th>
            <th>Máximo de asistentes</th>
            <th>Monitores necesarios</th>
        </tr>
        <%for (Actividad actividad: actividades) { %>
            <tr>
                <td><%= actividad.getIdentificador() %></td>
                <td><%= actividad.getNombre() %></td>
                <td><%=actividad.getNivelEducativo().toString() %></td>
                <td><%=actividad.getHorario() %></td>
                <td><%=actividad.getMaxParticipantes() %></td>
                <td><%=actividad.getMonitoresNecesarios() %></td>
            </tr>
        <% } %>
    </table>
    <div class="centrar">
        <a href="../../../home.jsp" class="btn btn-modificar mb-4 home-link" >Volver al home</a>
    </div>
</body>
</html>
