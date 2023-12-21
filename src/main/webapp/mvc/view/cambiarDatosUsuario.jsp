<%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 16/12/23
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/exception.jsp"%>
<jsp:useBean  id="customerBean" scope="session" class="Interface.CustomerBean"></jsp:useBean>
<html>
<head>
    <title>Cambio de datos personales</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body class="text-center">
<form method="post" action="${pageContext.request.contextPath}/CambiarDatosUsuarios" >

    <h1 class="h3 mb-3 font-weight-normal">Cambios de datos</h1>

    <br>

    <div class="form-outline mb-4">
        <label for="oldPassword">Introduzca la antigua contraseña:</label>
        <input type="password" id="oldPassword" name="oldPassword" value="" class="form-control" required>
    </div>

    <br>

    <div class="form-outline mb-4">
        <label for="newPassword"> Introduzca la nueva contraseña:</label>
        <input type="password" id="newPassword" name="newPassword" value="" class="form-control" required>
    </div>

    <br>

    <div class="form-outline mb-4">
        <label for="newPassword2"> Introduzca de nuevo la contraseña:</label>
        <input type="password" id="newPassword2" name="newPassword2" value="" class="form-control" required>
    </div>

    <br>

    <button type="submit" class="btn btn-primary">Cambiar contraseña:</button>
</form>
</body>
</html>
