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
  <form method="post" action="${pageContext.request.contextPath}/AsociarActividadCampamento">
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
              <td><input type="checkbox" name="actividad" value=<%=actividad.getIdentificador()%>></td>

            </tr>
            <% } %>
          </table>
      </div>
      <br>

      <div class="form-outline mb-4">
          <label for="campamento-table">Campamentos:</label>
          <table id="campamento-table">
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
              <td><input type="checkbox" name="campamento" value=<%=campamento.getIdCampamento()%>></td>
            </tr>
            <%}%>
          </table>
      </div>

      <button type="submit" class="btn btn-primary btn-block mb-4">Asociar</button>
      <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-danger btn-block mb-4">Cancelar</a>
  </form>
</body>
</html>
