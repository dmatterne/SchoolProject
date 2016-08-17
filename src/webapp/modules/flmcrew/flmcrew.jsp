<%@page contentType="text/html" %>
<jsp:useBean id="flc" class="beans.FlmCrew" >
<jsp:setProperty name="flc" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
	flc.buildForm();
%>
<jsp:getProperty name="flc" property="content"/>