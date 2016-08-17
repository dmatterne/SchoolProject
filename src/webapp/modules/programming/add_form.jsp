<%@page contentType="text/html" %>
<jsp:useBean id="prog" class="beans.Program">
	<jsp:setProperty name="prog" property="cmf_link_id" value="<%=request.getParameter("cmf_link_id")%>"/>
<jsp:setProperty name="prog" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
		prog.buildForm();
%>
<jsp:getProperty name="prog" property="content"/>