<%@page contentType="text/html" %>
<jsp:useBean id="flc" class="beans.FlmCrew">
	<jsp:setProperty name="flc" property="flc_character" value="<%=request.getParameter("flc_character")%>"/>
	<jsp:setProperty name="flc" property="flc_award" value="<%=request.getParameter("flc_award")%>"/>
	<jsp:setProperty name="flc" property="rls_id" value="<%=request.getParameter("rls_id")%>"/>
	<jsp:setProperty name="flc" property="fli_id" value="<%=request.getParameter("fli_id")%>"/>
	<jsp:setProperty name="flc" property="prs_id" value="<%=request.getParameter("prs_id")%>"/>
	<jsp:setProperty name="flc" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
		flc.saveFlmCrewMember();
%>
<jsp:getProperty name="flc" property="content"/>