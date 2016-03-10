package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.util.ArrayList;

public class CheckMethods {
	private GridPanel grid;
	private ArrayList<ArrayList<ShapeLabel>> matches;

	public CheckMethods(GridPanel gridPanel) {
		this.grid = gridPanel;
		this.matches = new ArrayList<ArrayList<ShapeLabel>>();
	}

	public boolean isValidSwap(ShapeLabel pressedLabel, ShapeLabel enteredLabel) {
		int pressedRow = pressedLabel.getRow();
		int pressedCol = pressedLabel.getCol();
		int enteredRow = enteredLabel.getRow();
		int enteredCol = enteredLabel.getCol();
		if (isValidSwapLocation(pressedRow, pressedCol, enteredRow, enteredCol)
				&& prospectiveMatch(pressedLabel, enteredLabel, pressedRow,
						pressedCol, enteredRow, enteredCol)) {
			return true;
		}
		return false;
	}

	private boolean isValidSwapLocation(int pressedRow, int pressedCol,
			int enteredRow, int enteredCol) {
		// TODO Auto-generated method stub
		if (((enteredRow == pressedRow + 1 || enteredRow == pressedRow - 1) && enteredCol == pressedCol)
				|| ((enteredCol == pressedCol + 1 || enteredCol == pressedCol - 1) && enteredRow == pressedRow)) {
			return true;
		}
		return false;
	}

	private boolean prospectiveMatch(ShapeLabel pressedLabel,
			ShapeLabel enteredLabel, int pressedRow, int pressedCol,
			int enteredRow, int enteredCol) {
		// TODO Auto-generated method stub

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
		int pieceId = piece.getId();
		if (pieceId == grid.getJewelAt(row1, col1).getId()
				&& (!piece.equals(grid.getJewelAt(row1, col1)) && pieceId == grid
						.getJewelAt(row2, col2).getId())
				&& (!piece.equals(grid.getJewelAt(row2, col2)))) {
			return true;
		}
		return false;
	}

	private boolean isVerticalMatch(int row1, int col1, ShapeLabel piece2) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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

	public int checkBoard() {
		this.matches = new ArrayList<ArrayList<ShapeLabel>>();
		checkVertical();
		checkHorizontal();
		System.out.println("here");
		if(matches.size()==0){
			
		System.out.println(matches.size());
		}
		if(matches.isEmpty()){
			System.out.println("empty");
			//return null;
		}
		return matches.size();
	}

	private void checkHorizontal() {
		// TODO Auto-generated method stub
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
					if (next.getId() == (start.getId())) {
						chain.add(next);
					} else {
						break;
					}
				}
				if (chain.size() > 2)
					this.matches.add(chain);
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
					if (next.getId() == start.getId()) {
						chain.add(next);
					} else {
						break;
					}
				}
				if (chain.size() > 2)
					this.matches.add(chain);
				c = temp - 1;
			}
		}
	}

}