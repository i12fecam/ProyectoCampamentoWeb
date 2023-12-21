  <%@ page import="java.util.ArrayList" %>
  <%@ page import="Data.DTO.Campamento" %>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
    <title>Campamentos Disponibles</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tables.css">
  </head>
  <%
    // Obtener la lista de campamentos filtrados del objeto request
    ArrayList<Campamento> campamentosFiltrados = (ArrayList<Campamento>) request.getAttribute("campamentosFiltrados");
  %>
  <body>
    <table class="table">
    <tr>
      <th>ID</th>
      <th>Fecha Inicio</th>
      <th>Fecha Final</th>
      <th>Nivel Educativo</th>
      <th>Nº Asistentes</th>
      <th></th>


    </tr>
    <% if (campamentosFiltrados != null && !campamentosFiltrados.isEmpty()) { %>
    <% for (Campamento campamento : campamentosFiltrados) { %>
    <tr>
      <td><%= campamento.getIdCampamento() %></td>
      <td><%= campamento.getFechaInicio() %></td>
      <td><%= campamento.getFechaFinal() %></td>
      <td><%= campamento.getNivelEducativo().toString() %></td>
      <td><%= campamento.getMaxAsistentes() %></td>
      <td>
        <div class="centrar">
          <form action="${pageContext.request.contextPath}/mvc/view/campamento/InscripcionCampamentos.jsp" method="GET">
            <input type="hidden" name="idCampamento" value="<%=campamento.getIdCampamento()%>">
            <button type="submit" class="btn btn-confirmar ">Inscribirse</button>
          </form>
        </div>
      </td>
    </tr>
    <% } %>
    <% } else { %>
    <tr>
      <td colspan="6">No hay campamentos disponibles en las fechas seleccionadas.</td>
    </tr>
    <% } %>
  </table>
    <div class="centrar">
      <a href="http://localhost:8080/ProyectoCampamentoWeb_war/home.jsp" class="btn btn-danger btn-block mb-4">Volver home</a>
    </div>
  </body>
  </html>
