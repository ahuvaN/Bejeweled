package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Graphics;

import javax.swing.JComponent;

public abstract class ShapeComponent extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected abstract void paintComponent(Graphics g);

	protected int shapeType;

	public int getShapeType() {
		return shapeType;
	}
}
