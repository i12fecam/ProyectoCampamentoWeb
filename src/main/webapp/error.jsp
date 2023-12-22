<%--
  Created by IntelliJ IDEA.
  User: Quinte01
  Date: 13/12/2023
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/exception.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mensaje de Error</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/error.css">
</head>
<body>
<div style="text-align: center; margin-top: 50px;">
    <h1 class="">¡Error!</h1>
    <p>Se ha producido un error.</p>
    <p>${requestScope.error_message}</p>
    <button><a href="home.jsp">Volver al home</a> </button>
</div>


</body>
</html>