/**
 * 
 */
package beans ;

import java.io.Serializable ;

import database.Authentication ;
import database.Connection ;
import design.DesignWrapper ;

/**
 * Is a link between the desine and the jsp. It allows the jsp to wrap the
 * design and it also gets the authentication. With this class its easy to check
 * each function of the people using the jsps.
 * 
 * @author root
 */
public class StaticPage implements Serializable
{


   /**
    * 
    */
   private static final long serialVersionUID = 432158943682L ;

   private String user_id = "0" ;

   private Connection con = new Connection() ;

   private Authentication authentication = new Authentication(con) ;

   private DesignWrapper design = new DesignWrapper(authentication) ;

   private boolean authenticated ;

   /**
    * pass the acces to the design class, only admins are allowed to view the
    * content of this page
    */
   private void authorize()
   {
      //       

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page

      design.setAccess(authenticated) ;

      // Sets the username in de design class, for fancy display

      design.setUserName(authentication.getUsrUserName()) ;
   }

   /**
    * set the authentication instance to the userId aquired from the session
    */
   public void authorizeAll()
   {
      // set the authentication instance to the userId aquired from the session

      authentication.setPersonId(user_id) ;
      authenticated = true ;
      this.authorize() ;
   }

   /**
    * set the authentication instance to the userId aquired from the session
    */
   public void authorizeAdmin()
   {
      // set the authentication instance to the userId aquired from the session

      authentication.setPersonId(user_id) ;
      authenticated = authentication.isAdmin() ;
      this.authorize() ;
   }

   /**
    * set the authentication instance to the userId aquired from the session
    *
    */
   public void authorizeEmployee()
   {
      // set the authentication instance to the userId aquired from the session

      authentication.setPersonId(user_id) ;
      authenticated = authentication.isEmployee() ;
      this.authorize() ;
   }

   /**
    * 
    *set the authentication instance to the userId aquired from the session
    */
   public void authorizeLoggedIn()
   {
      // set the authentication instance to the userId aquired from the session

      authentication.setPersonId(user_id) ;
      authenticated = authentication.isLoggedIn() ;
      this.authorize() ;

   }

   public void setTitel(String titel)
   {
      design.setTitel(titel) ;
   }

   public String wrapDesign(String content)
   {
      String lastError = con.getLastError() ;

      con.closeResultset() ;
      design.setErrorMessage(lastError) ;

      return design.getContent(content, false) ;
   }


   public String getUser_id()
   {
      return user_id ;
   }


   public void setUser_id(String user_id)
   {
      this.user_id = user_id ;
   }
}
