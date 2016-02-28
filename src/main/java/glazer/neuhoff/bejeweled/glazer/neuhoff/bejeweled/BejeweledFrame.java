package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class BejeweledFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Container container;
	private JLabel[][] grid;
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
		container = getContentPane();
		container.setLayout(new GridLayout(8, 8));
		
		add(container);
		grid = new JLabel[8][8];
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				grid[row][col] = new JLabel();
				//grid[row][col].setBackground(new Color(0, 0, 0, 0));
				container.add(grid[row][col]);
			}
		}
		
	}
}
