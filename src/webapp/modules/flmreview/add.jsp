<%@page contentType="text/html" %>
<jsp:useBean id="fre" class="beans.FlmReview" >
<jsp:setProperty name="fre" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
	fre.buildForm();
%>
<jsp:getProperty name="fre" property="content"/>