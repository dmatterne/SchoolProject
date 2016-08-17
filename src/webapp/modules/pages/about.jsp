<%@ page contentType="text/html" %>
	<jsp:useBean id="staticPage" class="beans.StaticPage" >
		<jsp:setProperty name="staticPage" property="user_id" value="<%=session.getAttribute("userId")%>"/>
	</jsp:useBean>
	<% 
		staticPage.authorizeAll();
		staticPage.setTitel("About");
		String content = "<H2>Welcome</h2>"
							+ "This program is made by ICT9."
							+ "<br> As administrator you can add, edit and list complex features, filminformation and upload photos "
							+ "<br> As client you got the possibility to list all complex features and filminformation"
							+ "<br> There is also the possibility to watch the programming and order tickets.";
		
	%>
	<%=staticPage.wrapDesign(content)%>
