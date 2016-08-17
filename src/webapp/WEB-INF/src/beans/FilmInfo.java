package beans ;

import html.DropDown ;
import html.Input ;
import html.Link ;
import html.Table ;
import java.io.Serializable ;
import java.sql.Date ;
import Comato.FlmInfo ;
import Comato.Photos ;
import database.Authentication ;
import database.Connection ;
import database.InputControl ;
import design.DesignWrapper ;
import com.intersys.objects.CacheException ;
import com.intersys.objects.Id ;

/**
 * This class provides a way to add, edit a movie.U can also get a list of those movies.
 * If u choose a movie in movie schedule, u can also get a detailed page about the movie
 * wich contains info about that movie. It contains information about actors, and several other roles.
 * @author ICT09
 *
 */
public class FilmInfo implements Serializable
{

   private static final long serialVersionUID = 4630901382792179233L ;

   private String cache_id = "" ;

   private String rls_id = "" ;

   private String fli_id = "" ;

   private String fli_name = "" ;

   private String fli_length = "" ;

   private String fli_website = "" ;

   private String fli_description = "" ;

   private String fli_color = "" ;

   private String fli_releasedate = "" ;

   private String fli_soundtrack = "" ;

   private String fli_format = "" ;

   private String fli_price = "" ;

   private String fli_targetgroup = "" ;

   private String gnr_id = "" ;

   private String fli_productionhouse = "" ;

   private String cnt_id ;

   private String dst_id = "" ;

   private String content = "" ;

   private String user_id = "" ;

   private boolean controle = false ;

   private Date date ;

   private Connection con = new Connection() ;

   private Connection con2 = new Connection() ;

   private InputControl input_control = new InputControl(con) ;

   private Authentication authentication = new Authentication(con) ;

   private DesignWrapper design = new DesignWrapper(authentication) ;


   /**
    * Shows a detailed paged of the movie where u can find some data about the
    * movie.
    */
   public void getDetailPage()
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


         content += "<h2>Detail Page </h2>" ;

         try
         {
            
            // get movie from database 
            
            FlmInfo fl = (FlmInfo) FlmInfo._open(con.getConnection(), new Id(cache_id)) ;
            String sql1 = "select Max(ID) from Comato.Photos where fliID ="
                  + fl.getfliID() ;

            con.generateResultSet(sql1) ;

            
            if (con.nextResult())
            {
               int flid = con.getResultInt(1) ;
               Photos pht = (Photos) Photos._open(con.getConnection(), new Id(flid)) ;

               content += "<h3>" + fl.getfliName() + "</h3>" ;

               
               // create the table in wich the details will be displayed
               
               Table tbl = new Table(2) ;

               
               tbl.setWidth("700") ;
               tbl.setMode(true) ;
               
               // Add the picture to the detail page with link 
               
               tbl.addItem("<a href=\"showImage?ID="
                     + flid
                     + "&type=normal\" target=\"_blank\"><center><img vspace=15 src=\"showImage?ID="
                     + con.getResultString(1)
                     + "&type=thumb\"><br>"
                     + pht.getphtName()
                     + "</center></a>") ;

               String sql2 = "select gnrDescription from Comato.Genres where gnrID ="
                     + fl.getgnrID() ;

               con.generateResultSet(sql2) ;

               String a = "Unknown" ;

               if (con.nextResult())
               {
                  a = con.getResultString(1) ;
               }

               // Add the info from the film object.
               
               tbl.addItem("<center>Genre : "
                     + a
                     + "<br><br>Targetgroup : "
                     + fl.getfliTargetGroup()
                     + "<br><br>Description : "
                     + fl.getfliDescription()
                     + "<br><br>Color : "
                     + fl.getfliColor()
                     + "<br><br>Format : "
                     + fl.getfliFormat()
                     + "<br><br>Productionhouse : "
                     + fl.getfliProductionhouse()
                     + "<br><br>Soundtrack : "
                     + fl.getfliSoundtrack()
                     + "<br><br>Length : "
                     + fl.getfliLength()
                     + "<br><br>Releasedate : "
                     + fl.getfliReleaseDate()
                     + "<br><br>Website : "
                     + fl.getfliWebsite()
                     + "<br><br>Standard Price : "
                     + fl.getfliPrice()
                     + "</center") ;
               
               //Add links to actor list, photo list, trailer list and producer list
               tbl.addItem(new Link("index.jsp?module=photo&amp;function=ListPhoto&amp;type=thumb&amp;contid=0&amp;ID="
                     + fl.getfliID(), "Check here for more photos of this movie", "LinkListPhotos")) ;
               tbl.addItem(new Link("index.jsp?module=trailer&amp;function=ListTrailer&amp;ID="
                     + fl.getfliID(), "Check here for trailers of this movie", "LinkListTrailers")) ;
               tbl.addItem(new Link("index.jsp?module=flmcrew&amp;function=listcrews&amp;fliid="
                     + fl.getfliID()
                     + "&amp;rlsid=1", "List of Actors", "LinkListActors")) ;
               tbl.addItem(new Link("index.jsp?module=flmcrew&amp;function=listcrews&amp;fliid="
                     + fl.getfliID()
                     + "&amp;rlsid=2", "List of Producers", "LinkListProducers")) ;
               tbl.addItem(showAwards(String.valueOf(fl.getfliID()))) ;
               tbl.nextRow() ;
               content += tbl + "<br><br>" ;

              
            }
         }
         catch (CacheException e)
         {
            content += e.getMessage() ;

         }
      }


   }

   /**
    * Shows a list of the awards of a movie.
    * 
    * @param flinr
    *           the id of the movie
    * @return the list of awards
    */
   public String showAwards(String flinr)
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

         String sql = "select flaName from Comato.flmAwards where fliID ="
               + flinr ;

         con.generateResultSet(sql) ;

         String cont = "::Awards for this Movie::" ;

         while (con.nextResult())
         {
            cont += "<br> -" + con.getResultString(1) ;
            controle = true ;
         }

         if (controle == false)
            cont += "No Awards" ;

         controle = false ;
         return cont ;
      }
      return "U have no rights" ;
   }

   /**
    * Shows a list of people of a certain role of a certain movie.
    */
   public void showCrews()
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
         String sql1 = "select rlsDescription from Comato.Roles where rlsID ="
               + rls_id ;

         con.generateResultSet(sql1) ;

         if (con.nextResult())
         {
            content += "<h3>" + con.getResultString(1) + "s</h3><br>" ;

            String sql2 = "select flcCharacter,prsID,flcAward from Comato.flmCrews where fliID ="
                  + fli_id
                  + "and rlsID ="
                  + rls_id ;

            con.generateResultSet(sql2) ;

            while (con.nextResult())
            {

               controle = true ;

               String character = con.getResultString(1) ;
               int personid = con.getResultInt(2) ;
               String award = con.getResultString(3) ;

               if (award == null || award == "")
                  award = "none" ;

               if (character == null || character == "")
                  character = "Unknown" ;

               String sql3 = "select prsName ,prsSex,prsBirthdate  from Comato.Persons where prsID ="
                     + personid ;

               con2.createConnection() ;
               con2.generateResultSet(sql3) ;

               if (con2.nextResult())
               {
                  content += "Character : "
                        + character
                        + "Real Name : "
                        + con2.getResultString(1)
                        + " Sex : "
                        + con2.getResultString(2)
                        + " Date of birth : "
                        + con2.getResultDate(3)
                        + " Award : "
                        + award ;
               }
               else
               {
                  content += "Person not found in database" ;
               }
               con2.closeResultset() ;
            }
            if (controle == false)
               content += "No persons found." ;

            con.closeResultset() ;
         }
         else
         {
            content += "Role not found in database. " ;
         }
         controle = false ;
      }
   }


   public void saveFlmInfo()
   {
      // set the authentication instance to the userId aquired from the session

      authentication.setPersonId(user_id) ;
      boolean authenticated = authentication.isAdmin() ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page

      design.setAccess(authenticated) ;

      // Sets the username in de design class, for fancy display

      design.setUserName(authentication.getusrUserName()) ;


      if (authenticated)
      {
         // Set the titel of this page.
         design.setTitel("Filminformation added ") ;

         // temporary int to store the parsed strings into.
         int flilength = 0 ;
         int fliprice = 0 ;

         // validate using inputControl
         input_control.checkEmpty(fli_name, "fliName") ;
         flilength = input_control.parseNumeric(fli_length, "fliLength") ;
         input_control.checkEmpty(fli_color, "fliColor") ;
         date = input_control.parseDate(fli_releasedate, "fliReleasedate") ;
         input_control.checkEmpty(fli_format, "fliFormat") ;
         fliprice = input_control.parseNumeric(fli_price, "fliPrice") ;
         input_control.checkEmpty(fli_productionhouse, "fliProductionhouse") ;


         // if validation failed
         if (input_control.isErrors())
         {
            buildForm() ;
         }
         else
         {

            try
            {
               FlmInfo flm ;

               con.createConnection() ;

               // if no cache id is found, create a new one
               // else open the given item from the database

               if (cache_id == null)
                  cache_id = "" ;
               if (cache_id.equals(""))
               {
                  flm = new FlmInfo(con.getConnection()) ;
               }
               else
               {
                  flm = (FlmInfo) FlmInfo._open(con.getConnection(), new Id(cache_id)) ;
               }

               // get the last id from the database

               String sql = "SELECT distinct MAX(fliID) FROM Comato.FlmInfo" ;

               // generate the resultset

               con.generateResultSet(sql) ;

               // Read one row from the resultset

               if (con.nextResult())
               {
                  // Get the id, and add 1 to the id, we will then use this id
                  // for
                  // the new oject

                  flm.setfliID(con.getResultLong(1) + 1) ;
               }
               else
               {
                  // If the resultset was empty we use 1 for the new id, in case
                  // of
                  // an empty table

                  flm.setfliID(new Long(1)) ;
               }

               // set all the values for this database object

               flm.setfliName(fli_name) ;
               flm.setfliLength(flilength) ;
               flm.setfliWebsite(fli_website) ;
               flm.setfliDescription(fli_description) ;
               flm.setfliColor(fli_color) ;
               flm.setfliReleaseDate(date) ;
               flm.setfliSoundtrack(fli_soundtrack) ;
               flm.setfliFormat(fli_format) ;
               flm.setfliPrice(fliprice) ;
               flm.setfliTargetGroup(fli_targetgroup) ;
               flm.setgnrID(Integer.parseInt(gnr_id)) ;
               flm.setfliProductionhouse(fli_productionhouse) ;
               flm.setcntID(Integer.parseInt(cnt_id)) ;
               flm.setdstID(Integer.parseInt(dst_id)) ;

               // save the object to the database

               flm.save() ;

               // display a message to the user, either we have edited an
               // existing
               // record or inserted an existing one

               if (cache_id.equals(""))
               {
                  content += flm.getfliName()
                        + " has been succesfully added to database "
                        + "<br><a href=\"index.jsp\">go back to film administration</a>" ;
               }
               else
               {
                  content += flm.getfliName()
                        + " has been changed succesfully"
                        + "<br><a href=\"index.jsp\">go back to film administration</a>" ;
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

   /**
    * Generates an html form based on the items in this class If it is possible
    * it will fill this form with the values asosiated with a complex. The form
    * is put inside the String content. If there were previous input errors,
    * these are displayed.
    */

   public void buildForm()
   {
      // set the authentication instance to the userId aquired from the session

      authentication.setPersonId(user_id) ;

      boolean authenticated = authentication.isAdmin() ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page

      design.setAccess(authenticated) ;

      // Sets the username in de design class, for fancy display

      design.setUserName(authentication.getusrUserName()) ;


      if (authenticated)
      {
         con.createConnection() ;

         content += "<h2>Add Movie</h2><form action=\"index.jsp?module=filminfo&function=add\" method=\"post\">" ;

         // create a new table object

         Table table = new Table(2) ;

         // create a new Input instance


         // add items to the table and go to the next row

         table.addItem("Name Film") ;
         table.addRedStarToLast();
         table.addItem(new Input("fli_name", fli_name, Input._TYPE_TEXT)) ;

         table.addToLastItem(input_control.getErrorMessage("fliName")) ;
         table.nextRow() ;

         table.addItem("Length") ;
         table.addRedStarToLast();
         table.addItem(new Input("fli_length", fli_length, Input._TYPE_TEXT)) ;

         table.addToLastItem(input_control.getErrorMessage("fliLength")) ;
         table.nextRow() ;

         table.addItem("Website") ;
         table.addItem(new Input("fli_website", fli_website, Input._TYPE_TEXT)) ;
         table.nextRow() ;

         table.addItem("Description") ;
         table.addItem(new Input("fli_description", fli_description, Input._TYPE_TEXT)) ;
         table.nextRow() ;

         table.addItem("Color") ;
         table.addRedStarToLast();
         table.addItem(new Input("fli_color", fli_color, Input._TYPE_TEXT)) ;
         
         table.addToLastItem(input_control.getErrorMessage("fliColor")) ;
         table.nextRow() ;

         table.addItem("Releasedate") ;
         table.addRedStarToLast();
         table.addItem(new Input("fli_releasedate", fli_releasedate, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("fliReleasedate")) ;
         table.nextRow() ;

         table.addItem("Soundtrack") ;
         table.addItem(new Input("fli_soundtrack", fli_soundtrack, Input._TYPE_TEXT)) ;
         table.nextRow() ;

         table.addItem("Format") ;
         table.addRedStarToLast();
         table.addItem(new Input("fli_format", fli_format, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("fliFormat")) ;
         table.nextRow() ;

         table.addItem("Price") ;
         table.addRedStarToLast();
         table.addItem(new Input("fli_price", fli_price, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("fliPrice")) ;
         table.nextRow() ;

         table.addItem("Targetgroup") ;
         table.addItem(new Input("fli_targetgroup", fli_targetgroup, Input._TYPE_TEXT)) ;
         table.nextRow() ;

         table.addItem("Genre") ;
         table.addItem(new DropDown("gnr_id", gnr_id, con, DropDown._TYPE_GENRES)) ;
         table.nextRow() ;

         table.addItem("Productionhouse") ;
         table.addRedStarToLast();
         table.addItem(new Input("fli_productionhouse", fli_productionhouse, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("fliProductionhouse")) ;
         table.nextRow() ;

         table.addItem("Country") ;
         table.addItem(new DropDown("cnt_id", cnt_id, con, DropDown._TYPE_COUNTRIES)) ;
         table.nextRow() ;

         table.addItem("Distributor") ;
         table.addItem(new DropDown("dst_id", dst_id, con, DropDown._TYPE_DISTRIBUTORS)) ;
         table.nextRow() ;

         table.addItem("");
         table.addRequiredPartOne();
         table.addItem("");
         table.addRequiredPartTwo();
         
         table.addItem(new Input("Submit", "Add", Input._TYPE_SUBMIT)) ;
         table.addItem("") ;

         table.nextRow() ;

         // get the table as string, line alternating mode is off


         content += table ;
         content += "</form>" ;
      }
   }


   public void editFilm()
   {
      // set the authentication instance to the userId aquired from the session

      authentication.setPersonId(user_id) ;

      boolean authenticated = authentication.isAdmin() ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page

      design.setAccess(authenticated) ;

      // Sets the username in de design class, for fancy display

      design.setUserName(authentication.getusrUserName()) ;


      if (authenticated)
      {
         con.createConnection() ;

         FlmInfo film ;

         try
         {
            // get the the object from the database

            film = (FlmInfo) FlmInfo._open(con.getConnection(), new Id(cache_id)) ;

            // fill the variables from the database

            fli_name = film.getfliName() ;
            fli_length = film.getfliLength().toString() ;
            fli_website = film.getfliWebsite() ;
            fli_description = film.getfliDescription() ;
            fli_color = film.getfliColor() ;
            fli_releasedate = input_control.dateString(film.getfliReleaseDate()) ;
            fli_soundtrack = film.getfliSoundtrack() ;
            fli_format = film.getfliFormat() ;
            fli_price = film.getfliPrice().toString() ;
            fli_targetgroup = film.getfliTargetGroup() ;
            gnr_id = film.getgnrID().toString() ;
            fli_productionhouse = film.getfliProductionhouse() ;
            cnt_id = film.getcntID().toString() ;
            dst_id = film.getdstID().toString() ;

            // build the form, it will use the above variables

            buildForm() ;

         }
         catch (CacheException e)
         {
            // set the errormessage in the design class

            design.setErrorMessage("<br>" + e.getMessage() + "<br>") ;
         }
      }
   }


   public void listFilm()
   {

      // set the authentication instance to the userId aquired from the session

      authentication.setPersonId(user_id) ;
      boolean authenticated = authentication.isAdmin() ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page

      design.setAccess(authenticated) ;

      // Sets the username in de design class, for fancy display

      design.setUserName(authentication.getusrUserName()) ;


      if (authenticated)
      {
         // create the connection

         con.createConnection() ;

         // set the title of this page

         design.setTitel("Film list") ;

         // declare a new table object with 15 colums

         Table table = new Table(8) ;


         // add the table headers to the table

         table.addThItem("Name") ;
         table.addThItem("Length") ;
         table.addThItem("Website") ;
         table.addThItem("Releasedate") ;
         table.addThItem("Price") ;
         table.addThItem("Genre") ;
         table.addThItem("Country") ;
         table.addThItem("&nbsp;") ;

         String sql = "SELECT f.ID, f.fliName, f.fliLength, f.fliWebsite, f.fliDescription, f.fliColor, f.fliReleaseDate, f.fliSoundtrack, f.fliFormat, f.fliPrice, f.fliTargetgroup, g.gnrDescription, f.fliProductionhouse, c.cntName, d.dstName FROM Comato.FlmInfo f, Niveau12.Countries c, Niveau12.Distributors d, Comato.Genres g where f.cntId = c.cntId and f.gnrId = g.gnrId and f.dstId = d.dstId" ;

         // generate a resultset inside the database class

         con.generateResultSet(sql) ;

         // Loop through the resultset

         while (con.nextResult())
         {
            // get the columns and place them inside the table
            table.addItem(con.getResultString(2)) ;
            table.addItem(con.getResultString(3)) ;
            table.addItem(con.getResultString(4)) ;
            table.addItem(input_control.dateString(con.getResultDate(7))) ;
            table.addItem(con.getResultString(10)) ;
            table.addItem(con.getResultString(12)) ;
            table.addItem(con.getResultString(14)) ;
            table.addItem(new Link("index.jsp?module=filminfo&function=edit&ID="
                  + con.getResultString(1), "Edit", "Edit this Movie")) ;

            // create a new row inside the table

            table.nextRow() ;
         }

         // get the html as a string alternating line mode is set tot true

         table.setMode(true) ;
         content += "<h2>Movie List</h2>" + table ;
      }
   }


   public String getContent()
   {
      String lastError = con.getLastError() ;
      boolean error = con.isErrors() ;

      con.closeResultset() ;
      design.setErrorMessage(lastError) ;

      // return the content string wrapped by the design class

      return design.getContent(content, error) ;
   }

   public void setContent(String content)
   {
      this.content = content ;
   }

   public String getFli_id()
   {
      return fli_id ;
   }

   public void setFli_id(String fli_id)
   {
      this.fli_id = fli_id ;
   }

   public String getFli_name()
   {
      return fli_name ;
   }

   public void setFli_name(String fli_name)
   {
      this.fli_name = fli_name ;
   }

   public String getFli_length()
   {
      return fli_length ;
   }

   public void setFli_length(String fli_length)
   {
      this.fli_length = fli_length ;
   }

   public String getFli_website()
   {
      return fli_website ;
   }

   public void setFli_website(String fli_website)
   {
      this.fli_website = fli_website ;
   }

   public String getFli_description()
   {
      return fli_description ;
   }

   public void setFli_description(String fli_description)
   {
      this.fli_description = fli_description ;
   }

   public String getFli_color()
   {
      return fli_color ;
   }

   public void setFli_color(String fli_color)
   {
      this.fli_color = fli_color ;
   }

   public String getFli_releasedate()
   {
      return fli_releasedate ;
   }

   public void setFli_releasedate(String fli_releasedate)
   {
      this.fli_releasedate = fli_releasedate ;
   }

   public String getFli_soundtrack()
   {
      return fli_soundtrack ;
   }

   public void setFli_soundtrack(String fli_soundtrack)
   {
      this.fli_soundtrack = fli_soundtrack ;
   }

   public String getFli_format()
   {
      return fli_format ;
   }

   public void setFli_format(String fli_format)
   {
      this.fli_format = fli_format ;
   }

   public String getFli_price()
   {
      return fli_price ;
   }

   public void setFli_price(String fli_price)
   {
      this.fli_price = fli_price ;
   }

   public String getFli_targetgroup()
   {
      return fli_format ;
   }

   public void setFli_targetgroup(String fli_targetgroup)
   {
      this.fli_targetgroup = fli_targetgroup ;
   }

   public String getGnr_id()
   {
      return gnr_id ;
   }

   public void setGnr_id(String gnr_id)
   {
      this.gnr_id = gnr_id ;
   }

   public String getFli_productionhouse()
   {
      return fli_productionhouse ;
   }

   public void setFli_productionhouse(String fli_productionhouse)
   {
      this.fli_productionhouse = fli_productionhouse ;
   }

   public String getCnt_id()
   {
      return cnt_id ;
   }

   public void setCnt_id(String cnt_id)
   {
      this.cnt_id = cnt_id ;
   }

   public String getDst_id()
   {
      return dst_id ;
   }

   public void setDst_id(String dst_id)
   {
      this.dst_id = dst_id ;
   }

   public String getCache_Id()
   {
      return cache_id ;
   }


   /**
    * @return Returns the userId.
    */
   public String getUser_id()
   {
      return user_id ;
   }


   /**
    * @param userId
    *           The userId to set.
    */
   public void setUser_id(String user_id)
   {
      this.user_id = user_id ;
   }


   public String getCache_id()
   {
      return cache_id ;
   }


   public void setCache_id(String cache_id)
   {
      this.cache_id = cache_id ;
   }


   public String getRls_id()
   {
      return rls_id ;
   }


   public void setRls_id(String rls_id)
   {
      this.rls_id = rls_id ;
   }

}
