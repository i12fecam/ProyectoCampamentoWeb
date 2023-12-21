<%--
  Created by IntelliJ IDEA.
  User: Quinte01
  Date: 12/12/2023
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%
        String email = customerBean.getEmailUser();
        String nombre = email.split("@")[0];
    %>

    <div class="mensaje-bienvenida">
    <h2>Bienvenido <%=nombre%> - <span id="fechaActual"></span></h2>
    </div>
    <div class="topnav" id="myTopnav">
    <a href="${pageContext.request.contextPath}/mvc/view/campamento/consultarCampamentosDisponibles.jsp">Campamentos Disponibles</a>
    <a href="${pageContext.request.contextPath}/mvc/view/campamento/consultarCampamentosAdscritos.jsp">Campamentos Inscritos</a>
    <a href="${pageContext.request.contextPath}/mvc/control/disconnectController.jsp" style="float:right">Desconectarse</a>
    <a href="${pageContext.request.contextPath}/mvc/view/cambiarDatosUsuario.jsp" style="float:right">Cambiar datos</a>
</div>
    <script>
        // Obtener la fecha actual
        var fecha = new Date();

        // Obtener día, mes y año
        var dia = fecha.getDate();
        var mes = fecha.getMonth() + 1;
        var year = fecha.getFullYear();

        var fechaFormateada = dia + '/' + mes + '/' + year;

        document.getElementById('fechaActual').innerHTML = fechaFormateada;
    </script>

