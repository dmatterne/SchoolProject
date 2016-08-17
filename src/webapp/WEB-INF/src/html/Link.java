package html ;

/**
 * This class provides an object based approach when creating an html based
 * link. For more information view the various constructors in this class.
 * 
 * @author ICT09
 */
public class Link
{

   private String target ;

   private String url ;

   private String label ;

   private String alt ;

   public static final Type _TARGET_BLANK = new Type("_blank") ;


   /**
    * Creates a Html link object
    * 
    * @param url
    *           the url this link refers to
    * @param label
    *           the label of this link, this is the thing you click on when
    *           viewing a page
    * @param alt
    *           The alt text for this link, this is the tooltip you get when
    *           hovering over the link
    */
   public Link(String url, String label, String alt)
   {
      this.url = url ;
      this.label = label ;
      this.alt = alt ;

   }

   /**
    * Creates a Html link object
    * 
    * @param url
    *           the url this link refers to
    * @param label
    *           the label of this link, this is the thing you click on when
    *           viewing a page
    * @param alt
    *           The alt text for this link, this is the tooltip you get when
    *           hovering over the link
    * @param target
    *           the target window to open the url in. You can get valid targets
    *           using Link._TARGET_BLANK or other similar fields in this class.
    */
   public Link(String url, String label, String alt, Type target)
   {
      this.url = url ;
      this.label = label ;
      this.alt = alt ;
      this.target = target.getType() ;
   }

   /**
    * resets these values into the link object, use a comparable constructor instead, this
    * function is Deprecated.
    * 
    * @param url
    *           the url this link refers to
    * @param label
    *           the label of this link, this is the thing you click on when
    *           viewing a page
    * @param alt
    *           The alt text for this link, this is the tooltip you get when
    *           hovering over the link
    */
   @Deprecated
   public void setLink(String url, String label, String alt)
   {
      this.url = url ;
      this.label = label ;
      this.alt = alt ;

   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      String content = "\n<a href=\"" + url + "\" alt=\"" + alt + "\"" ;

      if (this.target != null)
         content += " target=\"" + target + "\"" ;

      content += ">" + label + "</a>\n" ;

      return content ;
   }

}
