package uk.ac.cam.jp775.oop.tick5;

public class PackedWorld extends World {

	private long mWorld;

	public PackedWorld(Pattern p) throws Exception {
		super(p);
		if (getPattern().getHeight() * getPattern().getWidth() > 64) {
			throw new Exception("The requested pattern cannot be represented by a long. Try ArrayWorld instead");
		}
		getPattern().initialise(this);
	}

	public PackedWorld(PackedWorld w) {
		super(w);
		this.mWorld = w.mWorld;
	}

	public PackedWorld(String p) throws Exception {
		this(new Pattern(p));
	}

	@Override
	public void nextGenerationImpl() {
		long nextGeneration = 0L;
		for (int y = 0; y < getPattern().getHeight(); y++) {
			for (int x = 0; x < getPattern().getWidth(); ++x) {
				if (computeCell(x, y)) {
					nextGeneration = nextGeneration | (1L << (y * getPattern().getHeight() + x));
				}
			}
		}
		mWorld = nextGeneration;
	}

	@Override
	public boolean getCell(int col, int row) {
		return (mWorld >>> (row * getPattern().getHeight() + col) & 1) == 1;
	}

	@Override
	public void setCell(int col, int row, boolean val) {
		if (val) {
			mWorld = mWorld | (1L << (row * getPattern().getHeight() + col));
		} else {
			mWorld = ~(~mWorld | (1L << (row * getPattern().getHeight() + col)));
		}
	}

	@Override
	public PackedWorld clone() {
		PackedWorld copy = (PackedWorld) super.clone();
		copy.mWorld = this.mWorld;
		return copy;
	}
}
