<%@page contentType="text/html" %>
<jsp:useBean id="PhUp" class="beans.Photo" >
<jsp:setProperty name="PhUp" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean >
<% 
	PhUp.chooseMovieThumb();
%>
<jsp:getProperty name="PhUp" property="content"/>


