import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

public class MyIcon implements Icon{

	private int width;
	private int height;
	
	public void paintIcon(Component c, Graphics g, int x, int y){
		doDrawing(g, x, y);
	}
	
	public void doDrawing(Graphics g, int x, int y){
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.drawOval(24, 0, 18, 18);
		g2d.setColor(Color.white);
		g2d.fillOval(24, 0, 18, 18);
	}

	@Override
	public int getIconHeight() {
		return width;
	}

	@Override
	public int getIconWidth() {
		return height;
	}
}
