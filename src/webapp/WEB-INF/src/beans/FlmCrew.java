package beans ;

import com.intersys.objects.CacheException ;
import database.Authentication ;
import database.Connection ;
import database.InputControl ;
import design.DesignWrapper ;
import html.DropDown ;
import html.Input ;
import html.Table ;
import Comato.FlmCrews ;

/**
 * Creates the ability to add a certain crewmember to a movie. This crewmember
 * can have a specific role , but his information has to be in persons table.
 * CrewMember will be saved into database.
 * 
 * @author ICT09
 */
public class FlmCrew
{

   private String content = "<html><head><title>Register</title></head>" ;

   private String user_id ;

   private Connection con = new Connection() ;

   private Authentication authentication = new Authentication(con) ;

   private DesignWrapper design = new DesignWrapper(authentication) ;

   private InputControl input_control = new InputControl(con) ;

   private String flc_award = "" ;

   private String flc_character = "" ;

   private String prs_id ;

   private String fli_id ;

   private String rls_id ;


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

         content += "<h2>Add Crew Member</h2><form action=\"index.jsp?module=flmcrew&function=addcrewmember\" method=\"post\">" ;

         // create a new table object

         Table table = new Table(2) ;

         table.addItem("Film : ") ;
         table.addItem(new DropDown("fli_id", "", con, DropDown._TYPE_FILMINFO)) ;
         table.nextRow() ;

         table.addItem("Role : ") ;
         table.addItem(new DropDown("rls_id", "", con, DropDown._TYPE_ROLES)) ;
         table.nextRow() ;

         table.addItem("Persons : ") ;
         table.addItem(new DropDown("prs_id", "", con, DropDown._TYPE_PERSONS)) ;
         table.nextRow() ;

         table.addItem("Character Name : ") ;
         table.addItem(new Input("flc_character", flc_character, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("flc_character")) ;
         table.nextRow() ;

         table.addItem("Award : ") ;
         table.addItem(new Input("flc_award", flc_award, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("flc_award")) ;
         table.nextRow() ;

         table.addItem(new Input("Submit", "Add", Input._TYPE_SUBMIT)) ;
         table.addItem("") ;

         table.nextRow() ;

         // get the table as string, line alternating mode is off


         content += table ;
         content += "</form>" ;
      }

   }

   /**
    * Saves the new film crew member into database.
    */
   public void saveFlmCrewMember()
   {
      {
         // set the authentication instance to the userId aquired from the
         // session

         authentication.setPersonId(user_id) ;
         input_control.checkEmpty(flc_character, "flc_character") ;
         input_control.checkEmpty(flc_award, "flc_award") ;


         boolean authenticated = true ;

         // pass the acces to the design class,
         // only admins are allowed to view the content of this page

         design.setAccess(authenticated) ;

         // Sets the username in de design class, for fancy display

         design.setUserName(authentication.getusrUserName()) ;

         if (input_control.isErrors())
         {
            buildForm() ;
         }
         else
         {
            if (authenticated)
            {
               // Set the titel of this page.

               design.setTitel("Film Crew Member added ") ;

               // temporary int to store the parsed strings into.


               try
               {
                  FlmCrews flcr ;

                  con.createConnection() ;

                  flcr = new FlmCrews(con.getConnection()) ;


                  // get the last id from the database

                  String sql = "SELECT flcAward FROM Comato.FlmCrews where fliID =" + fli_id + " AND rlsID = " + rls_id + " AND prsID = " + prs_id ;

                  // generate the resultset

                  con.generateResultSet(sql) ;

                  // Read one row from the resultset

                  if (con.nextResult())
                  {
                     content += "This persons already has this role in this movie." ;
                  }
                  else
                  {

                     flcr.setfliID(Integer.parseInt(fli_id)) ;
                     flcr.setprsID(Integer.parseInt(prs_id)) ;
                     flcr.setrlsID(Integer.parseInt(rls_id)) ;
                     flcr.setflcAward(flc_award) ;
                     flcr.setflcCharacter(flc_character) ;
                     // save the object to the database

                     flcr.save() ;
                     content += "The Crew Member has been succesfully added to database "
                           + "<br><a href=\"index.jsp\">go back to film administration</a>" ;
                     con.closeResultset() ;
                  }
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

   /**
    * Gets the content string.
    * 
    * @return A string wich contains the html for the website
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
    * Sets the content.
    * 
    * @param content
    *           String to be set into content.
    */
   public void setContent(String content)
   {
      this.content = content ;
   }

   /**
    * Gets the flc_award string.
    * 
    * @return A string wich contains the flc_award.
    */
   public String getFlc_award()
   {
      return flc_award ;
   }

   /**
    * Sets the award.
    * 
    * @param String
    *           flc_award String to be set into flc_award.
    */
   public void setFlc_award(String flc_award)
   {
      this.flc_award = flc_award ;
   }

   /**
    * Gets the flc_character string.
    * 
    * @return A string wich contains the character name.
    */
   public String getFlc_character()
   {
      return flc_character ;
   }

   /**
    * Sets the flc_character.
    * 
    * @param flc_character
    *           String to be set into flc_character.
    */
   public void setFlc_character(String flc_character)
   {
      this.flc_character = flc_character ;
   }

   /**
    * Gets the id of the film.
    * 
    * @return A string wich contains the film id.
    */
   public String getFli_id()
   {
      return fli_id ;
   }

   /**
    * Sets the film id.
    * 
    * @param fli_id
    *           String to be set into fli_id.
    */
   public void setFli_id(String fli_id)
   {
      this.fli_id = fli_id ;
   }

   /**
    * Gets the person id string.
    * 
    * @return A string wich contains the person id.
    */
   public String getPrs_id()
   {
      return prs_id ;
   }

   /**
    * Sets the persons id.
    * 
    * @param prs_id
    *           String to be set into prs_id.
    */
   public void setPrs_id(String prs_id)
   {
      this.prs_id = prs_id ;
   }

   /**
    * Gets the rls id string.
    * 
    * @return A string wich contains the rls id.
    */
   public String getRls_id()
   {
      return rls_id ;
   }

   /**
    * Sets the rls id.
    * 
    * @param rls_id
    *           String to be set into rls_id.
    */
   public void setRls_id(String rls_id)
   {
      this.rls_id = rls_id ;
   }


   public String getUser_id()
   {
      return user_id ;
   }


   public void setUser_id(String user_id)
   {
      this.user_id = user_id ;
   }


   public InputControl getInput_control()
   {
      return input_control ;
   }


   public void setInput_control(InputControl input_control)
   {
      this.input_control = input_control ;
   }
}
