package html ;

import java.util.Vector ;

/**
 * This class allows us to acces an html table dynamicly. When you have finished
 * building the table using the methods in this class you can get its contents
 * as string using the toString function.
 * The basic approach is to declare a new table object with a given number of columns
 * and then start using the addItem function to add cells to the table.
 * You can then choose to force the move to the next row or not, perhaps if you want a couple of empty rows
 * for design purposes.
 * Then as final you would do a last nextRow, this is required, otherwise the contents of the last row you added are lost.
 * You can then pass the table into a String using:
 * String s1 = "";
 * tablename.nextRow()
 * s1 += tablename
 * 
 * 
 * @author Ict09
 */
public class Table
{

   public final static Type _ALIGN_CENTER = new Type(" align=\"center\" ") ;

   public final static Type _ALIGN_LEFT = new Type(" align=\"left\" ") ;

   public final static Type _ALIGN_RIGHT = new Type(" align=\"right\" ") ;

   private Vector<String[]> table ;

   private String[] row ;

   private String[] th ;

   private String[] cell_width ;

   private boolean has_header = false ;

   private boolean line_alternating = false ;

   private int col_id = -1 ;

   private int h_id = -1 ;

   private int cols ;

   private int current_rows = 0 ;

   private String width = "" ;


   private Type alignment ;


   /**
    * @param width
    *           The width to set.
    */
   public void setWidth(String width)
   {
      this.width = width = " width=\"" + width + "\" " ;
      ;
   }

   /**
    * Creates a new table
    * 
    * @param cols
    *           The number of columns wide this table is supposed to be. This
    *           can only be set trough this constructor.
    */
   public Table(int cols)
   {
      this.cols = cols ;
      row = new String[cols] ;
      th = new String[cols] ;
      for (int i = 0; i < cols; i++)
      {
         row[i] = "&nbsp;" ;
      }

      table = new Vector<String[]>() ;
   }

   public void addRedStarToLast()
   {
      row[col_id] += "<span class=\"errorMessage\"> * </span>" ;
   }


   public void addRequiredPartOne()
   {
      row[col_id] += "<span class=\"errorMessage\"> The fields marked with</span>" ;
   }

   public void addRequiredPartTwo()
   {
      row[col_id] += "<span class=\"errorMessage\">a red star are required fields</span>" ;
   }


   /**
    * Creates a new row in this table and moves the cursor towards this row. At
    * the end of the table it is necesary to use this function, otherwise the
    * last row inside the table isn't shown. At other times you can use this if
    * you want some empty rows for spacing.
    */
   public void nextRow()
   {
      current_rows++ ;
      col_id = -1 ;
      table.add(row) ;
      row = new String[cols] ;
      for (int i = 0; i < cols; i++)
      {
         row[i] = "&nbsp;" ;
      }
   }

   /**
    * Adds a new item to the table. It also moves the column cursor to this
    * item. If there are no new columns in the current row, we move to the next
    * row.
    * 
    * @param content
    *           The item to put inside this table.
    */
   public void addItem(Object content)
   {
      col_id += 1 ;
      if (col_id >= cols)
      {
         nextRow() ;
         col_id += 1 ;
      }
      row[col_id] = content.toString() ;

   }

   /**
    * In the table there is one row for headers. With this function you can
    * create these headers. If there are any headers set, they are automatically
    * added to the output of this table.
    * 
    * @param content
    */
   public void addThItem(Object content)
   {
      has_header = true ;
      h_id += 1 ;
      th[h_id] = content.toString() ;
   }

   /**
    * Sets an item at the given position in this table. It does not alter the
    * current row or column cursors in any way. Please be aware that the current
    * content is overwritten when using this function on an existing cell in the
    * table.
    * 
    * @param content
    *           the content to set inside the cell.
    * @param row
    *           The row to move to. Starting at 0
    * @param column
    *           the column to move to. Starting at 0
    */
   public void setItem(Object content, int row, int column)
   {
      String[] temprow = table.get(row) ;
      temprow[column] = content.toString() ;
      table.set(row, temprow) ;
   }

   /**
    * Add an item to the given position in this table. It does not alter the
    * current row or column cursors in any way.
    * 
    * @param content
    *           the content to set inside the cell.
    * @param row
    *           The row to move to. Starting at 0
    * @param column
    *           the column to move to. Starting at 0
    */
   public void addToItem(Object content, int row, int column)
   {
      String[] temprow = table.get(row) ;

      temprow[column] += content.toString() ;

      table.set(row, temprow) ;
   }

   /**
    * Adds an extra string to the current open cell. This string is always added
    * to the item you added with a previous addItem.
    * 
    * @param content
    *           The content to add to the cell.
    */
   public void addToLastItem(Object content)
   {
      row[col_id] += content.toString() ;
   }

   /**
    * The line alternating mode provides alternating colors in table rows. This
    * is helpful when constructing tables that present data. The default is
    * false, so if you do not need this functionality it is not necesary to use
    * this method.
    * 
    * @param alternating
    *           true if it should draw alternating colors, false if not.
    */
   public void setMode(boolean alternating)
   {
      this.line_alternating = alternating ;
   }

   /**
    * This method turns the entire table into a html formatted table. it returns
    * this table as a string. This function overides the default
    * Object.toString() function, making it possible to type lines like. String
    * s1 = "hello" + new Table(3);
    */
   @Override
   public String toString()
   {
      String html_table ;


      if (line_alternating && width.equals(""))
         width = " width=\"100%\" " ;

      html_table = "\n<table cellpadding=\"3\"" + width ;
      if (alignment != null)
         html_table += alignment.getType() ;

      html_table += ">" ;


      if (has_header)
      {
         html_table += "\n\t<tr>" ;
         for (int y = 0; y < cols; y++)
         {
            html_table += "\n\t\t<th>\n\t\t\t" + th[y] + "\n\t\t</th>" ;
         }

         html_table += "\n\t</tr>" ;
      }
      int x = 1 ;
      for (String[] temp_row : table)
      {
         x++ ;
         if ((x % 2 == 0) && (line_alternating))
         {
            html_table += "\n\t<tr class=\"row1\">" ;
         }
         else if (line_alternating)
         {
            html_table += "\n\t<tr class=\"row2\">" ;
         }
         else
         {
            html_table += "\n\t<tr>" ;
         }
         int y = 0 ;
         for (String cell : temp_row)
         {
            if (x == 2 && cell_width != null)
            {
               html_table += "\n\t\t<td" + cell_width[y++] + ">\n\t\t\t" + cell + "\n\t\t</td>" ;

            }
            else
            {

               html_table += "\n\t\t<td>\n\t\t\t" + cell + "\n\t\t</td>" ;
            }
         }
         html_table += "\n\t</tr>" ;

      }
      html_table += "\n</table>" ;


      return html_table ;
   }


   /**
    * @return Returns the currentrows.
    */
   public int getCurrent_rows()
   {
      return current_rows ;
   }


   /**
    * @param cell_width
    *           The cellwidth to set.
    */
   public void setCellWidth(String width, int pos)
   {
      if (this.cell_width == null)
      {
         this.cell_width = new String[cols] ;

         for (int i = 0; i < cols; i++)
         {
            cell_width[i] = "" ;
         }
      }

      this.cell_width[pos] = " Width=\"" + width + "\" " ;
   }

   /**
    * Set the type of alignment
    * @param alignment
    * the type of alignment to set, you can get an alignment inside this class like Table._TYPE_CENTER
    */
   public void setAlignment(Type alignment)
   {
      this.alignment = alignment ;
   }


}