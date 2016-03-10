package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GridPanel extends JPanel {

	/**
	 * http://stackoverflow.com/questions/4274606/how-to-change-cursor-icon-in-
	 * java
	 */
	private static final long serialVersionUID = 1L;

	static final int ROWS = 8, COLS = 8;
	private final Color backgroundColor = new Color(0, 0, 0, 150);
	private Random random;
	private ScorePanel scores;
	private ShapeLabel[][] grid;
	private ShapeLabel[] shapes;
	private ShapeLabel swapLabel, pressedLabel, enteredLabel;
	private int shapeNum, enteredRow, enteredCol, pressedRow, pressedCol;
	private boolean mouseClicked, checkAgain;

	public GridPanel(ScorePanel score) {
		setLayout(new GridLayout(ROWS, COLS));
		setBackground(backgroundColor);
		// setBackground(Color.BLUE);
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		this.random = new Random();
		this.grid = new ShapeLabel[ROWS][COLS];
		scores = score;
		random = new Random();
		shapes = new ShapeLabel[] { new ShapeLabel("/purple.png", 0, -1, -1),
				new ShapeLabel("/blue.png", 1, -1, -1),
				new ShapeLabel("/green.png", 2, -1, -1),
				new ShapeLabel("/orange.png", 3, -1, -1),
				new ShapeLabel("/red.png", 4, -1, -1),
				new ShapeLabel("/white.png", 5, -1, -1),
				new ShapeLabel("/yellow.png", 6, -1, -1) };

		mouseClicked = false;
		initializeGrid();

		mouseClicked = false;
		checkAgain = false;
		do {
			showBoard(); // display in console
			checkAgain = false;
			checkForMultiples();
		} while (checkAgain);
		showBoard(); // console

	}

	private void initializeGrid() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				grid[row][col] = getNextShape(row, col);
				add(grid[row][col]);
				grid[row][col].addMouseListener(listener);
			}
		}
	}

	private void swap() {
		swapLabel = new ShapeLabel(pressedLabel.getIconPic(),
				pressedLabel.getId(), pressedLabel.getRow(),
				pressedLabel.getCol());
		pressedLabel.setIconPic(enteredLabel.getIconPic());
		pressedLabel.setId(enteredLabel.getId());
		enteredLabel.setIconPic(swapLabel.getIconPic());
		enteredLabel.setId(swapLabel.getId());
	}

	private void showBoard() {
		System.out.println();
		for (int i = 0; i < ROWS; i++) {
			for (int x = 0; x < COLS; x++) {
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

	public ShapeLabel getJewelAt(int row, int column) {
		return grid[row][column];
	}

	public void checkForMultiples() {
		checkBoardVerticalMatches();
		checkBoardHorizontalMatches();
	}

	public void checkBoardVerticalMatches() {

		int count = 3;
		int next;
		boolean more = true;
		for (int m = ROWS - 1; m >= 2; m--) {
			for (int n = COLS - 1; n >= 0; n--) {
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
					checkAgain = true;
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
		for (int m = ROWS - 1; m >= 0; m--) {
			for (int n = COLS - 1; n >= 2; n--) {
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
					checkAgain = true;
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
		scores.setScore(10);
	}

	private void deleteNextVertical(ShapeLabel shapeLabel, int count) {
		int rowD = shapeLabel.getRow();
		int colD = shapeLabel.getCol();
		for (int i = rowD - count + 1; i <= rowD; i++) {
			System.out.println(i + " delete " + colD);
			deletePiece(grid[i][colD]);
		}
		scores.setScore(10);
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
				setBackground(backgroundColor);
				checkAgain = false;
				do {
					checkAgain = false;
					checkForMultiples();
				} while (checkAgain);
			}
		}
	};

	private boolean swapAllowed() {
		if (validSwapLocation() && willBeMatch()) {
			return true;
		}
		return false;
	}

	private boolean willBeMatch() {
		if (checkHorizontal(pressedRow, pressedCol, enteredLabel)
				|| checkHorizontal(enteredRow, enteredCol, pressedLabel)
				|| checkVertical(pressedRow, pressedCol, enteredLabel)
				|| checkVertical(enteredRow, enteredCol, pressedLabel)) {
			return true;
		}
		return false;
	}

	private boolean checkVertical(int row1, int col1, ShapeLabel piece2) {
		if (row1 + 2 < ROWS && piece2.getId() == grid[row1 + 1][col1].getId()
				&& (!piece2.equals(grid[row1 + 1][col1]))
				&& piece2.getId() == grid[row1 + 2][col1].getId()
				&& (!piece2.equals(grid[row1 + 2][col1]))) {
			System.out.println(piece2.getRow() + " " + piece2.getCol()
					+ "2 lower");
			return true;
		} else if ((row1 + 1 < ROWS && row1 - 1 >= 0)
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
		if (col1 + 2 < COLS && piece2.getId() == grid[row1][col1 + 1].getId()
				&& (!piece2.equals(grid[row1][col1 + 1]))
				&& piece2.getId() == grid[row1][col1 + 2].getId()
				&& (!piece2.equals(grid[row1][col1 + 2]))) {
			System.out.println(piece2.getRow() + " " + piece2.getCol()
					+ "2 right");
			return true;
		} else if ((col1 + 1 < COLS && col1 - 1 >= 0)
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
		if (((enteredRow == pressedRow + 1 || enteredRow == pressedRow - 1) && enteredCol == pressedCol)
				|| ((enteredCol == pressedCol + 1 || enteredCol == pressedCol - 1) && enteredRow == pressedRow)) {
			return true;
		}

		return false;

	}
}
