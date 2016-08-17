package servlets ;

import java.awt.Graphics2D ;
import java.awt.Image ;
import java.awt.RenderingHints ;
import java.awt.geom.AffineTransform ;
import java.awt.image.BufferedImage ;
import java.io.File ;
import java.io.FileInputStream ;
import java.io.FileOutputStream ;
import java.io.IOException ;
import java.io.InputStream ;
import java.io.OutputStream ;
import javax.servlet.ServletConfig ;
import javax.servlet.ServletException ;
import javax.servlet.http.HttpServlet ;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;
import javax.swing.ImageIcon ;
import database.Connection ;
import Comato.Photos ;
import com.intersys.objects.Id ;
import com.sun.image.codec.jpeg.JPEGCodec ;
import com.sun.image.codec.jpeg.JPEGImageEncoder ;

/**
 * This class makes a thumbnail of a picture and returns the outputstream.
 * It also makes the output stream of a normal pictures.
 * It first creates the file and then displays it.
 * @author ICT09
 *
 */
public class showImage extends HttpServlet
{

   private static final long serialVersionUID = 7880786601281894828L ;
   private static final String basic_path = "D:\\Sun\\AppServer\\domains\\domain1\\docroot\\thumbnail\\";

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
    * Create thumbnail if needed and normal photo and returns it as stream
    */
   public void doPost(HttpServletRequest request, HttpServletResponse response)
   {
      try
      {

         if (request.getParameter("type").equals("thumb"))
         {


            Photos pht = (Photos) Photos._open(con.getConnection(), new Id((String) request.getParameter("ID"))) ;

            response.setContentType(pht.getphtContentType()) ;
            // response.setContentType("image/jpeg") ;
            response.addHeader("content-disposition", "attachment; filename=thumb" + pht.getphtName()) ;

            if (!((new File(basic_path + pht.getphtName() + "-" + pht.getphtID() + ".jpg")).exists()))
            {
               File temp = new File(basic_path + pht.getphtName()) ;
               InputStream in ;

               in = pht.getphtPhotoIn() ;
               temp.createNewFile() ;
               temp.canWrite() ;

               FileOutputStream f_out = new FileOutputStream(temp) ;
               int bytes_read = 0 ;
               byte[] buf = new byte[1024] ;

               if (temp != null)
               {
                  while ((bytes_read = in.read(buf)) != -1)
                  {
                     f_out.write(buf, 0, bytes_read) ;
                  }
                  in.close() ;
               }
               f_out.flush() ;
               f_out.close() ;

               int max_dim = 210 ;
               ImageIcon icon = new ImageIcon( basic_path + pht.getphtName()) ;
               Image in_image = icon.getImage() ;
               double scale = (double) max_dim / (double) in_image.getHeight(null) ;

               if (in_image.getWidth(null) > in_image.getHeight(null))
               {
                  scale = (double) max_dim / (double) in_image.getWidth(null) ;
               }

               int scaledW = (int) (scale * in_image.getWidth(null)) ;
               int scaledH = (int) (scale * in_image.getHeight(null)) ;
               BufferedImage out_image = new BufferedImage(scaledW, scaledH, BufferedImage.TYPE_INT_RGB) ;
               AffineTransform tx = new AffineTransform() ;

               // If the image is smaller than the desired image size,
               // don't bother scaling.

               if (scale < 1.0d)
               {
                  tx.scale(scale, scale) ;
               }

               // Paint image.

               Graphics2D g2d = out_image.createGraphics() ;

               g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC) ;
               g2d.drawImage(in_image, tx, null) ;
               g2d.dispose() ;

               // JPEG-encode the image and write to file.

               OutputStream os = new FileOutputStream(basic_path + pht.getphtName() + "-" + pht.getphtID() + ".jpg") ;
               JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os) ;

               encoder.encode(out_image) ;
               os.close() ;
               os.flush() ;
               encoder = null ;
               temp.delete() ;
               tx = null ;
               out_image.flush() ;
               in_image.flush() ;
            }

            FileInputStream in = new FileInputStream(new File(basic_path + pht.getphtName() + "-" + pht.getphtID() + ".jpg")) ;
            
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
           
         }
         else
         {


            Photos pht = (Photos) Photos._open(con.getConnection(), new Id((String) request.getParameter("ID"))) ;

            // response.setContentType(pht.getphtContentType()) ;

            InputStream in ;

            in = pht.getphtPhotoIn() ;

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

            
         }
      }
      catch (Exception exception)
      {
         
      }
      con.closeResultset() ;

   }
}
