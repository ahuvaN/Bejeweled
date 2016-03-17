package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JCheckBoxMenuItem;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class ScorePanel extends JPanel {

	private boolean sound;
	private JCheckBoxMenuItem mute;
	private ScheduledExecutorService executor2;
	private MusicThread musicThread;
	private int score, highScore;;
	private JLabel highScoreLabel, highScoreText, icon, scoreOval;
	private JButton newGame;
	private Color backgroundColor = new Color(0, 0, 0, 0);
	private Color purpleColor = new Color(204, 153, 255);

	public ScorePanel(JButton ngame) {
		scoreOval = new JLabel("0");
		newGame = ngame;
		mute = new JCheckBoxMenuItem("MUTE");
		sound = true;
		musicThread = new MusicThread();
		executor2 = Executors.newScheduledThreadPool(1);
		executor2.scheduleAtFixedRate(playSound, 0, 66, TimeUnit.SECONDS);
		score = highScore = 0;
		highScoreText = new JLabel("HIGH SCORE");
		highScoreLabel = new JLabel("0");
		icon = new JLabel();

		setBackground(backgroundColor);
		setLayout(new GridLayout(5, 1, 0, 7));
		setComponents();
		addComponents();
	}

	private void setComponents() {
		setFontSize(highScoreLabel);
		setFontSize(highScoreText);
		setFontSize(newGame);
		setFontSize(scoreOval);
		setBackground(mute);
		setBackground(scoreOval);
		setBackground(newGame);

		highScoreLabel.setHorizontalAlignment(JLabel.CENTER);
		highScoreText.setHorizontalAlignment(JLabel.CENTER);
		newGame.setHorizontalTextPosition(JLabel.CENTER);
		scoreOval.setHorizontalTextPosition(JLabel.CENTER);

		icon.setIcon(new ImageIcon(getClass().getResource("/Bejeweled.png")));
		newGame.setIcon(new ImageIcon(getClass().getResource("/newGame.png")));
		
		scoreOval.setIcon(new ImageIcon(getClass().getResource("/OvalIcon.png")));

	}

	private void setFontSize(JComponent label) {
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setForeground(purpleColor);
	}

	private void setBackground(JComponent label) {
		label.setBackground(backgroundColor);
		label.setOpaque(true);
		
	}

	private void addComponents() {
		mute.addActionListener(muteGame);
		add(icon);
		add(scoreOval);
		JPanel p = new JPanel();
		p.setBackground(backgroundColor);
		p.setLayout(new GridLayout(6, 1));
		p.add(new JLabel(" "));
		p.add(new JLabel(" "));
		p.add(highScoreText);
		p.add(highScoreLabel);
		p.add(new JLabel(" "));
		p.add(new JLabel(" "));
		add(p);
		add(newGame);
		add(mute);
	}

	public boolean setScore(int value) {
		boolean bonus = false;
		if (score >= 1000) {
			value *= (score / 800);
		}
		int newScore = score + value;
		if (score < 1000 && newScore > 1000) {
			bonus = true;
		}
		score += value;
		scoreOval.setText(String.valueOf(score));
		return bonus;
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

	public void newGame() {
		if (score > highScore) {
			highScore = score;
			resetHighScore(highScore);
		}
		score = 0;
		scoreOval.setText(String.valueOf(score));
	}

	public void resetHighScore(int newHighScore) {
		highScore = newHighScore;
		highScoreLabel.setText(String.valueOf(highScore));
	}

	public Object getHighScore() {
		return highScore;
	}

	public void endGame() {
		if (score > highScore) {
			highScore = score;
		}
	}
}
