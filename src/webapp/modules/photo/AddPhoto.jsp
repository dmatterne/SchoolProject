<%@ page language="java" import="javazoom.upload.*,java.util.*" %>
<jsp:useBean id="upBean" scope="page" class="javazoom.upload.UploadBean" >
  <jsp:setProperty name="upBean" property="folderstore"     value="d:\\Sun\\AppServer\\domains\\domain1\\docroot\\uploads\\"/>
  <jsp:setProperty name="upBean" property="whitelist"       value="*.jpg,*.jpeg,*.gif" />
  <jsp:setProperty name="upBean" property="filesizelimit"   value="300000" />
</jsp:useBean>

<jsp:useBean id="pht" class="beans.Photo">
 <jsp:setProperty name="pht" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>

<%
	try
	{
	  UploadFile file = null;	
      if (MultipartFormDataRequest.isMultipartFormData(request))
      {
         
         MultipartFormDataRequest mrequest = new MultipartFormDataRequest(request);
         String todo = null;
         if (mrequest != null) todo = mrequest.getParameter("todo");
	     if ( (todo != null) && (todo.equalsIgnoreCase("upload")) )
	     {
                Hashtable files = mrequest.getFiles();
                if ( (files != null) && (!files.isEmpty()) )
                {
                    file = (UploadFile) files.get("uploadfile");
                    pht.setFli_id(mrequest.getParameter("film"));
                    
                    upBean.store(mrequest, "uploadfile");
                }
                else
                {
                 
                }
	     }
         
      }
      pht.setPht_cont_type(file.getContentType());
      pht.setPht_path(file.getFileName());
      pht.fillPhoto();
	}catch(UploadException u)
	{
	   out.println("An error has occured while uploading."); 
	   out.println("Please check if the file is of the following types : *.jpeg,*.jpg,*.gif");
	   out.println("The file might have exceeded the filesize limit of 300kb.");
	}
	
%><jsp:getProperty name="pht" property="content"/>