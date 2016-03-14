package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import javax.swing.JLabel;

public class FlashLabelThread extends Thread {

	private JLabel commentLabel;

	public FlashLabelThread(JLabel commentLabel) {
		this.commentLabel = commentLabel;
	}

	public void run() {

		int x = 0;
		while (x < 6) {
			this.commentLabel.setVisible(true);

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.commentLabel.setVisible(false);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			x++;
		}
	}
}
