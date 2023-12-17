<%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 23/11/23
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Proyecto Campamento</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css">
</head>

<body>

<jsp:useBean  id="customerBean" scope="session" class="Interface.CustomerBean"></jsp:useBean>
<%
    if(customerBean == null){
%>
    <jsp:forward page="/mvc/control/loginController.jsp"></jsp:forward>
<%
    }else if(customerBean.isEsAdmin()){
%>
    <%@include file="include/menu.jsp"%>
<%
    }else{
%>
    <%@include file="include/menu-asistente.jsp"%>
<% } %>

<img src="${pageContext.request.contextPath}/images/portada-home.png" alt="Campamento de verano">

</body>
<footer>
    <div class="centrar">
        <h2>Autores:</h2>
<pre>Abigaíl Fernández Cabrera
Noelia Ruiz Morón
Antonio Javier Quintero García
Fátima Caballero Núñez</pre>
        <p>&copy; 2023. Todos los derechos reservados.</p>
    </div>
</footer>
</html>