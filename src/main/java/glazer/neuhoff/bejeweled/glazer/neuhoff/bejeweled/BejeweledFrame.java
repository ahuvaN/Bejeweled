package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BejeweledFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Container container;
	private ImageIcon frameIcon;
	private Image img;
	private Game game;
	private JButton newGame;
	private JLabel commentLabel;
	private BejeweledFrame me;

	public BejeweledFrame() throws ClassNotFoundException, IOException,
			InterruptedException {
		setSize(1000, 750);
		setTitle("BEJEWELED");

		// will not allow x button to ensure that high score is saved on close
		// setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		me = this;
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				me.repaint();
				if (JOptionPane.showConfirmDialog(me,
						"Are you sure to close this window?",
						"Really Closing?", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					try {
						game.endGame();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.exit(0);
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});

		setContentPane(new JLabel(new ImageIcon(getClass().getResource(
				"/background1.jpg"))));
		frameIcon = new ImageIcon(getClass().getResource("/GameIcon.jpg"));
		img = frameIcon.getImage().getScaledInstance(250, 250,
				java.awt.Image.SCALE_SMOOTH);
		setIconImage(img);
		container = getContentPane();
		container.setLayout(new BorderLayout());

		commentLabel = new JLabel("EXCELLENT");
		commentLabel.setFont(new Font("Arial", Font.BOLD, 70));
		commentLabel.setForeground(Color.WHITE);
		container.add(commentLabel).setBounds(450, 250, 475, 100);
		commentLabel.setVisible(false);
		newGame = new JButton();
		game = new Game(this, newGame);
		MouseListener listener = new MouseListener() {
			@Override
			public void mousePressed(MouseEvent arg0) { 
				try {
					game.newGame();
					repaint();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				newGame.setFocusable(false);
				newGame.setOpaque(false);
				newGame.setContentAreaFilled(false);
				newGame.setBorderPainted(false);
				newGame.setFocusPainted(false);
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) { }
			
			@Override
			public void mouseReleased(MouseEvent arg0) { }
		};
		newGame.addMouseListener(listener);
		container.add(game, BorderLayout.CENTER);

	}

	public void setCommentLabel() throws InterruptedException {
		setFlashLabel();
		FlashLabelThread flashThread = new FlashLabelThread(commentLabel);
		flashThread.start();

	}

	public void setBonusLabel() throws InterruptedException {
		commentLabel.setText("BONUS!");
		commentLabel.setForeground(Color.RED);
		FlashLabelThread flashThread = new FlashLabelThread(commentLabel);
		flashThread.start();

	}

	private void setFlashLabel() {
		this.commentLabel.setText(chooseComment());
		this.commentLabel.setForeground(chooseColor());
	}

	private Color chooseColor() {
		Random rand = new Random();
		int num = rand.nextInt(5);
		switch (num) {
		case 1:
			return Color.YELLOW;
		case 2:
			return Color.WHITE;
		case 3:
			return Color.PINK;
		case 4:
			return Color.GREEN;
		case 5:
			return Color.ORANGE;
		}
		return Color.WHITE;
	}

	private String chooseComment() {
		Random r = new Random();
		int num = r.nextInt(5);
		switch (num) {
		case 1:
			return "EXCELLENT!";
		case 2:
			return "SUPER!";
		case 3:
			return "GREAT JOB!";
		case 4:
			return "HOORAY!";
		case 5:
			return "FANTASTIC!";
		}
		return null;
	}

	public static void main(String[] args) {
		BejeweledFrame frame;
		try {
			frame = new BejeweledFrame();
			frame.setVisible(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
