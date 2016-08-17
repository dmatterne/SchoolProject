<%@page contentType="text/html" %>
<jsp:useBean id="PUP" scope="page" class="beans.Photo" >
<jsp:setProperty name="PUP" property="fli_id" value="<%=request.getParameter("ID")%>" />
<jsp:setProperty name="PUP" property="cont_id" value="<%=request.getParameter("contid")%>" />
 <jsp:setProperty name="PUP" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
	PUP.listThumbs();
%>
<jsp:getProperty name="PUP" property="content"/>