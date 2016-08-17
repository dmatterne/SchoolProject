<%@page contentType="text/html" %>
<jsp:useBean id="trlUp" class="beans.Trailer" >
<jsp:setProperty name="trlUp" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
	trlUp.chooseMovie();
%>
<jsp:getProperty name="trlUp" property="content"/>


