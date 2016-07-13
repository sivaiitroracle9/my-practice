package gfg.ds.heap;

import java.util.Deque;
import java.util.LinkedList;

import sun.misc.Queue;

public class FindMaxInWindow {

	static int MAX_HEAP_SIZE = 128;

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 1, 4, 5, 2, 3, 6 };
		int k = 3;

		/**
		 * NLogK Algorithm. (Self Balancing Tree. AVL)
		 */
		// findMax(arr, k);

		/**
		 * O(N) Algorithm. Dequeue
		 */
		findMax_On(arr, k);
	}

	public static void findMax_On(int[] arr, int k) {

		Deque<Integer> de = new LinkedList<Integer>();

		for (int i = 0; i <= k - 2; i++) {

			while (!de.isEmpty() && arr[de.peekLast()] < arr[i])
				de.removeLast();

			de.addLast(i);
		}

		for (int i = k - 1; i < arr.length; i++) {

			while (!de.isEmpty() && de.peekFirst() < i + 1 - k)
				de.removeFirst();
			while (!de.isEmpty() && arr[de.peekLast()] < arr[i])
				de.removeLast();
			de.addLast(i);

			System.out.print(arr[de.peekFirst()] + " ");
		}

	}

	public static void findMax(int[] arr, int k) {
		AVLTree bst = new AVLTree();
		for (int i = 0; i <= k - 2; i++) {
			bst.insert(arr[i]);
		}

		for (int i = k - 1; i < arr.length; i++) {
			if (bst.size == k) {
				bst.delete(arr[i - k]);
			}
			bst.insert(arr[i]);
			System.out.print(bst.getMax() + " ");
		}
	}
}

class AVLTree {

	public int size = 0;

	private Node root = null;

	public int getMax() {
		if (root != null) {
			return helper(root);
		}
		return -1;
	}

	public int getSize() {
		return size;
	}

	public void insert(int val) {
		root = insert(root, val);
	}

	public void delete(int val) {
		root = delete(root, val);
	}

	private int helper(Node node) {
		if (node.right != null) {
			return helper(node.right);
		}
		return node.data;
	}

	private Node delete(Node node, int val) {

		if (node == null) {
			return node;
		}

		if (node.data > val) {
			node.left = delete(node.left, val);
		} else if (node.data < val) {
			node.right = delete(node.right, val);
		} else {

			if (node.count > 1) {
				size--;
				node.count = node.count - 1;
				return node;
			}

			if (node.left == null || node.right == null) {
				Node temp = null;
				if (node.left == null) {
					temp = node.right;
				} else {
					temp = node.left;
				}

				// No Child.
				if (temp == null) {
					node = null;
				} else {
					node = temp;
				}

			} else {
				Node temp = findNextMin(node);
				node.data = temp.data;
				node.right = delete(node.right, temp.data);
			}
		}

		if (node == null) {
			size--;
			return node;
		}

		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		int balance = getBalance(node);

		// Left - Left
		if (balance > 1 && getBalance(node.left) >= 0) {
			return rotateRight(node);
		}

		// Left - Right
		if (balance > 1 && getBalance(node.left) < 0) {
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}

		// Right - Right
		if (balance < -1 && getBalance(node.right) <= 0) {
			return rotateLeft(node);
		}

		// Right - Left
		if (balance < -1 && getBalance(node.right) > 0) {
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}

		return node;
	}

	private int getBalance(Node node) {
		if (node == null)
			return 0;

		return getHeight(node.left) - getHeight(node.right);
	}

	private int getHeight(Node node) {
		if (node == null)
			return 0;
		return node.height;
	}

	private Node findNextMin(Node node) {
		if (node.left != null) {
			return findNextMin(node.left);
		}
		return node;
	}

	private Node insert(Node node, int val) {

		if (node == null) {
			size++;
			return new Node(val);
		}

		if (node.data > val) {
			node.left = insert(node.left, val);
		} else if (node.data < val) {
			node.right = insert(node.right, val);
		} else {
			size++;
			node.count += 1;
			return node;
		}

		int lH = (node.left != null) ? node.left.height : 0;
		int rH = (node.right != null) ? node.right.height : 0;
		node.height = Math.max(lH, rH) + 1;

		int balance = lH - rH;

		// Left - Left
		if (balance > 1 && node.left != null && val < node.left.data) {
			return rotateRight(node);
		}

		// Left - Right
		if (balance > 1 && node.left != null && val > node.left.data) {
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}

		// Right - Right
		if (balance < -1 && node.right != null && val > node.right.data) {
			return rotateLeft(node);
		}

		// Right - Left
		if (balance < -1 && node.right != null && val < node.right.data) {
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}

		return node;
	}

	public void printPreorder(Node root) {
		preOrder(root);
		System.out.println();
	}

	private void preOrder(Node root) {
		System.out.print(root.data + " ");
		if (root.left != null)
			preOrder(root.left);
		if (root.right != null)
			preOrder(root.right);
	}

	private Node rotateRight(Node node) {
		Node left = node.left;
		Node left_right = node.left.right;
		node.left = left_right;
		node.height = Math.max(node.left != null ? node.left.height : 0,
				node.right != null ? node.right.height : 0) + 1;
		left.right = node;
		left.height = Math.max(left.left != null ? left.left.height : 0,
				left.right != null ? left.right.height : 0) + 1;
		return left;
	}

	private Node rotateLeft(Node node) {
		Node right = node.right;
		Node right_left = node.right.left;
		node.right = right_left;
		right.left = node;

		node.height = Math.max(node.left != null ? node.left.height : 0,
				node.right != null ? node.right.height : 0) + 1;
		right.height = Math.max(right.left != null ? right.left.height : 0,
				right.right != null ? right.right.height : 0) + 1;
		return right;
	}

	class Node {
		private Node left;
		private Node right;
		private int data;
		private int height = 1;
		private int count = 1;

		public Node(int val) {
			this.data = val;
		}
	}
}