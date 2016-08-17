<%@page contentType="text/html" %>
<jsp:useBean id="User" class="beans.Login" >
<jsp:setProperty name="User" property="usr_username" value="<%=request.getParameter("usr_username")%>"/>
<jsp:setProperty name="User" property="usr_password" value="<%=request.getParameter("usr_password")%>"/>
<jsp:setProperty name="User" property="user_id" value="<%=session.getAttribute("userid")%>"/>
</jsp:useBean>
<% 
	if(User.loginUser())
	{
		
		String userId = User.getCache_id();
		
		if (userId == null)
			userId = "" ;
			
		session.setAttribute( "userId", userId );
	}
	
%>
<jsp:getProperty name="User" property="content"/>
		