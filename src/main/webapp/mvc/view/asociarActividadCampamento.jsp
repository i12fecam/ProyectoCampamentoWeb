<%@ page import="Business.GestorCampamentos" %>
<%@ page import="Data.DTO.Actividad" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Data.DTO.Campamento" %><%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 9/12/23
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Asociar actividad a campamento</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tables.css">
</head>
<body class = "text-center">
<%
    GestorCampamentos gestor = new GestorCampamentos();
    ArrayList<Actividad> actividades = gestor.listarActividades();
    ArrayList<Campamento> campamentos = gestor.listarCampamentos();
%>

  <table>
    <tr>
      <th>ID</th>
      <th>Nombre</th>
      <th>Nivel Educativo</th>
      <th>Horario</th>
      <th>Máximo de asistentes</th>
      <th>Máximo de monitores</th>
    </tr>
    <%for (Actividad actividad: actividades) { %>
    <tr>
      <td><%= actividad.getIdentificador() %></td>
      <td><%=actividad.getNivelEducativo().toString() %></td>
      <td><%=actividad.getHorario() %></td>
      <td><%=actividad.getMaxParticipantes() %></td>
      <td><%=actividad.getMonitoresNecesarios() %></td>
    </tr>
    <% } %>
  </table>
  <br>
  <table>
    <tr>
      <th>ID</th>
      <th>Fecha de inicio</th>
      <th>Fecha final</th>
      <th>Nivel educativo</th>
      <th>Num asistentes/max</th>
      <th>Selección</th>
    </tr>
  </table>
</body>
</html>
