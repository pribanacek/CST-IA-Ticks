package uk.ac.cam.jp775.Algorithms.tick2;

public class LCSBottomUp {
	// extends LCSFinder {

	private String mString1;
	private String mString2;
	private int[][] mTable;

	public LCSBottomUp(String s1, String s2) {
		// super(s1, s2);
		if (!s1.isEmpty() && !s2.isEmpty()) {
			makeTable();
		}
	}

	private void makeTable() {
		mTable = new int[mString1.length()][mString2.length()];

		for (int i = 0; i < mString1.length(); i++) {
			for (int j = 0; j < mString2.length(); j++) {
				if (mString1.charAt(i) == mString2.charAt(j)) {
					mTable[i][j] = 1 + get(i - 1, j - 1);
				} else {
					mTable[i][j] = Math.max(get(i - 1, j), get(i, j - 1));
				}
			}
		}
	}

	// Helper method to take care of zeros
	private int get(int i, int j) {
		if (i < 0 || j < 0) {
			return 0;
		}
		return mTable[i][j];
	}

	// @Override
	public int getLCSLength() {
		if (mTable == null) {
			return 0;
		}
		return mTable[mString1.length() - 1][mString2.length() - 1];
	}

	// @Override
	public String getLCSString() {
		if (mTable == null) {
			return "";
		}
		String s = "";
		int i = mString1.length() - 1, j = mString2.length() - 1;
		while (i >= 0 && j >= 0) {
			if (mString1.charAt(i) == mString2.charAt(j)) {
				s = mString1.charAt(i) + s;
				i--;
				j--;
			} else {
				if (get(i - 1, j) > get(i, j - 1)) {
					i--;
				} else {
					j--;
				}
			}
		}
		return s;
	}

	public static void main(String[] args) {
		LCSBottomUp l = new LCSBottomUp("aleks", "abcdef");
		for (int i = 0; i < l.mTable.length; i++) {
			for (int j = 0; j < l.mTable[i].length; j++) {
				System.out.print(l.mTable[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println(l.getLCSString());
	}

}
