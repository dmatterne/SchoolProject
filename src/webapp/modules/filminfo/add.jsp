<%@page contentType="text/html" %>
<jsp:useBean id="flminfo" class="beans.FilmInfo">
<jsp:setProperty name="flminfo" property="fli_name" value="<%=request.getParameter("fli_name")%>"/>
<jsp:setProperty name="flminfo" property="fli_length" value="<%=request.getParameter("fli_length")%>"/>
<jsp:setProperty name="flminfo" property="fli_website" value="<%=request.getParameter("fli_website")%>"/>
<jsp:setProperty name="flminfo" property="fli_description" value="<%=request.getParameter("fli_description")%>"/>
<jsp:setProperty name="flminfo" property="fli_color" value="<%=request.getParameter("fli_color")%>"/>
<jsp:setProperty name="flminfo" property="fli_releasedate" value="<%=request.getParameter("fli_releasedate")%>"/>
<jsp:setProperty name="flminfo" property="fli_soundtrack" value="<%=request.getParameter("fli_soundtrack")%>"/>
<jsp:setProperty name="flminfo" property="fli_format" value="<%=request.getParameter("fli_format")%>"/>
<jsp:setProperty name="flminfo" property="fli_price" value="<%=request.getParameter("fli_price")%>"/>
<jsp:setProperty name="flminfo" property="fli_targetgroup" value="<%=request.getParameter("fli_targetgroup")%>"/>
<jsp:setProperty name="flminfo" property="gnr_id" value="<%=request.getParameter("gnr_id")%>"/>
<jsp:setProperty name="flminfo" property="fli_productionhouse" value="<%=request.getParameter("fli_productionhouse")%>"/>
<jsp:setProperty name="flminfo" property="cnt_id" value="<%=request.getParameter("cnt_id")%>"/>
<jsp:setProperty name="flminfo" property="dst_id" value="<%=request.getParameter("dst_id")%>"/>
<jsp:setProperty name="flminfo" property="user_id" value="<%=session.getAttribute("userId")%>"/>
<jsp:setProperty name="flminfo" property="cache_id" value="<%=request.getParameter("ID")%>"/>
</jsp:useBean>
<% 

	flminfo.saveFlmInfo();

%>
<jsp:getProperty name="flminfo" property="content"/>

