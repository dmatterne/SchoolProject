/**
 * 
 */
package html ;


/**
 * The image class provides an object based interface to the hmtl image tag. It allows you to easily add a html image to your page.
 * 
 * @author ICT09
 */
public class Image
{

   public static final Type _ALIGN_CENTER = new Type(" align=\"center\" ") ;

   public static final Type _ALIGN_LEFT = new Type(" align=\"left\" ") ;

   public static final Type _ALIGN_RIGHT = new Type(" align=\"right\" ") ;


   private String url ;

   private String alt ;

   private String alignment ;

/**
 * Creates a new image object
 * @param url
 * The url this image can be found at
 * @param alt
 * The alternate text, in case the image is not found
 */
   public Image(String url, String alt)
   {
      this.alt = alt ;
      this.url = url ;
   }

/**
 * Generates a html String representation of the image.
 */
   @Override
   public String toString()
   {
      return "<img src=\"" + url + "\" alt=\"" + alt + "\"" + alignment + "/>" ;
   }

   /**
    * @param alignment
    *           The alignment to set.
    */
   public void setAlignment(Type alignment)
   {
      this.alignment = alignment.getType() ;
   }

   /**
    * @param alt
    *           The alt to set.
    */
   public void setAlt(String alt)
   {
      this.alt = alt ;
   }

   /**
    * @param url
    *           The url to set.
    */
   public void setUrl(String url)
   {
      this.url = url ;
   }


}
