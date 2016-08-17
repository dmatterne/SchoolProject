package design ;

import database.Authentication ;

/**
 * This class houses the design. 
 * It also decides if we should display the page or a message that authorisation is required. 
 * If you pas this class a username, it will also display this name on the page.
 * Another thing this class is responsible for is setting the page title. And generating the menu to show on all pages.
 * You can pass any html string you want to wrap the design around into the function getContent. This function will then return the wrapped up String.
 * @author dimitri
 *
 */
public class DesignWrapper
{

   private String content = "" ;

   private String titel = "Kinepolis :: " ;

   private String error_message = "<br><br><p>There where some errors while constructing this page!<br>An Administrator will try to fix these shortly.<br>We Apologise for the inconvenience</p>" ;

   private boolean access = true ;

   private String user_name = "Guest, please <a href=\"index.jsp?module=login&amp;function=login\">login</a> or <a href=\"index.jsp?module=login&amp;function=register\">register</a>" ;

   private Authentication authentication ;

   public DesignWrapper(Authentication auth)
   {
      authentication = auth ;
   }

   private String generateMenu()
   {
      String menu = "" ;

      menu += "<div id=\"menu\">" + "<a href=\"index.jsp\">Home</a> | " + "<a href=\"index.jsp?module=pages&amp;function=about\">About</a>"
            + " | <a href=\"index.jsp?module=programming&amp;function=list\">Movie Schedule</a>" ;

      if (authentication.isAdmin())
         menu += " | <a href=\"index.jsp?module=administration\">Administration</a>" ;


      menu += "</div>" ;


      return menu ;
   }

   public String getContent(String inContent, boolean Errors)
   {

      content += "<html>" + "\n<head>" + "\n<title>" + titel + "</title>"
            + "\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">"
            + "\n<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">" + "\n<script language=\"JavaScript\" type=\"text/JavaScript\">"
            + "\n<!--" + "\nfunction MM_jumpMenu(targ,selObj,restore)\n{ //v3.0"
            + "\neval(targ+\".location='\"+selObj.options[selObj.selectedIndex].value+\"'\");" + "\nif (restore) selObj.selectedIndex=0;" + "\n}"
            + "\n//-->" + "\n</script>" + "\n</head>" + "\n<body>" + "\n<div id=\"header\"></div>" + "\n" + generateMenu()
            + "\n<div id=\"content\">\n\n" + "Welcome " + user_name + "<br><br>" ;
      if (access)
         content += inContent ;
      else
         content += "You do not have acces to this page!" ;


      if (Errors == true)
         content += error_message ;

      content += "\n\n</div>" + "\n</body>" + "\n</html>" ;

      return content ;
   }


   public void setErrorMessage(String error_message)
   {
      this.error_message += error_message ;
   }

   public void setTitel(String titel)
   {
      this.titel += titel ;
   }


   public void setUserName(String user_name)
   {
      if (!(user_name.equals("")))
      {
         this.user_name = user_name + ", <a href=\"index.jsp?module=login&amp;function=logout\">logout</a>" ;
      }

   }


   public void setAccess(boolean access)
   {
      this.access = access ;
   }


   /**
    * @param authentication
    *           The authentication to set.
    */
   public void setAuthentication(Authentication authentication)
   {
      this.authentication = authentication ;
   }


}
