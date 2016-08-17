package beans ;

import java.util.Date ;
import java.util.GregorianCalendar ;
import java.io.Serializable ;
import java.text.DateFormat ;

import Niveau34.Programming ;
import Niveau34.Tickets ;

import com.intersys.objects.CacheException ;
import com.intersys.objects.Id ;

import database.Authentication ;
import database.Connection ;
import design.DesignWrapper ;
import html.DropDown ;
import html.Input ;
import html.Link ;
import html.Table ;

/**
 * This class provides a form where u can order a ticket. 
 * It then orders the ticket and prints it using a generated barcode.
 * The ticket information is also saved into the database th
 * @author ICT09
 *
 */
public class Ticket implements Serializable
{

   private static final long serialVersionUID = 53469436787634L ;

   private int tck_id ;

   private int prg_id ;

   private int cst_id ;

   private String dsc_id ;

   private int cst_tel ;

   private int cst_day ;

   private int cst_year ;

   private int cst_month ;

   private Date cst_birthdate ;

   private float tck_price ;

   private float dsc_amount ;

   private String dsc_name ;

   private String cst_name = "" ;

   private String cst_address = "" ;

   private String cst_email = "" ;

   private String content = "" ;

   private String user_id = "0" ;

   private String cache_id = "" ;

   private String aantal = "1" ;

   Connection con = new Connection() ;

   Table table = new Table(2) ;

   private String tempPrice ;

   private String[] discounts ;

   Programming prg ;

   private String filmnaam = "" ;

   int fullPrice ;

   String price = "100" ;

   private Authentication authentication = new Authentication(con) ;

   private DesignWrapper design = new DesignWrapper(authentication) ;

/**
 * Creates the form to order a ticket.
 *
 */
   public void buildForm()
   {
      // set the authentication instance to the userId aquired from the session
      
      authentication.setPersonId(user_id) ;
      
      boolean authenticated = authentication.isLoggedIn() ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page
      
      design.setAccess(authenticated) ;
      
      // Sets the username in de design class, for fancy display
      
      design.setUserName(authentication.getUsrUserName()) ;


      if (authenticated)
      {
         if (aantal == null)
         {
            aantal = "1" ;
         }

         // create the connection
         
         con.createConnection() ;
         int tempAantal ;
         try
         {
            tempAantal = Integer.parseInt(aantal) ;
         }
         catch (NumberFormatException e)
         {
            tempAantal = 1 ;
         }

         try
         {
       
            prg = (Programming) Programming._open(con.getConnection(), new Id(cache_id)) ;
            content += "<form method=\"post\" action=\"index.jsp?module=Tickets&function=orderTicket\"><h2>Order Ticket </h2>" ;

            table.addItem("Ticket Holder : ") ;
            table.addItem(authentication.getusrName()) ;
            table.nextRow() ;
            table.addItem("Holder's Adress : ") ;
            table.addItem(authentication.getusrAddress()) ;
            table.nextRow() ;
            table.addItem("Holder's Email : ") ;
            table.addItem(authentication.getusrEmail()) ;
            table.nextRow() ;
            table.addItem("Date : ") ;

            GregorianCalendar cal = new GregorianCalendar() ;
            
            cal.setTime(prg.getprgDateTime()) ;
            
            DateFormat date = DateFormat.getDateInstance(DateFormat.LONG) ;
            String datestring = date.format(prg.getprgDateTime()) + " " + cal.get(GregorianCalendar.HOUR) + ":" + cal.get(GregorianCalendar.MINUTE) ;

            table.addItem(datestring) ;
            table.nextRow() ;
            table.addItem("Movie : ") ;

            String sql = "Select fliName from Comato.FlmInfo where fliID = " + prg.getfliID() ;

            con.generateResultSet(sql) ;
            
            if (con.nextResult())
            {
               filmnaam = con.getResultString(1) ;
               table.addItem(filmnaam) ;
            }

            table.addToLastItem(new Input("cache_Id", String.valueOf(cache_id), Input._TYPE_HIDDEN)) ;
            table.addToLastItem(new Input("aantal", String.valueOf(this.aantal), Input._TYPE_HIDDEN)) ;

            tempPrice = String.valueOf(prg.getprgPrice()) ;

            table.addToLastItem(new Input("tempPrice", String.valueOf(tempPrice), Input._TYPE_HIDDEN)) ;
            table.nextRow() ;
            table.addItem("Standard price per ticket : ") ;
            table.addItem("&euro; " + prg.getprgPrice()) ;
            table.addItem("Number of tickets : ") ;
            table.addItem(DropDown.getNumbers("index.jsp?module=Tickets&function=makeTicket&cache_Id=" + cache_id + "&aantal=", aantal)) ;
            table.nextRow() ;


            for (int i = 1; i <= tempAantal; i++)
            {
               table.addItem("Discount for person " + i + " : ") ;
               table.addItem(new DropDown("dsc_id" + i, "", con, DropDown._TYPE_DISCOUNTS)) ;
               table.nextRow() ;
            }


            content += table + "<br>" + new Input("submit", "Order", Input._TYPE_SUBMIT) ;
            content += "</form>" ;


         }
         catch (CacheException e)
         {
            // TODO Auto-generated catch block
            
            e.printStackTrace() ;
         }
      }

      /**
       * Opens an existing item for editing and sets this data into the items in
       * this class. Then it asks buildform to save this.
       */
   }

   /**
    * Code to order a ticket.
    *
    */
   public void orderTicket()
   {
      // set the authentication instance to the userId aquired from the session
      
      authentication.setPersonId(user_id) ;
      
      boolean authenticated = authentication.isLoggedIn() ;

      // pass the acces to the design class,
      // only admins are allowed to view the content of this page
      
      design.setAccess(authenticated) ;
      
      // Sets the username in de design class, for fancy display
      
      design.setUserName(authentication.getUsrUserName()) ;


      if (authenticated)
      {
         con.createConnection() ;
         content += "<form><h2>Print Ticket </h2>" ;

         double totalprice = 0 ;

         for (String s1 : discounts)
         {
           
            try
            {
               Tickets ticket = new Tickets(con.getConnection()) ;
               
               ticket.setdscID(Integer.parseInt(s1)) ;

               String sql = "Select prgID from Niveau34.Programming where ID=" + cache_id ;
               
               con.generateResultSet(sql) ;
               
               if (con.nextResult())
                  ticket.setprgID(con.getResultInt(1)) ;

               sql = "SELECT distinct MAX(tckID) FROM Niveau34.Tickets" ;
               con.generateResultSet(sql) ;

               if (con.nextResult())
                  ticket.settckID(con.getResultLong(1) + 1) ;
               else
                  ticket.settckID((long) 1) ;

               sql = "Select dscAmount from Niveau34.Discounts where dscID = " + s1 ;
               con.generateResultSet(sql) ;
               
               if (con.nextResult())
               {
                  double temp = Double.parseDouble(tempPrice) - con.getResultInt(1) ;
                  
                  if (temp < 0)
                     totalprice += 0 ;
                  else
                     totalprice += temp ;
               }

               ticket.settckPrice(Double.parseDouble(price)) ;
               ticket.setusrID(authentication.getusrID().intValue()) ;
               ticket.save() ;
            }
            catch (CacheException e)
            {
               // TODO Auto-generated catch block
               
               e.printStackTrace() ;
               content += "ERROR" ;
               content += "</form></body>" ;
            }
         }
         
         price = String.valueOf(totalprice) ;
         content += "Ticket ordered. Click " ;
         content += new Link("index.jsp?module=Tickets&function=printTicket&cache_Id=" + cache_id + "&price=" + price + "&aantal=" + this.aantal,
               "here", "Click here to print ticket", Link._TARGET_BLANK)
               + " to print ticket" ;
         content += "</form>" ;
      }
   }

   /**
    * Method for creating the ticket itself.
    *
    */
   public void printTicket()
   {
      // set the authentication instance to the userId aquired from the session
      
      authentication.setPersonId(user_id) ;
      
      boolean authenticated = authentication.isLoggedIn() ;

      if (authenticated)
      {
         content = "<body>" ;
         content += "<img src=\"Barcode?image=3&type=1&data=123456789012&barwidth=1&height=80\"><br>" ;
         try
         {
            prg = (Programming) Programming._open(con.getConnection(), new Id(cache_id)) ;
            table.addItem("Naam : ") ;
            table.addItem(authentication.getusrName()) ;
            table.nextRow() ;
            table.addItem("Filmnaam : ") ;
            
            String sql = "Select fliName from Comato.FlmInfo where fliID = " + prg.getfliID() ;

            con.generateResultSet(sql) ;
            
            if (con.nextResult())
            {
               filmnaam = con.getResultString(1) ;
               table.addItem(filmnaam) ;
            }
            table.nextRow() ;

            table.addItem("Datum & uur : ") ;
            
            GregorianCalendar cal = new GregorianCalendar() ;
            
            cal.setTime(prg.getprgDateTime()) ;
            
            DateFormat date = DateFormat.getDateInstance(DateFormat.LONG) ;
            
            String datestring = date.format(prg.getprgDateTime()) + " " + cal.get(GregorianCalendar.HOUR) + ":" + cal.get(GregorianCalendar.MINUTE) ;
            
            table.addItem(datestring) ;

            table.nextRow() ;

            table.addItem("Prijs : ") ;
            table.addItem("� " + price) ;

            table.addItem("Aantal personen : ") ;
            
            if (this.aantal == null)
               this.aantal = "1" ;

            table.addItem(this.aantal) ;
            table.nextRow() ;
            table.addItem("Zaal") ;
            table.addItem(prg.gethlcID()) ;
            table.nextRow() ;
         }
         catch (CacheException e)
         {
         }


         content += table ;
         content += "</body>" ;
      }
   }

   public String getCst_name()
   {
      return cst_name ;
   }

   public void setCst_name(String cst_name)
   {
      this.cst_name = cst_name ;
   }

   public int getCst_tel()
   {
      return cst_tel ;
   }

   public void setCst_tel(int cst_tel)
   {
      this.cst_tel = cst_tel ;
   }

   public String getCst_address()
   {
      return cst_address ;
   }

   public void setCst_address(String cst_address)
   {
      this.cst_address = cst_address ;
   }

   public Date getCst_birthdate()
   {
      return cst_birthdate ;
   }

   public void setCst_birthdate(Date cst_birthdate)
   {
      this.cst_birthdate = cst_birthdate ;
   }

   public String getCst_email()
   {
      return cst_email ;
   }
/**
 * 
 * @param cst_email
 */
   public void setCst_email(String cst_email)
   {
      this.cst_email = cst_email ;
   }
/**
 * 
 * @return
 */
   public String getDsc_name()
   {
      return dsc_name ;
   }
/**
 * 
 * @param dsc_name
 */
   public void setDsc_name(String dsc_name)
   {
      this.dsc_name = dsc_name ;
   }
/**
 * 
 * @return
 */
   public float getDsc_amount()
   {
      return dsc_amount ;
   }
/**
 * 
 * @param dsc_amount
 */
   public void setDsc_amount(int dsc_amount)
   {
      this.dsc_amount = dsc_amount ;
   }
/**
 * 
 * @return
 */
   public int getCst_id()
   {
      return cst_id ;
   }
/**
 * 
 * @param cst_id
 */
   public void setCst_id(int cst_id)
   {

      this.cst_id = cst_id ;
   }
   /**
    * 
    * @return
    */
   public String getDsc_id()
   {
      return dsc_id ;
   }

   /**
    * @return
    */
   public int getPrg_id()
   {
      return prg_id ;
   }

   /**
    * @param prg_id
    */
   public void setPrg_id(int prg_id)
   {
      this.prg_id = prg_id ;
   }

   /**
    * @return
    */
   public int getTck_id()
   {
      return tck_id ;
   }

   /**
    * @param tck_id
    */
   public void setTck_id(int tck_id)
   {
      this.tck_id = tck_id ;
   }

   /**
    * @return
    */
   public float getTck_price()
   {
      return tck_price ;
   }

   /**
    * @param tck_price
    */
   public void setTck_price(float tck_price)
   {
      this.tck_price = tck_price ;
   }

   /**
    * @return
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
    * @return
    */
   public String getContentNoDesign()
   {
      con.closeResultset() ;
      return content ;
   }

   /**
    * @param content
    */
   public void setContent(String content)
   {
      this.content = content ;
   }

   /**
    * @return
    */
   public int getCst_day()
   {
      return cst_day ;
   }

   /**
    * @param cst_day
    */
   public void setCst_day(int cst_day)
   {
      this.cst_day = cst_day ;
   }

   /**
    * @return
    */
   public int getCst_month()
   {
      return cst_month ;
   }

   /**
    * @param cst_month
    */
   public void setCst_month(int cst_month)
   {
      this.cst_month = cst_month ;
   }

   /**
    * @return
    */
   public int getCst_year()
   {
      return cst_year ;
   }

   /**
    * @param cst_year
    */
   public void setCst_year(int cst_year)
   {
      this.cst_year = cst_year ;
   }

   /**
    * @param dsc_amount
    */
   public void setDsc_amount(float dsc_amount)
   {
      this.dsc_amount = dsc_amount ;
   }

   /**
    * @return
    */
   public String getUser_id()
   {
      return user_id ;
   }

   /**
    * @param userId
    */
   public void setUser_id(String userId)
   {
      this.user_id = userId ;
   }

   /**
    * @return
    */
   public String getCache_id()
   {
      return cache_id ;
   }

   /**
    * @param cache_Id
    */
   public void setCache_id(String cache_Id)
   {
      this.cache_id = cache_Id ;
   }

   /**
    * @param dsc_id
    */
   public void setDsc_id(String dsc_id)
   {
      this.dsc_id = dsc_id ;
   }

   /**
    * @return
    */
   public String getAantal()
   {
      return aantal ;
   }

   /**
    * @param aantal
    */
   public void setAantal(String aantal)
   {
      this.aantal = aantal ;
   }

   /**
    * @return
    */
   public String[] getDiscounts()
   {
      return discounts ;
   }

   /**
    * @param discounts
    */
   public void setDiscounts(String[] discounts)
   {
      this.discounts = discounts ;
   }

   /**
    * @return
    */
   public String getTempPrice()
   {
      return tempPrice ;
   }

   /**
    * @param tempPrice
    */
   public void setTempPrice(String tempPrice)
   {
      this.tempPrice = tempPrice ;
   }

   /**
    * @return
    */
   public String getPrice()
   {
      return price ;
   }

   /**
    * @param price
    */
   public void setPrice(String price)
   {
      this.price = price ;
   }

   /**
    * @return
    */
   public Authentication getAuthentication()
   {
      return authentication ;
   }

   /**
    * @param authentication
    */
   public void setAuthentication(Authentication authentication)
   {
      this.authentication = authentication ;
   }

   /**
    * Gets the Connection.
    * 
    * @return con
    */
   public Connection getCon()
   {
      return con ;
   }

   /**
    * Sets the connection
    * 
    * @param con
    *           Connection to database
    */
   public void setCon(Connection con)
   {
      this.con = con ;
   }

   /**
    * Gets the desing
    * 
    * @return design
    */
   public DesignWrapper getDesign()
   {
      return design ;
   }

   /**
    * Sets the design .
    * 
    * @param design
    */
   public void setDesign(DesignWrapper design)
   {
      this.design = design ;
   }

   /**
    * Gets the name of the movie
    * 
    * @return filmnaam name of movie
    */
   public String getFilmnaam()
   {
      return filmnaam ;
   }

   /**
    * Sets the name of the movie
    * 
    * @param filmnaam
    *           Name of the movie
    */
   public void setFilmnaam(String filmnaam)
   {
      this.filmnaam = filmnaam ;
   }

   /**
    * Gets the full price of a ticket.
    * 
    * @return fullPrice
    */
   public int getFullPrice()
   {
      return fullPrice ;
   }

   /**
    * Sets the fullPrice of the ticket.
    * 
    * @param fullPrice
    *           The full price
    */
   public void setFullPrice(int fullPrice)
   {
      this.fullPrice = fullPrice ;
   }

   /**
    * Gets a programming prg.
    * 
    * @return
    */
   public Programming getPrg()
   {
      return prg ;
   }

   /**
    * Sets a new programming.
    * 
    * @param prg
    */
   public void setPrg(Programming prg)
   {
      this.prg = prg ;
   }

   /**
    * Gets the table.
    * 
    * @return table.
    */
   public Table getTable()
   {
      return table ;
   }

   /**
    * sets the table
    * 
    * @param table
    *           contains a table.
    */
   public void setTable(Table table)
   {
      this.table = table ;
   }

}
