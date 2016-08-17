<%@page contentType="text/html" %>
<jsp:useBean id="User" class="beans.Login" >
<jsp:setProperty name="User" property="cnt_id" value="<%=request.getParameter("cnt_id")%>"/>
<jsp:setProperty name="User" property="usr_sex" value="<%=request.getParameter("usr_sex")%>"/>
<jsp:setProperty name="User" property="usr_username" value="<%=request.getParameter("usr_username")%>"/>
<jsp:setProperty name="User" property="usr_password" value="<%=request.getParameter("usr_password")%>"/>
<jsp:setProperty name="User" property="usr_passwords" value="<%=request.getParameter("usr_passwords")%>"/>
<jsp:setProperty name="User" property="usr_year" value="<%=request.getParameter("usr_year")%>"/>
<jsp:setProperty name="User" property="usr_month" value="<%=request.getParameter("usr_month")%>"/>
<jsp:setProperty name="User" property="usr_day" value="<%=request.getParameter("usr_day")%>"/>
<jsp:setProperty name="User" property="usr_name" value="<%=request.getParameter("usr_name")%>"/>
<jsp:setProperty name="User" property="usr_address" value="<%=request.getParameter("usr_address")%>"/>
<jsp:setProperty name="User" property="usr_tel" value="<%=request.getParameter("usr_tel")%>"/>
<jsp:setProperty name="User" property="usr_email" value="<%=request.getParameter("usr_email")%>"/>
<jsp:setProperty name="User" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
	
	User.addUser();
%>
<jsp:getProperty name="User" property="content"/>
