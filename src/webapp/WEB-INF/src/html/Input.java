package html ;

/**
 * This class defines an html input item. It can be used as any java object.
 * There are static final strings in this class that allow you to easily declare
 * wich type of input item you want. This removes the need to remember such
 * things and allows a more object based approach to creating web applications.
 * It removes the need for knowledge on the correct html of these input items.
 * You can use the Types in this class to declare the Type of Input field you need. This type must be passed to all constructors.
 * 
 * @author ICT09
 */
public class Input
{

   public static final Type _TYPE_TEXT = new Type("text") ;

   public static final Type _TYPE_PASSWORD = new Type("password") ;

   public static final Type _TYPE_HIDDEN = new Type("hidden") ;

   public static final Type _TYPE_CHECKBOX = new Type("checkbox") ;

   public static final Type _TYPE_SUBMIT = new Type("submit") ;

   public static final Type _TYPE_RADIO = new Type("radio") ;

   private String name ;

   private String value ;

   private String type ;

   private String checked ;

   private String label ;


   /**
    * Creates an html input item.
    * 
    * @param name
    *           the name this item has in a form or page, useful when submitting
    *           form data
    * @param value
    *           the value to fill this item with
    * @param type
    *           the type, you can get a type using Input._TYPE_TEXT or other
    *           similar fields.
    */
   public Input(String name, String value, Type type)
   {
      this.name = name ;
      this.value = value ;
      this.type = type.getType() ;
   }

   /**
    * Creates an html input item.
    * 
    * @param name
    *           the name this item has in a form or page, useful when submitting
    *           form data
    * @param type
    *           the type, you can get a type using Input._TYPE_TEXT or other
    *           similar fields.
    */
   public Input(String name, Type type)
   {
      this.name = name ;
      this.type = type.getType() ;
   }


   /**
    * Creates a checkbox type input item
    * 
    * @param name
    *           the name this item has in a form or page, useful when submitting
    *           form data
    * @param label
    *           The label to put behind this checkbox
    * @param value
    *           The value used when the checkbox is checked
    * @param checked
    *           If the value of the checkbox equals the value passed in this
    *           parameter, the checkbox is already checked when it is loaded
    * @param type
    *           the type, you can get a type using Input._TYPE_CHECKBOX, this
    *           constructor works best with the checkbox and radiobutton types
    */
   public Input(String name, String label, String value, String checked, Type type)
   {
      this.name = name ;
      this.label = label ;
      this.value = value ;
      this.checked = checked ;
      this.type = type.getType() ;
   }

   @Override
   public String toString()
   {
      String content = "" ;

      if ((type.equals(Input._TYPE_CHECKBOX.getType()) || type.equals(Input._TYPE_RADIO.getType())) && label != null)
         content += "<LABEL FOR=\"" + name + "\">" ;

      content += "<input type=\"" + type + "\" id=\"" + name + "\" name=\"" + name + "\"" ;

      if (value != null)
         content += "value=\"" + value + "\" " ;
      if (value != null && checked != null)
      {
         if (value.equals(checked))
            content += "checked" ;
      }

      content += "/>" ;

      if ((type.equals(Input._TYPE_CHECKBOX.getType()) || type.equals(Input._TYPE_RADIO.getType())) && label != null)
         content += label + "</label>" ;


      return content ;
   }


}
