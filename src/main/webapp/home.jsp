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
    <%@include file="include/menuUser.jsp"%>
<% } %>


<div class="mainbody">

    <div class="main">
        <h1> Sobre nosotros</h1>
        <p>Proyecto Campamento Web es un programa para la gestión de campamentos, incluyendo control de monitores, actividades e inscripciones</p>
        <p>El programa ha sido realizado por el grupo 5 que contiene a:</p>
        <ul>
            <li>Abigaíl Fernández Cabrera</li>
            <li>Noelia Ruiz Morón</li>
            <li>Antonio Javier Quintero García</li>
            <li>Fátima Caballero Núñez</li>
        </ul>
    </div>
</div>

</body>
</html>