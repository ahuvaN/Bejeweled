package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BejeweledFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Container container;
	private ImageIcon frameIcon;
	private Image img;
	private Game game;
	private JButton newGame;
//	private JButton exit;
	private JLabel commentLabel;
	private JPanel northPanel;
	private BejeweledFrame me;

	public BejeweledFrame() throws ClassNotFoundException, IOException {
		setSize(850, 700);
		setTitle("BEJEWELED");
		//will not allow x button to ensure that high score is saved on close
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.me=this;
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(me, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	try {
						game.endGame();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
		            System.exit(0);
		        }else{
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
		this.northPanel = new JPanel();
		container.setLayout(new BorderLayout());

		this.commentLabel = new JLabel("EXCELLENT");
		this.commentLabel.setFont(new Font("Arial", Font.BOLD, 70));
		this.commentLabel.setForeground(Color.WHITE);
		// this.commentLabel.setVisible(false);
		this.container.add(this.commentLabel).setBounds(270, 250, 475, 100);
		this.commentLabel.setVisible(false);
		this.game = new Game(this);
		this.newGame = new JButton("NEW GAME");
		//this.exit = new JButton("END GAME");
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				game.newGame();
			//	setHighScore();
			}
		};
//		ActionListener endGameListener = new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				try {
//					game.endGame();
//				} catch (FileNotFoundException e1) {
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//		};
		this.newGame.addActionListener(listener);

		//this.exit.addActionListener(endGameListener);
		this.northPanel.add(this.newGame);
		//this.northPanel.add(this.exit);
		this.container.add(this.northPanel, BorderLayout.NORTH);
		this.container.add(game, BorderLayout.CENTER);

	}

	public void setCommentLabel() throws InterruptedException {
		setFlashLabel();
		FlashLabelThread flashThread = new FlashLabelThread(this.commentLabel);
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
		}
		
		//frame.setHighScore();
	}

}
