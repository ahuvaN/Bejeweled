package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Color;
import java.awt.Graphics;

public class WhiteCircle extends ShapeComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public WhiteCircle(){
		super.shapeType=0;
	}
	
	protected void paintComponent(Graphics g) {
		//paintComponent(g);

		g.setColor(Color.WHITE);
		g.fillOval(20, 20, 60, 60);
		super.repaint();
	}
}
