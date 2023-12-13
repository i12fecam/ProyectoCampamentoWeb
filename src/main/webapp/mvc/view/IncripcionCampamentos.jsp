<%@ page import="Business.GestorCampamentos" %>
<%@ page import="Data.DTO.Campamento" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: fatim
  Date: 11/12/2023
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inscripcion a un campamento</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tables.css">
</head>
<body class = "text-center">
<%
    GestorCampamentos gestor = new GestorCampamentos();
    ArrayList<Campamento> listaCampamentos = gestor.listarCampamentos();

    Boolean campamentosMostrados = (Boolean) session.getAttribute("campamentosMostrados");

    if (campamentosMostrados == null || !campamentosMostrados) {
%>
<table class="table">
    <h2>Campamentos Disponibles:</h2>
    <tr>
        <th>ID</th>
        <th>Fecha Incio</th>
        <th>Fecha Fin</th>
        <th>Nivel Educativo</th>
        <th>Maximo de asistentes</th>
        <th></th>
    </tr>
    <%for (Campamento campamentos: listaCampamentos) { %>
    <tr>
        <td><%= campamentos.getIdCampamento() %></td>
        <td><%=campamentos.getFechaInicio()%></td>
        <td><%=campamentos.getFechaFinal()%></td>
        <td><%=campamentos.getNivelEducativo().toString() %></td>
        <td><%=campamentos.getMaxAsistentes() %></td>
        <td>  <form method="get" action="${pageContext.request.contextPath}/Inscripcion">
            <input type="hidden" name="campamentoId" value="<%= campamentos.getIdCampamento() %>">
            <button type="submit">Inscribirse</button>
        </form>
        </td>
    </tr>
    <% } %>
</table>
<%
        session.setAttribute("campamentosMostrados", true);
    }
%>
<form method="post" action="${pageContext.request.contextPath}/Inscripcion">

    <h1 class="h3 mb-3 font-weight-normal">Formulario de inscripcion</h1>
    <!--Id del campamento seleccionado-->
    <div class="form-outline mb-4">
        <label for="campamento">Campamento Seleccionado:</label>
         <input type="text" id="campamento" name="campamento" value="<%= request.getParameter("campamentoId") %>" readonly>
    </div>

    <!--Id del participante -->
    //No se me ocurre como poner el id del participante
    <!--Fecha de inscripcion-->
    <div>
        <label for="fecha">Fecha de Inscripcion:</label>
        <input type="date" id="fecha" name="fecha" required>
    </div>

    <!--Horario-->
    <div class="form-outline mb-4">
        <span>Horario:</span>
        <div class="radio-options">
            <input type="radio" id="horario_1" name="horario" value="Parcial">
            <label for="horario_1">Parcial</label><br>
            <input type="radio" id="horario_2" name="horario" value="Completa">
            <label for="horario_2">Completa</label><br>
        </div>
    </div>
    <button type="submit">Enviar</button>
    <a href="../../home.jsp" class="btn btn-danger btn-block mb-4">Cancelar</a>
</form>
</body>
</html>
