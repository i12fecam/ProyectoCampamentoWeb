<%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 23/11/23
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean  id="customerBean" scope="session" class="Interface.CustomerBean"></jsp:useBean>
<jsp:setProperty property="emailUser" value="" name="customerBean"/>
<jsp:setProperty property="esAdmin" value="" name="customerBean"/>
<jsp:setProperty property="idAsistente" value="" name="customerBean"/>
<jsp:forward page="/mvc/view/loginView.jsp"></jsp:forward>
