package html ;

/**
 * This form class can help you build a standard html form. You can directly add all type of objects in it.
 * But take care that they are extracted later with the toString function, so make sure your objects toString function
 * works well.
 * @author dimitri
 *
 */
public class Form
{

   private String content = "" ;

   public static final Type _METHOD_POST = new Type("post") ;

   public static final Type _METHOD_GET = new Type("get") ;

   public static final Type _METHOD_LINK = new Type("link") ;


   public void add(Object item)
   {
      this.content += item.toString() ;
   }

   public String get()
   {
      return content + "</form>" ;
   }


   @Override
   public String toString()
   {

      return content + "</form>" ;
   }

   public Form(String action, Type method, String name)
   {
      content += "\n<form method=\"" + method.getType() + "\" action=\"" + action + "\" name=\"" + name + "\">" ;
   }

   public Form(String action, Type method)
   {
      content += "\n<form method=\"" + method.getType() + "\" action=\"" + action + "\">" ;

   }

}
