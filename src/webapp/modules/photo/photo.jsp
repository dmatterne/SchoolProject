<%@page contentType="text/html" %>
<jsp:useBean id="pht" class="beans.Photo">
<jsp:setProperty name="pht" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>

<% 
	pht.buildForm();
%>
<jsp:getProperty name="pht" property="content"/>