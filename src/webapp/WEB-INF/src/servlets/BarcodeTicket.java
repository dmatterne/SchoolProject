package servlets ;

import java.io.IOException ;
import java.io.PrintWriter ;

import javax.servlet.ServletException ;
import javax.servlet.http.HttpServlet ;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import com.bokai.barcodes.Barcode ;
import com.bokai.drawing.SimpleFont ;

public class BarcodeTicket extends HttpServlet
{

   private static final long serialVersionUID = 7880786601281894828L ;

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
   {
      int image = Barcode.BARCODE_PNG ;
      int type = Barcode.BCT_3OF9 ;
      int rotate = 0 ;
      int height = 40 ;
      int barwidth = 1 ;
      int fontsize = -1 ;

      String tmp ;

      tmp = request.getParameter("type") ;
      try
      {
         if (tmp != null)
            type = Integer.parseInt(tmp) ;
      }
      catch (NumberFormatException e)
      {
      }

      tmp = request.getParameter("image") ;
      try
      {
         if (tmp != null)
            image = Integer.parseInt(tmp) ;
      }
      catch (NumberFormatException e)
      {
      }

      tmp = request.getParameter("rotate") ;
      try
      {
         if (tmp != null)
            rotate = Integer.parseInt(tmp) ;
      }
      catch (NumberFormatException e)
      {
      }

      tmp = request.getParameter("height") ;
      try
      {
         if (tmp != null)
            height = Integer.parseInt(tmp) ;
      }
      catch (NumberFormatException e)
      {
      }

      tmp = request.getParameter("barwidth") ;
      try
      {
         if (tmp != null)
            barwidth = Integer.parseInt(tmp) ;
      }
      catch (NumberFormatException e)
      {
      }

      tmp = request.getParameter("fontsize") ;
      try
      {
         if (tmp != null)
            fontsize = Integer.parseInt(tmp) ;
      }
      catch (NumberFormatException e)
      {
      }


      Barcode barcode ;

      try
      {
         barcode = new Barcode(type) ;
      }
      catch (Exception e)
      {
         response.setContentType("text/plain") ;
         PrintWriter out = response.getWriter() ;
         out.println(e.getMessage()) ;
         e.printStackTrace(out) ;
         out.close() ;
         return ;
      }

      java.io.OutputStream os = null ;
      try
      {
         // display "Invalid Data" if invalid data:

         barcode.setInvalidDataAction(Barcode.IDA_DISPLAYINVALID) ;

         tmp = request.getParameter("fontname") ;
         if (tmp != null || fontsize != -1)
         {
            barcode.setSimpleFont(new SimpleFont(tmp != null ? tmp : "SansSerif", java.awt.Font.PLAIN, fontsize != -1 ? fontsize : 12)) ;
         }

         if (rotate != 0)
            barcode.setOrientation(rotate) ;

         tmp = request.getParameter("data") ;
         if (tmp != null)
            barcode.setData(tmp) ;

         tmp = request.getParameter("addondata") ;
         if (tmp != null)
            barcode.setAddOnData(tmp) ;

         tmp = request.getParameter("caption") ;
         if (tmp != null)
            barcode.setCaption(tmp) ;

         tmp = request.getParameter("addoncaption") ;
         if (tmp != null)
            barcode.setAddOnCaption(tmp) ;

         if (image == Barcode.BARCODE_JPEG)
            response.setContentType("image/jpeg") ;
         else
            response.setContentType("image/png") ;

         os = response.getOutputStream() ;
         switch (barcode.getOrientation())
         {
         case 1:
         case 3:
            barcode.makeSimpleImage(image, height, barwidth, true, os) ;
            break ;
         default:
            barcode.makeSimpleImage(image, barwidth, height, true, os) ;
         }
      }
      catch (Exception e)
      {
         throw new ServletException(e) ;
      }
      finally
      {
         if (os != null)
            os.close() ;
      }
   }
}
