<%@page contentType="text/html" %>
<jsp:useBean id="flminfo" class="beans.FilmInfo" >
<jsp:setProperty name="flminfo" property="cache_id" value="<%=request.getParameter("ID")%>"/>
<jsp:setProperty name="flminfo" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
	flminfo.getDetailPage();
%>
<jsp:getProperty name="flminfo" property="content"/>