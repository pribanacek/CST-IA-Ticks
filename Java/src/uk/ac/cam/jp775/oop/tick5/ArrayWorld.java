package uk.ac.cam.jp775.oop.tick5;

public class ArrayWorld extends World {

	private boolean[][] mWorld;
	private boolean[] mDeadRow;

	public ArrayWorld(Pattern p) throws PatternFormatException {
		super(p);
		mWorld = new boolean[getHeight()][getWidth()];
		getPattern().initialise(this);
		mDeadRow = new boolean[getPattern().getWidth()];
		for (int i = 0; i < mDeadRow.length; i++) {
			mDeadRow[i] = false;
		}
		for (int i = 0; i < mWorld.length; i++) {
			boolean dead = true;
			for (int j = 0; j < mWorld[i].length; j++) {
				if (mWorld[i][j]) {
					dead = false;
				}
			}
			if (dead) {
				mWorld[i] = mDeadRow;
			}
		}
	}

	public ArrayWorld(ArrayWorld w) {
		super(w);
		mWorld = new boolean[w.mWorld.length][];
		for (int i = 0; i < w.mWorld.length; i++) {
			if (w.mWorld[i] == w.mDeadRow) {
				mWorld[i] = w.mDeadRow;
			} else {
				mWorld[i] = w.mWorld[i].clone();
			}
		}
		mDeadRow = w.mDeadRow;
	}

	public ArrayWorld(String p) throws PatternFormatException {
		this(new Pattern(p));
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

	@Override
	public ArrayWorld clone() {
		ArrayWorld copy = (ArrayWorld) super.clone();
		copy.mWorld = new boolean[mWorld.length][];
		for (int i = 0; i < mWorld.length; i++) {
			boolean[] row = new boolean[mWorld[i].length];
			boolean dead = true;
			for (int j = 0; j < row.length; j++) {
				row[j] = mWorld[i][j];
				if (row[j]) {
					dead = false;
				}
			}
			copy.mWorld[i] = dead ? mDeadRow : row;
		}
		return copy;
	}

}