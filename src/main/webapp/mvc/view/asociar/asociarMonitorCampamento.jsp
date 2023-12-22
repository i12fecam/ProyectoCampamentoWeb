<%@ page import="Business.GestorCampamentos" %>
<%@ page import="Data.DTO.Monitor" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Data.DTO.Campamento" %>
<%--
  Created by IntelliJ IDEA.
  User: Quinte01
  Date: 15/12/2023
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/exception.jsp"%>
<%@include file="/include/checkValidAdmin.jsp"%>
<html>
<head>
    <title>Asociar monitor a campamento</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tables.css">
</head>
<body>
<%
    GestorCampamentos gestor = new GestorCampamentos();
    ArrayList<Monitor> monitores = gestor.listarMonitores();
    ArrayList<Campamento> campamentos = gestor.listarCampamentos();
%>
<h1 class="centrar">Asociar Monitor a Campamento</h1>
<form method="post" action="${pageContext.request.contextPath}/AsociarMonitorCampamento">
    <div class="form-outline mb-4">
        <label for="monitor-table"><h2>Monitores:</h2></label>
            <table id="monitor-table" class="table">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Fecha Nacimiento</th>
                    <th>Atención especial</th>
                    <th>Selección</th>
                </tr>
                <%for (Monitor monitor: monitores) { %>
                <tr>
                    <td><%=monitor.getIdentificador() %></td>
                    <td><%=monitor.getNombre() %></td>
                    <td><%=monitor.getApellidos() %></td>
                    <td><%=monitor.getFechaNacimiento() %></td>
                    <td><%=monitor.isEducadorEspecial() ? "Si" : "No" %></td>
                    <td><input type="radio" name="id_monitor" value=<%=monitor.getIdentificador()%>>
                    </td>
                </tr>
                <% } %>
            </table>
    </div>

    <div class="form-outline mb-4">
        <label for="campamento-table"><h2>Campamentos:</h2></label>
        <table id="campamento-table"  class="table">
            <tr>
                <th>ID</th>
                <th>Fecha de inicio</th>
                <th>Fecha final</th>
                <th>Nivel educativo</th>
                <th>Num asistentes/max</th>
                <th>Selección</th>

            </tr>
            <% for (Campamento campamento: campamentos){ %>
            <tr>
                <td><%=campamento.getIdCampamento()%></td>
                <td><%=campamento.getFechaInicio()%></td>
                <td><%=campamento.getFechaFinal()%></td>
                <td><%=campamento.getNivelEducativo().toString()%></td>
                <td><%=campamento.getMaxAsistentes()%></td>
                <td><input type="radio" name="campamento" value=<%=campamento.getIdCampamento()%>></td>
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
