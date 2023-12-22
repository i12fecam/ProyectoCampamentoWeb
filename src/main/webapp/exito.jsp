<%--
  Created by IntelliJ IDEA.
  User: 34644
  Date: 12/12/2023
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/exception.jsp"%>
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
  <p>${requestScope.success_message}</p>
  <button><a href="${pageContext.request.contextPath}/home.jsp">Volver al home</a> </button>
</div>

</body>
</html>
