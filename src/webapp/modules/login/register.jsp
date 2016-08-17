<%@page contentType="text/html" %>
<jsp:useBean id="User" class="beans.Login">
<jsp:setProperty name="User" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
	
	User.buildFormUser();
%>
<jsp:getProperty name="User" property="content"/>
