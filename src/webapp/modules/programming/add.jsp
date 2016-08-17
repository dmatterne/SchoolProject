<%@page contentType="text/html" %>
<jsp:useBean id="prog" class="beans.Program">
	<jsp:setProperty name="prog" property="prg_price" value="<%=request.getParameter("prg_price")%>"/>
	<jsp:setProperty name="prog" property="prg_start_month" value="<%=request.getParameter("prg_start_month")%>"/>
	<jsp:setProperty name="prog" property="prg_start_day" value="<%=request.getParameter("prg_start_day")%>"/>
	<jsp:setProperty name="prog" property="prg_start_year" value="<%=request.getParameter("prg_start_year")%>"/>
	<jsp:setProperty name="prog" property="prg_end_year" value="<%=request.getParameter("prg_end_year")%>"/>
	<jsp:setProperty name="prog" property="prg_end_month" value="<%=request.getParameter("prg_end_month")%>"/>
	<jsp:setProperty name="prog" property="prg_end_day" value="<%=request.getParameter("prg_end_day")%>"/>
	<jsp:setProperty name="prog" property="prg_hour" value="<%=request.getParameter("prg_hour")%>"/>
	<jsp:setProperty name="prog" property="prg_minute" value="<%=request.getParameter("prg_minute")%>"/>
	<jsp:setProperty name="prog" property="prg_days_1" value="<%=request.getParameter("prg_days_1")%>"/>
	<jsp:setProperty name="prog" property="prg_days_2" value="<%=request.getParameter("prg_days_2")%>"/>
	<jsp:setProperty name="prog" property="prg_days_3" value="<%=request.getParameter("prg_days_3")%>"/>
	<jsp:setProperty name="prog" property="prg_days_4" value="<%=request.getParameter("prg_days_4")%>"/>
	<jsp:setProperty name="prog" property="prg_days_5" value="<%=request.getParameter("prg_days_5")%>"/>
	<jsp:setProperty name="prog" property="prg_days_6" value="<%=request.getParameter("prg_days_6")%>"/>
	<jsp:setProperty name="prog" property="prg_days_7" value="<%=request.getParameter("prg_days_7")%>"/>
	<jsp:setProperty name="prog" property="prg_id" value="<%=request.getParameter("prg_id")%>"/>
	<jsp:setProperty name="prog" property="cmf_id" value="<%=request.getParameter("cmf_id")%>"/>
	<jsp:setProperty name="prog" property="hlc_id" value="<%=request.getParameter("hlc_id")%>"/>
	<jsp:setProperty name="prog" property="fli_id" value="<%=request.getParameter("fli_id")%>"/>
<jsp:setProperty name="prog" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
		prog.saveProgramming();
%>
<jsp:getProperty name="prog" property="content"/>