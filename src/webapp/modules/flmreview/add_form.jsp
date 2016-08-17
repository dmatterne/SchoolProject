<%@page contentType="text/html" %>
<jsp:useBean id="fre" class="beans.FlmReview">
	<jsp:setProperty name="fre" property="fre_name" value="<%=request.getParameter("frename")%>"/>
	<jsp:setProperty name="fre" property="fre_public" value="<%=request.getParameter("frepublic")%>"/>
	<jsp:setProperty name="fre" property="fre_title" value="<%=request.getParameter("fretitle")%>"/>
	<jsp:setProperty name="fre" property="lng_id" value="<%=request.getParameter("lngid")%>"/>
	<jsp:setProperty name="fre" property="fls_id" value="<%=request.getParameter("flsid")%>"/>
	<jsp:setProperty name="fre" property="fre_content" value="<%=request.getParameter("frecontent")%>"/>
	<jsp:setProperty name="fre" property="fli_id" value="<%=request.getParameter("fliid")%>"/>
	<jsp:setProperty name="fre" property="scr_id" value="<%=request.getParameter("scrid")%>"/>

</jsp:useBean>
<% 
		fre.saveFlmReview();
%>
<jsp:getProperty name="fre" property="content"/>
