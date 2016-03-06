package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Color;
import java.awt.Graphics;

public class RedSquare extends ShapeComponent {
	private static final long serialVersionUID = 1L;

	public RedSquare() {
		super.shapeType = 1;
	}

	@Override
	protected void paintComponent(Graphics g) {

		// paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(20, 20, 60, 60);
		super.repaint();
	}
}
//go to the labels..make it a sep class, it has an image, also returns a cursor, on click on label , return a cursor
//while clicked==true, only enable mouse within area
//we only need one button calss bec. all have the same code and we'll just pass in the filename of the icon
//use buttons and set icon, set cursor, set label type!!!!!!!!!