package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;

import javax.swing.JPanel;

public class GridPanel extends JPanel {

	/**
	 * http://stackoverflow.com/questions/4274606/how-to-change-cursor-icon-in-
	 * java
	 */
	private static final long serialVersionUID = 1L;

	private ShapeLabel[][] grid;
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
		setBackground(new Color(0, 0, 0, 150));
		// setBackground(Color.BLUE);
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		this.random = new Random();
		this.grid = new ShapeLabel[rows][cols];
		shapes = new ShapeLabel[] { new ShapeLabel("/purple.png", 0, -1, -1),
				new ShapeLabel("/blue.png", 1, -1, -1),
				new ShapeLabel("/green.png", 2, -1, -1),
				new ShapeLabel("/orange.png", 3, -1, -1),
				new ShapeLabel("/red.png", 4, -1, -1),
				new ShapeLabel("/white.png", 5, -1, -1),
				new ShapeLabel("/yellow.png", 6, -1, -1) };
		mouseClicked = false;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				grid[row][col] = getNextShape(row, col);
				add(grid[row][col]);
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
			return getLabel(0, row, col);
		case 1:
			return getLabel(1, row, col);
		case 2:
			return getLabel(2, row, col);
		case 3:
			return getLabel(3, row, col);
		case 4:
			return getLabel(4, row, col);
		case 5:
			return getLabel(5, row, col);
		case 6:
			return getLabel(6, row, col);
		}
		return null;
	}

	private ShapeLabel getLabel(int num, int row, int col) {
		return new ShapeLabel(shapes[num].getIconPic(), shapes[num].getId(),
				row, col);
	}

	public void checkForMultiples() {
		checkBoardVerticalMatches();
		checkBoardHorizontalMatches();
	}

	private void checkBoardVerticalMatches() {
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

	private void checkBoardHorizontalMatches() {
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
			deletePiece(grid[rowD][i]);
		}
	}

	private void deleteNextVertical(ShapeLabel shapeLabel, int count) {
		int rowD = shapeLabel.getRow();
		int colD = shapeLabel.getCol();
		for (int i = rowD - count + 1; i <= rowD; i++) {
			System.out.println(i + " delete " + colD);
			deletePiece(grid[i][colD]);
		}
	}

	private void deletePiece(ShapeLabel piece) {
		int pRow = piece.getRow();
		int pCol = piece.getCol();
		System.out.println(pRow + " " + pCol);
		while (pRow > 0) {
			grid[pRow][pCol].setIconPic(grid[pRow - 1][pCol].getIconPic());
			grid[pRow][pCol].setId(grid[pRow - 1][pCol].getId());
			pRow--;
		}
		ShapeLabel s = getNextShape(pRow, pCol);
		// grid[0][pCol] = s;
		grid[0][pCol].setIconPic(s.getIconPic());
		grid[0][pCol].setId(s.getId());
	}

	MouseListener listener = new MouseListener() {

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e2) {
			if (mouseClicked) {
				enteredLabel = (ShapeLabel) e2.getComponent();
				enteredRow = enteredLabel.getRow();
				enteredCol = enteredLabel.getCol();
			}
		}

		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {
			mouseClicked = true;
			pressedLabel = (ShapeLabel) e.getComponent();
			pressedRow = pressedLabel.getRow();
			pressedCol = pressedLabel.getCol();
			setCursor(pressedLabel.getCursor());
		}

		public void mouseReleased(MouseEvent e) {
			mouseClicked = false;
			setCursor(Cursor.getDefaultCursor());
			if (!(enteredRow == pressedRow && enteredCol == pressedCol)
					&& swapAllowed()) {
				swap();
				checkAgain = false;
				do {
					checkAgain = false;
					checkForMultiples();
				} while (checkAgain);

			}
		}
	};

	private boolean swapAllowed() {
		// TODO Auto-generated method stub
		if (validSwapLocation() && willBeMatch()) {
			return true;
		}
		return false;
	}

	private boolean willBeMatch() {
		// TODO Auto-generated method stub
		if (checkHorizontal(this.pressedRow, this.pressedCol, this.enteredLabel)
				|| checkHorizontal(this.enteredRow, this.enteredCol,
						this.pressedLabel)
				|| checkVertical(this.pressedRow, this.pressedCol,
						this.enteredLabel)
				|| checkVertical(this.enteredRow, this.enteredCol,
						this.pressedLabel)) {
			return true;
		}
		return false;
	}

	private boolean checkVertical(int row1, int col1, ShapeLabel piece2) {
		// TODO Auto-generated method stub
		if (row1 + 2 < rows && piece2.getId() == grid[row1 + 1][col1].getId()
				&& (!piece2.equals(grid[row1 + 1][col1]))
				&& piece2.getId() == grid[row1 + 2][col1].getId()
				&& (!piece2.equals(grid[row1 + 2][col1]))) {
			System.out.println(piece2.getRow() + " " + piece2.getCol()
					+ "2 lower");
			return true;
		} else if ((row1 + 1 < rows && row1 - 1 >= 0)
				&& piece2.getId() == grid[row1 + 1][col1].getId()
				&& (!piece2.equals(grid[row1 + 1][col1]))
				&& piece2.getId() == grid[row1 - 1][col1].getId()
				&& (!piece2.equals(grid[row1 - 1][col1]))) {
			System.out.println(piece2.getRow() + " " + piece2.getCol()
					+ "1 upper, 1 lower");
			return true;
		} else if ((row1 - 2 >= 0)
				&& piece2.getId() == grid[row1 - 1][col1].getId()
				&& (!piece2.equals(grid[row1 - 1][col1]))
				&& piece2.getId() == grid[row1 - 2][col1].getId()
				&& (!piece2.equals(grid[row1 - 2][col1]))) {
			System.out.println(piece2.getRow() + " " + piece2.getCol()
					+ "2 upper");
			return true;
		}
		return false;
	}

	private boolean checkHorizontal(int row1, int col1, ShapeLabel piece2) {
		if (col1 + 2 < cols && piece2.getId() == grid[row1][col1 + 1].getId()
				&& (!piece2.equals(grid[row1][col1 + 1]))
				&& piece2.getId() == grid[row1][col1 + 2].getId()
				&& (!piece2.equals(grid[row1][col1 + 2]))) {
			System.out.println(piece2.getRow() + " " + piece2.getCol()
					+ "2 right");
			return true;
		} else if ((col1 + 1 < cols && col1 - 1 >= 0)
				&& piece2.getId() == grid[row1][col1 + 1].getId()
				&& (!piece2.equals(grid[row1][col1 + 1]))
				&& piece2.getId() == grid[row1][col1 - 1].getId()
				&& (!piece2.equals(grid[row1][col1 - 1]))) {
			System.out.println(piece2.getRow() + " " + piece2.getCol()
					+ "1 left, 1 right");
			return true;
		} else if ((col1 - 2 >= 0)
				&& piece2.getId() == grid[row1][col1 - 1].getId()
				&& (!piece2.equals(grid[row1][col1 - 1]))
				&& piece2.getId() == grid[row1][col1 - 2].getId()
				&& (!piece2.equals(grid[row1][col1 - 2]))) {
			System.out.println(piece2.getRow() + " " + piece2.getCol()
					+ "2 left");
			return true;
		}
		return false;
	}

	private boolean validSwapLocation() {
		if (((this.enteredRow == pressedRow + 1 || this.enteredRow == pressedRow - 1) && this.enteredCol == pressedCol)
				|| ((this.enteredCol == pressedCol + 1 || this.enteredCol == this.pressedCol - 1) && this.enteredRow == this.pressedRow)) {
			return true;
		}

		return false;

	}
}
