<%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 23/11/23
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/exception.jsp"%>
<html>


<head>
    <title>Registrarse</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body class="text-center">
<form  method="post" action="../control/registerController.jsp">
    <h1 class="h3 mb-3 font-weight-normal">Registro</h1>

    <!-- Nombre -->
    <div class="form-outline mb-4">
    <label for="name">Nombre: </label>
    <input type="text" name="name" id="name" placeholder="Nombre" class="form-control" required><br>
    </div>

    <!-- Apellidos -->
    <div class="form-outline mb-4">
    <label for="surname">Apellidos: </label>
    <input type="text" name="surname" id="surname" placeholder="Apellidos" class="form-control" required><br>
    </div>

    <!-- Email -->
    <div class="form-outline mb-4">
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" placeholder="Email" class="form-control" required />

    </div>

    <br>

    <!-- Password -->
    <div class="form-outline mb-4">
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" placeholder="Password" class="form-control" required />
    </div>

    <br>

    <!-- Fecha Nacimiento -->

    <label for="fecha_nacimiento">Fecha Nacimiento:</label>
    <input type="date" name="fecha_nacimiento" id="fecha_nacimiento" placeholder="Fecha Nacimiento" class="form-control" required />

    <br>

    <!-- ¿Especial? -->

    <input type="checkbox" id="especial" name="especial" value="1">
    <label for="especial">¿Necesidades especiales?</label>

    <br>
    <br>

    <!-- Submit button -->
    <button type="submit" class="btn btn-primary btn-block mb-4">Registrarse</button>
    <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-danger btn-block mb-4">Cancelar</a>

</form>
</body>
</html>

