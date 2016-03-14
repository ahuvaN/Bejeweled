package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ShapeLabel extends JLabel {
	
	private static final long serialVersionUID = 1L;
	
	private int id, row, col;
	private ImageIcon iconPic;
	private Cursor cursor;

	public ShapeLabel(String iconFileName, int id, int row, int col) {
		iconPic = new ImageIcon(getClass().getResource(iconFileName));
		setIcon(iconPic);
		setCursor();
		this.id = id;
		this.row = row;
		this.col = col;
	}

	public ShapeLabel(ImageIcon icon, int id, int row, int col) {
		this.iconPic = icon;
		setIcon(this.iconPic);
		setCursor();
		this.id = id;
		this.row = row;
		this.col = col;
	}

	public ImageIcon getIconPic() {
		return this.iconPic;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIconPic(ImageIcon icon) {
		this.iconPic = icon;
		setIcon(this.iconPic);
		setCursor();
	}

	public int getId() {
		return id;
	}

	public Cursor getCursor() {
		return cursor;
	}

	private void setCursor() {
		this.cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				this.iconPic.getImage(), new Point(0, 30), " ");
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
