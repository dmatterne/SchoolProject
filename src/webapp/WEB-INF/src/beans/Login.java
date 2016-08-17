package beans ;

import html.DropDown ;
import html.Input ;
import html.Table ;

import java.io.Serializable;
import java.sql.Date ;

import Comato.Users ;

import com.intersys.objects.CacheException ;

import database.Authentication ;
import database.Connection ;
import database.InputControl ;
import database.MD5 ;
import design.DesignWrapper ;

/**This class makes it able for a user to registrate.
 * This class also sets the right for each user, employee or admin so some might be able to
 * add something, while others cant.
 * 
 * There is also a way to edit your own profile and there is a login screen.
 * All information will be saved into database.
 * @author ICT09
 *
 */
public class Login implements Serializable
{

   /**
    * 
    */
   private static final long serialVersionUID = 42396570680506543L ;

   private Long prs_id ;

   private String usr_name = "" ;

   private String usr_address = "" ;

   private String usr_email = "" ;

   private String usr_tel = "" ;

   private String usr_sex = "" ;

   private String usr_username = "" ;

   private String usr_password = "" ;

   private String usr_passwords = "" ;

   private String cache_id ;

   private String usr_function = "" ;

   private String usr_year = "" ;

   private String usr_month = "" ;

   private String usr_day = "" ;

   private String content = "<html><head><title>Register</title></head>" ;

   private String error_message = "" ;

   private String cnt_id ;

   private Connection con = new Connection() ;

   private InputControl input_control = new InputControl(con) ;

   private Authentication authentication = new Authentication(con) ;

   private DesignWrapper design = new DesignWrapper(authentication) ;

   Date birthday ;


   private String user_id ;

   /**
    * Login a user into the system
    */
   public boolean loginUser()
   {
      // set the authentication instance to the userid aquired from the session
      
      authentication.setPersonId(user_id) ;
      
      boolean authenticated = (authentication.isLoggedOut()) ;
      
      

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page
      
      design.setAccess(authenticated) ;

      // pass authentication to design class
      
      design.setAuthentication(authentication) ;

      // Sets the username in de design class, for fancy display
      
      design.setUserName(authentication.getUsrUserName()) ;


      if (authenticated)
      {
         boolean succes = false ;

         String sql = "select usrUserName, usrPassword, ID from Comato.Users where usrUserName='" + usr_username + "'" ;
         

         if (con.createConnection())
         {
            con.generateResultSet(sql) ;

            // Check each record and search for the username that is entered

            if (con.nextResult())
            {
               // Check if the password that is entered matches the password in
               // the database

               if (con.getResultString(2).equals(MD5.hash(usr_password)))
               {
                  cache_id = con.getResultString(3) ;
                  authentication.setPersonId(cache_id) ;
                  design.setUserName(authentication.getUsrUserName()) ;
                  design.setAccess(true) ;
                  content += "Succesfully Logged In !" ;
                  succes = true ;
               }
               else
               {
                  buildForm() ;
                  content += "<br>Password and Username do not match !!!" ;
               }
            }
            else
            {
               buildForm() ;
               content += "<br>Username not found in Database !" ;
            }
         }
         return succes ;
      }
      content += "you are already logged in!" ;
      return false ;
   }

   /**
    * Add a user to the database
    */
   public void addUser()
   {
      // set the authentication instance to the userid aquired from the session
      
      authentication.setPersonId(user_id) ;
      
      design.setAccess(true) ;

      // pass authentication to design class
      
      design.setAuthentication(authentication) ;

      // Sets the username in de design class, for fancy display
      
      design.setUserName(authentication.getUsrUserName()) ;

      
         Date birthDay = input_control.checkFuture(usr_day, usr_month, usr_year, "birthday") ;
         
         if(authentication.isLoggedOut() || (!(usr_password.equals(usr_passwords))) || (!(usr_password.equals(""))))
            input_control.checkPasswordEquality(usr_password, usr_passwords, "usr_password", "usr_passwords") ;
         input_control.checkEmpty(usr_username, "usr_username") ;
         input_control.checkEmpty(usr_name, "usr_name") ;
         input_control.checkEmpty(usr_address, "usr_address") ;
         // inputControl.checkEmpty(usr_email, "usr_email") ;
         input_control.checkEmail(usr_email, "usr_email") ;

         if (input_control.isErrors())
         {
            buildFormUser() ;
         }

         else
         {
            Users usr = null ;

            // get the last id from the database
            String sql = "SELECT distinct MAX(usrId) FROM Comato.Users" ;

            try
            {

               if (con.createConnection())
               {

                  
                  if (!(authentication.isLoggedIn()))

                  {
                     usr = new Users(con.getConnection()) ;
                     
                  }
                  // generate the resultset to check if the user
                  // already exists

                  // Generate the resultset for the last id
                  con.generateResultSet(sql) ;

                  // read 1 row from the resultset

                  if (con.nextResult())
                  {
                     // get the id, and add 1 to the id, we will then use
                     // this id for
                     // the new object

                     usr.setusrID(con.getResultLong(1) + 1) ;
                  }
                  else
                  {
                     // if the resultset was empty we use 1 for the new id,
                     // in case of
                     // an empty table

                     usr.setusrID((long) 1) ;
                  }

                  if (authentication.isLoggedIn())
                  {
                     content += cnt_id ;
                     
                     authentication.setcntID(Integer.parseInt(cnt_id)) ;
                     authentication.setusrName(usr_name) ;
                     authentication.setusrAddress(usr_address) ;
                     authentication.setusrEmail(usr_email) ;
                     authentication.setusrTel(Long.parseLong(usr_tel)) ;
                     authentication.setusrSex(usr_sex) ;
                     authentication.setusrBirthday(birthDay) ;
                     
                     if(!(usr_password.equals("")));
                        authentication.setusrPassword(MD5.hash(usr_password)) ;
                     authentication.setusrUserName(usr_username) ;
                     // deze functie staat voorlopig nog op admin
                     // bij het uiteindelijke programma moet hier user komen te
                     // staan!!
                     authentication.setusrFunction("Admin") ;

                     // save the object to the database

                     authentication.save() ;
                     
                  }
                  else{
                     
                  
                  // set all the values for this database object
                  content += cnt_id ;
                  String hashedPassword = MD5.hash(usr_password) ;
                  usr.setcntID(Integer.parseInt(cnt_id)) ;
                  usr.setusrName(usr_name) ;
                  usr.setusrAddress(usr_address) ;
                  usr.setusrEmail(usr_email) ;
                  try
                  {
                     usr.setusrTel(Long.parseLong(usr_tel)) ;
                  }
                  catch (NumberFormatException e)
                  {
                     usr.setusrTel(0l) ;
                  }

                  usr.setusrSex(usr_sex) ;
                  usr.setusrBirthday(birthDay) ;
                  usr.setusrPassword(hashedPassword) ;
                  usr.setusrUserName(usr_username) ;
                  // deze functie staat voorlopig nog op admin
                  // bij het uiteindelijke programma moet hier user komen te
                  // staan!!
                  usr.setusrFunction("User") ;

                  // save the object to the database

                  usr.save() ;


                  // display a message to the user that a new user is
                  // registered

                  con.closeResultset() ;
                  content += "<body>New User Registered" ;
               }}
               
            }

            catch (CacheException e)
            {
               // TODO Auto-generated catch block
               content += e.getMessage() ;
            }
         }

            

      }
   

   /**
    * Build the form for a user to register
    */
   public void buildFormUser()
   {
      // set the authentication instance to the userid aquired from the session
      
      authentication.setPersonId(user_id) ;
      boolean authenticated = !(authentication.isLoggedIn()) ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page
      
      design.setAccess(authenticated) ;

      // pass authentication to design class
      
      design.setAuthentication(authentication) ;

      // Sets the username in de design class, for fancy display
      
      design.setUserName(authentication.getUsrUserName()) ;


      if (authenticated)
      {
         content += "<h2>Register Here </h2>" + "<form name=\"form\" method=\"post\" action=\"index.jsp?module=login&function=add_user\">" ;

         // create a new table object

         Table table = new Table(2) ;

         // create a new Input instance


         // add items to the table and go to the next row

         table.addItem("Username") ;
         table.addRedStarToLast() ;
         table.addItem(new Input("usr_username", usr_username, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("usr_username")) ;

         table.addItem("Password") ;
         table.addRedStarToLast() ;
         table.addItem(new Input("usr_password", "", Input._TYPE_PASSWORD)) ;
         table.addToLastItem(input_control.getErrorMessage("usr_password")) ;

         table.addItem("Retype&nbsp;Password") ;
         table.addRedStarToLast() ;
         table.addItem(new Input("usr_passwords", "", Input._TYPE_PASSWORD)) ;
         table.addToLastItem(input_control.getErrorMessage("usr_passwords")) ;

         table.addItem("First&nbsp;&amp;&nbsp;Last&nbsp;Name") ;
         table.addRedStarToLast() ;
         table.addItem(new Input("usr_name", usr_name, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("usr_name")) ;

         table.addItem("Address") ;
         table.addRedStarToLast() ;
         table.addItem(new Input("usr_address", usr_address, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("usr_address")) ;

         table.addItem("Country") ;
         table.addItem(new DropDown("cnt_id", String.valueOf(cnt_id), con, DropDown._TYPE_COUNTRIES)) ;

         table.addItem("E-mail") ;
         table.addRedStarToLast() ;
         table.addItem(new Input("usr_email", usr_email, Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("usr_email")) ;

         table.addItem("Birthday") ;
         table.addRedStarToLast() ;
         table.addItem(DropDown.getDateField("usr_day", "usr_month", "usr_year", 0, 100)) ;
         table.addToLastItem(input_control.getErrorMessage("birthday")) ;

         table.addItem("Sex") ;
         table
               .addItem("<input name=\"usr_sex\" type=\"radio\" value=\"M\" checked>M &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name=\"usr_sex\" type=\"radio\" value=\"V\">V ") ;

         table.addItem("Telephone&nbsp;Number") ;
         table.addItem(new Input("usr_tel", String.valueOf(usr_tel), Input._TYPE_TEXT)) ;
         table.addToLastItem(input_control.getErrorMessage("usr_tel")) ;

         table.addItem("") ;
         table.addRequiredPartOne() ;
         table.addItem("") ;
         table.addRequiredPartTwo() ;

         String submit = "" ;
         if (authentication.isLoggedIn())
            submit += new Input("submit", "Edit", Input._TYPE_SUBMIT) ;
         else
            submit += new Input("submit", "Add", Input._TYPE_SUBMIT) ;

         table.addItem(submit) ;
         table.addItem("") ;
         table.nextRow() ;

         content += table ;
      }

   }

   /**
    * Make the login form
    */
   public void buildForm()
   {
      // set the authentication instance to the userid aquired from the session
      
      authentication.setPersonId(user_id) ;
      boolean authenticated = !(authentication.isLoggedIn()) ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page
      
      design.setAccess(authenticated) ;

      // pass authentication to design class
      
      design.setAuthentication(authentication) ;

      // Sets the username in de design class, for fancy display
      
      design.setUserName(authentication.getUsrUserName()) ;


      if (authenticated)
      {
         content += "<form method=\"post\" action=\"index.jsp?module=login&function=loginOk\">"
               + "<h2>Log In</h2>"
               + "<table border=\"0\"><tr><td width=\"147\">Username</td><td width=\"21\">:</td><td width=\"275\"><input type=\"text\" name=\"usr_username\"></td></tr>"
               + "<tr><td width=\"147\">Password</td><td width=\"21\">:</td><td width=\"275\"><input type=\"password\" name=\"usr_password\"></td></tr>"
               + "</table><br><input type=\"submit\" value=\"Enter\"/>" ;
      }
      else
      {
         content += "You are already logged in!" ;
      }
   }

   public void editUser()
   {
      // set the authentication instance to the userid aquired from the session
      
      authentication.setPersonId(user_id) ;
      boolean authenticated = authentication.isAdmin() ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page
      
      design.setAccess(authenticated) ;

      // Sets the username in de design class, for fancy display
      
      design.setUserName(authentication.getUsrUserName()) ;


      // get the the object from the database


      // fill the variables from the database
      
      usr_username = authentication.getusrUserName() ;
      usr_name = authentication.getusrName() ;
      usr_address = authentication.getusrAddress() ;
      cnt_id = authentication.getcntID().toString() ;
      usr_email = authentication.getusrEmail() ;
      birthday = input_control.checkFuture(usr_day, usr_month, usr_year, "birthday") ;
      usr_sex = authentication.getusrSex() ;
      usr_tel = authentication.getusrTel().toString() ;

      // build the form, it will use the above variables
      input_control = new InputControl(con);
      buildFormUser() ;


   }

   /**
    * Returns the string content. This string can first be built using other
    * functions in this bean
    * 
    * @return
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
    * The content to set
    * 
    * @param content
    */
   public void setContent(String content)
   {
      this.content = content ;
   }

   /**
    * Returns the errormessage
    * 
    * @return
    */
   public String getError_message()
   {
      return error_message ;
   }

   /**
    * The errormessage to set
    * 
    * @param errorMessage
    */
   public void setError_message(String error_message)
   {
      this.error_message = error_message ;
   }

   /**
    * Returns the function
    * 
    * @return
    */
   public String getUsr_function()
   {
      return usr_function ;
   }

   /**
    * The function to set
    * 
    * @param usr_function
    */
   public void setUsr_function(String usr_function)
   {
      this.usr_function = usr_function ;
   }

   /**
    * Returns the password
    * 
    * @return
    */
   public String getUsr_password()
   {
      return usr_password ;
   }

   /**
    * The password to set
    * 
    * @param usr_password
    */
   public void setUsr_password(String usr_password)
   {
      this.usr_password = usr_password ;
   }

   /**
    * Returns the username
    * 
    * @return
    */
   public String getUsr_username()
   {
      return usr_username ;
   }

   /**
    * The username to set
    * 
    * @param usr_username
    */
   public void setUsr_username(String usr_username)
   {
      this.usr_username = usr_username ;
   }

   /**
    * Returns the person id
    * 
    * @return
    */
   public Long getPrs_id()
   {
      return prs_id ;
   }

   /**
    * The person id to set
    * 
    * @param prs_id
    */
   public void setPrs_id(Long prs_id)
   {
      this.prs_id = prs_id ;
   }

   /**
    * Returns the country
    * 
    * @return
    */
   public String getCnt_id()
   {
      return cnt_id ;
   }

   /**
    * The country to set
    * 
    * @param cnt_id
    */
   public void setCnt_id(String cnt_id)
   {
      this.cnt_id = cnt_id ;
   }

   /**
    * Returns the day of the birthdate
    * 
    * @return
    */
   public String getUsr_day()
   {
      return usr_day ;
   }

   /**
    * The day of the birthdate to set
    * 
    * @param usr_day
    */
   public void setUsr_day(String usr_day)
   {
      this.usr_day = usr_day ;
   }

   /**
    * Returns the email
    * 
    * @return
    */
   public String getUsr_email()
   {
      return usr_email ;
   }

   /**
    * The email to set
    * 
    * @param usr_email
    */
   public void setUsr_email(String usr_email)
   {
      this.usr_email = usr_email ;
   }

   /**
    * Returns the month of the birthdate
    * 
    * @return
    */
   public String getUsr_month()
   {
      return usr_month ;
   }

   /**
    * The month of the birthdate to set
    * 
    * @param usr_month
    */
   public void setUsr_month(String usr_month)
   {
      this.usr_month = usr_month ;
   }

   /**
    * Returns the name
    * 
    * @return
    */
   public String getUsr_name()
   {
      return usr_name ;
   }

   /**
    * The name to set
    * 
    * @param usr_name
    */
   public void setUsr_name(String usr_name)
   {
      this.usr_name = usr_name ;
   }

   /**
    * Returns the password
    * 
    * @return
    */
   public String getUsr_passwords()
   {
      return usr_passwords ;
   }

   /**
    * The password to set
    * 
    * @param usr_passwords
    */
   public void setUsr_passwords(String usr_passwords)
   {
      this.usr_passwords = usr_passwords ;
   }

   /**
    * Returns the sex
    * 
    * @return
    */
   public String getUsr_sex()
   {
      return usr_sex ;
   }

   /**
    * The sex to set
    * 
    * @param usr_sex
    */
   public void setUsr_sex(String usr_sex)
   {
      this.usr_sex = usr_sex ;
   }

   /**
    * Returns the telephone number
    * 
    * @return
    */
   public String getUsr_tel()
   {
      return usr_tel ;
   }

   /**
    * The telephone number to set
    * 
    * @param usr_tel
    */
   public void setUsr_tel(String usr_tel)
   {
      this.usr_tel = usr_tel ;
   }

   /**
    * Returns the year of the birthdate
    * 
    * @return
    */
   public String getUsr_year()
   {
      return usr_year ;
   }

   /**
    * The year of the birthdate to set
    * 
    * @param usr_year
    */
   public void setUsr_year(String usr_year)
   {
      this.usr_year = usr_year ;
   }

   /**
    * Returns the address
    * 
    * @return
    */
   public String getUsr_address()
   {
      return usr_address ;
   }

   /**
    * The address to set
    * 
    * @param usr_address
    */
   public void setUsr_address(String usr_address)
   {
      this.usr_address = usr_address ;
   }

   
   public String getCache_id()
   {
      return cache_id ;
   }

   
   public void setCache_id(String cache_id)
   {
      this.cache_id = cache_id ;
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
