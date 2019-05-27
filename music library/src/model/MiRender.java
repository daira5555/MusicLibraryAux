package model;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MiRender extends DefaultTableCellRenderer
{
   public Component getTableCellRendererComponent(JTable table,
      Object value,
      boolean isSelected,
      boolean hasFocus,
      int row,
      int column)
   {
      super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
      if (Integer.parseInt((String) table.getValueAt(row, 5)) < 1)
      {
         this.setOpaque(true);
         this.setBackground(Color.RED);
 
      } else if(table.getValueAt(row, column).equals(1)){
    	  
      }  else {
    	  
      }
      
      

      return this;
   }
}
