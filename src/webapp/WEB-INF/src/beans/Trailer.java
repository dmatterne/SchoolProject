package beans ;

import html.DropDown ;
import html.Link ;

import java.io.File ;
import java.io.FileInputStream ;
import java.io.FileNotFoundException ;
import java.io.IOException ;
import java.io.Serializable ;

import com.intersys.objects.CacheException ;
import com.intersys.objects.CacheOutputStream ;

import Comato.Trailers ;

import database.Authentication ;
import database.Connection ;

import design.DesignWrapper ;

/**
 * This Class makes it possible to upload a trailer of a movie to the database.
 * It Also makes it possible to show a list of these trailers and to show the
 * trailer on the website. The Trailer will be shown in a quicktime plugin, and
 * only certain types of trailers can be uploaded The sizelimit of the trailer
 * is also set.
 * 
 * @author ICT09
 */
public class Trailer implements Serializable
{

   private static final long serialVersionUID = 243695476891234L ;

   private static final String basic_path = "d:\\Sun\\AppServer\\domains\\domain1\\docroot\\uploads\\" ;

   private String content = "" ;

   private boolean blntest = false ;

   private String fli_id ;

   private String trl_id ;

   private String trl_name ;

   private String trl_trailer ;

   private int trl_length ;

   private String trl_path ;

   private Connection con = new Connection() ;

   private Authentication authentication = new Authentication(con) ;

   private DesignWrapper design = new DesignWrapper(authentication) ;

   private String user_id ;


   /**
    * This method allows u to choose the movie of wich certain trailers will be
    * displayed
    */
   public void chooseMovie()
   {
      // Here we create the form where we can choose of wich movie we want to
      // see the thumbnails.
      // Droptable is being filled in dropdown.java

      authentication.setPersonId(user_id) ;

      boolean authenticated = true ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page

      design.setAccess(authenticated) ;

      // pass authentication to design class

      design.setAuthentication(authentication) ;

      // Sets the username in de design class, for fancy display

      design.setUserName(authentication.getUsrUserName()) ;

      // Create a form where u can choose of wich movie u want to sea the
      // trailers

      if (authenticated)
      {
         content += "<h2>Show Trailer</h2><br>" + "<form name=\"PhotoForm\">"
               + new DropDown("FilmJump", "", true, "index.jsp?module=trailer&amp;function=ListTrailer&amp;ID=", "", con, DropDown._TYPE_FILMINFO)
               + "<input type=\"button\" name=\"Button1\" value=\"Go\" onClick=\"MM_jumpMenuGo('FilmJump','parent',0)\">" + "</form>" ;
      }
   }

   /**
    * This method will save the movie into the database
    */
   public void fillTrailer()
   {
      try
      {
         authentication.setPersonId(user_id) ;

         boolean authenticated = authentication.isAdmin() ;

         // pass the acces to the design class,
         // only admins are allowed to view the content of this page

         design.setAccess(authenticated) ;

         // pass authentication to design class

         design.setAuthentication(authentication) ;

         // Sets the username in de design class, for fancy display

         design.setUserName(authentication.getUsrUserName()) ;


         if (authenticated)
         {
            con.createConnection() ;

            String sql = "Select distinct MAX(trlID) from Comato.Trailers" ;
            Trailers trl = new Trailers(con.getConnection()) ;
            String basename = Trailer.getBaseName(trl_path) ;

            // File that was uploaded through jsp is being called here
            // and will be deleted after its added to the Database.

            File file = new File(basic_path + basename) ;
            FileInputStream in = new FileInputStream(file) ;

            file.length() ;

            CacheOutputStream in2 = trl.gettrlTrailerOut() ;

            trl_length = (int) file.length() ;

            byte[] output = new byte[1024] ;

            // pht_photo is a stream object and so we fill it with streams of
            // byte.

            int bytesRead = 0 ;

            if (file != null)
            {
               while ((bytesRead = in.read(output)) != -1)
               {
                  in2.write(output, 0, bytesRead) ;
               }
               in.close() ;
            }

            con.generateResultSet(sql) ;

            if (con.nextResult())
            {
               // if there is a record already the highest phtId needs to be
               // upped
               // by 1.

               trl.settrlID(con.getResultInt(1) + 1) ;

            }
            else
            {
               // if there is no record yet the phtid will get value 1.

               trl.settrlID(1) ;

            }

            // insert values into database

            trl.setfliID(Integer.parseInt(fli_id)) ;
            trl.settrlLength(trl_length) ;
            trl.settrlName(basename) ;
            trl.save() ;
            in.close() ;

            content += " This Trailer was succesfully Uploaded !" ;

            // We dont need the file on computer so we delete it.

            con.closeResultset() ;
            file.deleteOnExit() ;
         }
      }
      catch (CacheException e)
      {
         content += e.getMessage() ;
      }
      catch (FileNotFoundException e)
      {
         content += e.getMessage() ;
      }
      catch (IOException e)
      {
         content += e.getMessage() ;
      }
   }

   /**
    * Creates a list of trailers for a certain movie.
    */
   public void listTrailer()
   {
      authentication.setPersonId(user_id) ;

      boolean authenticated = true ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page

      design.setAccess(authenticated) ;

      // pass authentication to design class

      design.setAuthentication(authentication) ;

      // Sets the username in de design class, for fancy display

      design.setUserName(authentication.getUsrUserName()) ;


      if (authenticated)
      {
         String sql = "select ID,trlName from Comato.Trailers where fliID =" + fli_id ;

         con.createConnection() ;
         con.generateResultSet(sql) ;

         content += "<h2>List Trailers</h2>" ;
         while (con.nextResult())
         {
            // Make a list of links for each trailer

            blntest = true ;
            content += new Link("index.jsp?module=trailer&amp;function=seeTrailer&amp;ID=" + con.getResultInt(1) + "\" target=\"_blank\"", con
                  .getResultString(2), "")
                  + "<br>" ;
         }
         if (blntest == false)
            content += "No Trailer Found" ;
      }
   }

   /**
    * Builds the trailer in the quicktime plugin
    * 
    * @param number
    *           number containing the trlID from the trailer
    */
   public void buildTrailer(String number)
   {
      authentication.setPersonId(user_id) ;

      boolean authenticated = true ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page

      design.setAccess(authenticated) ;

      // pass authentication to design class

      design.setAuthentication(authentication) ;

      // Sets the username in de design class, for fancy display

      design.setUserName(authentication.getUsrUserName()) ;

      // Creates the quicktime plugin so we can show the trailers on website

      if (authenticated)
      {
         content += "<EMBED SRC=\"showTrailer?ID="
               + number
               + "\" scale=\"aspect\" WIDTH=\"100%\" HEIGHT=\"250\" TYPE=\"video/quicktime\" PLUGINSPAGE=\"http://www.apple.com/quicktime/download/\" CONTROLS=\"ControlPanel\" LOOP=\"1\" VOLUME=\"25\"></EMBED>"
               + "<NOEMBED>Your browser does not support the quicktime plug-in</NOEMBED>" ;
      }
   }

   /**
    * @param fileName
    *           gets the filename of wich the basename needs to be made
    * @return the basename of the filename
    */
   public static String getBaseName(String fileName)
   {
      // Returns name without path.

      int ix = fileName.lastIndexOf("\\") ;
      if (ix < 0)
         return fileName ;
      return fileName.substring(ix + 1) ;
   }

   /**
    * This Method creates the html form for uploading the trailers
    */
   public void buildForm()
   {
      // Form is created with droptable wich contains movies.
      // U can choose the movie u want to see photos of.

      authentication.setPersonId(user_id) ;

      boolean authenticated = authentication.isAdmin() ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page

      design.setAccess(authenticated) ;

      // pass authentication to design class

      design.setAuthentication(authentication) ;

      // Sets the username in de design class, for fancy display

      design.setUserName(authentication.getUsrUserName()) ;

      // Creates the upload form for the Trailers

      if (authenticated)
      {
         content += "<h2>Upload Trailers</h2><br>"
               + "<form method=\"post\" action=\"index.jsp?module=trailer&amp;function=AddTrailer\" name=\"upform\" enctype=\"multipart/form-data\">"
               + "<p>" + "<form name=\"UploadPhotoForm\">" + new DropDown("film", "", con, DropDown._TYPE_FILMINFO) + "</p>" + "<p>"
               + "<input name=\"uploadfile\" type=\"file\">" + "</p>" + "<p>" + "<input type=\"hidden\" name=\"todo\" value=\"upload\">"
               + "<input type=\"submit\" name=\"Submit\" value=\"Upload\">" + "<input type=\"reset\" name=\"Reset\" value=\"Cancel\">" + "</p>"
               + "</form>" ;
      }
   }

   /**
    * This method sets the content for website
    * 
    * @return the content of website
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
    * Sets the Content String.
    * 
    * @param content
    *           Content containing html code.
    */
   public void setContent(String content)
   {
      this.content = content ;
   }

   /**
    * Retrieves the id of the Film.
    * 
    * @return the film id.
    */
   public String getFli_id()
   {
      return fli_id ;
   }

   /**
    * Sets the id of the film.
    * 
    * @param fli_id
    *           Id of film.
    */
   public void setFli_id(String fli_id)
   {
      this.fli_id = fli_id ;
   }

   /**
    * Retrieves the userid.
    * 
    * @return the id of the user
    */
   public String getUser_id()
   {
      return user_id ;
   }

   /**
    * Sets the id of the user.
    * 
    * @param userid300
    *           Used for defining user rights.
    */
   public void setUser_id(String user_id)
   {
      this.user_id = user_id ;
   }


   /**
    * Retrieves the id of the Trailer.
    * 
    * @return returns the trl_id.
    */
   public String getTrl_id()
   {
      return trl_id ;
   }

   /**
    * Sets the Trailerid of the Trailer.
    * 
    * @param trl_id
    *           Id of the Trailer.
    */
   public void setTrl_id(String trl_id)
   {
      this.trl_id = trl_id ;
   }

   /**
    * Retrieves the length of the Trailer.
    * 
    * @return the length of the Trailer.
    */
   public int getTrl_length()
   {
      return trl_length ;
   }

   /**
    * Sets the length of the Trailer.
    * 
    * @param trl_length
    *           Length of the Trailer.
    */
   public void setTrl_length(int trl_length)
   {
      this.trl_length = trl_length ;
   }

   /**
    * Gets the name of the Trailer.
    * 
    * @return name of the Trailer.
    */
   public String getTrl_name()
   {
      return trl_name ;
   }

   /**
    * Sets the name of the Trailer.
    * 
    * @param trl_name
    *           Name of the Trailer.
    */
   public void setTrl_name(String trl_name)
   {
      this.trl_name = trl_name ;
   }

   /**
    * Retrieves the path of the Trailer.
    * 
    * @return the string trl_path.
    */
   public String getTrl_path()
   {
      return trl_path ;
   }

   /**
    * Writes the given pathname to the variable trl_path.
    * 
    * @param trl_path -
    *           Path of file.
    */
   public void setTrl_path(String trl_path)
   {
      this.trl_path = trl_path ;
   }

   /**
    * Retrieves the string trl_trailer.
    * 
    * @return the string trl_trailer.
    */
   public String getTrl_trailer()
   {
      return trl_trailer ;
   }

   /**
    * Writes the given string to the variable trl_trailer.
    * 
    * @param trl_trailer -
    *           String containing the stream for setting the variable
    *           trl_trailer.
    */
   public void setTrl_trailer(String trl_trailer)
   {
      this.trl_trailer = trl_trailer ;
   }

}
