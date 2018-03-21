package uk.ac.cam.jp775.oop.supo3;

import java.util.Arrays;
import java.util.List;

public class RetValTest {
	public static String sEmail = "";

	public static class NoSentenceException extends Exception {
	}

	public static class EmailNotFoundException extends Exception {
	}

	public static void extractCamEmail(String sentence) throws NoSentenceException, EmailNotFoundException {
		if (sentence == null || sentence.length() == 0)
			throw new NoSentenceException();
		String tokens[] = sentence.split(" "); // split into tokens
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].endsWith("@cam.ac.uk")) {
				sEmail = tokens[i];
				return;
			}
		}
		throw new EmailNotFoundException();
	}

	public static void main(String[] args) {
		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

		myList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).sorted().forEach(System.out::println);

		try {
			RetValTest.extractCamEmail("My email is rkh23@cam.ac.uk");
			System.out.println("Success: " + RetValTest.sEmail);
		} catch (NoSentenceException e) {
			System.out.println("Supplied string empty");
			e.printStackTrace();
		} catch (EmailNotFoundException e) {
			System.out.println("No @cam address in supplied string");
			e.printStackTrace();
		}
	}
}
