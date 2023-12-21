<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/exception.jsp"%>
<jsp:useBean  id="customerBean" scope="session" class="Interface.CustomerBean"></jsp:useBean>
<% if (customerBean == null || customerBean.getEmailUser().equals("")){ %>
    <jsp:forward page="/mvc/control/loginController.jsp"></jsp:forward>
<% }else{ %>
    <jsp:forward page="/home.jsp"></jsp:forward>
<% } %>