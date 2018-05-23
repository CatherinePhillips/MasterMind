import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;

public class MyComboBox extends JComboBox {
	
	MyComboBox()
	{
		super();
		
		ImageIcon[] items =
	        {
	            new ImageIcon("NOCOLOR.gif"), // Fully transparent GIF
	            new ImageIcon("RED.gif"),
	            new ImageIcon("GREEN.gif"),
	            new ImageIcon("BLUE.gif"),
	            new ImageIcon("YELLOW.gif"),
	            new ImageIcon("BLACK.gif"),
	            new ImageIcon("WHITE.gif")
	        };	
		setModel(new DefaultComboBoxModel(items));

//		setModel(new DefaultComboBoxModel(new String[] {"", "Red", "Green", "Blue", "Yellow", "Black","White"}));

		// Below seems to have no effect!
		setMaximumSize(new Dimension(20,20));
		setPreferredSize(new Dimension(20,20));

		// Avoids border around box
		setUI(new BasicComboBoxUI());

		// Remove arrow button at right of JComboBox
		for (Component component : getComponents())
		{
			if (component instanceof JButton) {
				remove(component);
			}	
		}
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		// Fill the background color.  This overwrites the "selected" rectangle.
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// If not selected, draw circle, then fill middle disk with
		// BLACK (if enabled) and GRAY (if not enabled)
		if (this.getSelectedIndex()==0)
		{
			g.setColor(Color.BLACK);
			g.drawOval(0, 0, 70, 70);
			if (this.isEnabled())
				g.setColor(Color.BLACK);
			else  // If disabled, show center dot as light gray
				g.setColor(Color.GRAY);
			g.fillOval(19, 19, 32, 32);
		}
		else {  // A color has been selected.  Paint large disk with that color.

			switch (this.getSelectedIndex())
			{
			case 0:  break;  // 0 should appear empty, handled above
			case 1: g.setColor(Color.RED); break;
			case 2: g.setColor(Color.GREEN); break;
			case 3: g.setColor(Color.BLUE); break;
			case 4: g.setColor(Color.YELLOW); break;
			case 5: g.setColor(Color.BLACK); break;
			case 6: g.setColor(Color.WHITE); break;
			}
			g.fillOval(0,0,70,70);
		}
	}
}
