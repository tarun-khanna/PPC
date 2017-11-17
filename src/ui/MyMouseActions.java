package ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MyMouseActions extends MouseAdapter{

	AdminEdit adminEdit;
	MyMouseActions(Object obj )
	{
		adminEdit=(AdminEdit)obj;
		
	}
	

	@Override
	public void mouseEntered(MouseEvent e)
	{
		if (e.getSource() == adminEdit.update || e.getSource()== adminEdit.delete || e.getSource() == adminEdit.yes || e.getSource() == adminEdit.no || e.getSource() == adminEdit.saveUpdate)
		{
			((JButton)e.getSource()).setBackground(Color.GREEN);
		}
		
		
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		if (e.getSource() == adminEdit.update || e.getSource()== adminEdit.delete || e.getSource() == adminEdit.yes || e.getSource() == adminEdit.no || e.getSource() == adminEdit.saveUpdate)
		{
			((JButton)e.getSource()).setBackground(Color.CYAN);
		}
	}
	
}
