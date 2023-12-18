<%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 19/11/23
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <div class="topnav" id="myTopnav">
            <div class="dropdown">
                <button class="dropbtn">Asistentes
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/mvc/view/asistente/altaAsistente.jsp">Añadir asistente</a>
                    <a href="${pageContext.request.contextPath}/mvc/view/asistente/visualizarAsistentes.jsp">Visualizar asistentes</a>
                </div>
            </div>

            <div class="dropdown">
                <button class="dropbtn">Campamentos
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/mvc/view/campamento/altaCampamentoView.jsp">Crear nuevo campamento</a>
                    <a href="${pageContext.request.contextPath}/mvc/view/campamento/visualizarCampamentos.jsp">Visualizar campamentos</a>
                </div>
            </div>

            <div class="dropdown">
                <button class="dropbtn">Monitores
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/mvc/view/monitor/altaMonitor.jsp">Añadir monitor</a>
                    <a href="${pageContext.request.contextPath}/mvc/view/monitor/visualizarMonitor.jsp">Visualizar monitores</a>
                </div>
            </div>

            <div class="dropdown">
                <button class="dropbtn">Actividades
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/mvc/view/actividad/AltaActividad.jsp">Crear actividad</a>
                    <a href="${pageContext.request.contextPath}/mvc/view/actividad/visualizarActividades.jsp">Visualizar actividades</a>
                </div>
            </div>

            <div class="dropdown">
                <button class="dropbtn">Asociar
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/mvc/view/asociar/asociarMonitorActividad.jsp">Asociar monitores a actividades</a>
                    <a href="${pageContext.request.contextPath}/mvc/view/asociar/asociarMonitorCampamento.jsp">Asociar monitor a campamento</a>
                    <a href="${pageContext.request.contextPath}/mvc/view/asociar/asociarActividadCampamento.jsp">Asociar actividad a campamento</a>
                </div>
            </div>

            <a href="${pageContext.request.contextPath}/mvc/control/disconnectController.jsp" style="float:right">Desconectarse</a>
            <a href="${pageContext.request.contextPath}/mvc/view/cambiarDatosUsuario.jsp" style="float:right">Cambiar datos</a>

        </div>