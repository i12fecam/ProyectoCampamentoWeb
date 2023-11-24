<%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 19/11/23
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>

<html>
<head>
    <title>Inicio de Sesión</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body class="text-center">
<form  method="post" action="../control/loginController.jsp">
    <img class="mb-4" src="${pageContext.request.contextPath}/images/summer-camp.jpg" alt="" width="120" height="120">
    <h1 class="h3 mb-3 font-weight-normal">Login </h1>
    <!-- Email -->
    <div class="form-outline mb-4">
        <input type="email" id="email" placeholder="Email" class="form-control" />
        <label for="email"></label>
    </div>

    <!-- Password -->
    <div class="form-outline mb-4">
        <input type="password" id="password" placeholder="Password" class="form-control" />
        <label for="password"></label>
    </div>

    </br>

    <!-- Submit button -->
    <button type="submit" class="btn btn-primary btn-block mb-4">Iniciar Sesion</button>

    <!-- Register button -->
    <div class="text-center">
        <p>¿No estas registrado? <a href="../control/registerController.jsp">Registrarse</a></p>
    </div>
</form>
</body>
</html>
