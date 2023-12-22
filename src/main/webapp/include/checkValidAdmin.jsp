<%--
  Created by IntelliJ IDEA.
  User: abi
  Date: 13/12/23
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>

<jsp:useBean  id="customerBean" scope="session" class="Interface.CustomerBean"></jsp:useBean>
<% if(customerBean == null || customerBean.getEmailUser().equals("") || !customerBean.isEsAdmin() ){%>
<jsp:forward page="/index.jsp"></jsp:forward>
<% } %>