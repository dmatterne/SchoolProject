<%@page contentType="text/html" %>
<jsp:useBean id="TUP" scope="page" class="beans.Trailer" >
<jsp:setProperty name="TUP" property="fli_id" value="<%=request.getParameter("ID")%>" />
<jsp:setProperty name="TUP" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
	TUP.listTrailer();
%>
<jsp:getProperty name="TUP" property="content"/>