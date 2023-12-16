<%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 13/12/23
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultar campamentos disponibles</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<form  method="post" action="${pageContext.request.contextPath}/monitor/AltaMonitor">
    <div class="image-container">
        <img class="mb-4" src="${pageContext.request.contextPath}/images/campamentosDisponibles.jpg" alt="Imagen decorativa campamento" width="120" height="120">
    </div>
    <h1 class="h3 mb-3 font-weight-normal">Formulario para buscar campamentos disponibles</h1>
    <br>
    <hr>
    <br>
    <!-- Fecha de nacimiento-->
    <div class="form-outline mb-4">
        <label for="fecha_buscar">Selecciona la fecha a partir de la cual buscar campamentos disponibles:</label>
        <input type="date" name="fecha_buscar" required id="fecha_buscar" placeholder="Fecha" class="form-control" />
    </div>
    <br />
    <div>
        <label for="fecha_buscar_stop">Selecciona la fecha hasta la cual quieres que se muestren los campamentos disponibles:</label>
        <input type="date" name="fecha_buscar_stop" required id="fecha_buscar_stop" placeholder="Fecha" class="form-control" />

    </div>
    <br />
    <button type="submit" class="btn btn-primary btn-block mb-4">Buscar</button>
</form>

</body>
</html>
