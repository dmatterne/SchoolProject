<%@page contentType="text/html" %>
<jsp:useBean id="hall" class="beans.Hall" >
	<jsp:setProperty name="hall" property="cmf_id" value="<%=request.getParameter("cmf_id")%>"/>
	<jsp:setProperty name="hall" property="hlc_bookable" value="<%=request.getParameter("hlc_bookable")%>"/>
	<jsp:setProperty name="hall" property="hlc_capacity" value="<%=request.getParameter("hlc_capacity")%>"/>
	<jsp:setProperty name="hall" property="hlc_cupholder" value="<%=request.getParameter("hlc_cupholder")%>"/>
	<jsp:setProperty name="hall" property="hlc_digitalversion" value="<%=request.getParameter("hlc_digitalversion")%>"/>
	<jsp:setProperty name="hall" property="hlc_distancescreenprojection" value="<%=request.getParameter("hlc_distancescreenprojection")%>"/>
	<jsp:setProperty name="hall" property="hlc_screenwidth" value="<%=request.getParameter("hlc_screenwidth")%>"/>
	<jsp:setProperty name="hall" property="hlc_spaceinfrontofscreen" value="<%=request.getParameter("hlc_spaceinfrontofscreen")%>"/>
	<jsp:setProperty name="hall" property="hlc_twinseat" value="<%=request.getParameter("hlc_twinseat")%>"/>
	<jsp:setProperty name="hall" property="hlc_wheelchair" value="<%=request.getParameter("hlc_wheelchair")%>"/>
<jsp:setProperty name="hall" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 

	hall.save();

%>
<jsp:getProperty name="hall" property="content"/>

