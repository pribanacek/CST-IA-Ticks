package uk.ac.cam.jp775.oop.supo2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ClassMarks {

	private static Map<String, Integer> map = new HashMap<String, Integer>();

	public static List<String> getNames() {
		List<Entry<String, Integer>> entries = getEntries(false);
		List<String> names = new ArrayList<String>();
		for (Entry<String, Integer> e : entries) {
			names.add(e.getKey());
		}
		return names;
	}

	private static List<Entry<String, Integer>> getEntries(boolean byMarks) {
		Iterator<Entry<String, Integer>> it = map.entrySet().iterator();
		List<Entry<String, Integer>> marks = new ArrayList<Entry<String, Integer>>();

		if (it.hasNext()) {
			marks.add(it.next());
		}
		while (it.hasNext()) {
			Entry<String, Integer> x = it.next();
			for (int i = 0; i < marks.size(); i++) {
				if ((byMarks && x.getValue().compareTo(marks.get(i).getValue()) < 0)
						|| (!byMarks && x.getKey().compareTo(marks.get(i).getKey()) < 0)) {
					marks.add(i, x);
					break;
				} else if (i == marks.size() - 1) {
					marks.add(x);
					break;
				}
			}
		}
		return marks;
	}

	public static List<String> getTop(float percentile) {
		List<Entry<String, Integer>> entries = getEntries(true);
		int size = entries.size();
		List<String> names = new ArrayList<String>();
		int length = Math.round(size * percentile / 100F);
		for (int i = size; i > size - length; i--) {
			names.add(entries.get(i).getKey());
		}
		return names;
	}

	public static int getMedianMark() {
		List<Entry<String, Integer>> entries = getEntries(true);
		int size = entries.size();
		if (size % 2 == 0) {
			return (entries.get((size / 2) + 1).getValue() + entries.get((size / 2) - 1).getValue()) / 2;
		} else {
			return entries.get((size / 2) + 1).getValue();
		}

	}

}
