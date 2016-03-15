package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Testing extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Testing() {
		setSize(400, 400);
		setTitle("BEJEWELED");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container=getContentPane();
		JLabel label1 = new JLabel();
		label1.setLayout(new GridLayout(0, 2, 10, 10));
		ImageIcon newIcon = new ImageIcon(getClass().getResource(
				"/Bejeweled.jpg"));
		JLabel label2 = new JLabel();
		label2.setIcon(newIcon);
		JLabel label3 = new JLabel();
		ImageIcon icon = new ImageIcon(getClass().getResource("/purple.png"));
		label3.setIcon(icon);
		label1.add(label2);
		label1.add(label3);
		container.add(label1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Testing frame = new Testing();
		frame.setVisible(true);
	}

}
