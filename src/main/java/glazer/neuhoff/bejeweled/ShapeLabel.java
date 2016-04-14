package glazer.neuhoff.bejeweled;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ShapeLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	public enum ShapeColor {
		PURPLE, BLUE, GREEN, ORANGE, RED, WHITE, YELLOW, NONE
	}

	public enum Special {
		NONE, FIRE, SUPERNOVA, BOMB
	}

	private int row, col;
	private ImageIcon iconPic;
	private Cursor cursor;
	private ShapeColor color;
	private Special special;

	public ShapeLabel(String iconFileName, ShapeColor shapec, int r, int c) {
		iconPic = new ImageIcon(getClass().getResource(iconFileName));
		setIcon(iconPic);
		setCursor();
		color = shapec;
		special = Special.NONE;
		row = r;
		col = c;
	}

	public ShapeLabel(ImageIcon icon, ShapeColor shapec, int r, int c) {
		this(icon, shapec, Special.NONE, r, c);
	}

	public ShapeLabel(ImageIcon icon, ShapeColor shapec, Special s, int r, int c) {
		iconPic = icon;
		setIcon(iconPic);
		setCursor();
		color = shapec;
		special = s;
		row = r;
		col = c;
	}

	public ImageIcon getIconPic() {
		return iconPic;
	}

	public void setShapeColor(ShapeColor c) {
		color = c;
	}

	public void setIconPic(ImageIcon icon) {
		ImageIcon newIcon = icon;
		JLabel label = new JLabel();
		label.setIcon(newIcon);
		add(label);

		iconPic = icon;
		setIcon(iconPic);
		setCursor();
	}

	public void specialIcon(ShapeColor shapec, Special s, int r, int c) {
		color = shapec;
		special = s;
		String iconFileName = "";
		String colorName = color.name().toLowerCase();
		switch (special.name()) {
		case "NONE":
			iconFileName = colorName + ".png";
			break;
		case "FIRE":
			iconFileName = colorName + "Fire.png";
			break;
		case "SUPERNOVA":
			iconFileName = colorName + "SN.png";
			break;
		case "BOMB":
			iconFileName = "bomb.png";
			break;

		}
		iconPic = new ImageIcon(getClass().getResource(iconFileName));
		setIcon(iconPic);
		setCursor();
		row = r;
		col = c;
	}

	public ShapeColor getShapeColor() {
		return color;
	}

	public Cursor getCursor() {
		return cursor;
	}

	private void setCursor() {
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				iconPic.getImage(), new Point(0, 30), " ");
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
