package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JCheckBoxMenuItem;

import java.awt.Font;

import javax.swing.AbstractButton;
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
	private Color backgroundColor;
	private JLabel scoreOval;
	private Color purpleColor;

	public ScorePanel(JButton newGame) {
		setBackground(new Color(0, 0, 0, 0));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.bejeweledIcon = new ImageIcon(getClass().getResource(
				"/Bejeweled.png"));
		this.scoreOval = new JLabel();
		this.newGame = newGame;
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
		this.backgroundColor = new Color(0, 0, 0, 0);
		this.purpleColor= new Color(204, 153, 255);
		setComponents();
		addComponents();
	}

	private void setComponents() {
		// TODO Auto-generated method stub
		setFontSize(this.highScoreLabel);
		this.highScoreLabel.setHorizontalTextPosition(JLabel.CENTER);
		setFontSize(this.highScoreText);
		this.highScoreText.setHorizontalTextPosition(JLabel.CENTER);
		setFontSize(this.newGame);
		setBackground(bar);
		setBackground(mute);
		//setBackground(newGame);
		setBackground(this.scoreOval);
		bar.setBorderPainted(true);
		bar.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		icon.setIcon(this.bejeweledIcon);
		this.newGame.setIcon(new ImageIcon(getClass().getResource("/NewGameIcon2.jpg")));
		this.newGame.setHorizontalTextPosition(JLabel.CENTER);
		newGame.setOpaque(false);
		newGame.setContentAreaFilled(false);
		newGame.setBorderPainted(false);
		 newGame.setFocusPainted(false);
		this.scoreOval.setIcon(new ImageIcon(getClass().getResource(
				"/ScoreOval.jpg")));
		this.scoreOval.setText("0");
		this.scoreOval.setHorizontalTextPosition(JLabel.CENTER);
		this.scoreOval.setForeground(this.purpleColor);
	}

	private void setFontSize(JComponent label) {
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setForeground(this.purpleColor);
		
		
		// setBackground(new Color(0, 0, 0, 0));
	}

	private void setBackground(JComponent label) {
		label.setBackground(backgroundColor);
		label.setOpaque(true);
	}

	private void addComponents() {
		this.mute.addActionListener(muteGame);
		add(new JLabel(" "));
		add(new JLabel(" "));
		add(icon);
		add(new JLabel(" "));
		add(new JLabel(" "));
		add(new JLabel());
		add(this.scoreOval);
		add(new JLabel(" "));
		add(this.highScoreText);
		add(this.highScoreLabel);
		add(new JLabel(" "));
		add(bar);
		add(this.newGame);
		add(new JLabel(" "));
		add(mute);
	}

	public void setScore(int value) {
		score += value;
		this.scoreOval.setText(String.valueOf(score));
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
		// TODO Auto-generated method stub
		this.bar.setMaximum(highScore);
	}

	public void newGame() {
		// TODO Auto-generated method stub
		if (this.score > highScore) {
			highScore = this.score;
			resetHighScore(highScore);
		}
		this.score = 0;
		this.scoreOval.setText(String.valueOf(score));
		this.bar.setValue(0);
	}

	public void resetHighScore(int newHighScore) {
		// TODO Auto-generated method stub
		this.highScore = newHighScore;
		this.highScoreLabel.setText(String.valueOf(this.highScore));
		setBarGoal();
	}

	public Object getHighScore() {
		// TODO Auto-generated method stub
		return this.highScore;
	}

	public void endGame() {
		// TODO Auto-generated method stub
		if (this.score > highScore) {
			highScore = this.score;
		}
	}
}
