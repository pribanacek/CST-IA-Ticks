package uk.ac.cam.jp775.oop.supo2;

public class ConstructorChain {

	public int x = 0;

	public void ConstructorChain() {
		x = 7;
	}

	public static class A {
		A() {
			System.out.println("A");
		}
	}

	public static class B extends A {
		B() {
			System.out.println("B");
		}
	}

	public static class C extends B {

		C() {
			System.out.println("C");
		}
	}

	public static void main(String[] args) {
		C c = new C();
		ConstructorChain t = new ConstructorChain();
		System.out.println(t.x);
	}

}
