package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

public class SwapThread extends Thread {
	private ShapeLabel pressedLabel;
	private ShapeLabel enteredLabel;
	private GridPanel grid;

	public SwapThread(ShapeLabel enteredLabel, ShapeLabel pressedLabel,
			GridPanel grid) {
		this.pressedLabel = pressedLabel;
		this.enteredLabel = enteredLabel;
		this.grid = grid;
	}

	public void run() {
		grid.swap(pressedLabel, enteredLabel);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		grid.swap(pressedLabel, enteredLabel);
	}
}
