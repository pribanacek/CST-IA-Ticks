package uk.ac.cam.jp775.oop.supo2;

public class Point3D implements Comparable<Point3D> {

	private double x;
	private double y;
	private double z;

	@Override
	public int compareTo(Point3D p) {
		if (z != p.getZ()) {
			return z > p.getZ() ? 1 : -1;
		}
		if (y != p.getY()) {
			return y > p.getY() ? 1 : -1;
		}
		if (x != p.getX()) {
			return x > p.getX() ? 1 : -1;
		}
		return 0;
	}

	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

}
