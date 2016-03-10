package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.util.ArrayList;

public class CheckMethods {
	private GridPanel grid;
	private ArrayList<ArrayList<ShapeLabel>> matches;

	public CheckMethods(GridPanel grid) {
		this.grid = grid;
		this.matches = new ArrayList<ArrayList<ShapeLabel>>();
	}

	public void checkBoard() {
		checkVertical();
		checkHorizontal();
	}

	private void checkHorizontal() {
		// TODO Auto-generated method stub
		int r;
		int c;
		int temp;
		for (c = 0; c < GridPanel.COLS; c++) {
			for (r = 0; r < GridPanel.ROWS - 2; r++) {
				ShapeLabel start = grid.getJewelAt(r, c);
				ArrayList chain = new ArrayList();
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