package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class MouseListener extends MouseInputAdapter {

	ShapeLabel enteredLabel;
	ShapeLabel pressedLabel;
	boolean mouseClicked;
	private Game game;

	public MouseListener(Game game) {
		this.game = game;
	}

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
		game.jewelClicked(pressedLabel);
	}

	public void mouseReleased(MouseEvent e) {
		mouseClicked = false;
		game.jewelReleased(this.pressedLabel, this.enteredLabel);
	}
}
