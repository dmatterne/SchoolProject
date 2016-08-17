<%@page contentType="text/html" %>
<jsp:useBean id="prog" class="beans.Program" >
	<jsp:setProperty name="prog" property="position" value="<%=request.getParameter("pos")%>"/>
	<jsp:setProperty name="prog" property="cmf_id" value="<%=request.getParameter("cmf_link_id")%>"/>
<jsp:setProperty name="prog" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
	prog.listProgramming();
%>
<jsp:getProperty name="prog" property="content"/>