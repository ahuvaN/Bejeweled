package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	static final int ROWS = 8, COLS = 8;
	private final Color backgroundColor = new Color(0, 0, 0, 150);
	private Random random;
	private ShapeLabel[][] grid;
	private ShapeLabel[] shapes;
	private Game game;

	public GridPanel(Game game) {
		setLayout(new GridLayout(ROWS, COLS));
		//setBackground(backgroundColor);
		setBackground(Color.BLUE);
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		this.random = new Random();
		this.game = game;
		this.grid = new ShapeLabel[ROWS][COLS];
		random = new Random();
		createSampleJewels();
		initializeGrid();
		
		
		// game.checkMatches();
	}

	private void createSampleJewels() {
		shapes = new ShapeLabel[] { new ShapeLabel("/purple.png", 0, -1, -1), new ShapeLabel("/blue.png", 1, -1, -1),
				new ShapeLabel("/green.png", 2, -1, -1), new ShapeLabel("/orange.png", 3, -1, -1),
				new ShapeLabel("/red.png", 4, -1, -1), new ShapeLabel("/white.png", 5, -1, -1),
				new ShapeLabel("/yellow.png", 6, -1, -1) };

	}

	private void initializeGrid() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				grid[row][col] = getNextShape(row, col);
				grid[row][col].addMouseListener(listener);
				add(grid[row][col]);

			}
		}
	}
	public void newGrid() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				ShapeLabel s = getNextShape(row, col);
				grid[row][col].setIconPic(s.getIconPic());
				grid[row][col].setId(s.getId());
			}
		}
		
	}
	public void swap(ShapeLabel label1, ShapeLabel label2) {
		ShapeLabel swapLabel = new ShapeLabel(label1.getIconPic(), label1.getId(), label1.getRow(), label1.getCol());
		label1.setIconPic(label2.getIconPic());
		label1.setId(label2.getId());
		label2.setIconPic(swapLabel.getIconPic());
		label2.setId(swapLabel.getId());
	}

	public void showBoard() {
		System.out.println();
		for (int i = 0; i < ROWS; i++) {
			for (int x = 0; x < COLS; x++) {
				System.out.print(grid[i][x].getId() + " ");
			}
			System.out.println();
		}
	}

	private ShapeLabel getNextShape(int row, int col) {
		int num = random.nextInt(7);
		switch (num) {
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
		return new ShapeLabel(shapes[num].getIconPic(), shapes[num].getId(), row, col);
	}

	public ShapeLabel getJewelAt(int row, int column) {
		return grid[row][column];
	}

	private void deletePiece(ShapeLabel piece) {
		int pRow = piece.getRow();
		int pCol = piece.getCol();
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

	public void deleteMatches(ArrayList<ArrayList<ShapeLabel>> deletions, BejeweledFrame frame) throws InterruptedException {
		// TODO Auto-generated method stub
		int size;
		for (ArrayList<ShapeLabel> match : deletions) {
			size=match.size();
			game.increaseScore(size * 10);
			if(size>3){
				frame.setCommentLabel();
			}
			for (ShapeLabel jewel : match) {
				deletePiece(jewel);
			}

		}
	}

	private ShapeLabel enteredLabel;
	private ShapeLabel pressedLabel;
	private boolean mouseClicked;
	MouseListener listener = new MouseListener() {

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e2) {
			if (mouseClicked) {
				enteredLabel = (ShapeLabel) e2.getComponent();
			}
		}

		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {
			mouseClicked = true;
			pressedLabel = (ShapeLabel) e.getComponent();
			enteredLabel = (ShapeLabel) e.getComponent();
			game.jewelClicked(pressedLabel);
		}

		public void mouseReleased(MouseEvent e) {
			mouseClicked = false;
			try {
				game.jewelReleased(pressedLabel, enteredLabel);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};

	
}
