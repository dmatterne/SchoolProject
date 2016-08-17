package servlets ;

import java.io.IOException ;
import java.io.InputStream ;
import java.io.OutputStream ;

import javax.servlet.ServletConfig ;
import javax.servlet.ServletException ;
import javax.servlet.http.HttpServlet ;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;
import database.Connection ;
import Comato.Trailers ;
import com.intersys.objects.Id ;

/**
 * Creates the stream for the trailer and displays it
 * @author ICT09
 *
 */
public class showTrailer extends HttpServlet
{


   private static final long serialVersionUID = 7880786601281894828L ;

   private Connection con = new Connection() ;

   /**
    * Setup database connection and create SQL statement
    */
   public void init(ServletConfig config) throws ServletException
   {
      con.createConnection() ;
   }


   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
      doPost(request, response) ;
   }

/**
 * Gets trailer from database and shows it as trailer on website
 */
   public void doPost(HttpServletRequest request, HttpServletResponse response)
   {
      try
      {
         Trailers trl = (Trailers) Trailers._open(con.getConnection(), new Id((String) request.getParameter("ID"))) ;
         InputStream in ;

         in = trl.gettrlTrailerIn() ;

         OutputStream outs = response.getOutputStream() ;
         byte[] buf = new byte[1024] ;
         int count = 0 ;

         while ((count = in.read(buf)) >= 0)
         {
            outs.write(buf, 0, count) ;
         }

         in.close() ;
         outs.flush() ;
         outs.close() ;
         con.closeResultset() ;

      }
      catch (Exception exception)
      {

      }
      con.closeResultset() ;

   }
}
