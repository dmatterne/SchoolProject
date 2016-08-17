<%@ page language="java" import="javazoom.upload.*,java.util.*" %>
<jsp:useBean id="upBean" scope="page" class="javazoom.upload.UploadBean" >
  <jsp:setProperty name="upBean" property="folderstore"     value="d:\\Sun\\AppServer\\domains\\domain1\\docroot\\uploads\\" />
  <jsp:setProperty name="upBean" property="whitelist"       value="*.mpg,*.avi,*.mov" />
  <jsp:setProperty name="upBean" property="filesizelimit"   value="1500000000" />
 
</jsp:useBean>

<jsp:useBean id="trl" class="beans.Trailer">
 <jsp:setProperty name="trl" property="user_id" value="<%=session.getAttribute("userId")%>"/>
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
                    trl.setFli_id(mrequest.getParameter("film"));
                    
                    upBean.store(mrequest, "uploadfile");
                }
                else
                {
                 
                }
	     }
         
      }
      
      trl.setTrl_path(file.getFileName());
      trl.fillTrailer();
	}catch(UploadException u)
	{
	   out.println("An error has occured while uploading."); 
	   out.println("Please check if the file is of the following types : *.mpg,*.wmv,*.mov");
	   out.println("The file might have exceeded the filesize limit of 300kb.");
	}
	
%><jsp:getProperty name="trl" property="content"/>