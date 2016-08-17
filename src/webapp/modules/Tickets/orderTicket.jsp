<%@page contentType="text/html" %>
<%@page import = "java.lang.Integer" %>
<jsp:useBean id="Ticket" class="beans.Ticket">
<jsp:setProperty name="Ticket" property="user_id" value="<%=session.getAttribute("userId")%>"/>
<jsp:setProperty name="Ticket" property="cache_id" value="<%=request.getParameter("cache_Id")%>"/>
<jsp:setProperty name="Ticket" property="aantal" value="<%=request.getParameter("aantal")%>"/>
<jsp:setProperty name="Ticket" property="tempPrice" value="<%=request.getParameter("tempPrice")%>"/>
</jsp:useBean>
<% 
	int tempNumber;
	try
	{
	   tempNumber = Integer.parseInt(Ticket.getAantal());
	}catch(NumberFormatException e){
	   tempNumber = 1;
	}
	String[] discounts= new String[tempNumber];
	
	for (int i=1;i<=tempNumber;i++)
				{
					discounts[i-1]=request.getParameter("dsc_id"+i);
				}
	for(String s1: discounts)
	{
	System.out.println(s1);
	}
	Ticket.setDiscounts(discounts);
	Ticket.orderTicket();
	
	
%>
<jsp:getProperty name="Ticket" property="content"/>