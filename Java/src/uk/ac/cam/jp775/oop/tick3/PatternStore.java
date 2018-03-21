package uk.ac.cam.jp775.oop.tick3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PatternStore {

	private List<Pattern> mPatterns = new LinkedList<>();
	private Map<String, List<Pattern>> mMapAuths = new HashMap<>();
	private Map<String, Pattern> mMapName = new HashMap<>();

	public PatternStore(String source) throws IOException {
		if (source.startsWith("http://")) {
			loadFromURL(source);
		} else {
			loadFromDisk(source);
		}
	}

	public PatternStore(Reader source) throws IOException {
		load(source);
	}

	private void load(Reader r) throws IOException {
		BufferedReader b = new BufferedReader(r);
		String line = b.readLine();
		while (line != null) {
			Pattern p;
			try {
				p = new Pattern(line);
				mPatterns.add(p);
				if (mMapAuths.get(p.getAuthor()) == null) {
					mMapAuths.put(p.getAuthor(), new ArrayList<Pattern>());
				}
				mMapAuths.get(p.getAuthor()).add(p);

				mMapName.put(p.getName(), p);

			} catch (PatternFormatException e) {
				System.out.println(line);
				e.printStackTrace();
			}
			line = b.readLine();
		}
	}

	private void loadFromURL(String url) throws IOException {
		URL dest = new URL(url);
		URLConnection c = dest.openConnection();
		Reader r = new java.io.InputStreamReader(c.getInputStream());
		load(r);
	}

	private void loadFromDisk(String filename) throws IOException {
		Reader r = new FileReader(filename);
		load(r);
	}

	public List<Pattern> getPatternsNameSorted() {
		Collections.sort(mPatterns);
		return new ArrayList<Pattern>(mPatterns);
	}

	public List<Pattern> getPatternsAuthorSorted() {
		Collections.sort(mPatterns, new Comparator<Pattern>() {
			@Override
			public int compare(Pattern p1, Pattern p2) {
				int x = p1.getAuthor().compareTo(p2.getAuthor());
				return x != 0 ? x : p1.compareTo(p2);
			}
		});
		return new ArrayList<Pattern>(mPatterns);
	}

	public List<Pattern> getPatternsByAuthor(String author) throws PatternNotFound {
		List<Pattern> list = mMapAuths.get(author);
		if (list == null) {
			throw new PatternNotFound();
		}
		Collections.sort(list);
		return new ArrayList<Pattern>(list);
	}

	public Pattern getPatternByName(String name) throws PatternNotFound {
		Pattern p = mMapName.get(name);
		if (p == null) {
			throw new PatternNotFound();
		}
		return p;
	}

	public List<String> getPatternAuthors() {
		Set<String> authors = mMapAuths.keySet();
		List<String> list = new ArrayList<String>();
		for (String s : authors) {
			list.add(s);
		}
		Collections.sort(list);
		return list;
	}

	public List<String> getPatternNames() {
		Set<String> names = mMapName.keySet();
		List<String> list = new ArrayList<String>();
		for (String s : names) {
			list.add(s);
		}
		Collections.sort(list);
		return list;
	}
}