package beans ;

import com.intersys.objects.CacheException ;
import database.Authentication ;
import database.Connection ;
import database.InputControl ;
import design.DesignWrapper ;
import html.DropDown ;
import html.Input ;
import html.Table ;
import Comato.FlmReviews ;
import Comato.FlmScores ;

/**
 * A user or someone else can have an opinion about a movie and thats why
 * this class gives the opportunity to add a review of a certain movie.
 * @author Steveke
 *
 */
public class FlmReview
{

   private String content = "" ;

   private String user_id ;

   private Connection con = new Connection() ;

   private Authentication authentication = new Authentication(con) ;

   private DesignWrapper design = new DesignWrapper(authentication) ;

   private InputControl input_control = new InputControl(con) ;

   private String fre_name = "" ;

   private String fre_public = "" ;

   private String fre_title = "" ;

   private String lng_id ;

   private String fls_id ;

   private String fre_content = "" ;

   private String fli_id ;

   private String scr_id ;

   /**
    * Creates the add page for flmCrews.
    */
   public void buildForm()
   {
      // set the authentication instance to the userId aquired from the session
      
      authentication.setPersonId(user_id) ;
      
      boolean authenticated = true ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page
      
      design.setAccess(authenticated) ;

      // Sets the username in de design class, for fancy display
      
      design.setUserName(authentication.getusrUserName()) ;


      if (authenticated)
      {

         content += "<h2>Add Review</h2><form action=\"index.jsp?module=flmreview&function=add_form\" method=\"post\">" ;

         // create a new table object

         Table table = new Table(2) ;

         table.addItem("Film : ") ;
         table.addItem(new DropDown("fliid", "", con, DropDown._TYPE_FILMINFO)) ;
         table.nextRow() ;

         table.addItem("Language : ") ;
         table.addItem(new DropDown("lngid", "", con, DropDown._TYPE_LANGUAGES)) ;
         table.nextRow() ;

         table.addItem("Please enter your name : ") ;
         table.addItem(new Input("frename", fre_name, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("frename")) ;
         table.nextRow() ;

         content += table + "<br>" ;

         Table table2 = new Table(3) ;

         table2.addItem("Status : ") ;
         table2.addItem(new Input("frepublic", "Public", "false", "false", Input._TYPE_RADIO)) ;
         table2.addToLastItem(input_control.getErrorMessage("frepublic")) ;
         table2.addItem(new Input("frepublic", "Critici", "true", "false", Input._TYPE_RADIO)) ;
         table2.addToLastItem(input_control.getErrorMessage("frepublic")) ;
         table2.nextRow() ;

         content += table2 + "<br>" ;

         Table tab = new Table(2) ;

         tab.addItem("Title of review : ") ;
         tab.addItem(new Input("fretitle", fre_title, Input._TYPE_TEXT)) ;
         tab.addToLastItem(input_control.getErrorMessage("fretitle")) ;
         tab.nextRow() ;

         tab.addItem("Score : ") ;
         tab.addItem(new DropDown("scrid", "", con, DropDown._TYPE_SCORES)) ;
         tab.nextRow() ;

         tab.addItem("Review : ") ;
         tab.addItem("") ;
         tab.nextRow() ;

         content += tab ;

         content += "<textarea name=\"frecontent\" cols=\"75\" rows=\"13\">\"\"</textarea>" ;
         Table tabs = new Table(2) ;

         tabs.addItem(new Input("Submit", "Add", Input._TYPE_SUBMIT)) ;
         tabs.addItem("") ;

         tabs.nextRow() ;
         
         // get the table as string, line alternating mode is off


         content += tabs ;
         content += "</form>" ;
      }

   }

   /**
    * Saves the new film crew member into database.
    */
   public void saveFlmReview()
   {
      {
         // set the authentication instance to the userId aquired from the
         // session
         
         authentication.setPersonId(user_id) ;
         
         boolean authenticated = true ;

         // pass the acces to the design class,
         // only admins are allowed to view the content of this page
         
         design.setAccess(authenticated) ;

         // Sets the username in de design class, for fancy display
         
         design.setUserName(authentication.getusrUserName()) ;


         if (authenticated)
         {
            
            
            input_control.checkEmpty(fre_name, "freName") ;
            input_control.checkEmpty(fre_title, "freTitle") ;
            if (input_control.isErrors())
            {
               buildForm() ;
            }else
            {
            // Set the titel of this page.
            
            design.setTitel("Film Crew Member added ") ;

            // temporary int to store the parsed strings into.


            try
            {
               FlmReviews fre ;
               FlmScores fls ;

               con.createConnection() ;

               fre = new FlmReviews(con.getConnection()) ;
               fls = new FlmScores(con.getConnection()) ;

               // get the last id from the database

               String sql = "SELECT MAX(freID) FROM Comato.FlmReviews" ;
               String sql2 = "SELECT MAX(flsID) FROM Comato.FlmScores" ;

               // generate the resultset

               con.generateResultSet(sql) ;

               // Read one row from the resultset

               if (con.nextResult())
               {
                  fre.setfreID(new Long(con.getResultInt(1) + 1)) ;

               }
               else
               {
                  fre.setfreID(new Long(1)) ;
               }

               con.generateResultSet(sql2) ;
               int cntfls = con.getResultInt(1) ;
               if (con.nextResult())
               {

                  fre.setflsID(new Long(cntfls + 1)) ;
                  fls.setflsID(new Long(cntfls + 1)) ;

               }
               else
               {
                  fre.setflsID(new Long(1)) ;
                  fls.setflsID(new Long(1)) ;
               }

               fls.setfliID(Integer.parseInt(fli_id)) ;
               fls.setflsName(fre_name) ;
               fls.setflsPublic(Boolean.parseBoolean(fre_public)) ;
               fls.setscrID(Integer.parseInt(scr_id)) ;
               fre.setfreName(fre_name) ;
               fre.setfrePublic(Boolean.parseBoolean(fre_public)) ;
               fre.setfreTitle(fre_title) ;
               fre.setfreContent(fre_content) ;
               fre.setlngID(Integer.parseInt(lng_id)) ;
               fls.save() ;
               fre.save() ;
               content += "The Review has been succesfully added to database " + "<br><a href=\"index.jsp\">go back to film administration</a>" ;
               con.closeResultset() ;
            }

            catch (CacheException e)
            {
               // in case of an error we will set the errormessage inside the
               // designer class
                             
               design.setErrorMessage("<br>" + e.getMessage() + "<br>") ;
            }
            }
         }
      }
   }

   public String getFli_id()
   {
      return fli_id ;
   }


   public void setFli_id(String fli_id)
   {
      this.fli_id = fli_id ;
   }


   public String getFls_id()
   {
      return fls_id ;
   }


   public void setFls_id(String fls_id)
   {
      this.fls_id = fls_id ;
   }


   public String getFre_content()
   {
      return fre_content ;
   }



   public String getFre_name()
   {
      return fre_name ;
   }


   public void setFre_name(String fre_name)
   {
      this.fre_name = fre_name ;
   }


   public String getFre_public()
   {
      return fre_public ;
   }


   public void setFre_public(String fre_public)
   {
      this.fre_public = fre_public ;
   }


   public String getFre_title()
   {
      return fre_title ;
   }


   public void setFre_title(String fre_title)
   {
      this.fre_title = fre_title ;
   }


   public InputControl getInput_control()
   {
      return input_control ;
   }


   public void setInput_control(InputControl input_control)
   {
      this.input_control = input_control ;
   }


   public String getScr_id()
   {
      return scr_id ;
   }


   public void setScr_id(String scr_id)
   {
      this.scr_id = scr_id ;
   }


   public String getLng_id()
   {
      return lng_id ;
   }


   public void setLng_id(String lng_id)
   {
      this.lng_id = lng_id ;
   }


   public String getUser_id()
   {
      return user_id ;
   }


   public void setUser_id(String user_id)
   {
      this.user_id = user_id ;
   }

   /**
    * @return
    */
   public String getContent()
   {
      String lastError = con.getLastError() ;
      boolean error = con.isErrors() ;
      
      con.closeResultset() ;
      design.setErrorMessage(lastError) ;

      // return the content string wrapped by the design class.

      return design.getContent(content, error) ;
   }

   /**
    * @param content
    */
   public void setContent(String content)
   {
      this.content = content ;
   }

   
   public void setFre_content(String fre_content)
   {
      this.fre_content = fre_content ;
   }

}
