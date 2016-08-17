<%@page contentType="text/html" %>
<jsp:useBean id="cmpfeat" class="beans.Complex">
<jsp:setProperty name="cmpfeat" property="cnt_id" value="<%=request.getParameter("cnt_id")%>"/>
<jsp:setProperty name="cmpfeat" property="cmf_name" value="<%=request.getParameter("cmf_name")%>"/>
<jsp:setProperty name="cmpfeat" property="cmf_constructionyear" value="<%=request.getParameter("cmf_constructionyear")%>"/>
<jsp:setProperty name="cmpfeat" property="cmf_type" value="<%=request.getParameter("cmf_type")%>"/>
<jsp:setProperty name="cmpfeat" property="cmf_address" value="<%=request.getParameter("cmf_address")%>"/>
<jsp:setProperty name="cmpfeat" property="cache_id" value="<%=request.getParameter("ID")%>"/>
<jsp:setProperty name="cmpfeat" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% cmpfeat.save();%>
<jsp:getProperty name="cmpfeat" property="content"/>

