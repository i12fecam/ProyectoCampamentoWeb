<%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 21/12/23
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Ha ocurrido un error:<%=exception.getMessage()%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/exito.css">
</head>
<body class="centrar">
<script>
    function redirigirASitio() {
        // Cambia la URL a la que quieres redirigir
        window.location.href = "index.jsp";
    }
</script>
    <h1>ha ocurrido un error:</h1>
    <button onclick="redirigirASitio()" class="btn-confirmar btn">Volver al home</button>
</body>

</html>
