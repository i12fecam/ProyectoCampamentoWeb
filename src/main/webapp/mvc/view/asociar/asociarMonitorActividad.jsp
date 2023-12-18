<%@ page import="Business.GestorCampamentos" %>
<%@ page import="Data.DTO.Actividad" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Data.DTO.Monitor" %>
<%--
  Created by IntelliJ IDEA.
  User: fatim
  Date: 15/12/2023
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Asociar Monitor a actividad</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tables.css">
</head>
<body class = "text-center">
<%
    GestorCampamentos gestor = new GestorCampamentos();
    ArrayList<Actividad> actividades = gestor.listarActividades();
    ArrayList<Monitor> monitores = gestor.listarMonitores();
%>
<form method="post" action="${pageContext.request.contextPath}/AsociarMonitorActividad">
    <div class="form-outline mb-4">
        <label for="actividad-table">Actividades:</label>
        <table id="actividad-table">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Nivel Educativo</th>
                <th>Horario</th>
                <th>Máximo de asistentes</th>
                <th>Máximo de monitores</th>
                <th>Selección</th>
            </tr>
            <%for (Actividad actividad: actividades) { %>
            <tr>
                <td><%= actividad.getIdentificador() %></td>
                <td><%= actividad.getNombre() %></td>
                <td><%=actividad.getNivelEducativo().toString() %></td>
                <td><%=actividad.getHorario() %></td>
                <td><%=actividad.getMaxParticipantes() %></td>
                <td><%=actividad.getMonitoresNecesarios() %></td>
                <td><input type="radio" name="actividad" value=<%=actividad.getIdentificador()%>></td>

            </tr>
            <% } %>
        </table>
    </div>
    <br>
    <div class="form-outline mb-4">
        <label for="monitores-table">Monitores:</label>
        <table id="monitores-table">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Fecha nacimiento</th>
                <th>Atención especial</th>
                <th>Selección</th>

            </tr>
            <% for (Monitor monitor: monitores){ %>
            <tr>
                <td><%=monitor.getIdentificador()%></td>
                <td><%=monitor.getNombre()%></td>
                <td><%=monitor.getApellidos()%></td>
                <td><%=monitor.getFechaNacimiento()%></td>
                <td><%=monitor.isEducadorEspecial() ? "Si" : "No"%></td>
                <td><input type="radio" name="monitor" value=<%=monitor.getIdentificador()%>></td>
            </tr>
            <%}%>
        </table>
    </div>
    <div class="centrar">
        <button type="submit" class="btn btn-confirmar">Asociar</button>
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-danger btn-block mb-4">Cancelar</a>
    </div>
</form>
</body>
</html>
