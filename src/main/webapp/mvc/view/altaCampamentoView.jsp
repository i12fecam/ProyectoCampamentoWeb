<%--
  Created by IntelliJ IDEA.
  User: Quinte01
  Date: 29/11/2023
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<head>
    <title>Crear Campamento</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body class="text-center">
<form  method="get" action="AltaCampamento">
    <h1 class="h3 mb-3 font-weight-normal">Crear campamento</h1>

    <!-- Fecha Inicio -->
    <div class="form-outline mb-4">
        <label for="fecha_inicio">Fecha Inicio:</label>
        <input type="date" name="fecha_inicio" id="fecha_inicio" class="form-control" />
    </div>

    <br>

    <!-- Fecha Fin -->
    <div class="form-outline mb-4">
        <label for="fecha_fin">Fecha Fin:</label>
        <input type="date" name="fecha_fin" id="fecha_fin" class="form-control" />
    </div>

    <br>

    <!-- Nivel Educativo -->
    <div class="form-outline mb-4">
        <span>Nivel Educativo:</span>
        <div class="radio-options">
        <input type="radio" id="nivel_educativo" name="nivel_educativo" value="Infantil">
        <label for="nivel_educativo">Infantil</label><br>
        <input type="radio" id="nivel_educativo2" name="nivel_educativo" value="Juvenil">
        <label for="nivel_educativo2">Juvenil</label><br>
        <input type="radio" id="nivel_educativo3" name="nivel_educativo" value="Adolescente">
        <label for="nivel_educativo3">Adolescente</label><br>
        </div>
    </div>

    <br>

    <!-- Nº max asistentes -->
    <div class="form-outline mb-4">
        <label for="max_asistentes">Nº de Asistentes:</label>
        <input type="number" name="max_asistentes" id="max_asistentes" class="form-control" />
    </div>
    <br>
    <br>

    <!-- Submit button -->
    <button type="submit" class="btn btn-primary btn-block mb-4">Crear</button>
    <a href="../../home.jsp" class="btn btn-danger btn-block mb-4">Cancelar</a>


</form>
</body>
</html>


