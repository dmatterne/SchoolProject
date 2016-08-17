/**
 * 
 */
package beans ;

import java.io.Serializable ;

import Niveau34.HllComplex ;
import html.DropDown ;
import html.Input ;
import html.Table ;
import database.Authentication ;
import database.Connection ;
import database.InputControl ;
import design.DesignWrapper ;


/**
 * Makes it possible for an admin to add a hall. He can also view a list of
 * halls for each complex. The hall will be saved into the database.
 * 
 * @author ICT09
 */
public class Hall implements Serializable
{

   private static final long serialVersionUID = 2436547356889L ;

   private String cmf_id ;

   private String hlc_bookable ;

   private String hlc_capacity ;

   private String hlc_cupholder ;

   private String hlc_digitalversion ;

   private String hlc_distancescreenprojection ;

   private String hlc_screenwidth ;

   private String hlc_spaceinfrontofscreen ;

   private String hlc_twinseat ;

   private String hlc_wheelchair ;

   private String content = "" ;

   private String user_id = "" ;

   private HllComplex hlc ;

   private Connection con = new Connection() ;

   private InputControl input_control = new InputControl(con) ;

   private Authentication authentication = new Authentication(con) ;

   private DesignWrapper design = new DesignWrapper(authentication) ;

   /**
    * Creates a list of halls with their features
    */
   public void list()
   {
      // set the authentication instance to the userId aquired from the session

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
         content += "<h2>Hall List</h2>" + "<br><h3>Choose Complex</h3>"
               + new DropDown("cmf_link_id", cmf_id, true, "index.jsp?module=hall&function=list&cmf_id=", "", con, DropDown._TYPE_COMPLEX) + "<br>" ;

         if (cmf_id != null || true)
         {
            content += "<br><h3>Hall list</h3>" ;

            con.createConnection() ;

            // Set the titel of this page

            design.setTitel("Hall list") ;

            // declare a new table object with 6 columns

            Table table = new Table(6) ;


            // add the table headers to the table

            table.addThItem("Number") ;
            table.addThItem("Capacity") ;
            table.addThItem("Cupholder") ;
            table.addThItem("Digital") ;
            table.addThItem("Twinseat") ;
            table.addThItem("Wheelchair") ;

            String sql = "SELECT distinct hlcID, hlcCapacity, hlcCupholder, hlcDigitalVersion, hlcTwinseat, hlcWheelchair FROM Niveau34.HllComplex where cmfId="
                  + cmf_id ;

            // generate a resultset inside the database class

            con.generateResultSet(sql) ;

            // loop trough the resultset

            while (con.nextResult())
            {
               // get the columns and place them inside the table
               table.addItem(con.getResultString(1)) ;
               table.addItem(con.getResultString(2)) ;
               table.addItem(con.getResultString(3)) ;
               table.addItem(con.getResultString(4)) ;
               table.addItem(con.getResultString(5)) ;
               table.addItem(con.getResultString(6)) ;
               table.nextRow() ;

            }

            // get the html table as a string, alternating line mode is set to
            // true

            table.setMode(true) ;

            content += table ;
         }


      }
   }

   /**
    * Saves the hall into the database.
    */
   public void save()
   {
      // set the authentication instance to the userId aquired from the session

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


         // validate using inputControl

         input_control.checkEmpty(hlc_screenwidth, "hlc_screenwidth") ;
         input_control.checkEmpty(hlc_spaceinfrontofscreen, "hlc_spaceinfrontofscreen") ;
         int hlcCapacity = input_control.parseNumeric(hlc_capacity, "hlc_capacity") ;
         int hlcDistancescreenprojection = input_control.parseNumeric(hlc_distancescreenprojection, "hlc_distancescreenprojection") ;

         // if validation failed

         if (input_control.isErrors())
         {
            buildForm() ;
         }
         else
         {

            try
            {


               con.createConnection() ;

               // if no cache id is found, create a new one
               // else open the given item from the database

               // get the last id from the database

               hlc = new HllComplex(con.getConnection()) ;

               String sql = "SELECT distinct MAX(hlcID) FROM Niveau34.HllComplex where cmfId=" + cmf_id ;

               // generate the resultset

               con.generateResultSet(sql) ;

               // read 1 row from the resultset

               if (con.nextResult())
               {
                  // get the id, and add 1 to the id, we will then use this id
                  // for
                  // the new object

                  hlc.sethlcID(con.getResultInt(1) + 1) ;
               }
               else
               {
                  // if the resultset was empty we use 1 for the new id, in case
                  // of
                  // an empty table

                  hlc.sethlcID(1) ;
               }


               hlc.setcmfID(Integer.parseInt(cmf_id)) ;
               hlc.sethlcTwinseat(Boolean.parseBoolean(this.hlc_twinseat)) ;
               hlc.sethlcWheelchair(Boolean.parseBoolean(this.hlc_wheelchair)) ;
               hlc.sethlcDigitalVersion(Boolean.parseBoolean(this.hlc_digitalversion)) ;
               hlc.sethlcCupHolder(Boolean.parseBoolean(this.hlc_cupholder)) ;
               hlc.sethlcCapacity(hlcCapacity) ;
               hlc.sethlcBookable(Boolean.parseBoolean(this.hlc_bookable)) ;
               hlc.sethlcDistanceScreenProjection(hlcDistancescreenprojection) ;
               hlc.sethlcSpaceInFrontOfScreen(this.hlc_spaceinfrontofscreen) ;
               hlc.sethlcScreenWidth(this.hlc_screenwidth) ;
               hlc.save() ;

               content += "Hall " + hlc.gethlcID() + " has been succesfully added to the database"
                     + "<br><a href=\"index.jsp?module=administration\">go back to administration</a>" ;

            }
            catch (Exception e)
            {

               // in case of an error we will set the errormessage inside the
               // design class

               e.printStackTrace() ;
            }
         }
      }
   }

   /**
    * Creates the form for adding a new hall.
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

      design.setUserName(authentication.getUsrUserName()) ;


      if (authenticated)
      {
         con.createConnection() ;

         content += "<h2>Add Hall</h2><form action=\"index.jsp?module=hall&function=add\" method=\"post\">" ;

         // create a new table object

         content += "<br><h3>Hall properties</h3>" ;

         Table table = new Table(2) ;

         // create a new Input instance

         table.addItem("Complex") ;
         table.addItem(new DropDown("cmf_id", cmf_id, con, DropDown._TYPE_COMPLEX)) ;

         table.nextRow() ;

         // add items to the table and go to the next row

         table.addItem("Capacity") ;
         table.addRedStarToLast() ;
         table.addItem(new Input("hlc_capacity", hlc_capacity, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("hlc_capacity")) ;
         table.nextRow() ;

         table.addItem("Space before screen") ;
         table.addRedStarToLast() ;
         table.addItem(new Input("hlc_spaceinfrontofscreen", hlc_spaceinfrontofscreen, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("hlc_spaceinfrontofscreen")) ;
         table.nextRow() ;

         table.addItem("Distance to screen") ;
         table.addRedStarToLast() ;
         table.addItem(new Input("hlc_distancescreenprojection", hlc_distancescreenprojection, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("hlc_distancescreenprojection")) ;
         table.nextRow() ;

         table.addItem("Screen width") ;
         table.addRedStarToLast() ;
         table.addItem(new Input("hlc_screenwidth", hlc_screenwidth, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("hlc_screenwidth")) ;
         table.nextRow() ;

         content += table + "<h3>Hall capabilities</h3>" ;

         table = new Table(3) ;

         table.addItem(new Input("hlc_bookable", "Bookable for events", "TRUE", hlc_bookable, Input._TYPE_CHECKBOX)) ;
         table.addItem(new Input("hlc_cupholder", "Cupholders", "TRUE", hlc_cupholder, Input._TYPE_CHECKBOX)) ;
         table.addItem(new Input("hlc_digitalversion", "Digital", "TRUE", hlc_digitalversion, Input._TYPE_CHECKBOX)) ;
         table.addItem(new Input("hlc_twinseat", "Twinseat", "TRUE", hlc_twinseat, Input._TYPE_CHECKBOX)) ;
         table.addItem(new Input("hlc_wheelchair", "Wheelchair", "TRUE", hlc_wheelchair, Input._TYPE_CHECKBOX)) ;
         table.nextRow() ;

         content += table ;
         content += new Input("submit", "Add", Input._TYPE_SUBMIT) ;
         content += "</form>" ;
      }
   }


   /**
    * @return Returns the content.
    */
   public String getContent()
   {
      String lastError = con.getLastError() ;
      boolean error = con.isErrors() ;

      con.closeResultset() ;
      design.setErrorMessage(lastError) ;
      return design.getContent(content, error) ;
   }


   /**
    * @param content
    *           The content to set.
    */
   public void setContent(String content)
   {
      this.content = content ;
   }


   /**
    * @return Returns the cmf_id.
    */
   public String getCmf_id()
   {
      return cmf_id ;
   }


   /**
    * @param cmf_id
    *           The cmf_id to set.
    */
   public void setCmf_id(String cmf_id)
   {
      this.cmf_id = cmf_id ;
   }


   /**
    * @return Returns the hlc_bookable.
    */
   public String getHlc_bookable()
   {
      return hlc_bookable ;
   }


   /**
    * @param hlc_bookable
    *           The hlc_bookable to set.
    */
   public void setHlc_bookable(String hlc_bookable)
   {
      this.hlc_bookable = hlc_bookable ;
   }


   /**
    * @return Returns the hlc_capacity.
    */
   public String getHlc_capacity()
   {
      return hlc_capacity ;
   }


   /**
    * @param hlc_capacity
    *           The hlc_capacity to set.
    */
   public void setHlc_capacity(String hlc_capacity)
   {
      this.hlc_capacity = hlc_capacity ;
   }


   /**
    * @return Returns the hlc_cupholder.
    */
   public String getHlc_cupholder()
   {
      return hlc_cupholder ;
   }


   /**
    * @param hlc_cupholder
    *           The hlc_cupholder to set.
    */
   public void setHlc_cupholder(String hlc_cupholder)
   {
      this.hlc_cupholder = hlc_cupholder ;
   }


   /**
    * @return Returns the hlc_digitalversion.
    */
   public String getHlc_digitalversion()
   {
      return hlc_digitalversion ;
   }


   /**
    * @param hlc_digitalversion
    *           The hlc_digitalversion to set.
    */
   public void setHlc_digitalversion(String hlc_digitalversion)
   {
      this.hlc_digitalversion = hlc_digitalversion ;
   }


   /**
    * @return Returns the hlc_distancescreenprojection.
    */
   public String getHlc_distancescreenprojection()
   {
      return hlc_distancescreenprojection ;
   }


   /**
    * @param hlc_distancescreenprojection
    *           The hlc_distancescreenprojection to set.
    */
   public void setHlc_distancescreenprojection(String hlc_distancescreenprojection)
   {
      this.hlc_distancescreenprojection = hlc_distancescreenprojection ;
   }


   /**
    * @return Returns the hlc_screenwidth.
    */
   public String getHlc_screenwidth()
   {
      return hlc_screenwidth ;
   }


   /**
    * @param hlc_screenwidth
    *           The hlc_screenwidth to set.
    */
   public void setHlc_screenwidth(String hlc_screenwidth)
   {
      this.hlc_screenwidth = hlc_screenwidth ;
   }


   /**
    * @return Returns the hlc_spaceinfrontofscreen.
    */
   public String getHlc_spaceinfrontofscreen()
   {
      return hlc_spaceinfrontofscreen ;
   }


   /**
    * @param hlc_spaceinfrontofscreen
    *           The hlc_spaceinfrontofscreen to set.
    */
   public void setHlc_spaceinfrontofscreen(String hlc_spaceinfrontofscreen)
   {
      this.hlc_spaceinfrontofscreen = hlc_spaceinfrontofscreen ;
   }


   /**
    * @return Returns the hlc_twinseat.
    */
   public String getHlc_twinseat()
   {
      return hlc_twinseat ;
   }


   /**
    * @param hlc_twinseat
    *           The hlc_twinseat to set.
    */
   public void setHlc_twinseat(String hlc_twinseat)
   {
      this.hlc_twinseat = hlc_twinseat ;
   }

   /**
    * @return Returns the hlc_wheelchair.
    */
   public String getHlc_wheelchair()
   {
      return hlc_wheelchair ;
   }


   /**
    * @param hlc_wheelchair
    *           The hlc_wheelchair to set.
    */
   public void setHlc_wheelchair(String hlc_wheelchair)
   {
      this.hlc_wheelchair = hlc_wheelchair ;
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
