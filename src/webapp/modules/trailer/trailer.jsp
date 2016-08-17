<%@page contentType="text/html" %>
<jsp:useBean id="trl" class="beans.Trailer">
 <jsp:setProperty name="trl" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
	trl.buildForm();
%>
<jsp:getProperty name="trl" property="content"/>