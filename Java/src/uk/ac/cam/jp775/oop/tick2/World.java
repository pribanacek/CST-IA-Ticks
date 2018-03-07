package uk.ac.cam.jp775.oop.tick2;

public abstract class World {

	private int mGeneration;
	private Pattern mPattern;

	public World(String pattern) {
		mPattern = new Pattern(pattern);
		mGeneration = 0;
	}

	public int getWidth() {
		return mPattern.getWidth();
	}

	public int getHeight() {
		return mPattern.getHeight();
	}

	public int getGenerationCount() {
		return mGeneration;
	}

	protected void incrementGenerationCount() {
		mGeneration++;
	}

	protected Pattern getPattern() {
		return mPattern;
	}

	public void nextGeneration() {
		nextGenerationImpl();
		mGeneration++;
	}

	protected abstract void nextGenerationImpl();

	public abstract boolean getCell(int col, int row);

	public abstract void setCell(int col, int row, boolean val);

	protected int countNeighbours(int x, int y) {
		int n = 0;
		for (int r = -1; r < 2; r++) {
			for (int c = -1; c < 2; c++) {
				if (!(r == 0 && c == 0) && getCell(x + r, y + c))
					n++;
			}
		}
		return n;
	}

	protected boolean computeCell(int x, int y) {

		// liveCell is true if the cell at position (col,row) in world is live
		boolean liveCell = getCell(x, y);

		// neighbours is the number of live neighbours to cell (col,row)
		int neighbours = countNeighbours(x, y);

		// we will return this value at the end of the method to indicate whether
		// cell (col,row) should be live in the next generation
		boolean nextCell = false;

		// A live cell with less than two neighbours dies (underpopulation)
		if (neighbours < 2) {
			nextCell = false;
		}

		// A live cell with two or three neighbours lives (a balanced population)
		if (liveCell && (neighbours == 2 || neighbours == 3)) {
			nextCell = true;
		}

		// A live cell with with more than three neighbours dies (overcrowding)
		if (neighbours > 3) {
			nextCell = false;
		}

		// A dead cell with exactly three live neighbours comes alive
		if (!liveCell && neighbours == 3) {
			nextCell = true;
		}

		return nextCell;
	}

}
