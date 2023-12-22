<%--
  Created by IntelliJ IDEA.
  User: fatim
  Date: 21/12/2023
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/exception.jsp"%>
<%@include file="/include/checkValidUser.jsp"%>
<html>
<head>
    <title>Confirmacion</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body onload="showConfirmation()">
<h1>Confirmación de Inscripción</h1>

<form  id="confirmationForm" method="post" action = "${pageContext.request.contextPath}/Confirmacion" >
    <input type="hidden" id="campamento" name="campamento" value="<%= request.getAttribute("idCampamento") %>" readonly>
    <input type="hidden" id="idAsistente" name="idAsistente" value="<%= request.getAttribute("idAsistente") %>" readonly>
    <input type="hidden" id="horario" name="horario" value="<%= request.getAttribute("horario") %>" readonly>
    <input type="hidden" id="precio" name="precio" value="<%= request.getAttribute("precio") %>" readonly>
</form>
<script>
    function showConfirmation() {
        var precio = <%= request.getAttribute("precio") %>;
        var confirmacion = confirm("El precio es de: " + precio + "\n¿Deseas confirmar la inscripción?");

        if (confirmacion) {
            // Enviar el formulario al servidor
            document.getElementById("confirmationForm").submit();
        } else {
            // Redirigir a la página principal u otra página
            window.location.href = "${pageContext.request.contextPath}/home.jsp";
        }
    }
</script>
</body>
</html>
