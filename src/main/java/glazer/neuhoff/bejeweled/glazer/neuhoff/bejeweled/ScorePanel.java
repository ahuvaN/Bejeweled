package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JCheckBoxMenuItem;
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
	private JLabel scoreLabel, scoreValue;
	private boolean sound;
	private JCheckBoxMenuItem mute;
	private ScheduledExecutorService executor2;
	private MusicThread musicThread;
	private JProgressBar bar;
	private int score;

	public ScorePanel() {
		setBackground(new Color(0, 0, 0, 0));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel scoreValues = new JPanel();
		scoreValues.setLayout(new FlowLayout());
		scoreValues.setBackground(new Color(0, 0, 0, 0));
		scoreLabel = new JLabel("SCORE:");
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
		this.scoreValue = new JLabel("0");
		scoreValue = new JLabel("0");
		scoreValue.setFont(new Font("Arial", Font.BOLD, 20));
		bar = new JProgressBar(SwingConstants.VERTICAL, 0, 100);
		bar.setBorderPainted(true);
		bar.setBackground(new Color(0, 0, 0, 0));
		bar.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		this.mute = new JCheckBoxMenuItem("MUTE");
		mute.setBackground(new Color(0, 0, 0, 0));
		this.mute.addActionListener(muteGame);
		this.sound = true;
		this.musicThread = new MusicThread();
		this.executor2 = Executors.newScheduledThreadPool(1);
		this.executor2.scheduleAtFixedRate(playSound, 0, 66, TimeUnit.SECONDS);
		this.score = 0;
		scoreValues.add(scoreLabel);
		scoreValues.add(scoreValue);
		add(scoreValues);
		add(bar);
		add(mute);
	}

	public void setScore(int value) {
		score += value;
		scoreLabel.setText(String.valueOf(score));
		bar.setValue(score);
	}

	Runnable playSound = new Runnable() {

		public void run() {
			if (sound) {
				musicThread = new MusicThread();
				musicThread.start();
			}
		}
	};
	ActionListener muteGame = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			if (sound) {
				sound = false;
				musicThread.stopMusic();
			} else {

				musicThread = new MusicThread();
				musicThread.start();
				sound = true;
			}

		}

	};
}
