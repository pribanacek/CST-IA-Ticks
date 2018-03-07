package uk.ac.cam.jp775.oop.tick2;

import java.io.IOException;

public class GameOfLife {

	private World mWorld;

	public GameOfLife(World w) {
		mWorld = w;
	}

	public void play() throws IOException {
		int userResponse = 0;
		while (userResponse != 'q') {
			print();
			userResponse = System.in.read();
			mWorld.nextGeneration();
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

	public static void main(String[] args) throws Exception {

		World w = null;

		if (args[0].equals("--array")) {
			w = new ArrayWorld(args[1]);
		} else if (args[0].equals("--packed")) {
			w = new PackedWorld(args[1]);
		} else {
			w = new ArrayWorld(args[0]);
		}

		GameOfLife gol = new GameOfLife(w);
		gol.play();
	}

}
