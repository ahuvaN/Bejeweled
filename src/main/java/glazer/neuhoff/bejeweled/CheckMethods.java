package glazer.neuhoff.bejeweled;

import java.util.ArrayList;

public class CheckMethods {
	private GridPanel grid;
	private ArrayList<ArrayList<ShapeLabel>> matches;

	public CheckMethods(GridPanel gridPanel) {
		this.grid = gridPanel;
	}

	public boolean isValidSwapLocation(int pressedRow, int pressedCol,
			int enteredRow, int enteredCol) {
		if (((enteredRow == pressedRow + 1 || enteredRow == pressedRow - 1) && enteredCol == pressedCol)
				|| ((enteredCol == pressedCol + 1 || enteredCol == pressedCol - 1) && enteredRow == pressedRow)) {
			return true;
		}
		return false;
	}

	public boolean prospectiveMatch(ShapeLabel pressedLabel,
			ShapeLabel enteredLabel, int pressedRow, int pressedCol,
			int enteredRow, int enteredCol) {

		if (isHorizontalMatch(pressedRow, pressedCol, enteredLabel)
				|| isHorizontalMatch(enteredRow, enteredCol, pressedLabel)
				|| isVerticalMatch(pressedRow, pressedCol, enteredLabel)
				|| isVerticalMatch(enteredRow, enteredCol, pressedLabel)) {
			return true;
		}
		return false;
	}

	private boolean checkMatch(ShapeLabel piece, int row1, int row2, int col1,
			int col2) {
		int pieceId = piece.getShapeColor().ordinal();
		if (pieceId == grid.getJewelAt(row1, col1).getShapeColor().ordinal()
				&& (!piece.equals(grid.getJewelAt(row1, col1)) && pieceId == grid
						.getJewelAt(row2, col2).getShapeColor().ordinal())
				&& (!piece.equals(grid.getJewelAt(row2, col2)))) {
			return true;
		}
		return false;
	}

	private boolean isVerticalMatch(int row1, int col1, ShapeLabel piece2) {
		if (row1 + 2 < GridPanel.ROWS
				&& checkMatch(piece2, row1 + 1, row1 + 2, col1, col1)) {
			return true;
		} else if (row1 + 1 < GridPanel.ROWS && row1 - 1 >= 0
				&& checkMatch(piece2, row1 + 1, row1 - 1, col1, col1)) {
			return true;
		} else if ((row1 - 2 >= 0)
				&& checkMatch(piece2, row1 - 1, row1 - 2, col1, col1)) {
			return true;
		}
		return false;
	}

	private boolean isHorizontalMatch(int row1, int col1, ShapeLabel piece2) {
		if (col1 + 2 < GridPanel.COLS
				&& checkMatch(piece2, row1, row1, col1 + 1, col1 + 2)) {
			return true;
		} else if (col1 + 1 < GridPanel.COLS && col1 - 1 >= 0
				&& checkMatch(piece2, row1, row1, col1 + 1, col1 - 1)) {
			return true;
		} else if ((col1 - 2 >= 0)
				&& checkMatch(piece2, row1, row1, col1 - 1, col1 - 2)) {
			return true;
		}
		return false;
	}

	public ArrayList<ArrayList<ShapeLabel>> checkBoard() {
		matches = new ArrayList<ArrayList<ShapeLabel>>();
		checkVertical();
		checkHorizontal();
		if(matches.isEmpty()){
			return null;
		}
		return matches;
		
	}

	private void checkHorizontal() {
		int r;
		int c;
		int temp;
		for (c = 0; c < GridPanel.COLS; c++) {
			for (r = 0; r < GridPanel.ROWS - 2; r++) {
				ShapeLabel start = grid.getJewelAt(r, c);
				ArrayList<ShapeLabel> chain = new ArrayList<ShapeLabel>();
				chain.add(start);
				for (temp = (r + 1); temp < 8; temp++) {
					ShapeLabel next = grid.getJewelAt(temp, c);
					if (next.getShapeColor() == (start.getShapeColor())) {
						chain.add(next);
					} else {
						break;
					}
				}
				if (chain.size() > 2)
					matches.add(chain);
				r = temp - 1;
			}
		}

	}

	private void checkVertical() {
		// check vertical rows
		int temp;
		int r;
		int c;
		for (r = 0; r < GridPanel.ROWS; r++) {
			for (c = 0; c < GridPanel.COLS - 2; c++) {
				ShapeLabel start = grid.getJewelAt(r, c);
				ArrayList<ShapeLabel> chain = new ArrayList<ShapeLabel>();
				chain.add(start);
				for (temp = (c + 1); temp < GridPanel.ROWS; temp++) {
					ShapeLabel next = grid.getJewelAt(r, temp);
					if (next.getShapeColor() == start.getShapeColor()) {
						chain.add(next);
					} else {
						break;
					}
				}
				if (chain.size() > 2)
					matches.add(chain);
				c = temp - 1;
			}
		}
	}

	public void setNewGrid(GridPanel grid2) {
		grid = grid2;
	}

}