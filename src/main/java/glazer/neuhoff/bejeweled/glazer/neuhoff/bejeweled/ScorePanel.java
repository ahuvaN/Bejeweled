package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel score;
	private JLabel scoreValue;
	private boolean sound;
	private JCheckBoxMenuItem mute;
	private ScheduledExecutorService executor2;
	private MusicThread musicThread;

	public ScorePanel() {
		setBackground(new Color(0, 0, 0, 0));
		this.score = new JLabel("SCORE:");
		this.scoreValue = new JLabel("0");
		this.mute = new JCheckBoxMenuItem("MUTE");
		mute.setBackground(new Color(0, 0, 0, 0));
		this.mute.addActionListener(muteGame);
		this.sound = true;
		this.musicThread = new MusicThread();
		this.executor2 = Executors.newScheduledThreadPool(1);
		this.executor2.scheduleAtFixedRate(playSound, 0, 66, TimeUnit.SECONDS);
		add(score);
		add(scoreValue);
		add(mute);
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
