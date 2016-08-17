<%@page contentType="text/html" %>
<jsp:useBean id="Ticket" class="beans.Ticket">
<jsp:setProperty name="Ticket" property="user_id" value="<%=session.getAttribute("userId")%>"/>
<jsp:setProperty name="Ticket" property="cache_id" value="<%=request.getParameter("cache_Id")%>"/>
<jsp:setProperty name="Ticket" property="price" value="<%=request.getParameter("price")%>"/>
<jsp:setProperty name="Ticket" property="aantal" value="<%=request.getParameter("aantal")%>"/>
</jsp:useBean>
<% 
	Ticket.printTicket();
%>
<%=Ticket.getContentNoDesign()   %>