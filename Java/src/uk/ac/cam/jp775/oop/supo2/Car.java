package uk.ac.cam.jp775.oop.supo2;

public class Car implements Comparable<Car> {

	private String manufacturer;
	private int age;

	@Override
	public int compareTo(Car c) {
		if (manufacturer.compareTo(c.getManufacturer()) != 0) {
			return manufacturer.compareTo(c.getManufacturer());
		} else {
			return age - c.getAge();
		}
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public int getAge() {
		return age;
	}

}
