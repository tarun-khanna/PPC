package ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

class MyRenderer implements TableCellRenderer {
	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer(); 
	
	
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	      boolean hasFocus, int row, int column) {
			
		  Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, column);

//		  if (row == ManagerRequestPage.approvedRowIndex || c.getBackground().getRGB() == Color.green.getRGB())  {
//			  c.setBackground(Color.GREEN);
//		  }
//		  else 
//			  c.setBackground(Color.WHITE);
		  if(row%2 == 0)
			  c.setBackground(Color.decode("#e5fffe"));
		  else 
			  c.setBackground(Color.decode("#b6eaea"));
		  return c;
	   
	  }
}
