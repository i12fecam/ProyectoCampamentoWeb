<%--
  Created by IntelliJ IDEA.
  User: Noelia
  Date: 29/11/2023
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/exception.jsp" %>
<%@include file="/include/checkValidAdmin.jsp"%>
<html>
<head>
    <title>Alta Monitor</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body class="text-center">
<form  method="post" action="${pageContext.request.contextPath}/monitor/AltaMonitor">
    <img class="mb-4" src="${pageContext.request.contextPath}/images/usuario.png" alt="Imagen decorativa persona" width="70" height="70">
    <h1 class="h3 mb-3 font-weight-normal">Formulario para dar de alta a un monitor</h1>
    <br>
    <hr>
    <br>
    <!-- Nombre -->
    <div class="form-outline mb-4">
        <label for="nombre"  >Nombre del Monitor</label>
        <input type="text" required name="nombre" id="nombre" placeholder="Nombre" class="form-control" />
    </div>
    <br>
    <br>
    <!-- Apellidos -->
    <div class="form-outline mb-4">
        <label for="apellidos" >Apellidos del Monitor</label>
        <input type="text" required name="apellidos" id="apellidos" placeholder="Apellidos" class="form-control" />
    </div>
    <br>
    <br>
    <!-- Fecha de nacimiento-->
    <div class="form-outline mb-4">
        <label for="fecha_nacimiento"  >Fecha de nacimiento del Monitor</label>
        <input type="date" name="fecha_nacimiento" required id="fecha_nacimiento" placeholder="Fecha Nacimiento" class="form-control" />
    </div>
    <br>
    <br>
    <!-- Atención especial -->
    <div class="form-outline mb-4">
        <label for="especial">¿El Monitor es de atención especial?</label>
        <select name="especial" id="especial" class="form-control" >
            <option value="true">Sí</option>
            <option value="false">No</option>
        </select>
    </div>
    <br>
    <br>
    <!-- Submit button -->
    <button type="submit" class="btn btn-primary btn-block mb-4">Crear nuevo monitor</button>
    <a href="../../../home.jsp" class="btn btn-danger btn-block mb-4">Cancelar</a>
</form>
</body>
</html>
