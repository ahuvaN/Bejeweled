package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Game extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CheckMethods methods;
	private GridPanel grid;
	private ScorePanel scorePanel;
	private final Color backgroundColor = new Color(0, 0, 0, 150);


	public Game() {
		setBackground(backgroundColor);
		setLayout(new BorderLayout());
		newGame();
		add(scorePanel, BorderLayout.WEST);
		add(grid, BorderLayout.CENTER);

	}

	public void newGame() {
		grid = new GridPanel(this);
		methods = new CheckMethods(grid);
		scorePanel = new ScorePanel();
	}

	// change to animate the switch instead of cursor
	public void jewelClicked(ShapeLabel pressedLabel) {
		setCursor(pressedLabel.getCursor());
	}

	public void jewelReleased(ShapeLabel pressedLabel, ShapeLabel enteredLabel) {
		setCursor(Cursor.getDefaultCursor());
		if (!pressedLabel.equals(enteredLabel)) {
			tryJewelSwap(pressedLabel, enteredLabel);
			System.out.println("hereto");
		}
	}

	public void tryJewelSwap(ShapeLabel pressedLabel, ShapeLabel enteredLabel) {
		if (methods.isValidSwap(pressedLabel, enteredLabel)) {
			grid.swap(pressedLabel, enteredLabel);
			checkMatches();

		}
	}

	public void checkMatches() {
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
