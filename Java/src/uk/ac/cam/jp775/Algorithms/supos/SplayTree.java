package uk.ac.cam.jp775.Algorithms.supos;

public class SplayTree {

	private Node root;

	public static class Node {
		private int value;
		private Node left, right;

		public Node(int value) {
			this.value = value;
		}
	}

	public boolean find(Node n, int x) { // start with n = root
		if (n == null) {
			return false;
		} else if (x == n.value) {
			root = splay(root, x);
			return true;
		} else if (x > n.value) {
			return find(n.right, x);
		}
		return find(n.left, x);
	}

	public void insert(Node n, int x) { // start with n = root
		if (x > n.value) {
			if (n.right != null) {
				insert(n.right, x);
			} else {
				n.right = new Node(x);
				root = splay(root, x);
			}
		} else if (x < n.value) {
			if (n.left != null) {
				insert(n.left, x);
			} else {
				n.left = new Node(x);
				root = splay(root, x);
			}
		}
	}

	private Node rotateLeft(Node p) {
		Node x = p.right;
		p.right = x.left;
		x.left = p;
		return x;
	}

	private Node rotateRight(Node p) {
		Node x = p.left;
		p.left = x.right;
		x.right = p;
		return x;
	}

	private Node splay(Node n, int v) {
		if (n == null)
			return null;

		if (v < n.value) {
			if (n.left == null) {
				return n;
			}
			if (v < n.left.value) {
				n.left.left = splay(n.left.left, v);
				n = rotateRight(n);
			} else if (v > n.left.value) {
				n.left.right = splay(n.left.right, v);
				if (n.left.right != null)
					n.left = rotateLeft(n.left);
			}

			if (n.left == null)
				return n;
			else
				return rotateRight(n);
		}

		else if (v > n.value) {
			if (n.right == null) {
				return n;
			}

			if (v < n.right.value) {
				n.right.left = splay(n.right.left, v);
				if (n.right.left != null)
					n.right = rotateRight(n.right);
			} else if (v > n.right.value) {
				n.right.right = splay(n.right.right, v);
				n = rotateLeft(n);
			}

			if (n.right == null)
				return n;
			else
				return rotateLeft(n);
		}
		return n;
	}
}
