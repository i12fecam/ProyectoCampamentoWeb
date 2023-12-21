<%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 19/11/23
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" errorPage="/exception.jsp"%>

<html>
<head>
    <title>Inicio de Sesión</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body class="text-center">
<form  method="post" action="${pageContext.request.contextPath}/mvc/control/loginController.jsp">
    <img class="mb-4" src="${pageContext.request.contextPath}/images/summer-camp.jpg" alt="" width="120" height="120">
    <h1 class="h3 mb-3 font-weight-normal">Login </h1>

    <!-- Email -->
    <div class="form-outline mb-4">
        <input type="email" name="email" id="email" required placeholder="Email" class="form-control" />
        <label for="email"></label>
    </div>

    <br>

    <!-- Password -->
    <div class="form-outline mb-4">
        <input type="password" name="password" required id="password" placeholder="Password" class="form-control" />
        <label for="password"></label>
    </div>

    <br>

    <!-- Submit button -->
    <button type="submit" class="btn btn-primary">Iniciar Sesion</button>

    <!-- Register button -->
    <div class="text-center">
        <p>¿No estas registrado? <a href="${pageContext.request.contextPath}/mvc/control/registerController.jsp">Registrarse</a></p>
    </div>

    <!-- Mensaje usuario no existe -->
    <%
        String mensajeNextPage = request.getParameter("message");
        if (mensajeNextPage != null) {
    %>
    <div>
        <p class="mensaje-error"><%= mensajeNextPage %></p>
    </div>
    <%
        }
    %>
</form>
</body>
</html>
