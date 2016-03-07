package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel score;
	private JLabel scoreValue;

	public ScorePanel() {
		setBackground(new Color(0,0,0,0));
		this.score = new JLabel("SCORE:");
		this.scoreValue = new JLabel("0");
		add(score);
		add(scoreValue);
	}
}
