package uk.ac.cam.jp775.oop.tick4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GameOfLife {

	private ArrayList<World> mCachedWorlds;
	private World mWorld;
	private PatternStore mStore;

	public GameOfLife(PatternStore ps) {
		mStore = ps;
	}

	public void play() throws Exception {

		String response = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int gen = 0; // newest generation created so far
		System.out.println("Please select a pattern to play (l to list:");
		while (!response.equals("q")) {
			response = in.readLine();
			if (response.equals("f")) {
				if (mWorld == null)
					System.out.println("Please select a pattern to play (l to list):");
				else {
					if (mWorld.getGenerationCount() < gen) {
						mWorld = mCachedWorlds.get(mWorld.getGenerationCount() + 1);
					} else {
						mWorld = copyWorld(true);
						mWorld.nextGeneration();
						mCachedWorlds.add(mWorld);
						gen++;
					}
					print();
				}
			} else if (response.equals("l")) {
				List<Pattern> names = mStore.getPatternsNameSorted();
				int i = 0;
				for (Pattern p : names) {
					System.out.println(i + " " + p.getName() + "  (" + p.getAuthor() + ")");
					i++;
				}
			} else if (response.startsWith("p")) {
				List<Pattern> names = mStore.getPatternsNameSorted();
				int x = Integer.parseInt(response.split(" ")[1]);
				Pattern p = names.get(x);
				if (p.getHeight() * p.getWidth() <= 64) {
					mWorld = new PackedWorld(p);
				} else {
					mWorld = new ArrayWorld(p);
				}
				mCachedWorlds = new ArrayList<World>();
				mCachedWorlds.add(mWorld);
				print();
			} else if (response.startsWith("b")) {
				if (mWorld == null)
					System.out.println("Please select a pattern to play (l to list):");
				else {
					if (mWorld.getGenerationCount() > 0) {
						System.out.println("Gen" + mWorld.getGenerationCount());
						mWorld = mCachedWorlds.get(mWorld.getGenerationCount() - 1);
					}
					print();
				}
			}
		}
	}

	public void print() {
		System.out.println("- " + mWorld.getGenerationCount());
		for (int row = 0; row < mWorld.getHeight(); row++) {
			for (int col = 0; col < mWorld.getWidth(); col++) {
				System.out.print(mWorld.getCell(row, col) ? "#" : "_");
			}
			System.out.println();
		}
	}

	public static void main(String args[]) throws Exception {

		if (args.length != 1) {
			System.out.println("Usage: java GameOfLife <path/url to store>");
			return;
		}

		try {
			PatternStore ps = new PatternStore(args[0]);
			GameOfLife gol = new GameOfLife(ps);
			gol.play();
		} catch (IOException ioe) {
			System.out.println("Failed to load pattern store");
		}

	}

	private World copyWorld(boolean useCloning) {
		if (!useCloning) {
			if (mWorld instanceof PackedWorld) {
				return new PackedWorld((PackedWorld) mWorld);
			}
			return new ArrayWorld((ArrayWorld) mWorld);
		}
		return mWorld.clone();
	}

}
