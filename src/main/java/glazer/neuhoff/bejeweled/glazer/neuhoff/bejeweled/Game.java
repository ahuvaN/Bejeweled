package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;

public class Game extends JComponent{

	private static final long serialVersionUID = 1L;
	private CheckMethods methods;
	private GridPanel grid;
	private ScorePanel scorePanel;
	private BejeweledFrame frame;
	private int highScore;

	public Game(BejeweledFrame frame, JButton newGame)
			throws ClassNotFoundException, IOException, InterruptedException {
		this.highScore = 500;
		setLayout(new BorderLayout());
		this.frame = frame;
		this.grid = new GridPanel(this);
		this.methods = new CheckMethods(grid);
		this.scorePanel = new ScorePanel(newGame);
		add(scorePanel, BorderLayout.WEST);
		add(grid, BorderLayout.CENTER);
		getSavedHighScore();
	}

	private void getSavedHighScore() throws ClassNotFoundException, IOException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(
				"HighScore.ser"));

		this.highScore = (Integer) input.readObject();
		this.scorePanel.resetHighScore(this.highScore);
		input.close();
	}

	public void newGame() throws InterruptedException {
		this.grid.newGrid();
		this.methods.setNewGrid(grid);
		this.scorePanel.newGame();
	}

	// change to animate the switch instead of cursor
	public void jewelClicked(ShapeLabel pressedLabel) {
		setCursor(pressedLabel.getCursor());
	}

public void jewelReleased(ShapeLabel pressedLabel, ShapeLabel enteredLabel)
			throws InterruptedException {
		setGridCursor(Cursor.getDefaultCursor());
		System.out.println(enteredLabel.getRow() + " " + enteredLabel.getCol());
		System.out.println(pressedLabel.getRow() + " " + pressedLabel.getCol());
		grid.showBoard();
		if (!pressedLabel.equals(enteredLabel)) {
			tryJewelSwap(pressedLabel, enteredLabel);
		}
		grid.showBoard();
	}


	public void setGridCursor(Cursor cursor) {
		setCursor(cursor);
	}

	public void tryJewelSwap(ShapeLabel pressedLabel, ShapeLabel enteredLabel)
			throws InterruptedException {
		int pressedRow = pressedLabel.getRow();
		int pressedCol = pressedLabel.getCol();
		int enteredRow = enteredLabel.getRow();
		int enteredCol = enteredLabel.getCol();
		if (methods.isValidSwapLocation(pressedRow, pressedCol, enteredRow,
				enteredCol)) {
			if (methods.prospectiveMatch(pressedLabel, enteredLabel,
					pressedRow, pressedCol, enteredRow, enteredCol)) {
				grid.swap(pressedLabel, enteredLabel);
				checkMatches();

			} else {
				SwapThread thread = new SwapThread(pressedLabel, enteredLabel,
						grid);
				thread.start();

			}
		}
	}

	public void checkMatches() throws InterruptedException {
		boolean checkAgain = false;
		do {
			checkAgain = false;
			ArrayList<ArrayList<ShapeLabel>> deletions = new ArrayList<ArrayList<ShapeLabel>>();
			deletions = methods.checkBoard();
			if (deletions != null) {
				if (deletions.size() > 1) {
					frame.setCommentLabel();
				}
				grid.deleteMatches(deletions, frame);
				checkAgain = true;
			}
			// make a thread.sleep here
		} while (checkAgain);
	}

	public void increaseScore(int amount) throws InterruptedException {

		boolean bonus = scorePanel.setScore(amount);
		if (bonus) {
			frame.setBonusLabel();
			grid.deleteBonus();
		}
	}

	public void endGame() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		this.scorePanel.endGame();
		ObjectOutputStream output = new ObjectOutputStream(
				new FileOutputStream("HighScore.ser"));
		output.writeObject(this.scorePanel.getHighScore());
		output.close();
	}
}
