package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

public class JewelReleasedThread extends Thread {
	private Game game;
	private ShapeLabel pressedLabel;
	private ShapeLabel enteredLabel;

	public JewelReleasedThread(Game game, ShapeLabel pressedLabel,
			ShapeLabel enteredLabel) {
		this.game = game;
		this.pressedLabel = pressedLabel;
		this.enteredLabel = enteredLabel;
	}

	public void run() {
		try {
			game.jewelReleased(pressedLabel, enteredLabel);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
