<%@page contentType="text/html" %>
<jsp:useBean id="cmpfeat" class="beans.Complex">
	<jsp:setProperty name="cmpfeat" property="cache_id" value="<%=request.getParameter("ID")%>"/>
<jsp:setProperty name="cmpfeat" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 

	cmpfeat.edit();

%>
<jsp:getProperty name="cmpfeat" property="content"/>
