package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;

public class GridPanel extends JPanel {

	/**
	 * http://stackoverflow.com/questions/4274606/how-to-change-cursor-icon-in-
	 * java
	 */
	private static final long serialVersionUID = 1L;

	// private ShapeComponent[][] grid;
	private ShapeLabel[][] grid;
	// private JComponent shape;
	private Color navyBlue;
	// queue may be unnecessary
	private ShapeQueue queue;
	private int shapeNum;
	private Random random;
	private boolean mouseClicked;
	private ShapeLabel[] shapes;

	public GridPanel() {
		setLayout(new GridLayout(8, 8));
		this.queue = new ShapeQueue();
		this.random = new Random();

		this.navyBlue = new Color(76, 0, 153);
		setBackground(navyBlue);
		// this.grid = new JLabel[8][8];
		// this.grid = new ShapeComponent[8][8];
		this.grid = new ShapeLabel[8][8];
		shapes = new ShapeLabel[] { new ShapeLabel("/purpleTriangle.jpg", 0),
				new ShapeLabel("/blueDropper.png", 1),
				new ShapeLabel("/greenOctagon.jpg", 2),
				new ShapeLabel("/orangeHexagon.jpg", 3),
				new ShapeLabel("/redSquare.jpg", 4),
				new ShapeLabel("/whiteCircle.jpg", 5),
				new ShapeLabel("/yellowTriangle.png", 6) };
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				// grid[row][col] = new JLabel(row + ", " + col);
				grid[row][col] = getNextShape();
				grid[row][col].setForeground(Color.WHITE);
				add(grid[row][col]);
				mouseClicked = false;
				grid[row][col].addMouseListener(new MouseListener() {

					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub
					}

					public void mouseDragged(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
					}

					public void mouseEntered(MouseEvent e2) {
						// TODO Auto-generated method stub
						if (mouseClicked) {
							System.out.println("moved!!!!" + e2.getComponent());
						}
					}

					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						System.out.println("pressed" + e.getComponent());
						mouseClicked = true;
						ShapeLabel e4 = (ShapeLabel) e.getComponent();
						setCursor(e4.getCursor());
					}

					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						mouseClicked = false;
						System.out.println("released" + e.getComponent());
						ShapeLabel e4 = (ShapeLabel) e.getComponent();
						e4.getId();
						setCursor(Cursor.getDefaultCursor());
					}
				});
			}
		}
	}

	private ShapeLabel getNextShape() {
		// TODO Auto-generated method stub
		this.shapeNum = random.nextInt(7);
		switch (shapeNum) {
		case 0:
			return new ShapeLabel(shapes[0].getIcon(), shapes[0].getId());
		case 1:
			return new ShapeLabel(shapes[1].getIcon(), shapes[1].getId());
		case 2:
			return new ShapeLabel(shapes[2].getIcon(), shapes[2].getId());
		case 3:
			return new ShapeLabel(shapes[3].getIcon(), shapes[3].getId());
		case 4:
			return new ShapeLabel(shapes[4].getIcon(), shapes[4].getId());
		case 5:
			return new ShapeLabel(shapes[5].getIcon(), shapes[5].getId());
		case 6:
			return new ShapeLabel(shapes[6].getIcon(), shapes[6].getId());
		}
		return null;
	}
}
