<%@page contentType="text/html" %>
<jsp:useBean id="flminfo" class="beans.FilmInfo" >
<jsp:setProperty name="flminfo" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
	flminfo.buildForm();
%>
<jsp:getProperty name="flminfo" property="content"/>