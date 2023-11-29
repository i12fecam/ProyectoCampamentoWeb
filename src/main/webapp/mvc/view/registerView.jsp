<%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 23/11/23
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inicio de Sesión</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="register_box">
    <div>
        <form method="post" action="../control/registerController.jsp">

            <label for="email">Email: </label><br/>
            <input type="text" name="email" value="admin@email.com" required><br>
            <label for="password">Password: </label><br/>
            <input type="text" name="password" value="1234" required><br>
            <label for="name">Nombre: </label><br/>
            <input type="text" name="name" value="Pablo Dueñas" required><br>
            <label for="surname">Apellidos: </label><br/>
            <input type="text" name="surname" value="Dueñas Fuentes" required><br>
            <label for="fecha_nacimiento">Fecha nacimiento:</label>
            <input type="date" name="fecha_nacimiento" required><br/>
            <label for="especial">Necesidades especiales</label><br/>
            <input type="checkbox" name="especial" value="1" ><br/>
            <input type="submit" value="Submit">
            <a href="../control/registerController.jsp">Registrarse</a>
        </form>
    </div>
</div>
</body>
</html>

