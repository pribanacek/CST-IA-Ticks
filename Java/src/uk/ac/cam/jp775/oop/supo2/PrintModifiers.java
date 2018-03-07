package uk.ac.cam.jp775.oop.supo2;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import uk.ac.cam.jp775.oop.tick1.ArrayLife;

public class PrintModifiers {

	Map<String, String> m = new HashMap<String, String>();

	public void m() {
	}

	public static void main(String[] args) {
		String s1 = new String("Hi");
		String s2 = new String("Hi");
		String s3 = "Hi";
		String s4 = "Hi";

		Class life = ArrayLife.class;
		Class pattern = Pattern.class;

		Method[] m1 = life.getMethods();
		Method[] m2 = pattern.getMethods();
		//
		// for (int i = 0; i < m1.length; i++) {
		// System.out.println(Modifier.toString(m1[i].getModifiers()) + " " +
		// m1[i].getName());
		// }
		// for (int i = 0; i < m2.length; i++) {
		// System.out.println(Modifier.toString(m2[i].getModifiers()) + " " +
		// m2[i].getName());
		// }

	}

}
