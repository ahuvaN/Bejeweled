package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

public class SwapThread extends Thread {
	private ShapeLabel pressedLabel;
	private ShapeLabel enteredLabel;
	private GridPanel grid;
	public SwapThread(ShapeLabel enteredLabel, ShapeLabel pressedLabel, GridPanel grid) {
this.pressedLabel=pressedLabel;
this.enteredLabel=enteredLabel;
this.grid=grid;
	}
	public void run(){
		grid.swap(pressedLabel, enteredLabel);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		grid.swap(pressedLabel, enteredLabel);
	}
}
