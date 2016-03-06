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
	private int shapeNum;
	private Random random;
	private boolean mouseClicked;
	private ShapeLabel[] shapes;
	private final int rows = 8;
	private final int cols = 8;
	private boolean checkAgain;
	private int enteredRow;
	private int enteredCol;
	private ShapeLabel swapLabel;
	private int pressedRow;
	private int pressedCol;
	private ShapeLabel pressedLabel;
	private ShapeLabel enteredLabel;

	public GridPanel() {
		setLayout(new GridLayout(rows, cols));
		this.random = new Random();
		this.navyBlue = new Color(76, 0, 153);
		setBackground(navyBlue);
		// this.grid = new JLabel[8][8];
		// this.grid = new ShapeComponent[8][8];
		this.grid = new ShapeLabel[rows][cols];
		shapes = new ShapeLabel[] {
				new ShapeLabel("/purple.png", 0, -1, -1),
				new ShapeLabel("/blue.png", 1, -1, -1),
				new ShapeLabel("/green.png", 2, -1, -1),
				new ShapeLabel("/orange.png", 3, -1, -1),
				new ShapeLabel("/red.png", 4, -1, -1),
				new ShapeLabel("/white.png", 5, -1, -1),
				new ShapeLabel("/yellow.png", 6, -1, -1) };
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				// grid[row][col] = new JLabel(row + ", " + col);
				grid[row][col] = getNextShape(row, col);
				add(grid[row][col]);
				mouseClicked = false;
				grid[row][col].addMouseListener(listener);
			}
		}

		this.checkAgain = false;
		do {
			showBoard();
			this.checkAgain = false;
			checkForMultiples();
		} while (checkAgain);
		showBoard();

	}

	private void swap() {
		this.swapLabel = new ShapeLabel(pressedLabel.getIconPic(),
				pressedLabel.getId(), pressedLabel.getRow(),
				pressedLabel.getCol());
		this.pressedLabel.setIconPic(enteredLabel.getIconPic());
		this.pressedLabel.setId(enteredLabel.getId());
		this.enteredLabel.setIconPic(this.swapLabel.getIconPic());
		this.enteredLabel.setId(this.swapLabel.getId());
	}

	private void showBoard() {
		System.out.println();
		for (int i = 0; i < rows; i++) {
			for (int x = 0; x < cols; x++) {
				System.out.print(grid[i][x].getId() + " ");
			}
			System.out.println();
		}

	}

	private ShapeLabel getNextShape(int row, int col) {
		this.shapeNum = random.nextInt(7);
		switch (shapeNum) {
		case 0:
			return new ShapeLabel(shapes[0].getIconPic(), shapes[0].getId(),
					row, col);
		case 1:
			return new ShapeLabel(shapes[1].getIconPic(), shapes[1].getId(),
					row, col);
		case 2:
			return new ShapeLabel(shapes[2].getIconPic(), shapes[2].getId(),
					row, col);
		case 3:
			return new ShapeLabel(shapes[3].getIconPic(), shapes[3].getId(),
					row, col);
		case 4:
			return new ShapeLabel(shapes[4].getIconPic(), shapes[4].getId(),
					row, col);
		case 5:
			return new ShapeLabel(shapes[5].getIconPic(), shapes[5].getId(),
					row, col);
		case 6:
			return new ShapeLabel(shapes[6].getIconPic(), shapes[6].getId(),
					row, col);
		}
		return null;
	}

	public void checkForMultiples() {
		checkHorizontalMatches();
		checkVerticalMatches();

	}

	private void checkVerticalMatches() {
		// check vertical rows
		int count = 3;
		int next;
		boolean more = true;
		for (int m = rows - 1; m >= 2; m--) {
			for (int n = cols - 1; n >= 0; n--) {
				if (grid[m][n].getId() == grid[m - 1][n].getId()
						&& grid[m][n].getId() == grid[m - 2][n].getId()) {
					next = m - 3;
					while (next >= 0 && more) {
						if (grid[m][n].getId() == grid[next][n].getId()) {
							count++;
							more = true;
							next--;
						} else {
							more = false;
						}
					}
					this.checkAgain = true;
					System.out.println("true");
					deleteNextVertical(grid[m][n], count);
				}
			}
		}
	}

	private void checkHorizontalMatches() {
		// check horizontal rows
		int count = 3;
		boolean more = true;
		int next;
		for (int m = rows - 1; m >= 0; m--) {
			for (int n = cols - 1; n >= 2; n--) {
				if (grid[m][n].getId() == grid[m][n - 1].getId()
						&& grid[m][n].getId() == grid[m][n - 2].getId()) {
					next = n - 3;
					while (next >= 0 && more) {
						if (grid[m][n].getId() == grid[m][next].getId()) {
							more = true;
							count++;
							next--;
						} else {
							more = false;
						}
					}
					this.checkAgain = true;
					System.out.println("true");
					deleteNextHorizontal(grid[m][n], count);
					break;
				}
			}
		}
	}

	private void deleteNextHorizontal(ShapeLabel shapeLabel, int count) {

		int rowD = shapeLabel.getRow();
		int colD = shapeLabel.getCol();
		for (int i = colD; i > colD - count; i--) {
			deletePeice(grid[rowD][i]);
		}
	}

	private void deleteNextVertical(ShapeLabel shapeLabel, int count) {
		int rowD = shapeLabel.getRow();
		int colD = shapeLabel.getCol();
		for (int i = rowD - count + 1; i <= rowD; i++) {
			System.out.println(i + " delete " + colD);
			deletePeice(grid[i][colD]);
		}
	}

	private void deletePeice(ShapeLabel peice) {
		int pRow = peice.getRow();
		int pCol = peice.getCol();
		System.out.println(pRow + " " + pCol);
		while (pRow > 0) {
			grid[pRow][pCol].setIconPic(grid[pRow - 1][pCol].getIconPic());
			grid[pRow][pCol].setId(grid[pRow - 1][pCol].getId());
			// grid[pRow][pCol]=grid[pRow-1][pCol];
			grid[pRow][pCol].setBackground(Color.BLACK);
			grid[pRow][pCol].setOpaque(true);
			pRow--;
		}
		ShapeLabel s = getNextShape(pRow, pCol);
		grid[0][pCol] = s;
		grid[0][pCol].setBackground(Color.BLACK);
		grid[0][pCol].setOpaque(true);
		// grid[pRow][pCol]=getNextShape(pRow, pCol);

	}

	MouseListener listener = new MouseListener() {

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e2) {
			if (mouseClicked) {
				System.out.println("entered!!!!" + e2.getComponent());
				enteredLabel = (ShapeLabel) e2.getComponent();

				enteredRow = enteredLabel.getRow();
				enteredCol = enteredLabel.getCol();
				System.out.println("entered  " + enteredRow + " " + enteredCol);
			}
		}

		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {
			System.out.println("pressed" + e.getComponent());
			mouseClicked = true;
			pressedLabel = (ShapeLabel) e.getComponent();
			System.out.println("pressed  " + pressedLabel.getRow() + " "
					+ pressedLabel.getCol());
			setCursor(pressedLabel.getCursor());
		}

		public void mouseReleased(MouseEvent e) {
			mouseClicked = false;
			// System.out.println("released" + e.getComponent());

			ShapeLabel e4 = (ShapeLabel) e.getComponent();
			e4.getId();
			pressedRow = e4.getRow();
			pressedCol = e4.getCol();
			System.out.println("released  " + pressedRow + " " + pressedCol);
			setCursor(Cursor.getDefaultCursor());
			if (!(enteredRow == pressedRow && enteredCol == pressedCol)) {
				swap();
			}
		}

	};
}
