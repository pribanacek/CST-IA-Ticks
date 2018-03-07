package uk.ac.cam.jp775.oop.tick2;

public class ArrayWorld extends World {

	private boolean[][] mWorld;

	public ArrayWorld(String format) {
		super(format);
		mWorld = new boolean[getHeight()][getWidth()];
		getPattern().initialise(this);
	}

	@Override
	public boolean getCell(int col, int row) {
		if (row < 0 || row > getPattern().getHeight() - 1)
			return false;
		if (col < 0 || col > getPattern().getWidth() - 1)
			return false;

		return mWorld[row][col];
	}

	@Override
	public void setCell(int col, int row, boolean value) {
		if (row < 0 || row > getPattern().getHeight() - 1)
			return;
		if (col < 0 || col > getPattern().getWidth() - 1)
			return;
		mWorld[row][col] = value;
	}

	@Override
	public void nextGenerationImpl() {
		boolean[][] nextGeneration = new boolean[mWorld.length][];
		for (int y = 0; y < getPattern().getHeight(); y++) {
			nextGeneration[y] = new boolean[mWorld[y].length];
			for (int x = 0; x < mWorld[y].length; ++x) {
				boolean nextCell = computeCell(x, y);
				nextGeneration[y][x] = nextCell;
			}
		}
		mWorld = nextGeneration;
	}

}