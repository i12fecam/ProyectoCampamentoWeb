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
    <title>Registrarse</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body class="text-center">
<form  method="post" action="../control/registerController.jsp">
    <h1 class="h3 mb-3 font-weight-normal">Registro</h1>

    <!-- Email -->
    <div class="form-outline mb-4">
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" placeholder="Email" class="form-control" />

    </div>

    <br>

    <!-- Password -->
    <div class="form-outline mb-4">
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" placeholder="Password" class="form-control" />
    </div>

    <br>

    <!-- Fecha Nacimiento -->

    <label for="fecha_nacimiento">Fecha Nacimiento:</label>
    <input type="date" name="fecha_nacimiento" id="fecha_nacimiento" placeholder="Fecha Nacimiento" class="form-control" />

    <br>

    <!-- ¿Especial? -->

    <input type="checkbox" id="especial" name="especial" value="1">
    <label for="especial">¿Necesidades especiales?</label>

    <br>
    <br>

    <!-- Submit button -->
    <button type="submit" class="btn btn-primary btn-block mb-4">Registrarse</button>
    <a href="../../home.jsp" class="btn btn-danger btn-block mb-4">Cancelar</a>

</form>








<!--
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
-->
</body>
</html>

