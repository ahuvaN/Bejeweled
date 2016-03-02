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
