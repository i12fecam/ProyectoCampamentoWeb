<%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 30/11/23
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Añadir una nueva actividad</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">

</head>
<body class="text-center">
<form method="post" action="${pageContext.request.contextPath}/AltaActividad">
    <h1 class="h3 mb-3 font-weight-normal">Añadir nueva actividad</h1>

    <!-- Nombre -->
    <div class="form-outline mb-4">
        <label for="name">Nombre:</label>
        <input type="text" name="name" id="name" class="form-control" required/>
    </div>

    <br>

    <!-- Nivel educativo-->
    <div class="form-outline mb-4">
        <span>Nivel Educativo:</span>
        <div class="radio-options ">
            <input type="radio" id="nivel_educativo" name="nivel_educativo" value="Infantil" >
            <label for="nivel_educativo">Infantil</label><br>
            <input type="radio" id="nivel_educativo2" name="nivel_educativo" value="Juvenil">
            <label for="nivel_educativo2">Juvenil</label><br>
            <input type="radio" id="nivel_educativo3" name="nivel_educativo" value="Adolescente">
            <label for="nivel_educativo3">Adolescente</label><br>
        </div>
    </div>

    <br>

    <!-- Horario-->
    <div class="form-outline mb-4">
        <span>Horario:</span>
        <div class="radio-options">
            <input type="radio" id="horario_1" name="horario" value="Parcial">
            <label for="horario_1">Parcial</label><br>
            <input type="radio" id="horario_2" name="horario" value="Completa">
            <label for="horario_2">Completa</label><br>

        </div>
    </div>

    <br>

    <!--max Particpantes -->
    <div class="form-outline mb-4">
        <label for="maxParticipantes">Número máximo de participantes:</label>
        <input type="number" name="maxParticipantes" id="maxParticipantes" class="form-control" required/>
    </div>


    <!--monitores necesarios -->
    <div class="form-outline mb-4">
        <label for="monitoresNecesarios">Número de monitores necesarios:</label>
        <input type="number" name="monitoresNecesarios" id="monitoresNecesarios" class="form-control" required/>
    </div>

    <br>

    <!-- Submit button -->
    <button type="submit" class="btn btn-primary btn-block mb-4">Crear</button>
    <a href="../../home.jsp" class="btn btn-danger btn-block mb-4">Cancelar</a>

</form>

</body>
</html>
