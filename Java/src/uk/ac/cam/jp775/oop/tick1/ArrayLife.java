package uk.ac.cam.jp775.oop.tick1;

public class ArrayLife {

	private boolean[][] mWorld;
	private Pattern mPattern;

	// NAME:AUTHOR:WIDTH:HEIGHT:STARTUPPERCOL:STARTUPPERROW:CELLS
	public ArrayLife(String format) {
		mPattern = new Pattern(format);
		mWorld = new boolean[mPattern.getHeight()][mPattern.getWidth()];
		mPattern.initialise(mWorld);
	}

	public static void main(String[] args) throws java.io.IOException {
		String a = "";
		for (String s : args) {
			a = a + " " + s;
		}
		ArrayLife life = new ArrayLife(a.substring(1));
		life.play();
	}

	public void play() throws java.io.IOException {
		int userResponse = 0;
		while (userResponse != 'q') {
			print();
			userResponse = System.in.read();
			nextGeneration();
		}
	}

	public void print() {
		for (int row = 0; row < mPattern.getHeight(); row++) {
			for (int col = 0; col < mPattern.getWidth(); col++) {
				System.out.print(getCell(row, col) ? "#" : "_");
			}
			System.out.println();
		}
	}

	public boolean getCell(int col, int row) {
		if (row < 0 || row > mPattern.getHeight() - 1)
			return false;
		if (col < 0 || col > mPattern.getWidth() - 1)
			return false;

		return mWorld[row][col];
	}

	public void setCell(int col, int row, boolean value) {
		if (row < 0 || row > mPattern.getHeight() - 1)
			return;
		if (col < 0 || col > mPattern.getWidth() - 1)
			return;
		mWorld[row][col] = value;
	}

	private int countNeighbours(int col, int row) {
		int n = 0;
		for (int r = -1; r < 2; r++) {
			for (int c = -1; c < 2; c++) {
				if (!(r == 0 && c == 0) && getCell(col + r, row + c))
					n++;
			}
		}
		return n;
	}

	private boolean computeCell(int col, int row) {

		// liveCell is true if the cell at position (col,row) in world is live
		boolean liveCell = getCell(col, row);

		// neighbours is the number of live neighbours to cell (col,row)
		int neighbours = countNeighbours(col, row);

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

	public void nextGeneration() {
		boolean[][] nextGeneration = new boolean[mWorld.length][];
		for (int y = 0; y < mPattern.getHeight(); y++) {
			nextGeneration[y] = new boolean[mWorld[y].length];
			for (int x = 0; x < mWorld[y].length; ++x) {
				boolean nextCell = computeCell(x, y);
				nextGeneration[y][x] = nextCell;
			}
		}
		mWorld = nextGeneration;
	}

}