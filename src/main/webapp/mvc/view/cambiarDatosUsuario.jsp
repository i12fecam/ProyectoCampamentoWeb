<%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 16/12/23
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean  id="customerBean" scope="session" class="Interface.CustomerBean"></jsp:useBean>
<html>
<head>
    <title>Cambio de datos personales</title>
</head>
<body>
<form action="/CambiarDatosUsuarios" >
     <label for="oldpassword">Introduzca la antigua contraseña</label>
    <input type="text" id="oldpassword" value="">
    <label for="newpassword"> Introduzca la nueva contraseña</label>
    <input type="text" id="newpassword" value="">
    <label for="newpassword2"> Introduzca de nuevo la contraseña</label>
    <input type="text" id="newpassword2" value="">
</form>
</body>
</html>
