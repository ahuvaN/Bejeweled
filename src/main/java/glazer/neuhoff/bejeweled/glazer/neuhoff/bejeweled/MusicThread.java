package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class MusicThread extends Thread {
	private AudioClip click;

	public MusicThread() {

	}

	public void run() {
		URL urlClick = getClass().getResource("/music.mp3");
		click = Applet.newAudioClip(urlClick);
		click.play();
	}

	public void stopMusic() {
		click.stop();
	}
}
