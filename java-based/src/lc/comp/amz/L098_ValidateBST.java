package leetcode.company.amazon;

public class L098_ValidateBST {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public boolean isValidBST(TreeNode root) {

		return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public boolean isValidBST(TreeNode root, long min, long max) {
		if (root == null)
			return true;

		if (root.val <= min || root.val >= max) {
			return false;
		}

		if ((root.left != null && root.val <= root.left.val)
				|| (root.right != null && root.val >= root.right.val))
			return false;

		return isValidBST(root.left, min, root.val)
				&& isValidBST(root.right, root.val, max);
	}
}
