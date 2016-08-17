<%@ page contentType="text/html" %>
<jsp:useBean id="staticPage" class="beans.StaticPage" >
<jsp:setProperty name="staticPage" property="user_id" value="<%=session.getAttribute("userId")%>"/>
</jsp:useBean>
<% 
	staticPage.authorizeAdmin();
staticPage.setTitel("Admin Panel");
	String content = "<H2>Administration</h2><table cellpadding=\"20\" align=\"center\" style=\"text-align:center;\" width=\"90%\" border=\"0\">"
						+	"<tr>"
						+	"<td><a href=\"index.jsp?module=complex&function=add_form\"><img src=\"images/admin/add_complex.jpg\"><br>Add Complex</a></td>"
						+	"<td><a href=\"index.jsp?module=complex&function=list\"><img src=\"images/admin/complex_list.jpg\"><br>List Complex</a></td>"
						+	"<td><a href=\"index.jsp?module=filminfo&function=add_form\"><img src=\"images/admin/add_movie.jpg\"><br>Add Movie</a></td>"
						+	"<td><a href=\"index.jsp?module=filminfo&function=list\"><img src=\"images/admin/movie_list.jpg\"><br>List Movies</a></td>"
						
						      +	"</tr><tr><td><a href=\"index.jsp?module=photo&function=photo\"><img src=\"images/admin/add_photo.jpg\"><br>Add Screenshot</a></td>"
						+	"<td><a href=\"index.jsp?module=photo&function=showPhoto\"><img src=\"images/admin/photo_list.jpg\"><br>List Screenshots</a></td>"
						+	"<td><a href=\"index.jsp?module=programming&function=add_form\"><img src=\"images/admin/add_program.jpg\"><br>Schedule Movie</a></td>"
						+	"<td><a href=\"index.jsp?module=programming&function=list\"><img src=\"images/admin/program_list.jpg\"><br>List Movie Schedule</a></td>"
						
						      +	"</tr><tr><td><a href=\"index.jsp?module=trailer&function=trailer\"><img src=\"images/admin/add_trailer.jpg\"><br>Add Trailer</a></td>"
						+	"<td><a href=\"index.jsp?module=trailer&function=showTrailer\"><img src=\"images/admin/trailers_list.jpg\"><br>List Trailers</a></td>"
						+	"<td><a href=\"index.jsp?module=flmcrew&function=flmcrew\"><img src=\"images/admin/add_crew.jpg\"><br>Add Crew Member</a></td>"
						+	"<td><a href=\"index.jsp?module=flmreview&function=add\"><img src=\"images/admin/add_review.jpg\"><br>Add Review</a></td>"
						
						+	"</tr><tr><td><a href=\"index.jsp?module=hall&function=add_form\"><img src=\"images/admin/add_hall.jpg\"><br>Add Hall</a></td>"
						+	"<td><a href=\"index.jsp?module=hall&function=list\"><img src=\"images/admin/hall_list.jpg\"><br>List Halls</a></td>"
						+	"<td><a href=\"help/\"  target=\"_blank\"><img src=\"images/admin/help.jpg\"><br>Administration help</a></td>"
						+	"<td><a href=\"javadoc/\" target=\"_blank\"><img src=\"images/admin/javadoc.jpg\"><br>Javadocs</a></td>"
						+	"</tr></table>";
%>
<%=staticPage.wrapDesign(content)%>