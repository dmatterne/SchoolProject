<%@page contentType="text/html" %>
<jsp:useBean id="hall" class="beans.Hall" >
	<jsp:setProperty name="hall" property="cmf_id" value="<%=request.getParameter("cmf_id")%>"/>
<jsp:setProperty name="hall" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 

	hall.list();

%>
<jsp:getProperty name="hall" property="content"/>

