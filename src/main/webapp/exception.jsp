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
    <title>Ha ocurrido un error:</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/exito.css">
</head>
<body class="centrar">

    <h1>ha ocurrido un error:<%=exception.getMessage()%></h1>
    <button class="btn-confirmar btn"><a href="${pageContext.request.contextPath}/home.jsp">Volver al home</a></button>
</body>

</html>
