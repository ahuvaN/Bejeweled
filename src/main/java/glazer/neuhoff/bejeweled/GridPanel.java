package glazer.neuhoff.bejeweled;

import glazer.neuhoff.bejeweled.ShapeLabel.ShapeColor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	static final int ROWS = 8, COLS = 8;
	private Random random;
	private ShapeLabel[][] grid;
	private ShapeLabel[] shapes;
	private Game game;

	public GridPanel(Game bGame) throws InterruptedException {
		setLayout(new GridLayout(ROWS, COLS));
		setBackground(new Color(0,0,0,115));
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		random = new Random();
		game = bGame;
		grid = new ShapeLabel[ROWS][COLS];
		random = new Random();
		createSampleJewels();
		initializeGrid();
		game.frame.repaint();
	}

	private void createSampleJewels() {
		shapes = new ShapeLabel[] { 
				new ShapeLabel("/purple.png", ShapeLabel.ShapeColor.PURPLE, -1, -1),
				new ShapeLabel("/blue.png", ShapeLabel.ShapeColor.BLUE, -1, -1),
				new ShapeLabel("/green.png", ShapeLabel.ShapeColor.GREEN, -1, -1),
				new ShapeLabel("/orange.png", ShapeLabel.ShapeColor.ORANGE, -1, -1),
				new ShapeLabel("/red.png", ShapeLabel.ShapeColor.RED, -1, -1),
				new ShapeLabel("/white.png", ShapeLabel.ShapeColor.WHITE, -1, -1),
				new ShapeLabel("/yellow.png", ShapeLabel.ShapeColor.YELLOW, -1, -1) };

	}

	private void initializeGrid() throws InterruptedException {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				grid[row][col] = getNextShape(row, col);
				grid[row][col].addMouseListener(listener);
				add(grid[row][col]);
			}
		}
		initialCheckMethods();
	}

	private void initialCheckMethods() throws InterruptedException {
		boolean checkAgain = false;
		do {
			checkAgain = false;
			ArrayList<ArrayList<ShapeLabel>> deletions = new ArrayList<ArrayList<ShapeLabel>>();
			CheckMethods initialCheck = new CheckMethods(this);
			deletions = initialCheck.checkBoard();
			if (deletions != null) {
				initialDeleteMatches(deletions);
				checkAgain = true;
			}
		} while (checkAgain);
	}

	public void initialDeleteMatches(ArrayList<ArrayList<ShapeLabel>> deletions)
			throws InterruptedException {
		for (ArrayList<ShapeLabel> match : deletions) {
			for (ShapeLabel jewel : match) {
				deletePiece(jewel);
			}
		}
	}

	public void newGrid() throws InterruptedException {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				ShapeLabel s = getNextShape(row, col);
				grid[row][col].setIconPic(s.getIconPic());
				grid[row][col].setShapeColor(s.getShapeColor());
			}
		}
		initialCheckMethods();
	}

	public void swap(ShapeLabel label1, ShapeLabel label2) {
		ShapeLabel swapLabel = new ShapeLabel(label1.getIconPic(),
				label1.getShapeColor(), label1.getRow(), label1.getCol());
		label1.setIconPic(label2.getIconPic());
		label1.setShapeColor(label2.getShapeColor());
		label2.setIconPic(swapLabel.getIconPic());
		label2.setShapeColor(swapLabel.getShapeColor());
		game.frame.repaint();
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
		return new ShapeLabel(shapes[num].getIconPic(), shapes[num].getShapeColor(),
				row, col);
	}

	public ShapeLabel getJewelAt(int row, int column) {
		return grid[row][column];
	}

	private void deletePiece(ShapeLabel piece) throws InterruptedException {
		int pRow = piece.getRow();
		int pCol = piece.getCol();

		while (pRow > 0) {
			grid[pRow][pCol].setIconPic(grid[pRow - 1][pCol].getIconPic());
			grid[pRow][pCol].setShapeColor(grid[pRow - 1][pCol].getShapeColor());
			pRow--;
		}
		ShapeLabel s = getNextShape(pRow, pCol);
		grid[0][pCol].setIconPic(s.getIconPic());
		grid[0][pCol].setShapeColor(s.getShapeColor());
	}

	public void deleteMatches(ArrayList<ArrayList<ShapeLabel>> deletions,
			BejeweledFrame frame) throws InterruptedException {
		int size;
		int count = 1;
		boolean bonus;
		for (ArrayList<ShapeLabel> match : deletions) {
			size = match.size();
			frame.repaint();
			if (size == 3) {
				game.increaseScore(count * 20);
			} else {// (size > 3)
				frame.setCommentLabel();
				game.increaseScore(count * (size * 10));
			}
			for (ShapeLabel jewel : match) {
				deletePiece(jewel);
				frame.repaint();
			}
			Thread.sleep(300);
			count++;
		}
		frame.repaint();
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
			JewelReleasedThread thread = new JewelReleasedThread(game,
					pressedLabel, enteredLabel);
			thread.start();
		}
	};

	public void deleteBonus() throws InterruptedException {
		Random rand = new Random();
		int row, col;
		for (int i = 0; i < 15; i++) {
			row = rand.nextInt(8);
			col = rand.nextInt(8);
			deletePiece(grid[row][col]);
			game.increaseScore(15);
			Thread.sleep(300);
			game.frame.repaint();
		}
	}

}
