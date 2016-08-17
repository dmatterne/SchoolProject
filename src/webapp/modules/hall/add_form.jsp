<%@page contentType="text/html" %>
<jsp:useBean id="hall" class="beans.Hall" >
<jsp:setProperty name="hall" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
System.out.println(session.getAttribute("userId"));
		hall.buildForm();
	
%>
<jsp:getProperty name="hall" property="content"/>