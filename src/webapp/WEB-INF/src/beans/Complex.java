package beans ;

import html.DropDown ;
import html.Input ;
import html.Link ;
import html.Table ;

import java.io.Serializable ;

import Niveau34.CmpFeatures ;

import com.intersys.objects.CacheException ;
import com.intersys.objects.Id ;

import database.Authentication ;
import database.Connection ;
import database.InputControl ;

import design.DesignWrapper ;


/**
 * This class provides a way to add and edit a certain complex and it also
 * provides a list of these complexes. The constructor of this class needs a
 * Database.Connection object to accomplish all this. This can be the same
 * Connection object you use in other java files. If you want to add, edit or
 * list u need to have certain user rights.
 * 
 * @author ICT09
 */
public class Complex implements Serializable
{

   private static final long serialVersionUID = -5497417394572677623L ;

   private String cache_id = "" ;

   private String cmf_id = "" ;

   private String cmf_name = "" ;

   private String cnt_id = "" ;

   private String cmf_address = "" ;

   private String cmf_constructionyear = "" ;

   private String cmf_type = "" ;

   private String content = "" ;

   private String user_id = "0" ;

   CmpFeatures cmf ;

   private Connection con = new Connection() ;

   private InputControl input_control = new InputControl(con) ;

   private Authentication authentication = new Authentication(con) ;

   private DesignWrapper design = new DesignWrapper(authentication) ;


   /**
    * Saves all fields into the database. If cache_id is set it updates an
    * existing item otherwise it will create a new Complex in the database.
    */

   public void save()
   {
      // set the authentication instance to the userid aquired from the session

      authentication.setPersonId(user_id) ;

      boolean authenticated = authentication.isAdmin() ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page

      design.setAccess(authenticated) ;


      // Sets the username in de design class, for fancy display

      design.setUserName(authentication.getUsrUserName()) ;


      if (authenticated)
      {
         // Set the titel of this page.

         design.setTitel("Complex toegevoegd") ;

         // temporary int to store the parsed strings into.

         int cmfconstructionyear = 0 ;

         // validate using inputcontrol

         input_control.checkEmpty(cmf_name, "cmfName") ;
         input_control.checkEmpty(cmf_address, "cmf_address") ;
         cmfconstructionyear = input_control.parseNumeric(cmf_constructionyear, "cmf_constructionyear") ;
         input_control.checkEmpty(cmf_type, "cmf_type") ;

         // if validation failed

         if (input_control.isErrors())
         {
            buildForm() ;
         }
         else
         {

            try
            {

               CmpFeatures cmp ;

               con.createConnection() ;

               // if no cache id is found, create a new one
               // else open the given item from the database

               if (cache_id == null)
                  cache_id = "" ;

               if (cache_id.equals(""))
               {
                  cmp = new CmpFeatures(con.getConnection()) ;

               }
               else
               {
                  cmp = (CmpFeatures) CmpFeatures._open(con.getConnection(), new Id(cache_id)) ;

               }

               // get the last id from the database

               String sql = "SELECT distinct MAX(cmfID) FROM Niveau34.CmpFeatures" ;

               // generate the resultset

               con.generateResultSet(sql) ;

               // read 1 row from the resultset

               if (con.nextResult())
               {
                  // get the id, and add 1 to the id, we will then use this id
                  // for
                  // the new object

                  cmp.setcmfID(con.getResultInt(1) + 1) ;
               }
               else
               {
                  // if the resultset was empty we use 1 for the new id, in case
                  // of
                  // an empty table

                  cmp.setcmfID(1) ;
               }

               // set all the values for this database object

               cmp.setcntID(Integer.parseInt(cnt_id)) ;
               cmp.setcmfAddress(cmf_address) ;
               cmp.setcmfConstructionYear(cmfconstructionyear) ;
               cmp.setcmfName(cmf_name) ;
               cmp.setcmfType(getCmf_type()) ;

               // save the object to the database

               cmp.save() ;

               // display a message to the user, either we have edited an
               // existing
               // record or
               // inserted an existing one

               if (cache_id.equals(""))
               {
                  content += cmp.getcmfName() + " Succesfully added to the database!" ;
               }
               else
               {
                  content += cmp.getcmfName() + " Succesfully updated!" ;
               }

            }
            catch (Exception e)
            {

               // in case of an error we will set the errormessage inside the
               // design class

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

      // set the authentication instance to the userid aquired from the session

      authentication.setPersonId(user_id) ;

      boolean authenticated = authentication.isAdmin() ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page

      design.setAccess(authenticated) ;

      // Sets the username in de design class, for fancy display

      design.setUserName(authentication.getUsrUserName()) ;


      if (authenticated)
      {
         // create the connection

         con.createConnection() ;
         if (this.cache_id != null && this.cache_id != "")
            content += "<h2>Edit Complex</h2>" ;
         else
            content += "<h2>Add Complex</h2>" ;

         content += "<form action=\"index.jsp?module=complex&function=add\" method=\"post\">" ;


         // create a new table object

         Table table = new Table(2) ;


         // add items to the table and go to the next row

         table.addItem("Name Complex") ;
         table.addRedStarToLast() ;
         table.addItem(new Input("cmf_name", cmf_name, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("cmfName")) ;
         table.nextRow() ;

         table.addItem("Country") ;

         table.addItem(new DropDown("cnt_id", cnt_id, con, DropDown._TYPE_COUNTRIES)) ;
         table.nextRow() ;

         table.addItem("Address") ;
         table.addRedStarToLast() ;
         table.addItem(new Input("cmf_address", cmf_address, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("cmf_address")) ;
         table.nextRow() ;

         table.addItem("Construction&nbsp;Year") ;
         table.addRedStarToLast() ;
         table.addItem(new Input("cmf_constructionyear", cmf_constructionyear, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("cmf_constructionyear")) ;
         table.nextRow() ;

         table.addItem("Type&nbsp;Complex") ;
         table.addRedStarToLast() ;
         table.addItem(new Input("cmf_type", cmf_type, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("cmf_type")) ;
         table.nextRow() ;

         table.addItem("") ;
         table.addRequiredPartOne() ;
         table.addItem("") ;
         table.addRequiredPartTwo() ;

         // get the table as string, line alternating mode is off.

         content += table + "<br>" + new Input("submit", "Add", Input._TYPE_SUBMIT) + "</form>" ;
      }
   }

   /**
    * Opens an existing item for editing and sets this data into the items in
    * this class. Then it asks buildform to save this.
    */
   public void edit()
   {
      // set the authentication instance to the userid aquired from the session

      authentication.setPersonId(user_id) ;

      boolean authenticated = authentication.isAdmin() ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page

      design.setAccess(authenticated) ;


      // Sets the username in de design class, for fancy display

      design.setUserName(authentication.getUsrUserName()) ;


      if (authenticated)
      {
         con.createConnection() ;


         try
         {
            // get the the object from the database

            cmf = (CmpFeatures) CmpFeatures._open(con.getConnection(), new Id(cache_id)) ;

            // fill the variables from the database

            cmf_name = cmf.getcmfName() ;
            cnt_id = cmf.getcntID().toString() ;
            cmf_address = cmf.getcmfAddress() ;
            cmf_constructionyear = cmf.getcmfConstructionYear().toString() ;
            cmf_type = cmf.getcmfType() ;

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

   /**
    * Displays a list of Complexes. inside a htms table This table is put inside
    * the String content
    */

   public void list()
   {
      // set the authentication instance to the userid aquired from the session

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

         // Set the titel of this page

         design.setTitel("Complex list") ;

         content += "<h2>Complex List</h2>";
         // declare a new table object with 6 columns
         

         Table table = new Table(6) ;

         // declare a new link instance.


         // add the table headers to the table

         table.addThItem("Name") ;
         table.addThItem("Country") ;
         table.addThItem("Construction&nbsp;Year") ;
         table.addThItem("type") ;
         table.addThItem("Hall&nbsp;Amount") ;
         table.addThItem("&nbsp;") ;

         String sql = "SELECT cmp.ID, cmp.cmfName, c.cntName, cmp.cmfConstructionyear, cmp.cmfType, (select count(hlcID) from Niveau34.HllComplex hall where hall.cmfID=cmp.cmfID) FROM Niveau34.CmpFeatures cmp, Niveau12.Countries c where cmp.cntId = c.cntId" ; 
                                                                                                                                                                                                                                                                    
         con.generateResultSet(sql) ;

         // loop trough the resultset

         while (con.nextResult())
         {
            // get the columns and place them inside the table

            table.addItem(con.getResultString(2)) ;
            table.addItem(con.getResultString(3)) ;
            table.addItem(con.getResultString(4)) ;
            table.addItem(con.getResultString(5)) ;
            table.addItem(con.getResultString(6)) ;
            table.addItem(new Link("index.jsp?module=complex&function=edit&ID=" + con.getResultString(1), "Edit", "Edit this Complex")) ;

            // create a new row inside the table

            table.nextRow() ;
         }

         // get the html table as a string, alternating line mode is set to true

         table.setMode(true) ;

         content += table ;
      }


   }

   /**
    * Returns the string content. This string can first be built using other
    * functions in this repository_bean.
    * 
    * @return
    *  content string.
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
    * Sets the 
    * @param content
    */
   public void setcontent(String content)
   {
      this.content = content ;
   }

   public String getCmf_address()
   {
      return cmf_address ;
   }

   public void setCmf_address(String cmf_address)
   {
      this.cmf_address = cmf_address ;
   }

   public String getCmf_id()
   {
      return cmf_id ;
   }

   public void setCmf_id(String cmf_id)
   {
      this.cmf_id = cmf_id ;
   }

   public String getCmf_name()
   {
      return cmf_name ;
   }

   public void setCmf_name(String cmf_name)
   {
      this.cmf_name = cmf_name ;
   }

   public String getCmf_type()
   {
      return cmf_type ;
   }

   public void setCmf_type(String cmf_type)
   {
      this.cmf_type = cmf_type ;
   }

   public String getCnt_id()
   {
      return cnt_id ;
   }

   public void setCnt_id(String cnt_id)
   {
      this.cnt_id = cnt_id ;
   }

   public String getCache_id()
   {
      return cache_id ;
   }

   public void setCache_id(String cache_Id)
   {
      this.cache_id = cache_Id ;
   }

   public String getCmf_constructionyear()
   {
      return cmf_constructionyear ;
   }

   public void setCmf_constructionyear(String cmf_constructionyear)
   {
      this.cmf_constructionyear = cmf_constructionyear ;
   }


   public String getUser_id()
   {
      return user_id ;
   }


   public void setContent(String content)
   {
      this.content = content ;
   }


   public void setUser_id(String user_id)
   {
      this.user_id = user_id ;
   }

}
