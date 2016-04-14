package glazer.neuhoff.bejeweled;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;

public class Game extends JComponent {

	private static final long serialVersionUID = 1L;
	private CheckMethods methods;
	private GridPanel grid;
	private ScorePanel scorePanel;
	protected BejeweledFrame frame;
	private int highScore;

	public Game(BejeweledFrame bframe, JButton newGame)
			throws ClassNotFoundException, IOException, InterruptedException {
		highScore = 500;
		setLayout(new BorderLayout());
		frame = bframe;
		grid = new GridPanel(this);
		methods = new CheckMethods(grid);
		scorePanel = new ScorePanel(newGame);
		add(scorePanel, BorderLayout.WEST);
		add(grid, BorderLayout.CENTER);
		checkFileExist();
		getSavedHighScore();
	}

	private void checkFileExist() throws FileNotFoundException, IOException {
        File file = new File(System.getProperty("user.home") + "\\HighScore.ser");
        if (!file.exists()) {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));

            output.writeObject(this.scorePanel.getHighScore());
            output.close();
        }
    }
	private void getSavedHighScore() throws ClassNotFoundException, IOException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(
				System.getProperty("user.home") + "\\HighScore.ser"));

		highScore = (Integer) input.readObject();
		scorePanel.resetHighScore(highScore);
		input.close();
	}

	public void newGame() throws InterruptedException {
		grid.newGrid();
		methods.setNewGrid(grid);
		scorePanel.newGame();
		frame.repaint();
	}

	// change to animate the switch instead of cursor
	public void jewelClicked(ShapeLabel pressedLabel) {
		setCursor(pressedLabel.getCursor());
	}

	public void jewelReleased(ShapeLabel pressedLabel, ShapeLabel enteredLabel)
			throws InterruptedException {
		setGridCursor(Cursor.getDefaultCursor());
		if (!pressedLabel.equals(enteredLabel)) {
			tryJewelSwap(pressedLabel, enteredLabel);
		}
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
		frame.repaint();
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
				new FileOutputStream(System.getProperty("user.home") + "\\HighScore.ser"));
		output.writeObject(this.scorePanel.getHighScore());
		output.close();
	}
}
