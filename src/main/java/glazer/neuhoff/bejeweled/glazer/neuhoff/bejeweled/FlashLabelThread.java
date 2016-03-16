package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import javax.swing.JLabel;

public class FlashLabelThread extends Thread {

	private JLabel commentLabel;

	public FlashLabelThread(JLabel comment) {
		commentLabel = comment;
	}

	public void run() {

		int x = 0;
		while (x < 6) {
			commentLabel.setVisible(true);

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			commentLabel.setVisible(false);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			x++;
		}
	}
}
