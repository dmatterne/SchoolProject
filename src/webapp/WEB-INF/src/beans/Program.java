package beans ;

import html.DropDown ;
import html.Form ;
import html.Image ;
import html.Input ;
import html.Link ;
import html.Table ;

import java.io.Serializable ;
import java.sql.Timestamp ;
import java.text.SimpleDateFormat ;
import java.util.GregorianCalendar ;
import java.util.Locale ;

import Niveau34.Programming ;
import database.Authentication ;
import database.Connection ;
import database.InputControl ;
import design.DesignWrapper ;

/**
 * This class contains ways to program a movie into a complex. It also provides
 * us a way to add a movie Afterwards , users can look at the programming list
 * and choose the movie they want to see. All Information about the programming
 * will be saved into the database.
 * 
 * @author ICT09
 */
public class Program implements Serializable
{

   private static final long serialVersionUID = 341295432695237L ;

   private Connection con = new Connection() ;

   private Authentication authentication = new Authentication(con) ;

   private String cache_id = "" ;

   private String cmf_id ;

   private String cmf_link_id ;


   private String content = "" ;

   private DesignWrapper design = new DesignWrapper(authentication) ;

   private String fli_id ;

   private String hlc_id ;

   private InputControl input_control = new InputControl(con) ;

   private String position = "0" ;

   private String prg_datetime ;

   private String prg_days_1 ;

   private String prg_days_2 ;

   private String prg_days_3 ;

   private String prg_days_4 ;

   private String prg_days_5 ;

   private String prg_days_6 ;

   private String prg_days_7 ;

   private String prg_end_day ;

   private String prg_end_month ;

   private String prg_end_year ;

   private String prg_hour ;

   private String prg_id ;

   private String prg_minute ;

   private String prg_price ;

   private String prg_start_day ;

   private String prg_start_month ;

   private String prg_start_year ;

   private String user_id ;


   public void buildForm()
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

      con.createConnection() ;


      Table table = new Table(2) ;
      Form form = new Form("index.jsp?module=programming&function=add", Form._METHOD_POST) ;

      content += "<h2>Schedule Movie</h2>" ;

      if (cmf_id == null)
         cmf_id = "0" ;

      if (cmf_link_id != null)
         cmf_id = cmf_link_id ;

      table.addItem("<h3>Choose Complex</h3>") ;
      table.nextRow() ;
      table.addItem("Complex") ;
      table.addItem(new DropDown("cmf_link_id", cmf_id, true, "index.jsp?module=programming&function=add_form&cmf_link_id=", "", con,
            DropDown._TYPE_COMPLEX)) ;
      table.addToLastItem(new Input("cmf_id", cmf_id, Input._TYPE_HIDDEN)) ;
      table.nextRow() ;

      table.addItem("Hall") ;
      table.addItem(new DropDown("hlc_id", hlc_id, false, "", "WHERE cmfID=" + cmf_id, con, DropDown._TYPE_HALLS)) ;

      table.nextRow() ;
      table.nextRow() ;
      table.addItem("<h3>Choose Movie</h3>") ;
      table.nextRow() ;
      table.addItem("Film Name") ;
      table.addItem(new DropDown("fli_id", fli_id, "", con, DropDown._TYPE_FILMINFO)) ;


      table.addItem("Price") ;
      table.addItem("<input type=\"text\" name=\"prg_price\">") ;
      table.addToLastItem(input_control.getErrorMessage("prg_price")) ;

      table.nextRow() ;
      table.nextRow() ;
      table.addItem("<h3>Choose date</h3>") ;
      table.nextRow() ;


      table.addItem("Start Date") ;
      table.addItem(DropDown.getDateField("prg_start_day", "prg_start_month", "prg_start_year", 2, 6)) ;
      table.nextRow() ;

      table.addItem("End Date") ;
      table.addItem(DropDown.getDateField("prg_end_day", "prg_end_month", "prg_end_year", 2, 6)) ;
      table.nextRow() ;
      table.nextRow() ;

      table.addItem("Time") ;

      String time = "<select name=\"prg_hour\">" + "<Option>11</option>" + "<Option>14</option>" + "<Option>15</option>" + "<Option>17</option>"
            + "<Option>20</option>" + "<Option>22</option>" + "</select><select name=\"prg_minute\">" + "<Option>00</option>" + "<Option>15</option>"
            + "<Option>30</option>" + "<Option>45</option>"

            + "</select>" ;


      table.addItem(time) ;
      table.nextRow() ;
      table.setMode(false) ;

      form.add(table) ;
      form.add("<br><h3>choose days</h3>") ;


      Table days = new Table(4) ;
      days.addItem(new Input("prg_days_1", "Monday", "true", prg_days_1, Input._TYPE_CHECKBOX)) ;


      days.addItem(new Input("prg_days_2", "Tuesday", "true", prg_days_2, Input._TYPE_CHECKBOX)) ;
      days.addItem(new Input("prg_days_3", "Wednesday", "true", prg_days_3, Input._TYPE_CHECKBOX)) ;
      days.addItem(new Input("prg_days_4", "Thirsday", "true", prg_days_4, Input._TYPE_CHECKBOX)) ;
      days.addItem(new Input("prg_days_5", "Friday", "true", prg_days_5, Input._TYPE_CHECKBOX)) ;
      days.addItem(new Input("prg_days_6", "Satudray", "true", prg_days_6, Input._TYPE_CHECKBOX)) ;
      days.addItem(new Input("prg_days_7", "Sunday", "true", prg_days_7, Input._TYPE_CHECKBOX)) ;
      days.addItem("") ;
      days.nextRow() ;
      days.nextRow() ;
      days.addItem("<input type=\"submit\" value=\"Add\"/>") ;
      days.nextRow() ;
      form.add(days) ;

      content += form ;
   }


   public String getCache_id()
   {
      return cache_id ;
   }


   public String getCmf_id()
   {
      return cmf_id ;
   }

   /**
    * @return Returns the cmf_link_id.
    */
   public String getCmf_link_id()
   {
      return cmf_link_id ;
   }

   public String getContent()
   {
      String lastError = con.getLastError() ;
      boolean error = con.isErrors() ;
      con.closeResultset() ;
      design.setErrorMessage(lastError) ;
      return design.getContent(content, error) ;
   }

   public String getFli_id()
   {
      return fli_id ;
   }

   public String getHlc_id()
   {
      return hlc_id ;
   }

   /**
    * @return Returns the position.
    */
   public String getPosition()
   {
      return position ;
   }

   public String getPrg_datetime()
   {
      return prg_datetime ;
   }

   /**
    * @return Returns the prg_days_1.
    */
   public String getPrg_days_1()
   {
      return prg_days_1 ;
   }

   /**
    * @return Returns the prg_days_2.
    */
   public String getPrg_days_2()
   {
      return prg_days_2 ;
   }

   /**
    * @return Returns the prg_days_3.
    */
   public String getPrg_days_3()
   {
      return prg_days_3 ;
   }

   /**
    * @return Returns the prg_days_4.
    */
   public String getPrg_days_4()
   {
      return prg_days_4 ;
   }

   /**
    * @return Returns the prg_days_5.
    */
   public String getPrg_days_5()
   {
      return prg_days_5 ;
   }

   /**
    * @return Returns the prg_days_6.
    */
   public String getPrg_days_6()
   {
      return prg_days_6 ;
   }

   /**
    * @return Returns the prg_days_7.
    */
   public String getPrg_days_7()
   {
      return prg_days_7 ;
   }

   /**
    * @return Returns the prg_end_day.
    */
   public String getPrg_end_day()
   {
      return prg_end_day ;
   }

   /**
    * @return Returns the prg_end_month.
    */
   public String getPrg_end_month()
   {
      return prg_end_month ;
   }

   /**
    * @return Returns the prg_end_year.
    */
   public String getPrg_end_year()
   {
      return prg_end_year ;
   }

   public String getPrg_hour()
   {
      return prg_hour ;
   }

   public String getPrg_id()
   {
      return prg_id ;
   }

   public String getPrg_minute()
   {
      return prg_minute ;
   }

   public String getPrg_price()
   {
      return prg_price ;
   }


   /**
    * @return Returns the prg_start_day.
    */
   public String getPrg_start_day()
   {
      return prg_start_day ;
   }


   /**
    * @return Returns the prg_start_month.
    */
   public String getPrg_start_month()
   {
      return prg_start_month ;
   }


   /**
    * @return Returns the prg_start_year.
    */
   public String getPrg_start_year()
   {
      return prg_start_year ;
   }

   /**
    * Creates the form in wich all programming information can be viewed
    */
   public void listProgramming()
   {
      // set the authentication instance to the userId aquired from the session

      authentication.setPersonId(user_id) ;

      boolean authenticated = true ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page

      design.setAccess(authenticated) ;

      // pass authentication to design class

      design.setAuthentication(authentication) ;

      // Sets the username in de design class, for fancy display

      design.setUserName(authentication.getUsrUserName()) ;

      int pos ;

      try
      {
         pos = Integer.parseInt(position) ;
      }
      catch (Exception e)
      {
         pos = 0 ;
      }
      content += "<h2>Movie Schedule</h2>" + "<br><h3>Choose Complex</h3>"
            + new DropDown("cmf_link_id", cmf_id, true, "index.jsp?module=programming&function=list&cmf_link_id=", "", con, DropDown._TYPE_COMPLEX)
            + "<br><br>" ;
      if (cmf_id != null)
      {
         content += "<br><h3>Scheduling</h3>" ;

         Table tableCal ;
         Table navigation ;

         GregorianCalendar cal = new GregorianCalendar() ;
         cal.setFirstDayOfWeek(GregorianCalendar.SUNDAY) ;
         cal.add(GregorianCalendar.DAY_OF_YEAR, pos) ;

         SimpleDateFormat format = new SimpleDateFormat("MMMM", Locale.ENGLISH) ;

         format.applyPattern("'<B>'EEEE'&nbsp;'dd'&nbsp;'MMMM'&nbsp;</B>'") ;


         content += "Click on an hour to order a ticket for a movie<br>Click on the movie to get more information<br><br>" ;

         content += "<h3>" + format.format(cal.getTime()) + "</h3>" ;
         cal = new GregorianCalendar() ;

         cal.add(GregorianCalendar.DAY_OF_YEAR, pos) ;

         navigation = new Table(3) ;
         navigation.nextRow() ;

         Image image ;
         Link link ;

         if (pos > 0)
         {

            image = new Image("images/buttons/first.jpg", "back") ;

            link = new Link("index.jsp?module=programming&amp;function=list&amp;pos=" + (pos - 1) + "&amp;cmf_link_id=" + cmf_id, image.toString(),
                  "Go To Previous Day") ;

            navigation.setItem(link, 0, 0) ;

            image = new Image("images/buttons/begin.jpg", "today") ;
            link = new Link("index.jsp?module=programming&amp;function=list&amp;pos=" + 0 + "&amp;cmf_link_id=" + cmf_id, image.toString(),
                  "Go To this Week") ;
            navigation.setItem(link, 0, 1) ;
         }
         image = new Image("images/buttons/last.jpg", "next") ;
         link = new Link("index.jsp?module=programming&amp;function=list&amp;pos=" + (pos + 1) + "&amp;cmf_link_id=" + cmf_id, image.toString(),
               "Go To Next Day") ;

         navigation.setItem(link, 0, 2) ;
         navigation.setCellWidth("34", 0) ;
         navigation.setCellWidth("34", 1) ;
         navigation.setCellWidth("34", 2) ;
         tableCal = new Table(1) ;
         tableCal.setMode(true) ;

         format.applyPattern("yyyy-MM-dd HH:mm") ;


         con.createConnection() ;
         cal.set(GregorianCalendar.HOUR_OF_DAY, 0) ;

         String s1 = format.format(cal.getTime()) ;

         cal.add(GregorianCalendar.DAY_OF_YEAR, 1) ;

         String s2 = format.format(cal.getTime()) ;


         String sql = "select film.fliName, prog.prgDateTime, prog.ID, film.ID from Niveau34.Programming prog, Comato.FlmInfo film where prog.prgDateTime>'"
               + s1 + "' And prog.prgDateTime<'" + s2 + "' and prog.cmfID=" + cmf_id + " and film.fliID=prog.fliID Order By prog.fliID" ;

         con.generateResultSet(sql) ;

         System.out.println(sql + "\n" + GregorianCalendar.MONDAY + GregorianCalendar.TUESDAY + GregorianCalendar.WEDNESDAY
               + GregorianCalendar.THURSDAY + GregorianCalendar.FRIDAY + GregorianCalendar.SATURDAY + GregorianCalendar.SUNDAY) ;
         int i = 1 ;
         String lastFilm = "" ;
         int currentrows = 0 ;
         tableCal.setMode(true) ;

         format.applyPattern("HH:mm") ;

         while (con.nextResult())
         {


            // key.
            // cal.setTime(con.getResultTimeStamp(2));
            if (lastFilm.equals(con.getResultString(1)))
            {
               lastFilm = con.getResultString(1) ;

               tableCal.addToLastItem(format.format(con.getResultTimeStamp(2)) + "&nbsp;&nbsp;") ;
            }
            else
            {
               link = new Link("index.jsp?module=filminfo&amp;function=detail&amp;ID=" + con.getResultString(4), con.getResultString(1), con
                     .getResultString(1)) ;

               if (!(lastFilm.equals("")))
                  tableCal.addToLastItem("<br>") ;

               tableCal.addItem(link + "<br><br>") ;

               lastFilm = con.getResultString(1) ;

               link = new Link("index.jsp?module=Tickets&amp;function=makeTicket&amp;cache_Id=" + con.getResultString(3), format.format(con
                     .getResultTimeStamp(2)), format.format(con.getResultTimeStamp(2))) ;

               tableCal.addToLastItem("&nbsp;&nbsp;&nbsp;&nbsp;" + link) ;

            }


            System.out.println(i + "\t" + pos + "\t" + currentrows + "\t" + con.getResultTimeStamp(2) + con.getResultString(1)) ;
            // System.out.println(con.getResultString(1)) ;


            i++ ;
         }
         tableCal.nextRow() ;
         tableCal.setWidth("60%") ;
         content += tableCal + "" + navigation ;
      }


   }

   /**
    * Saves the programming into the database.
    */
   public void saveProgramming()
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

      double prgPrice = 0.0 ;

      prgPrice = input_control.parseDouble(prg_price, "prg_price") ;


      if (input_control.isErrors())
      {
         buildForm() ;
      }

      else
      {
         try
         {


            String sql = "SELECT distinct MAX(ID) FROM Niveau34.Programming" ;

            Long prgId ;


            // Read resultset
            con.generateResultSet(sql) ;

            // Read first record and check if its worked
            if (con.nextResult())
            {
               // Get id from last record if there is already one
               prgId = con.getResultLong(1) + 1 ;
            }
            else
            {
               // If there is no data yet => id = 0
               prgId = new Long(1) ;
            }


            con.createConnection() ;
            Niveau34.Programming prog ;

            GregorianCalendar cal1 = new GregorianCalendar() ;
            GregorianCalendar cal2 = new GregorianCalendar() ;
            int day1 = Integer.parseInt(prg_start_day) ;
            int day2 = Integer.parseInt(prg_end_day) ;
            Integer.parseInt(prg_hour) ;
            Integer.parseInt(prg_minute) ;
            Integer.parseInt(prg_start_year) ;
            Integer.parseInt(prg_start_month) ;
            cal1.set(Integer.parseInt(prg_start_year), Integer.parseInt(prg_start_month) - 1, day1, Integer.parseInt(prg_hour), Integer
                  .parseInt(prg_minute)) ;
            cal2.set(Integer.parseInt(prg_end_year), Integer.parseInt(prg_end_month) - 1, day2) ;

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm") ;

            GregorianCalendar cal3 ;
            GregorianCalendar cal4 ;

            boolean checkExists = false ;

            // loop first time to see if there is already a film programmed on
            // this time
            while (cal1.before(cal2) && (checkExists == false))
            {
               boolean add = false ;

               // check if the current day of the week was checked in a box
               switch (cal1.get(GregorianCalendar.DAY_OF_WEEK))
               {
               case GregorianCalendar.MONDAY:
                  add = Boolean.parseBoolean(prg_days_1) ;
                  break ;
               case GregorianCalendar.TUESDAY:
                  add = Boolean.parseBoolean(prg_days_2) ;
                  break ;
               case GregorianCalendar.WEDNESDAY:
                  add = Boolean.parseBoolean(prg_days_3) ;
                  break ;
               case GregorianCalendar.THURSDAY:
                  add = Boolean.parseBoolean(prg_days_4) ;
                  break ;
               case GregorianCalendar.FRIDAY:
                  add = Boolean.parseBoolean(prg_days_5) ;
                  break ;
               case GregorianCalendar.SATURDAY:
                  add = Boolean.parseBoolean(prg_days_6) ;
                  break ;
               case GregorianCalendar.SUNDAY:
                  add = Boolean.parseBoolean(prg_days_7) ;
                  break ;
               }

               if (add)
               {
                  // create a copy instead of a reference
                  cal3 = (GregorianCalendar) cal1.clone() ;

                  cal3.set(GregorianCalendar.HOUR_OF_DAY, 0) ;

                  String s1 = format.format(cal3.getTime()) ;

                  cal3.add(GregorianCalendar.DAY_OF_YEAR, 1) ;

                  String s2 = format.format(cal3.getTime()) ;

                  sql = "select film.fliLength, prog.prgDateTime from Niveau34.Programming prog, Comato.FlmInfo film where prog.prgDateTime>'" + s1
                        + "' And prog.prgDateTime<'" + s2 + "' and prog.cmfID=" + cmf_id + " and film.fliID=prog.fliID" ;

                  while (con.nextResult() && (checkExists == false))
                  {
                     cal3.setTime(con.getResultTimeStamp(2)) ;
                     cal4 = (GregorianCalendar) cal3.clone() ;

                     cal4.add(GregorianCalendar.MINUTE, con.getResultInt(1)) ;

                     if (cal1.after(cal3) && cal1.before(cal4))
                        checkExists = true ;
                  }
               }
               // add 1 to day
               cal1.add(GregorianCalendar.DAY_OF_YEAR, 1) ;
            }

            if (checkExists)
            {
               content += "Error! there was already a film programmed in this scope!" ;
               buildForm() ;
            }
            else
            {
               cal1.set(Integer.parseInt(prg_start_year), Integer.parseInt(prg_start_month) - 1, day1, Integer.parseInt(prg_hour), Integer
                     .parseInt(prg_minute)) ;

               // if there were no errors in the past, it is now time to really
               // add the programming
               while (cal1.before(cal2))
               {

                  boolean add = false ;

                  // check if the current day of the week was checked in a box
                  switch (cal1.get(GregorianCalendar.DAY_OF_WEEK))
                  {
                  case GregorianCalendar.MONDAY:
                     add = Boolean.parseBoolean(prg_days_1) ;
                     break ;
                  case GregorianCalendar.TUESDAY:
                     add = Boolean.parseBoolean(prg_days_2) ;
                     break ;
                  case GregorianCalendar.WEDNESDAY:
                     add = Boolean.parseBoolean(prg_days_3) ;
                     break ;
                  case GregorianCalendar.THURSDAY:
                     add = Boolean.parseBoolean(prg_days_4) ;
                     break ;
                  case GregorianCalendar.FRIDAY:
                     add = Boolean.parseBoolean(prg_days_5) ;
                     break ;
                  case GregorianCalendar.SATURDAY:
                     add = Boolean.parseBoolean(prg_days_6) ;
                     break ;
                  case GregorianCalendar.SUNDAY:
                     add = Boolean.parseBoolean(prg_days_7) ;
                     break ;
                  }

                  if (add)
                  {
                     Timestamp time = new Timestamp(cal1.getTime().getTime()) ;

                     prog = new Programming(con.getConnection()) ;
                     prog.setprgID(prgId) ;
                     prog.setprgDateTime(time) ;
                     prog.setcmfID(Integer.parseInt(cmf_id)) ;
                     prog.setfliID(Integer.parseInt(fli_id)) ;
                     prog.sethlcID(Integer.parseInt(hlc_id)) ;
                     prog.setprgID(prgId) ;
                     prog.setprgPrice(prgPrice) ;
                     prog.save() ;


                     prgId++ ;


                  }

                  // add 1 to day
                  cal1.add(GregorianCalendar.DAY_OF_YEAR, 1) ;
               }
               content += "Movie has been succesfully added to movie schedule" ;

            }
         }
         catch (Exception e)
         {
            // TODO Auto-generated catch block

            e.printStackTrace() ;
         }
      }

   }


   public void setCache_id(String cache_Id)
   {
      this.cache_id = cache_Id ;
   }


   public void setCmf_id(String cmf_id)
   {
      this.cmf_id = cmf_id ;
   }


   /**
    * @param cmf_link_id
    *           The cmf_link_id to set.
    */
   public void setCmf_link_id(String cmf_link_id)
   {
      this.cmf_link_id = cmf_link_id ;
   }


   public void setContent(String content)
   {
      this.content = content ;
   }


   public void setFli_id(String ntf_id)
   {
      this.fli_id = ntf_id ;
   }


   public void setHlc_id(String hlc_id)
   {
      this.hlc_id = hlc_id ;
   }


   /**
    * @param position
    *           The position to set.
    */
   public void setPosition(String position)
   {
      this.position = position ;
   }


   public void setPrg_datetime(String prg_datetime)
   {
      this.prg_datetime = prg_datetime ;
   }


   /**
    * @param prg_days_1
    *           The prg_days_1 to set.
    */
   public void setPrg_days_1(String prg_days_1)
   {
      this.prg_days_1 = prg_days_1 ;
   }


   /**
    * @param prg_days_2
    *           The prg_days_2 to set.
    */
   public void setPrg_days_2(String prg_days_2)
   {
      this.prg_days_2 = prg_days_2 ;
   }


   /**
    * @param prg_days_3
    *           The prg_days_3 to set.
    */
   public void setPrg_days_3(String prg_days_3)
   {
      this.prg_days_3 = prg_days_3 ;
   }


   /**
    * @param prg_days_4
    *           The prg_days_4 to set.
    */
   public void setPrg_days_4(String prg_days_4)
   {
      this.prg_days_4 = prg_days_4 ;
   }


   /**
    * @param prg_days_5
    *           The prg_days_5 to set.
    */
   public void setPrg_days_5(String prg_days_5)
   {
      this.prg_days_5 = prg_days_5 ;
   }


   /**
    * @param prg_days_6
    *           The prg_days_6 to set.
    */
   public void setPrg_days_6(String prg_days_6)
   {
      this.prg_days_6 = prg_days_6 ;
   }


   /**
    * @param prg_days_7
    *           The prg_days_7 to set.
    */
   public void setPrg_days_7(String prg_days_7)
   {
      this.prg_days_7 = prg_days_7 ;
   }


   /**
    * @param prg_end_day
    *           The prg_end_day to set.
    */
   public void setPrg_end_day(String prg_end_day)
   {
      this.prg_end_day = prg_end_day ;
   }


   /**
    * @param prg_end_month
    *           The prg_end_month to set.
    */
   public void setPrg_end_month(String prg_end_month)
   {
      this.prg_end_month = prg_end_month ;
   }


   /**
    * @param prg_end_year
    *           The prg_end_year to set.
    */
   public void setPrg_end_year(String prg_end_year)
   {
      this.prg_end_year = prg_end_year ;
   }


   public void setPrg_hour(String prg_hour)
   {
      this.prg_hour = prg_hour ;
   }


   public void setPrg_id(String prg_id)
   {
      this.prg_id = prg_id ;
   }


   public void setPrg_minute(String prg_minute)
   {
      this.prg_minute = prg_minute ;
   }


   public void setPrg_price(String prg_price)
   {
      this.prg_price = prg_price ;
   }


   /**
    * @param prg_start_day
    *           The prg_start_day to set.
    */
   public void setPrg_start_day(String prg_start_day)
   {
      this.prg_start_day = prg_start_day ;
   }


   /**
    * @param prg_start_month
    *           The prg_start_month to set.
    */
   public void setPrg_start_month(String prg_start_month)
   {
      this.prg_start_month = prg_start_month ;
   }


   /**
    * @param prg_start_year
    *           The prg_start_year to set.
    */
   public void setPrg_start_year(String prg_start_year)
   {
      this.prg_start_year = prg_start_year ;
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
   public void setUser_id(String userId)
   {
      this.user_id = userId ;
   }

}