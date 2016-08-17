<%@page contentType="text/html" %>
<jsp:useBean id="fli" class="beans.FilmInfo" >
<jsp:setProperty name="flminfo" property="fli_id" value="<%=request.getParameter("fliid")%>"/>
<jsp:setProperty name="flminfo" property="rls_id" value="<%=request.getParameter("rlsid")%>"/>
<jsp:setProperty name="flminfo" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
	fli.showCrews();
%>
<jsp:getProperty name="flminfo" property="content"/>