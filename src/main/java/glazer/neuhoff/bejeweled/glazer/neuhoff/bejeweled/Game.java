package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Cursor;

import javax.swing.JComponent;

public class Game extends JComponent {
	private CheckMethods methods;
	private GridPanel grid;
	private ScorePanel scorePanel;

	public Game() {
		this.grid=new GridPanel();
		this.methods= new CheckMethods();
		this.scorePanel= new ScorePanel();
		this.addMouseListener(new MouseListener(this));
	}
	public void newGame(){
		
	}

	public void jewelClicked(ShapeLabel pressedLabel) {
		setGridCursor(pressedLabel.getCursor());
		
	}
	public void setGridCursor(Cursor cursor){
		setCursor(cursor);
	}
	public void tryJewelSwap(){
		methods.trySwap();
		if (!(enteredRow == pressedRow && enteredCol == pressedCol)
				&& swapAllowed()) {
			swap();
			checkAgain = false;
			do {
				checkAgain = false;
				checkForMultiples();
			} while (checkAgain);
		}
		methods.checkBoard();
	}
}
