package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ShapeLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private ImageIcon icon;
	private Cursor cursor;

	public ShapeLabel(String iconFileName, int id) {
		this.icon = new ImageIcon(getClass().getResource(iconFileName));
		setIcon(icon);
		this.cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				icon.getImage(), new Point(0, 30), " ");
		this.id = id;

	}
	public ShapeLabel(ImageIcon icon, int id) {
		this.icon=icon;
		setIcon(this.icon);
		this.cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				this.icon.getImage(), new Point(0, 30), " ");
		this.id = id;
	}

	public ImageIcon getIcon() {
		return this.icon;
	}
	public int getId() {
		return id;
	}

	public Cursor getCursor() {
		return cursor;

	}
}
