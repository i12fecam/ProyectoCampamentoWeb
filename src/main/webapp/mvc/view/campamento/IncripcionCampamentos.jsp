<%@ page import="java.time.LocalDate" %>
<%--
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body class = "text-center">

<form method="post" action="${pageContext.request.contextPath}/Inscripcion">
<%
    LocalDate fechaActual = LocalDate.now();
%>
    <h1 class="h3 mb-3 font-weight-normal">Formulario de inscripcion</h1>
    <!--Id del campamento seleccionado-->
    <div class="form-outline mb-4">
        <label for="campamento">Campamento Seleccionado:</label>
         <input type="number" id="campamento" name="campamento" value="<%= request.getParameter("campamentoId") %>" readonly>
    </div>

    <!--Id del participante -->
    //No se me ocurre como poner el id del participante

    <!--Fecha de inscripcion-->
    <div>
        <label for="fecha">Fecha de Inscripcion:</label>
        <input type="date" id="fecha" name="fecha"  value ="<%=fechaActual%>" readonly>
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
    <button type="submit" class="btn btn-primary btn-block mb-4">Enviar</button>
    <a href="../../../home.jsp" class="btn btn-danger btn-block mb-4">Cancelar</a>
</form>
</body>
</html>

//Cambio para que cuando le den a cancelar vuelvan a los campamentos?