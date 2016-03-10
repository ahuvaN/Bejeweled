package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.BorderLayout;
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
		setLayout(new BorderLayout());
		newGame();
		add(scorePanel, BorderLayout.WEST);
		add(grid, BorderLayout.CENTER);

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
			System.out.println("hereto");
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
		do {
			checkAgain = false;
			ArrayList<ArrayList<ShapeLabel>> deletions = new ArrayList<ArrayList<ShapeLabel>>();
			deletions = methods.checkBoard();
			if (deletions != null) {
				grid.deleteMatches(deletions);
				System.out.println("delete");

				checkAgain = true;
			}
		} while (checkAgain);

	}

	public void increaseScore(int amount) {
		scorePanel.setScore(amount);
	}
}
