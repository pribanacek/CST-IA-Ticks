package uk.ac.cam.jp775.oop.supo1;

public class Vector2D {

	public float x;
	public float y;

	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void add(Vector2D v) {
		this.x += v.x;
		this.y += v.y;
	}

	public float scalarProduct(Vector2D v) {
		return this.x * v.x + this.y + v.y;
	}

	public void normalise() {
		float a = magnitude();
		this.x /= a;
		this.y /= a;
	}

	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y);
	}

}
