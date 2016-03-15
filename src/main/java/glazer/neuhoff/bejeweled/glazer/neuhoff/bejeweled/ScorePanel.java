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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
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
	private JLabel highScoreLabel;
	private int highScore;
	private JLabel highScoreText;
	private JButton newGame;
	private ImageIcon bejeweledIcon;
	private JLabel icon;
	private Color backgroundColor = new Color(0, 0, 0, 0);

	public ScorePanel(JButton newGame) {
		setBackground(backgroundColor);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.bejeweledIcon = new ImageIcon(getClass().getResource(
				"/Bejeweled.png"));
		this.scoreLabel = new JLabel("SCORE:");
		this.newGame = newGame;
		this.scoreValue = new JLabel("0");
		this.bar = new JProgressBar(SwingConstants.VERTICAL, 0, 100);
		this.mute = new JCheckBoxMenuItem("MUTE");
		this.sound = true;
		this.musicThread = new MusicThread();
		this.executor2 = Executors.newScheduledThreadPool(1);
		this.executor2.scheduleAtFixedRate(playSound, 0, 66, TimeUnit.SECONDS);
		this.score = 0;
		this.highScore = 0;
		this.highScoreLabel = new JLabel("0");
		this.highScoreText = new JLabel("HIGH SCORE:");
		this.icon = new JLabel();
		this.backgroundColor= new Color(0,0,0,0);
		setComponents();
		addComponents();
	}

	private void setComponents() {
		// TODO Auto-generated method stub
		setFontSize(scoreLabel);
		setFontSize(scoreValue);
		setFontSize(this.highScoreLabel);
		setFontSize(this.highScoreText);
		setFontSize(this.newGame);
		setBackground(bar);
		setBackground(mute);
		setBackground(newGame);
		bar.setBorderPainted(true);
		bar.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));		
		icon.setIcon(this.bejeweledIcon);
	}

	private void setFontSize(JComponent label) {
		label.setFont(new Font("Arial", Font.BOLD, 20));
		// setBackground(new Color(0, 0, 0, 0));
	}
	private void setBackground(JComponent label){
		label.setBackground(backgroundColor);
		label.setOpaque(true);
	}

	private void addComponents() {
		this.mute.addActionListener(muteGame);
		add(icon);
		add(this.newGame);
		add(scoreLabel);
		add(scoreValue);
		add(this.highScoreText);
		add(this.highScoreLabel);
		add(bar);
		add(mute);
	}

	public void setScore(int value) {
		score += value;
		this.scoreValue.setText(String.valueOf(score));
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

	public void setBarGoal() {
		this.bar.setMaximum(highScore);
	}

	public void newGame() {
		if (this.score > highScore) {
			highScore = this.score;
			resetHighScore(highScore);
		}
		this.score = 0;
		this.scoreValue.setText(String.valueOf(score));
		this.bar.setValue(0);
	}

	public void resetHighScore(int newHighScore) {
		this.highScore = newHighScore;
		this.highScoreLabel.setText(String.valueOf(this.highScore));
		setBarGoal();
	}

	public Object getHighScore() {
		return this.highScore;
	}

	public void endGame() {
		if (this.score > highScore) {
			highScore = this.score;
		}
	}
}
