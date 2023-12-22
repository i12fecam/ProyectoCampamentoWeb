<%@ page import="Business.GestorCampamentos" %>
<%@ page import="java.util.List" %>
<%@ page import="Data.DTO.Campamento" %><%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 13/12/23
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/exception.jsp"%>
<%@include file="/include/checkValidUser.jsp"%>
<html>
<head>
    <title>Campamentos Adscritos</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tables.css">
</head>
<body>

<%
    GestorCampamentos gestorCampamentos = new GestorCampamentos();
    List<Campamento> campamentos = gestorCampamentos.listarCampamentosInscritos(customerBean.getIdAsistente());
%>
<table class="table">
    <tr>
        <th>ID</th>
        <th>Fecha Inicio</th>
        <th>Fecha Final</th>
        <th></th>
        <!--
        <th>Nivel Educativo</th>
        <th>Nº Asistentes ma</th>
        <th>Monitor Responsable</th>
        th>Monitor Especial</th>
        -->

    </tr>
    <%for (Campamento campamento: campamentos) { %>
    <tr>
        <td><%=campamento.getIdCampamento() %></td>
        <td><%=campamento.getFechaInicio() %></td>
        <td><%=campamento.getFechaFinal() %></td>
        <!--
        <td><%=campamento.getNivelEducativo().toString() %></td>
        <td><%=campamento.getMaxAsistentes() %></td>
        -->
        <td>
            <div class="centrar">
                <form action="${pageContext.request.contextPath}/CancelarCampamento" method="POST">
                    <input type="hidden" name="id_campamento" value="<%=campamento.getIdCampamento()%>">
                    <input type="hidden" name="fk_asistente" value="<%=customerBean.getIdAsistente()%>">
                    <button type="submit" class="btn btn-danger ">Cancelar</button>
                </form>
            </div>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
