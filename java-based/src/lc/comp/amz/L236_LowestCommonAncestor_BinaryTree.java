package lc.comp.amz;

public class L236_LowestCommonAncestor_BinaryTree {

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		if (root == p || root == q || root == null)
			return root;

		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);

		if (right == null && left != null)
			return left;
		else if (right != null && left == null)
			return right;

		if ((left == p && right == q) || (left == q && right == p))
			return root;
		return null;
	}
}
