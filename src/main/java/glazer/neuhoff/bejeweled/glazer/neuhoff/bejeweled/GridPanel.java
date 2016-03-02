package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ShapeComponent[][] grid;
	// private JComponent shape;
	private Color navyBlue;
	private ShapeQueue queue;
	private int shapeNum;
	private Random random;
private boolean mouseClicked;
	public GridPanel() {
		setLayout(new GridLayout(8, 8));
		this.queue = new ShapeQueue();
		this.random = new Random();

		this.navyBlue = new Color(76, 0, 153);
		setBackground(navyBlue);
		// this.grid = new JLabel[8][8];
		this.grid = new ShapeComponent[8][8];
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
						System.out.println("clicked" + e.getX() + ", "
								+ e.getY());
					}

					public void mouseDragged(MouseEvent e) {
						// TODO Auto-generated method stub
						System.out.println("clicked" + e.getX() + ", "
								+ e.getY());
					}

					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						System.out.println("clicked" + e.getX() + ", "
								+ e.getY());
						System.out.println("clicked" + e.getXOnScreen() + ", "
								+ e.getXOnScreen());

						// System.out.println("clicked" + e.getComponent());

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
					}

					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						mouseClicked = false;
						System.out.println("released" + e.getComponent());
					}
				});
			}
		}
	}

	private ShapeComponent getNextShape() {
		// TODO Auto-generated method stub
		this.shapeNum = random.nextInt(2);
		switch (shapeNum) {
		case 0:
			return new WhiteCircle();
		case 1:
			return new RedSquare();
		}

		return null;
	}
}
