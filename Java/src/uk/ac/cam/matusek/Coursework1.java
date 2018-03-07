package uk.ac.cam.matusek;

/**
 *
 * @author apupo
 */
import java.util.Scanner;

public class Coursework1 {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int pass = 1;
		int defer = 1;
		int fail = 1;
		while (defer + fail + pass != 120) {
			while (pass % 20 != 0 || pass > 120 || pass < 0) {
				System.out.println(
						"Input the number of passed credits (Accpetable values are 0, 20, 40, 60, 80, 100 and 120.)");
				if (input.hasNextInt()) {
					pass = input.nextInt();
				} else {
					System.out.println("You have not input an integer.");
				}
				if (pass % 20 != 0 || pass > 120 || pass < 0) {
					System.out.println("Wrong input. Read the instructions carefully.");
				}
			}
			while (defer % 20 != 0 || defer > 120 || defer < 0) {
				System.out.println(
						"Input the number of deferred credits (Accpetable values are 0, 20, 40, 60, 80, 100 and 120).");
				if (input.hasNextInt()) {
					defer = input.nextInt();
				} else {
					System.out.println("You have not input an integer.");
					input.nextLine();
				}
				if (defer % 20 != 0 || defer > 120 || defer < 0) {
					System.out.println("Wrong input. Read the instructions carefully.");
				}
			}
			while (fail % 20 != 0 || fail > 120 || fail < 0) {
				System.out.println(
						"Input the number of failed credits (Accpetable values are 0, 20, 40, 60, 80, 100 and 120).");
				if (input.hasNextInt()) {
					fail = input.nextInt();
				} else {
					System.out.println("You have not input an integer.");
					input.nextLine();
				}
				if (fail % 20 != 0 || fail > 120 || fail < 0) {
					System.out.println("Wrong input. Read the instructions carefully.");
				}
			}
			if (defer + fail + pass != 120) {
				System.out.println("The number of inputed credits does not add up to 120, try again.");
				pass = 1;
				defer = 1;
				fail = 1;
			}
		}
		if (pass == 120) {
			System.out.println("Progress");
		} else if (pass == 100) {
			System.out.println("Progress - module trailer");
		} else if (pass == 80) {
			System.out.println("Do not progress - module retriever");
		} else if (pass == 60) {
			System.out.println("Do not progress - module retriever");
		} else if (pass == 40) {
			if (defer == 0) {
				System.out.println("Exclude");
			} else {
				System.out.println("Do not progress - module retriever");
			}
		} else if (pass == 20) {
			if (defer <= 20) {
				System.out.println("Exlude");
			} else {
				System.out.println("Do not progress - module retriever");
			}
		} else {
			if (defer <= 40) {
				System.out.println("Exclude");
			} else {
				System.out.println("Do not progress - module retriever");
			}
		}
	}
}
