package lc.question.better;

import java.util.LinkedList;
import java.util.Queue;

public class L111_MinDepthBinaryTree {

	public static void main(String[] args) {

	}

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int minDepth(TreeNode root) {

		if (root == null)
			return 0;

		Queue<TreeNode> currlevel = new LinkedList<L111_MinDepthBinaryTree.TreeNode>();
		Queue<TreeNode> nextlevel = new LinkedList<L111_MinDepthBinaryTree.TreeNode>();
		currlevel.offer(root);
		int level = 1;
		while (!currlevel.isEmpty()) {
			TreeNode node = currlevel.poll();
			if (node.left == null && node.right == null)
				return level;

			if (node.left != null)
				nextlevel.offer(node.left);
			if (node.right != null)
				nextlevel.offer(node.right);

			if (currlevel.isEmpty()) {
				level++;
				currlevel.addAll(nextlevel);
				nextlevel.clear();
			}

		}
		return 0;
	}

}
