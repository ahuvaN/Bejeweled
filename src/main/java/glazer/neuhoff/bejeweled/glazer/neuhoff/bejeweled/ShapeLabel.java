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
	private int row;
	private int col;

	public ShapeLabel(String iconFileName, int id, int row, int col) {
		this.icon = new ImageIcon(getClass().getResource(iconFileName));
		setIcon(icon);
		this.cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				icon.getImage(), new Point(0, 30), " ");
		this.id = id;
		this.row=row;
		this.col=col;

	}
	public ShapeLabel(ImageIcon icon, int id, int row, int col) {
		this.icon=icon;
		setIcon(this.icon);
		this.cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				this.icon.getImage(), new Point(0, 30), " ");
		this.id = id;
		this.row=row;
		this.col=col;
	}

	public ImageIcon getIconPic() {
		return this.icon;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIconPic(ImageIcon icon) {
		this.icon = icon;
		setIcon(this.icon);
	}
	public int getId() {
		return id;
	}

	public Cursor getCursor() {
		return cursor;

	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
}
