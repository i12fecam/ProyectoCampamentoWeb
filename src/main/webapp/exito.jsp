<%--
  Created by IntelliJ IDEA.
  User: 34644
  Date: 12/12/2023
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Mensaje de Éxito</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/exito.css">
</head>
<body>
<div style="text-align: center; margin-top: 50px;">
  <h1 class="">¡Éxito!</h1>
  <p>El proceso se ha completado con éxito.</p>
  <button onclick="redirigirASitio()">Ir a otro sitio</button>
</div>

<script>
  function redirigirASitio() {
    // Cambia la URL a la que quieres redirigir
    window.location.href = 'home.jsp';
  }
</script>
</body>
</html>
