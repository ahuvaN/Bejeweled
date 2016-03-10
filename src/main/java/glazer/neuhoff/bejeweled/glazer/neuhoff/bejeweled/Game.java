package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Cursor;
import java.util.ArrayList;
import javax.swing.JComponent;

public class Game extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CheckMethods methods;
	private GridPanel grid;
	private ScorePanel scorePanel;

	public Game() {
		this.addMouseListener(new MouseListener(this));
		newGame();
	}

	public void newGame() {
		this.grid = new GridPanel(this);
		this.methods = new CheckMethods(grid);
		this.scorePanel = new ScorePanel();
	}

	public void jewelClicked(ShapeLabel pressedLabel) {
		setGridCursor(pressedLabel.getCursor());
	}

	public void jewelReleased(ShapeLabel pressedLabel, ShapeLabel enteredLabel) {
		setGridCursor(Cursor.getDefaultCursor());
		if (!pressedLabel.equals(enteredLabel)) {
			tryJewelSwap(pressedLabel, enteredLabel);
		}
	}

	public void setGridCursor(Cursor cursor) {
		setCursor(cursor);
	}

	public void tryJewelSwap(ShapeLabel pressedLabel, ShapeLabel enteredLabel) {
		if (methods.isValidSwap(pressedLabel, enteredLabel)) {
			grid.swap(pressedLabel, enteredLabel);
			checkMatches();

		}
	}

	public void checkMatches() {
		// TODO Auto-generated method stub
		boolean checkAgain = false;
		try {
			do {
				ArrayList<ArrayList<ShapeLabel>> deletions = methods
						.checkBoard();

				if (!deletions.isEmpty()) {
					grid.deleteMatches(deletions);
					checkAgain = true;
				}
			} while (checkAgain);
		} catch (Exception ex) {
			System.out.println("Invalid or no matches to delete.");
		}
	}

	public void increaseScore(int amount) {
		scorePanel.setScore(amount);
	}
}
