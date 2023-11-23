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
<body>
<div class="login_box">
    <div>
    <form method="post" action="../control/loginController.jsp">

        <label for="email">Email: </label>
        <input type="text" name="email" value="admin@email.com"><br>
        <label for="password">Password: </label>
        <input type="text" name="password" value="1234"><br>
        <br/>
        <input type="submit" value="Submit">
        <a href="../control/registerController.jsp">Registrarse</a>
    </form>
    </div>
</div>
</body>
</html>
