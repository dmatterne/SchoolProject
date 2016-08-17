package html ;

import java.util.Date ;
import java.util.GregorianCalendar ;
import java.util.Vector ;

import database.Connection ;

/**
 * The class DropDown helps you generate various dropdown boxes. There is built in functionality to generate dynamic ones with just a few extra arguments to the Constructor.
 * This class also contains various types to pre generate a drowdown with items from the database.
 * These types can be found as static final type members in this class. Like DropDown._TYPE_COMPLEX. You can pass these types
 * to the class to generate the corresponding drowdown. You can easily create new types in this class for further use.
 * If you want to get the contents of this class you can get them using the standard Object.toString function. You can also add its content to another string with the  += operator.
 * @author ICT09
 *
 */
public class DropDown
{

   
   public final static Type _TYPE_COMPLEX = new Type("SELECT cmfID, cmfName FROM Niveau34.CmpFeatures ") ;

   public final static Type _TYPE_LANGUAGE_VERSIONS = new Type("SELECT lnvID, lnvName FROM Niveau12.LngVersions ") ;

   public final static Type _TYPE_GENRES = new Type("SELECT gnrID,gnrDescription FROM Comato.Genres  ") ;

   public final static Type _TYPE_COUNTRIES = new Type("SELECT cntID,cntName FROM Niveau12.Countries ") ;

   public final static Type _TYPE_FILMINFO = new Type("SELECT fliId, fliName FROM Comato.FlmInfo") ;

   public final static Type _TYPE_DISTRIBUTORS = new Type("SELECT dstID, dstName FROM Niveau12.distributors ") ;

   public final static Type _TYPE_HALLS = new Type("SELECT hlcID, hlcID FROM Niveau34.HllComplex ") ;

   public final static Type _TYPE_DISCOUNTS = new Type("SELECT dscID,dscName FROM Niveau34.Discounts") ;

   public final static Type _TYPE_ROLES = new Type("SELECT rlsID,rlsDescription FROM Comato.Roles") ;

   public final static Type _TYPE_PERSONS = new Type("SELECT prsID,prsName  FROM Comato.Persons") ;

   public final static Type _TYPE_LANGUAGES = new Type("SELECT lngID,lngLanguage FROM Niveau34.Languages") ;

   public final static Type _TYPE_SCORES = new Type("SELECT scrID,scrDescription FROM Comato.Scores") ;
   
   public final static Type _TYPE_FLM_REVIEWS = new Type("SELECT freID,freID FROM Comato.Scores") ;


   private Vector<String> drop_down = new Vector<String>() ;

   private boolean link_mode = false ;

   private String name = "" ;

   Connection con = null ;


/**
 * Generates a html String based representation of this dropdown box.
 */
   @Override
   public String toString()
   {
      String content ;
      content = "<select name=\"" + name + "\"" ;

      if (link_mode)
         content += " onChange=\"MM_jumpMenu('parent',this,0)\"" ;

      content += ">" ;

      for (String s1 : drop_down)
      {
         content += "\n\t" + s1 ;
      }

      content += "</select>" ;


      return content ;
   }
/**
 * Creates a drop down box using the paramaters
 * @param name
 * The name of this box inside a form
 * @param selected
 * Give the value of any eventual pre-selected items
 * @param link_mode
 * If link mode is true, this DropDown will be enhanced with javascript, to make it refresh pages on select in the browser
 * @param url
 * The url this Dropdown links to when using linkmode
 * @param extra_sql
 * Any eventual sql code, often used with where "WHERE variable=2" for example.
 * @param con
 * The connection object to use when filling boxes
 * @param type
 * The Type of DropDown to generate, you can get working types in this class, like DropDown._TYPE_SCORES
 */
   public DropDown(String name, String selected, boolean link_mode, String url, String extra_sql, Connection con, Type type)
   {
      if (selected == null)
         selected = "" ;

      this.link_mode = link_mode ;
      this.name = name ;

      con.createConnection() ;

      String sql = type.getType() + extra_sql ;

      con.generateResultSet(sql) ;

      // prepare the query

      if (link_mode)
         drop_down.add("<Option value=\"0\">- Select an Item -</Option>") ;


      while (con.nextResult())
      {
         // Dump the columns in each row

         String item = "<Option value=\"" + url + con.getResultString(1) + "\" " ;

         if (selected.equals(con.getResultString(1)))
            item += "selected" ;

         // compare the distributor in the
         // resultset with the distributor
         // entered. in a previous attempt

         item += ">" + con.getResultString(2) + "</option>" ;
         drop_down.add(item) ;

      }
   }
   /**
    * Creates a drop down box using the paramaters
    * @param name
    * The name of this box inside a form
    * @param selected
    * Give the value of any eventual pre-selected items
    * @param con
    * The connection object to use when filling boxes
    * @param type
    * The Type of DropDown to generate, you can get working types in this class, like DropDown._TYPE_SCORES
    */
   public DropDown(String name, String selected, Connection con, Type type)
   {
      if (selected == null)
         selected = "" ;

      this.name = name ;

      con.createConnection() ;

      String sql = type.getType() ;

      con.generateResultSet(sql) ;

      while (con.nextResult())
      {
         // Dump the columns in each row

         String item = "<Option value=\"" + con.getResultString(1) + "\" " ;

         if (selected.equals(con.getResultString(1)))
            item += "selected" ;

         // compare the distributor in the
         // resultset with the distributor
         // entered. in a previous attempt

         item += ">" + con.getResultString(2) + "</option>" ;
         drop_down.add(item) ;
      }
   }
   /**
    * Creates a drop down box using the paramaters
    * @param name
    * The name of this box inside a form
    * @param selected
    * Give the value of any eventual pre-selected items
    * @param extra_sql
    * Any eventual sql code, often used with where "WHERE variable=2" for example.
    * @param con
    * The connection object to use when filling boxes
    * @param type
    * The Type of DropDown to generate, you can get working types in this class, like DropDown._TYPE_SCORES
    */
   public DropDown(String name, String selected, String extra_sql, Connection con, Type type)
   {
      if (selected == null)
         selected = "" ;

      this.name = name ;

      con.createConnection() ;

      String sql = type.getType() + extra_sql ;

      con.generateResultSet(sql) ;

      while (con.nextResult())
      {
         // Dump the columns in each row

         String item = "<Option value=\"" + con.getResultString(1) + "\" " ;

         if (selected.equals(con.getResultString(1)))
            item += "selected" ;

         // compare the distributor in the
         // resultset with the distributor
         // entered. in a previous attempt

         item += ">" + con.getResultString(2) + "</option>" ;
         drop_down.add(item) ;
      }
   }

   /**
    * Generates a list of numbers from 1 to 50, when you click a number the page will navigate automaticly to the passed url.
    * It will also add the number to the url
    * @param url
    * The url to navigate to after click
    * @param selected
    * the item to pre-select on advance
    * @return
    */
   public static String getNumbers(String url, String selected)
   {
      String naam = "number" ;
      String numbers ;
      int i = 1 ;
      numbers = "<select name=\"" + naam + "\"  onChange=\"MM_jumpMenu('parent',this,0)\" >" ;


      while (i <= 50)
      {
         numbers += "<Option value = " + url + i ;

         try
         {
            if (Integer.parseInt(selected) == i)
               numbers += "selected" ;
         }
         catch (NumberFormatException e)
         {

         }


         numbers += ">" + i + "</option>" ;
         i++ ;
      }

      numbers += "</select>" ;

      return numbers ;
   }

   /**
    * Generates a date-time picker for you
    * @param day_field
    * the name of the day field inside a html form
    * @param month_field
    * the name of the month field inside a html form
    * @param year_field
    * the name of the year field inside a html form
    * @param year_offset
    * the year ofset from today, how many years to go into the future.
    * @param year_scope
    * The scope of years to show. This is subtracted from the current year + the year offset.
    * if ofset is 2 and scope it 100; you wil get 2 years into the future and 88 years in the past.
    * @return
    */
   public static String getDateField(String day_field, String month_field, String year_field, int year_offset, int year_scope)
   {
      String date_field ;

      date_field = "<select name=\"" + day_field + "\">" ;

      int i = 1 ;

      while (i <= 31)
      {
         date_field += "<Option>" + i + "</option>" ;
         i++ ;
      }

      int a = 1 ;

      date_field += "</select><select name=\"" + month_field + "\">" ;

      while (a <= 12)
      {
         date_field += "<Option value=\"" + a + "\">" ;
         switch (a)
         {
         case 1:
            date_field += "January" ;
            break ;
         case 2:
            date_field += "February" ;
            break ;
         case 3:
            date_field += "March" ;
            break ;
         case 4:
            date_field += "April" ;
            break ;
         case 5:
            date_field += "May" ;
            break ;
         case 6:
            date_field += "June" ;
            break ;
         case 7:
            date_field += "July" ;
            break ;
         case 8:
            date_field += "August" ;
            break ;
         case 9:
            date_field += "September" ;
            break ;
         case 10:
            date_field += "October" ;
            break ;
         case 11:
            date_field += "November" ;
            break ;
         case 12:
            date_field += "December" ;
            break ;
         }

         a++ ;
         date_field += "</option>" ;

      }

      date_field += "</select><select name=\"" + year_field + "\">" ;


      GregorianCalendar cal = new GregorianCalendar() ;
      cal.setTime(new Date()) ;

      int year = cal.get(GregorianCalendar.YEAR) ;

      year = year + year_offset ;

      int j = year - year_scope ;

      while (year >= j)
      {
         date_field += "<option " ;

         if (j == cal.get(GregorianCalendar.YEAR))
            date_field += "selected" ;


         date_field += ">"


         + year + "</option>" ;
         year-- ;
      }

      date_field += "</select>" ;

      return date_field ;

   }


}
