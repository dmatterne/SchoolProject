package database ;

import java.sql.Date ;

import Comato.Users ;

import com.intersys.objects.CacheException ;
import com.intersys.objects.Id ;

/**
 * This class provides basic authentication capabilities. It provides acces to
 * all the information We have on the user that is currently logged in. The
 * constructor of this class needs a Database.Connection object to accomplish
 * all this. This can be the same Connection object you use in other java files,
 * so that a single connection is shared during the run of the page. You will
 * still need to pass the user id to the function if you need to acces
 * information from the user After the id is passed to this class trough the
 * setPersonId function, all information on the user is available trough the
 * corresponding methods in this class. Authentication can happen respectively
 * via the isAdmin, isLoggedIn, isLoggedOut and isEmployee methods. These can be
 * used in other class to check the authentication of the current user.
 * 
 * @author ICT09
 */
public class Authentication
{

   private Id user_id ;

   private Users user ;

   private Connection con ;

   private boolean debug_mode = false ;

   public Authentication(Connection con)
   {
      this.con = con ;
      con.createConnection() ;
   }

   /**
    * Sets the userId and opens this user from the database, later
    * authentication is possible
    * 
    * @param userId
    *           The userId to pass to this class
    */
   public void setPersonId(String user_id)
   {

      if (user_id == null)
         user_id = "" ;

      if (!(user_id.equals("")))
      {
         this.user_id = new Id(user_id) ;
         this.getUserInfo() ;
      }

   }

   private void getUserInfo()
   {
      try
      {
         user = (Users) Users._open(con.getConnection(), user_id) ;
      }
      catch (Exception e)
      {

      }
   }

   public String getUsrFunction()
   {
      try
      {
         return user.getusrFunction() ;
      }
      catch (Exception e)
      {
         return "" ;
      }
   }

   /**
    * check if the current user is Admin
    * 
    * @return If the current user is an Admin return true
    */
   public boolean isAdmin()
   {
      if (debug_mode)
         return debug_mode ;

      if (user != null)
      {
         try
         {
            if (user.getusrFunction().equalsIgnoreCase("Admin"))
               return true ;
         }
         catch (Exception e)
         {
            return false ;
         }
      }
      return false ;
   }

   /**
    * check if the current user is Logged In
    * 
    * @return If the current user is logged in, return true
    */
   public boolean isLoggedIn()
   {
      if (debug_mode)
         return debug_mode ;

      if (user == null)
         return false ;

      return true ;
   }

   /**
    * check if the current user is Logged Out
    * 
    * @return If the current user is logged out, return true
    */
   public boolean isLoggedOut()
   {
      if (debug_mode)
         return debug_mode ;

      if (user == null)
         return true ;

      return false ;
   }

   /**
    * check if the current user is an employee
    * 
    * @return If the current user is an employee, return true
    */
   public boolean isEmployee()
   {
      if (debug_mode)
         return debug_mode ;

      if (user != null)
      {
         try
         {
            if (user.getusrFunction().equalsIgnoreCase("Employee"))
               return true ;
         }
         catch (Exception e)
         {
            return false ;
         }
      }
      return false ;
   }

   public String getUsrUserName()
   {
      try
      {
         return user.getusrUserName() ;
      }
      catch (Exception e)
      {
         return "" ;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#getcntID()
    */
   public Integer getcntID()
   {
      try
      {
         return user.getcntID() ;
      }
      catch (Exception e)
      {
         return 0 ;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.intersys.classes.Persistent#getId()
    */
   public Id getId()
   {
      try
      {
         return user.getId() ;
      }
      catch (Exception e)
      {
         return null ;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#getusrAddress()
    */
   public String getusrAddress()
   {
      try
      {
         return user.getusrAddress() ;
      }
      catch (Exception e)
      {
         return "" ;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#getusrBirthday()
    */
   public Date getusrBirthday()
   {
      try
      {
         return user.getusrBirthday() ;
      }
      catch (Exception e)
      {
         return null ;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#getusrEmail()
    */
   public String getusrEmail()
   {
      try
      {
         return user.getusrEmail() ;
      }
      catch (Exception e)
      {
         return "" ;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#getusrFunction()
    */
   public String getusrFunction()
   {
      try
      {
         return user.getusrFunction() ;
      }
      catch (Exception e)
      {
         return "" ;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#getusrID()
    */
   public Long getusrID()
   {
      try
      {
         return user.getusrID() ;
      }
      catch (Exception e)
      {
         return new Long(0) ;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#getusrName()
    */
   public String getusrName()
   {
      try
      {
         return user.getusrName() ;
      }
      catch (Exception e)
      {
         return "" ;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#getusrPassword()
    */
   public String getusrPassword()
   {
      try
      {
         return user.getusrPassword() ;
      }
      catch (Exception e)
      {
         return "" ;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#getusrSex()
    */
   public String getusrSex()
   {
      try
      {
         return user.getusrSex() ;
      }
      catch (Exception e)
      {
         return "" ;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#getusrTel()
    */
   public Long getusrTel()
   {
      try
      {
         return user.getusrTel() ;
      }
      catch (Exception e)
      {
         return new Long(0) ;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#getusrUserName()
    */
   public String getusrUserName()
   {
      try
      {
         return user.getusrUserName() ;
      }
      catch (Exception e)
      {
         return "" ;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.intersys.classes.Persistent#save()
    */
   public int save() throws CacheException
   {
      return user.save() ;
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#setcntID(java.lang.Integer)
    */
   public void setcntID(Integer value) throws CacheException
   {
      user.setcntID(value) ;
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#setusrAddress(java.lang.String)
    */
   public void setusrAddress(String value) throws CacheException
   {
      user.setusrAddress(value) ;
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#setusrBirthday(java.sql.Date)
    */
   public void setusrBirthday(Date value) throws CacheException
   {
      user.setusrBirthday(value) ;
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#setusrEmail(java.lang.String)
    */
   public void setusrEmail(String value) throws CacheException
   {
      user.setusrEmail(value) ;
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#setusrFunction(java.lang.String)
    */
   public void setusrFunction(String value) throws CacheException
   {
      user.setusrFunction(value) ;
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#setusrID(java.lang.Long)
    */
   public void setusrID(Long value) throws CacheException
   {
      user.setusrID(value) ;
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#setusrName(java.lang.String)
    */
   public void setusrName(String value) throws CacheException
   {
      user.setusrName(value) ;
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#setusrPassword(java.lang.String)
    */
   public void setusrPassword(String value) throws CacheException
   {
      user.setusrPassword(value) ;
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#setusrSex(java.lang.String)
    */
   public void setusrSex(String value) throws CacheException
   {
      user.setusrSex(value) ;
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#setusrTel(java.lang.Long)
    */
   public void setusrTel(Long value) throws CacheException
   {
      user.setusrTel(value) ;
   }

   /*
    * (non-Javadoc)
    * 
    * @see Comato.Users#setusrUserName(java.lang.String)
    */
   public void setusrUserName(String value) throws CacheException
   {
      user.setusrUserName(value) ;
   }

   
   public Id getUser_id()
   {
      return user_id ;
   }

   
   public void setUser_id(Id user_id)
   {
      this.user_id = user_id ;
   }
}
