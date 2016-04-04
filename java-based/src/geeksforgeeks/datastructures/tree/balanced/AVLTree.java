package geeksforgeeks.datastructures.tree.balanced;

public class AVLTree {

	public static void main(String[] args) {
		int[] arr = { 10, 20, 30, 40, 50, 25 };

		AVLTree avlTree = new AVLTree();
		Node root = null;
		for (int in : arr) {
			root = avlTree.insert(root, in);
			avlTree.printPreorder(root);
		}
	}

	public Node insert(Node node, int val) {

		if (node == null) {
			return new Node(val);
		}

		if (node.data >= val) {
			node.left = insert(node.left, val);
		} else {
			node.right = insert(node.right, val);
		}

		int lH = (node.left != null) ? node.left.height: 0;
		int rH = (node.right != null) ? node.right.height: 0;
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
		node.height = Math.max(node.left!=null ? node.left.height : 0, 
				node.right!=null ? node.right.height : 0) + 1;
		left.right = node;
		left.height = Math.max(left.left!=null ? left.left.height : 0, 
				left.right!=null ? left.right.height : 0) + 1;
		return left;
	}

	private Node rotateLeft(Node node) {
		Node right = node.right;
		Node right_left = node.right.left;
		node.right = right_left;
		right.left = node;
		
		node.height = Math.max(node.left!=null ? node.left.height : 0, 
				node.right!=null ? node.right.height : 0) + 1;
		right.height = Math.max(right.left!=null ? right.left.height : 0, 
				right.right!=null ? right.right.height : 0) + 1;
		return right;
	}

	class Node {
		private Node left;
		private Node right;
		private int data;
		private int height = 1;

		public Node(int val) {
			this.data = val;
		}
	}
}
