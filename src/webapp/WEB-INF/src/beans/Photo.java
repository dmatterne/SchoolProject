package beans ;

import html.DropDown ;
import html.Table ;

import java.io.File ;
import java.io.FileInputStream ;
import java.io.FileNotFoundException ;
import java.io.IOException ;
import java.io.Serializable ;

import com.intersys.objects.CacheException ;
import com.intersys.objects.CacheOutputStream ;

import Comato.Photos ;

import database.Authentication ;
import database.Connection ;

import design.DesignWrapper ;

/**
 * This Class makes it possible to upload a photo of a movie to the database. It
 * Also makes it possible to show a list of these photos and to show the photo
 * on the website. The list will contain thumbs , wich can be clicked so u can
 * see the entire picture. Only 6 thumbnails will be shown on each page.
 * 
 * @author ICT09
 */
public class Photo implements Serializable
{

   private static final long serialVersionUID = 2369546954325602L ;

   private static final String basic_path = "d:\\Sun\\AppServer\\domains\\domain1\\docroot\\uploads\\" ;

   private String content = "" ;

   private boolean bln_test = false ;

   private String fli_id ;

   private String pht_id ;

   private String pht_name ;

   private String pht_photo ;

   private String pht_cont_type ;

   private int pht_length ;

   private String pht_path ;

   private String cont_id ;

   private Connection con = new Connection() ;

   private Authentication authentication = new Authentication(con) ;

   private DesignWrapper design = new DesignWrapper(authentication) ;

   private String user_id ;


   /**
    * This methods creates thumbs and shows them in the content of the webpage
    */
   public void listThumbs()
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

         // We only display 6 photos on one page.
         int pos = 0 ;
         if (cont_id == null)
         {
            pos = 0 ;
         }
         else
         {
            pos = Integer.parseInt(cont_id) ;
         }

         String sql = "select ID,phtName from Comato.Photos where fliID =" + fli_id ;
         String sql2 = "select count(ID) from Comato.Photos where fliID =" + fli_id ;

         con.createConnection() ;
         con.generateResultSet(sql2) ;

         // See how many photos have been added into database.

         int teller = 0 ;
         if (con.nextResult())
         {
            teller = con.getResultInt(1) ;
         }

         Table table = new Table(2) ;
         table.setAlignment(Table._ALIGN_CENTER) ;

         // We need to make sure only 6 photos are being displayed.

         if (pos != 0)
         {
            bln_test = true ;
         }
         int count = 0 ;


         con.generateResultSet(sql) ;

         int a = 0 ;
         while (con.nextResult() && a < 6)
         {
            if (pos <= count)
            {
               String ContFlmName = con.getResultString(2) ;

               // The thumbnails with their link to the normal photo are being
               // added to table.

               table.addItem("<a href=\"showImage?ID=" + con.getResultString(1)
                     + "&type=normal\" target=\"_blank\"><center><img vspace=15 src=\"showImage?ID=" + con.getResultString(1) + "&type=thumb\"><br>"
                     + ContFlmName + "</center></a>") ;
               a++ ;
            }
            count++ ;
         }
         table.nextRow() ;

         Table tableBut = new Table(2) ;

         if (bln_test == true)
         {
            tableBut.addItem("<form method=\"post\" action=\"index.jsp?module=photo&amp;function=ListPhoto&amp;type=thumb&amp;contid=" + (pos - 6)
                  + "&amp;ID=" + fli_id + "\"><input type=\"submit\" name=\"Submit\" value=\"Previous\"></form>") ;
         }
         if (teller > 6 + pos)
         {
            tableBut.addItem("<form method=\"post\" action=\"index.jsp?module=photo&amp;function=ListPhoto&amp;type=thumb&amp;contid=" + (pos + 6)
                  + "&amp;ID=" + fli_id + "\"><input type=\"submit\" name=\"Submit\" value=\"Next\"></form>") ;
         }
         tableBut.nextRow() ;

         if (a == 0)
            content += "Sorry we were unable to find any screenshots for this movie." ;
         else
            table.setMode(true) ;
         content += tableBut + "" + table ;

         con.closeResultset() ;
      }
   }

   /**
    * This method allows u to choose the movie of wich certain thumbs will be
    * displayed
    */
   public void chooseMovieThumb()
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


      if (authenticated)
      {

         content += "<h2>Show Photos</h2><br>"
               + "<form name=\"PhotoForm\">"
               + new DropDown("FilmJump", "", true, "index.jsp?module=photo&amp;function=ListPhoto&amp;type=thumb&amp;contid=0&amp;ID=", "", con,
                     DropDown._TYPE_FILMINFO)
               + "<input type=\"button\" name=\"Button1\" value=\"Go\" onClick=\"MM_jumpMenuGo('FilmJump','parent',0)\">" + "</form>" ;
      }
   }

   /**
    * This method will save the movie into the database
    */
   public void fillPhoto()
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
         try
         {
            con.createConnection() ;

            String sql = "Select distinct MAX(phtID) from Comato.Photos" ;
            Photos pht = new Photos(con.getConnection()) ;
            String basename = Photo.getBaseName(pht_path) ;

            // File that was uploaded through jsp is being called here
            // and will be deleted after its added to the Database.

            File file = new File(basic_path + basename) ;
            FileInputStream in = new FileInputStream(file) ;

            file.length() ;

            CacheOutputStream in2 = pht.getphtPhotoOut() ;

            pht_length = (int) file.length() ;

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

               pht.setphtID(con.getResultInt(1) + 1) ;

            }
            else
            {
               // if there is no record yet the phtid will get value 1.

               pht.setphtID(1) ;

            }

            pht.setfliID(Integer.parseInt(fli_id)) ;
            pht.setphtLength(pht_length) ;
            pht.setphtName(basename) ;
            pht.setphtContentType(pht_cont_type) ;
            pht.save() ;
            in.close() ;
            content += " This Photo was succesfully Uploaded !" ;
            // We dont need the file on computer so we delete it.

            file.deleteOnExit() ;
            con.closeResultset() ;
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
    * This Method creates the html form for uploading the photos
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


      if (authenticated)
      {
         // Upload form for photos

         content += "<h2>Upload Photos</h2><br>"
               + "<form method=\"post\" action=\"index.jsp?module=photo&amp;function=AddPhoto\" name=\"upform\" enctype=\"multipart/form-data\">"
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
    * Retrieves the id of the Photo.
    * 
    * @return returns the pht_id.
    */
   public String getPht_id()
   {
      return pht_id ;
   }

   /**
    * Sets the photoid of the Photo.
    * 
    * @param pht_id
    *           Id of the Photo.
    */
   public void setPht_id(String pht_id)
   {
      this.pht_id = pht_id ;
   }

   /**
    * Retrieves the length of the photo.
    * 
    * @return the length of the photo.
    */
   public int getPht_length()
   {
      return pht_length ;
   }

   /**
    * Sets the length of the photo.
    * 
    * @param pht_length
    *           length of the photograph.
    */
   public void setPht_length(int pht_length)
   {
      this.pht_length = pht_length ;
   }

   /**
    * Gets the name of the Photo.
    * 
    * @return name of the Photo.
    */
   public String getPht_name()
   {
      return pht_name ;
   }

   /**
    * Sets the name of the Photo.
    * 
    * @param pht_name
    *           Name of the Photo.
    */
   public void setPht_name(String pht_name)
   {
      this.pht_name = pht_name ;
   }

   /**
    * Retrieves the path of the photo.
    * 
    * @return the string pht_path.
    */
   public String getPht_path()
   {
      return pht_path ;
   }

   /**
    * Writes the given pathname to the variable pht_path.
    * 
    * @param pht_path -
    *           Path of file.
    */
   public void setPht_path(String pht_path)
   {
      this.pht_path = pht_path ;
   }

   /**
    * Retrieves the string pht_photo.
    * 
    * @return the string pht_photo.
    */
   public String getPht_photo()
   {
      return pht_photo ;
   }

   /**
    * Writes the given string to the variable pht_photo.
    * 
    * @param pht_photo -
    *           String containing the stream for setting the variable pht_photo.
    */
   public void setPht_photo(String pht_photo)
   {
      this.pht_photo = pht_photo ;
   }


   public String getCont_id()
   {
      return cont_id ;
   }


   public void setCont_id(String cont_id)
   {
      this.cont_id = cont_id ;
   }


   public String getPht_cont_type()
   {
      return pht_cont_type ;
   }


   public void setPht_cont_type(String pht_cont_type)
   {
      this.pht_cont_type = pht_cont_type ;
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
