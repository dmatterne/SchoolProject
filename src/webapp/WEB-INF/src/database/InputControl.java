package database ;

import java.sql.Date ;
import java.util.HashMap ;

/**
 * An input control class. This class allows you to check items for validity and
 * later on display apropriate error messages. This class formats these message
 * and does the validation, it also does some parsing linked to validation.
 * This class is optimised for the often sequential usage in html pages. You can pass some variables to pass to this class. 
 * So that you can later on get any errormessages that occured with these variables. If
 * No error happened it just returns an empty string, this string obviously does not appear on the page, since it is empty.
 * 
 * @author dimitri
 */
public class InputControl
{


   private Connection con ;

   // in this hashmap the errormessages are put while checking, later on the can
   // be drawn again.

   private HashMap<String, String> error_message2 = new HashMap<String, String>() ;

   // private LinkedList item;
   private boolean errors = false ;

   public void checkPasswordEquality(String pass1, String pass2, String name1, String name2)
   {
      if (pass1 == null)
         pass1 = "" ;

      if (pass2 == null)
         pass2 = "" ;

      if (pass1.equals("") && pass2.equals(""))
      {
         error_message2.put(name1, "<span class=\"errorMessage\">*&nbsp;Please&nbsp;enter&nbsp;a&nbsp;value</span>") ;
         error_message2.put(name2, "<span class=\"errorMessage\">*&nbsp;Please&nbsp;enter&nbsp;a&nbsp;value</span>") ;
         errors = true ;
      }
      else
      {
         if (pass1.equals(pass2))
         {
            error_message2.put(name2, "") ;
            error_message2.put(name1, "") ;
         }
         else
         {
            error_message2.put(name2, "<span class=\"errorMessage\">*&nbsp;The&nbsp;passwords&nbsp;did&nbsp;not&nbsp;match!</span>") ;
            error_message2.put(name1, "<span class=\"errorMessage\">*&nbsp;The&nbsp;passwords&nbsp;did&nbsp;not&nbsp;match!</span>") ;
            errors = true ;
         }
      }


   }

   @SuppressWarnings("deprecation")
   public Date checkFuture(String day1, String month1, String year1, String name)
   {
      try
      {
         java.util.Date today = new java.util.Date() ;
         Date returnDate = new java.sql.Date((Integer.parseInt(year1) - 1900), Integer.parseInt(month1) - 1, Integer.parseInt(day1)) ;

         java.util.Date date = returnDate ;

         // Check if Birthdate isnt in the future

         if (date.after(today))
         {
            errors = true ;
            error_message2
                  .put(name,
                        "<span class=\"errorMessage\">*&nbsp;Please&nbsp;enter&nbsp;a&nbsp;different&nbsp;date,&nbsp;date&nbsp;was&nbsp;in&nbsp;the&nbsp;future</span>") ;
            return null ;
         }
      }
      catch (Exception ex)
      {
         errors = true ;
         error_message2.put(name, "<span class=\"errorMessage\">*&nbsp;Enter a valid Date, example: 30/05/2005!</span>") ;
         return null ;
      }
      error_message2.put(name, "") ;
      return new java.sql.Date((Integer.parseInt(year1) - 1900), Integer.parseInt(month1) - 1, Integer.parseInt(day1)) ;
   }


   /**
    * checks if a given value already exists inside a table, if it exists an
    * errormessage is generated This errormessage can later be aquired from the
    * errormessage list using the name you used here.
    * 
    * @param table
    *           the name of the table to check
    * @param name
    *           the name of the field to check
    * @param input
    *           the value to check against
    */
   public void checkExists(String table, String name, String input)
   {
      if (input == null)
         input = "" ;

      if (!(input.equals("")))
      {
         String sql = "SELECT " + name + " FROM " + table + " WHERE " + name + " like '" + input + "'" ;

         con.createConnection() ;
         con.generateResultSet(sql) ;

         if (con.nextResult())
         {
            errors = true ;
            error_message2
                  .put(name,
                        "<span class=\"errorMessage\">*&nbsp;Please&nbsp;enter&nbsp;a&nbsp;different&nbsp;value,&nbsp;item&nbsp;already&nbsp;exists</span>") ;
         }
         else
         {
            error_message2.put(name, "") ;
         }
      }
      else
      {
         error_message2.put(name, "<span class=\"errorMessage\">*&nbsp;Please&nbsp;enter&nbsp;a&nbsp;value</span>") ;
         errors = true ;
      }


   }

   public InputControl(Connection con)
   {
      this.con = con ;
   }

   /**
    * pulls the first errormessage that occured in this instance. This
    * errormessage will then be removed. If no error occured this will result an
    * empty String ""
    */
   public String getErrorMessage(String name)
   {
      String s1 = error_message2.get(name) ;
      if (s1 == null)
         s1 = "" ;
      return s1 ;
   }

   /**
    * checks if the lenght of a given string is below a given length. If this
    * fails an errormessage is generated and can be pulled from the ErrorMessage
    * queue at a later time.
    * 
    * @param input
    *           The String to check the length of
    * @param length
    *           The maximum length this string is allowed to be.
    */
   public void checkLength(String input, String name, int length)
   {
      if (input.length() <= length)
      {
         this.error_message2.put(name, "") ;
      }
      else
      {
         errors = true ;
         this.error_message2.put(name, "The entered value may be up to " + length + " characters long") ;
      }
   }

   /**
    * Checks if a given string is empty. If it is empty an error message is
    * generated. This Message can be pulled later from the queue
    * 
    * @param input
    *           The String this function should check.
    */
   public void checkEmpty(String input, String name)
   {
      if (input == null)
         input = "" ;

      if (!(input.equals("")))
      {
         error_message2.put(name, "") ;
      }
      else
      {
         error_message2.put(name, "<span class=\"errorMessage\">*&nbsp;Please&nbsp;enter&nbsp;a&nbsp;value</span>") ;
         errors = true ;
      }

   }

   /**
    * Tries to parse a given String into a numeric. If it fails an error message
    * is generated. This Message can be pulled later from the queue
    * 
    * @param input
    *           The string to parste into an int
    * @return the int that this string represents, returns -1 if it failed.
    */
   public int parseNumeric(String input, String name)
   {
      if (input == null)
         input = "" ;

      if (!(input.equals("")))
      {
         try
         {
            int i = Integer.parseInt(input) ;

            error_message2.put(name, "") ;

            return i ;
         }
         catch (NumberFormatException e)
         {
            errors = true ;
            error_message2.put(name, "<span class=\"errorMessage\">*&nbsp;Please&nbsp;enter&nbsp;a&nbsp;number") ;
            return -1 ;
         }
      }
      error_message2.put(name, "<span class=\"errorMessage\">*&nbsp;Please&nbsp;enter&nbsp;a&nbsp;value</span>") ;
      errors = true ;
      return -1 ;
   }

   public double parseDouble(String input, String name)
   {
      if (input == null)
         input = "" ;

      if (!(input.equals("")))
      {
         try
         {
            double i = Double.parseDouble(input) ;

            error_message2.put(name, "") ;
            return i ;
         }
         catch (NumberFormatException e)
         {
            errors = true ;

            error_message2.put(name, "<span class=\"errorMessage\">*&nbsp;Please&nbsp;enter&nbsp;a&nbsp;number") ;
            return -1 ;
         }
      }
      error_message2.put(name, "<span class=\"errorMessage\">*&nbsp;Please&nbsp;enter&nbsp;a&nbsp;value</span>") ;
      errors = true ;
      return -1 ;
   }


   @SuppressWarnings("deprecation")
   public String dateString(Date date)
   {
      String datum ;
      datum = date.getDate() + "/" + (date.getMonth() + 1) + "/" + (date.getYear() + 1900) ;

      return datum ;

   }


   /**
    * Tries to parse a given String into a sql date format. If it fails an error
    * message is generated. This Message can be pulled later from the queue
    * 
    * @param date
    *           The String to parse into a date
    * @return The date that this string represents, if it fails this function
    *         returns null
    */
   @SuppressWarnings("deprecation")
   public Date parseDate(String date, String name)
   {
      if (date == null)
         date = "" ;

      if (date.equals(""))
      {
         error_message2.put(name, "<span class=\"errorMessage\">*&nbsp;Please&nbsp;enter&nbsp;a&nbsp;value</span>") ;
         errors = true ;
         return null ;
      }

      try
      {
         String[] datum = date.split("/") ;
         int year = Integer.parseInt(datum[2]) - 1900 ;
         int month = Integer.parseInt(datum[1]) ;
         int day = Integer.parseInt(datum[0]) ;
         int max_days ;

         switch (month)
         {
         case 1:
         case 3:
         case 5:
         case 7:
         case 8:
         case 10:
         case 12:
            max_days = 31 ;
            break ;
         case 4:
         case 6:
         case 9:
         case 11:
            max_days = 30 ;
            break ;
         case 2:
            if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0))
               max_days = 29 ;
            else
               max_days = 28 ;
            break ;

         default:
            max_days = 0 ;
            break ;
         }

         if (day > max_days)
         {
            errors = true ;
            error_message2.put(name,
                  "<span class=\"errorMessage\">*&nbsp;Please&nbsp;enter&nbsp;a&nbsp;valid&nbsp;date,&nbsp;example:&nbsp;30/05/2005!</span>") ;
            return null ;
         }

         Date dateOut = new Date(year, month - 1, Integer.parseInt(datum[0])) ;
         error_message2.put(name, "") ;
         return dateOut ;
      }
      catch (Exception e)
      {
         errors = true ;
         error_message2.put(name,
               "<span class=\"errorMessage\">*&nbsp;Please&nbsp;enter&nbsp;a&nbsp;valid&nbsp;date,&nbsp;example:&nbsp;30/05/2005!</span>") ;
      }
      errors = true ;
      return null ;
   }

   /**
    * Tries to check if a given String is a valid email adress. If it fails an
    * error message is generated. This Message can be pulled later from the
    * queue
    * 
    * @param mail
    *           The string to validate returns true if it validated correctly.
    */
   public boolean checkEmail(String mail, String name)
   {
      if (mail.indexOf(".", mail.indexOf("@")) > 2 && mail.indexOf("@") > 1 && mail.indexOf(" ") < 1)
      {
         error_message2.put(name, "") ;
         return true ;
      }
      errors = true ;
      error_message2.put(name, "<span class=\"errorMessage\">*&nbsp;Please&nbsp;enter&nbsp;a&nbsp;valid&nbsp;email&nbsp;adress</span>") ;
      return false ;
   }

   /**
    * @return Returns true if any errors were met during the life of this
    *         instance.
    */
   public boolean isErrors()
   {
      return errors ;
   }

}
