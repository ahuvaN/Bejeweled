package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class ScorePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel score;
	private JLabel scoreValue;
	private JProgressBar bar;

	public ScorePanel() {
		setBackground(new Color(0,0,0,0));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel scoreValues = new JPanel();
		scoreValues.setLayout(new FlowLayout());
		scoreValues.setBackground(new Color(0,0,0,0));

		score = new JLabel("SCORE:");
		score.setFont(new Font("Arial", Font.BOLD, 20));
		scoreValue = new JLabel("0");
		scoreValue.setFont(new Font("Arial", Font.BOLD, 20));
		bar = new JProgressBar(SwingConstants.VERTICAL, 0, 100);
		bar.setBorderPainted(true);
		bar.setBackground(new Color(0,0,0,0));
		bar.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		
		scoreValues.add(score);
		scoreValues.add(scoreValue);
		add(scoreValues);
		add(bar);
	}
	
	public void setScore(int value){
		//if we can set score like we did in android pi calculation
		//maybe also create a status bar of points until goal/game end
		String val = scoreValue.getText();
		int score = Integer.parseInt(val);
		score += value;
		scoreValue.setText(String.valueOf(score));
		bar.setValue(score);
	}
}
