package uk.ac.cam.jp775.oop.tick5;

public class Pattern implements Comparable<Pattern> {

	private String mName;
	private String mAuthor;
	private int mWidth;
	private int mHeight;
	private int mStartCol;
	private int mStartRow;
	private String mCells;

	public String getName() {
		return mName;
	}

	public String getAuthor() {
		return mAuthor;
	}

	public int getWidth() {
		return mWidth;
	}

	public int getHeight() {
		return mHeight;
	}

	public int getStartCol() {
		return mStartCol;
	}

	public int getStartRow() {
		return mStartRow;
	}

	public String getCells() {
		return mCells;
	}

	public Pattern(String format) throws PatternFormatException {
		if (format.isEmpty()) {
			throw new PatternFormatException("Please specify a pattern.");
		}
		String[] t = format.split(":");

		if (t.length != 7) {
			throw new PatternFormatException(
					"Invalid pattern format: Incorrect number of fields in pattern (found " + t.length + ").");
		}
		mName = t[0];
		mAuthor = t[1];
		try {
			mWidth = Integer.parseInt(t[2]);
		} catch (Exception e) {
			throw new PatternFormatException(
					"Invalid pattern format: Could not interpret the width field as a number ('" + t[2] + "' given).");
		}
		try {
			mHeight = Integer.parseInt(t[3]);
		} catch (Exception e) {
			throw new PatternFormatException(
					"Invalid pattern format: Could not interpret the height field as a number ('" + t[3] + "' given).");
		}
		try {
			mStartCol = Integer.parseInt(t[4]);
		} catch (Exception e) {
			throw new PatternFormatException(
					"Invalid pattern format: Could not interpret the startX field as a number ('" + t[4] + "' given).");
		}
		try {
			mStartRow = Integer.parseInt(t[5]);
		} catch (Exception e) {
			throw new PatternFormatException(
					"Invalid pattern format: Could not interpret the startY field as a number ('" + t[5] + "' given).");
		}
		mCells = t[6];
	}

	public void initialise(World world) throws PatternFormatException {
		String[] pattern = mCells.split(" ");
		char[][] p = new char[pattern.length][];
		for (int i = 0; i < p.length; i++) {
			p[i] = pattern[i].toCharArray();
		}

		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p[i].length; j++) {
				if (p[i][j] != '1' && p[i][j] != '0') {
					throw new PatternFormatException("Invalid pattern format: Malformed pattern '" + mCells + "'.");
				}
				world.setCell(mStartCol + i, mStartRow + j, p[i][j] == '1');
			}

		}
	}

	@Override
	public int compareTo(Pattern p) {
		return mName.compareTo(p.getName());
	}

	@Override
	public String toString() {
		return mName.replaceAll(" ", "-") + " (" + mAuthor + ")";
	}
}
