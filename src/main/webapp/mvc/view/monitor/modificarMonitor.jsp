<%@ page import="Business.GestorCampamentos" %>
<%@ page import="Data.DTO.Monitor" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalDate" %>
<%--
  Created by IntelliJ IDEA.
  User: Quinte01
  Date: 15/12/2023
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/exception.jsp"%>
<%@include file="/include/checkValidAdmin.jsp"%>
<html>
<head>
    <title>Alta Monitor</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body class="text-center">

<%
    String id= request.getParameter("id");
    GestorCampamentos gestor = new GestorCampamentos();
    ArrayList<Monitor> monitores = gestor.listarMonitores();
    String nombre = "";
    String apellidos = "";
    LocalDate fecha = null;
    String especial = "";
    for (Monitor monitor: monitores) {
        String id2= String.valueOf( monitor.getIdentificador());
        if(id2.equals(id)) {
            nombre = monitor.getNombre();
            apellidos = monitor.getApellidos();
            fecha = monitor.getFechaNacimiento();
            especial= monitor.isEducadorEspecial() ? "Si" : "No";
        }}
%>

<form  method="post" action="${pageContext.request.contextPath}/ModificarMonitor">
    <img class="mb-4" src="${pageContext.request.contextPath}/images/usuario.png" alt="" width="70" height="70">
    <h1 class="h3 mb-3 font-weight-normal">Actualizar Monitor</h1>
    <br>
    <hr>
    <br>
    <!-- ID Asistente (hidden input) -->
    <input type="hidden" name="idMonitor" value="<%= id %>">

    <!-- Nombre -->
    <div class="form-outline mb-4">
        <label for="nombre"  >Nombre:</label>
        <input type="text" required name="nombre" id="nombre" placeholder="Nombre" value="<%=nombre%>" class="form-control" />
    </div>
    <br>
    <br>
    <!-- Apellidos -->
    <div class="form-outline mb-4">
        <label for="apellidos" >Apellidos:</label>
        <input type="text" required name="apellidos" id="apellidos" placeholder="Apellidos" value="<%=apellidos%>" class="form-control" />
    </div>
    <br>
    <br>
    <!-- Fecha de nacimiento-->
    <div class="form-outline mb-4">
        <label for="fecha_nacimiento">Fecha de nacimiento:</label>
        <input type="date" name="fecha_nacimiento" required id="fecha_nacimiento" placeholder="Fecha Nacimiento" value="<%=fecha%>" class="form-control" />
    </div>
    <br>
    <br>
    <!-- Atención especial -->
    <div class="form-outline mb-4">
        <label for="especial">¿Requiere atención especial?</label>
        <select name="especial" id="especial" class="form-control" >
            <option value="true"  <%= especial.equals("Si") ? "selected" : "" %>>Sí</option>
            <option value="false" <%= especial.equals("No") ? "selected" : "" %>>No</option>
        </select>
    </div>
    <br>
    <br>
    <!-- Submit button -->
    <button type="submit" class="btn btn-primary btn-block mb-4">Actualizar</button>
    <a href="../../../home.jsp" class="btn btn-danger btn-block mb-4">Cancelar</a>
</form>
</body>
</html>