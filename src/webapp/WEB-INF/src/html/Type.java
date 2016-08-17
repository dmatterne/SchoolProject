/**
 * 
 */
package html ;


/**
 * A class that provides the types for other classes in the html packages. since
 * its members are protected you are unable to declare a new object in other
 * classes. This way you are forced to use the statics from the class you are
 * using.
 * 
 * @author Ict9
 */
public class Type
{

   private String type ;

   /**
    * The type to set, this can be any string you could use later on in another class
    */
   protected Type(String type)
   {
      this.type = type ;
   }

  /**
   * This returns the string
   */ 
   protected String getType()
   {
      return type ;
   }

}
