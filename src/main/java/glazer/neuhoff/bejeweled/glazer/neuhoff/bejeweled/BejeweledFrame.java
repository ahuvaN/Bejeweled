package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BejeweledFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Container container;
	private ImageIcon frameIcon;
	private Image img;
	private GridPanel gridPanel;
	private ScorePanel westPanel;

	public BejeweledFrame() {
		setSize(850, 700);
		setTitle("BEJEWELED");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new JLabel(new ImageIcon(getClass().getResource(
				"/background1.jpg"))));
		frameIcon = new ImageIcon(getClass().getResource("/GameIcon.jpg"));
		img = frameIcon.getImage().getScaledInstance(250, 250,
				java.awt.Image.SCALE_SMOOTH);
		setIconImage(img);
		container = getContentPane();
		container.setLayout(new BorderLayout());
		westPanel = new ScorePanel();
		container.add(westPanel, BorderLayout.WEST);
		gridPanel = new GridPanel(westPanel);
		container.add(gridPanel, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		BejeweledFrame frame = new BejeweledFrame();
		frame.setVisible(true);
	}

}
