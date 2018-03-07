package uk.ac.cam.jp775.oop.tick2;

public class Pattern {

	String mName;
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

	public Pattern(String format) {
		String[] t = format.split(":");

		mName = t[0];
		mAuthor = t[1];
		mWidth = Integer.parseInt(t[2]);
		mHeight = Integer.parseInt(t[3]);
		mStartCol = Integer.parseInt(t[4]);
		mStartRow = Integer.parseInt(t[5]);
		mCells = t[6];
	}

	public void initialise(World world) {
		String[] pattern = mCells.split(" ");
		char[][] p = new char[pattern.length][];
		for (int i = 0; i < p.length; i++) {
			p[i] = pattern[i].toCharArray();
		}

		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p[i].length; j++) {
				world.setCell(mStartCol + i, mStartRow + j, p[i][j] == '1');
			}
		}
	}

	public static void main(String[] args) {
		String format = "";
		for (String s : args) {
			format = format + " " + s;
		}
		Pattern p = new Pattern(format.substring(1));

		System.out.println("Name: " + p.getName());
		System.out.println("Author: " + p.getAuthor());
		System.out.println("Width: " + p.getWidth());
		System.out.println("Height: " + p.getHeight());
		System.out.println("StartCol: " + p.getStartCol());
		System.out.println("StartRow: " + p.getStartRow());
		System.out.println("Pattern: " + p.getCells());
	}

}
