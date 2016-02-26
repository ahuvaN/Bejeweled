package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class BejeweledFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container container;
	private ImageIcon frameIcon;
	private Image img;

	public BejeweledFrame() {
		setSize(700, 700);
		setTitle("BEJEWELED");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frameIcon = new ImageIcon(getClass().getResource("./GameIcon.jpg"));
		this.img = frameIcon.getImage().getScaledInstance(250, 250,
				java.awt.Image.SCALE_SMOOTH);
		setIconImage(img);
		this.container = getContentPane();
		container.setLayout(new GridLayout(8, 8));
	}
}
