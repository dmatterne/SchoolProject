package database ;

import java.sql.Date ;
import java.sql.ResultSet ;
import java.sql.Timestamp ;
import java.util.logging.FileHandler ;
import java.util.logging.Level ;
import java.util.logging.Logger ;
import java.util.logging.SimpleFormatter ;

import com.intersys.objects.CacheDatabase ;
import com.intersys.objects.CacheQuery ;
import com.intersys.objects.Database ;

/**
 * This connection class houses the Cache database connection and other
 * capabilities that correspond directly to this connection. It also houses the
 * logger as it logs all connection related errors to disk. On the J2ee server the logs are
 * held by the server. This class also closes and cleans up the connection when the function closeResultset is called.
 * Other capabilities of this class are generating resultsets and showing data from this set.
 * Returning a working Database object. Opening the Connection to the database. And executing Sql code.
 * 
 * @author ICT09
 */
public class Connection
{

   private final static String error_line = "\n###########################################################################\n" ;

   private Database connection = null ;

   private ResultSet resultset = null ;

   @Deprecated
   private String last_error = "" ;

   private boolean connected = false ;

   private boolean errors = false ;

   private static Logger logger = Logger.getLogger("Database.Connection") ;

   private static FileHandler fh ;

   private static final String url = "jdbc:Cache://localhost:1972/USER" ;

   //private static final String url = "jdbc:Cache://172.16.244.241:1972/USER" ;


   /**
    * The constructor opens the loger and instantiates the connection object
    * It also opens the file handler for the logger.
    * Please note that on the J2EE server logging is handled by the server,
    * an attempt to open a new log file wil result in a security exception.
    */
   public Connection()
   {
      try
      {
         fh = new FileHandler("kinepolis.log") ;
         fh.setLevel(Level.ALL) ;
         fh.setFormatter(new SimpleFormatter()) ;
         logger.addHandler(fh) ;
      }
      catch (Exception e)
      {
         logger.info("We could not open the file handler, just using default log");
      }
      
      // Request that every detail gets logged.
      // Log a simple INFO message.
      
      logger.info(error_line + "Inititialising Connection Class" + error_line) ;


   }

   /**
    * Closes the connection and cleans up.
    * It closes the logger that was opened in the constructor.
    * It tries to close a resultset, if there was any opened, this is not always so.
    * It closes the connection.
    * 
    * @return If this fails for some reason, the function returns false.
    */
   public boolean closeResultset()
   {
      try
      {
         logger.log(Level.INFO, error_line + "closing the connection and cleaning up" + error_line) ;
         
         resultset.close() ;

         
         errors = false ;
      }
      catch (Exception e)
      {
            logger.info("No resultset to close!");
      }
      
      
      try
      {
         connection.closeAllObjects();
         connection.close();
         connection.flush();
         connected = false ;
      }
      catch (Exception e)
      {
         logger.log(Level.WARNING, "Failed to close the connection", e) ;
         errors = true ;
         return false ;
      }
      
      try
      {
         // the filehandler is not used in J2EE
         fh.flush() ;
         logger.removeHandler(fh) ;
         fh.close() ;
      }
      catch(Exception e)
      {
         logger.info("failed to close the log file, or log file never opened") ;
      }

      

      return true ;
   }

   /**
    * Creates a connection, You can get a reference to this connection trough the getConnection function.
    * 
    * 
    * @return If the connection failed this returns false
    */
   public boolean createConnection()
   {
      boolean check = false ;
      if (connected)
      {
         check = true ;
      }
      else
      {
         String username = "_SYSTEM" ;
         String pwd = "SYS" ;

         try
         {
            
            connection = CacheDatabase.getDatabase(url, username, pwd) ;
            connected = true ;
            check = true ;
         }
         catch (Exception e)
         {
            logger.log(Level.SEVERE, "Failed to connect to Database", e) ;
            connected = false ;
            check = false ;
         }
      }
      return check ;

   }

   /**
    * If a resultset was created this navigates the resultset to the next result
    * It also logs any possible errors.
    * 
    * @return returns true as long as there are no errors and the cursor moved
    *         to the next result
    */
   public boolean nextResult()
   {
      try
      {
         if (resultset.next())
         {
            return true ;
         }
      }
      catch (Exception e)
      {

         logger.log(Level.SEVERE, "Failed to get next row in resultset", e) ;
         connected = false ;
         errors = true ;
         return false ;
      }
      return false ;
   }

/**
 * Gets an int from the resultset
 * @param position
 * the position in the resultset
 * @return
 * the int found in the resultset
 */
   public int getResultInt(int position)
   {
      try
      {
         return resultset.getInt(position) ;
      }
      catch (Exception e)
      {
         connected = false ;
         logger.log(Level.SEVERE, "Failed to get int from resultset", e) ;
         errors = true ;
         return 0 ;
      }
   }
/**
 * Gets a timestamp from the resultset
 * @param position
 * The position in the resultset
 * @return
 * the timestamp found
 */
   public Timestamp getResultTimeStamp(int position)
   {
      try
      {
         return resultset.getTimestamp(position) ;
      }
      catch (Exception e)
      {
         connected = false ;
         logger.log(Level.SEVERE, "Failed to get int from resultset", e) ;
         errors = true ;
         return null ;
      }
   }
/**
 * Gets a string from the resultset
 * @param position
 * the position in the resultset
 * @return
 * The String found
 */
   public String getResultString(int position)
   {
      try
      {
         return resultset.getString(position) ;
      }
      catch (Exception e)
      {
         connected = false ;
         logger.log(Level.SEVERE, "Failed to get String from resultset", e) ;
         errors = true ;
         return "" ;
      }
   }
   /**
    * Gets a Long from the resultset
    * @param position
    * the position in the resultset
    * @return
    * The long found
    */
   public long getResultLong(int position)
   {
      try
      {
         return resultset.getLong(position) ;
      }
      catch (Exception e)
      {
         connected = false ;
         logger.log(Level.SEVERE, "Failed to get Long from resultset", e) ;
         errors = true ;
         return 0 ;
      }
   }
   /**
    * Gets a Date from the resultset
    * @param position
    * the position in the resultset
    * @return
    * The Date found
    */
   public Date getResultDate(int position)
   {
      try
      {
         return resultset.getDate(position) ;
      }
      catch (Exception e)
      {
         connected = false ;
         errors = true ;
         logger.log(Level.SEVERE, "Failed to get Date from resultset", e) ;
         return null ;
      }
   }


   /**
    * Generates and returns a resultset based on sql query
    * You can later on use the function nextResult and getResultString and others to use this resultset
    * You can also exctract the full resultset, should you have more advanced needs.
    * 
    * @param sqlQuery
    *           The query this function will base its resultset on
    * @return True if there was a resultset
    */
   public boolean generateResultSet(String sqlQuery)
   {
      CacheQuery stmt ;
      try
      {
         stmt = new CacheQuery(connection, sqlQuery) ;
         resultset = stmt.execute() ;
      }
      catch (Exception e)
      {
         connected = false ;
         logger.log(Level.SEVERE, "Failed to generate resultset", e) ;
         errors = true ;
         return false ;
      }
      return true ;
   }

   /**
    * Executes a sql query, useful for complex updates or deletes.
    * @param sqlQuery
    * The query to execute
    * @return
    * true if succes, false if it fails
    */
   public boolean executeQuery(String sqlQuery)
   {
      CacheQuery stmt ;
      try
      {
         stmt = new CacheQuery(connection, sqlQuery) ;
         stmt.execute() ;
      }
      catch (Exception e)
      {
         connected = false ;
         logger.log(Level.SEVERE, "Failed to execute query", e) ;
         errors = true ;
         return false ;
      }
      return true ;
   }

   /**
    * returns a database object, it may be helpful to first open the connection
    * via the method makeConnection
    * 
    * @return returns the database object
    */
   public Database getConnection()
   {
      return connection ;
   }

   /**
    * Returns a resultset should you desire to work directly with the resultset.
    * For logging and other reasons it is advised to use the methods in this
    * class to work with the resultset. This method is Deprecated.
    * 
    * @return A resultset object generated with the method generateResultset
    */


   /**
    * Returns the last error that occured in this class
    */

   public String getLastError()
   {
      return last_error ;
   }

   /**
    * If there were any errors in any of the methods of this class, this
    * function returns true
    * 
    * @return true if there were errors in this class.
    */
   public boolean isErrors()
   {
      return errors ;
   }

}
