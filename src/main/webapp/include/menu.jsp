<%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 19/11/23
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <link rel="stylesheet" type="text/css" href="css/menu.css">
</head>

        <div class="topnav" id="myTopnav">
            <div class="dropdown">
                <button class="dropbtn">Asistentes
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="#">Añadir asistente</a>
                    <a href="#">Modificar asistente</a>
                    <a href="#">Visualizar asistentes</a>
                </div>
            </div>

            <div class="dropdown">
                <button class="dropbtn">Campamentos
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="mvc/view/altaCampamentoView.jsp">Crear nuevo campamento</a>
                    <a href="#">Visualizar campamentos</a>
                </div>
            </div>

            <div class="dropdown">
                <button class="dropbtn">Monitores
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="#">Añadir monitor</a>
                    <a href="#">Visualizar monitores</a>
                </div>
            </div>

            <div class="dropdown">
                <button class="dropbtn">Actividades
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="AltaActividad">Crear actividad</a>
                    <a href="#">Visualizar actividades</a>
                </div>
            </div>

            <div class="dropdown">
                <button class="dropbtn">Asociar
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="#">Asociar monitores a actividades</a>
                    <a href="#">Asociar monitor responsable a un campamento</a>
                    <a href="#">Asociar monitor de atención especial a un campamento</a>
                    <a href="#">Asociar actividad a campamento</a>
                </div>
            </div>

            <a href="mvc/control/disconnectController.jsp" style="float:right">Desconectarse</a>
            <a href="http://localhost:8080/change_user_data " style="float:right">Cambiar datos</a>

        </div>
        <!--
        <ul title="Selección de asistentes">
            <li>Consultar campamentos disponibles</li>
            <li>Consultar inscripciones</li>
        </ul>

        -->