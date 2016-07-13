package lc.comp.amz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class L102_BinaryTreeLevelOrder {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<List<Integer>> levelOrder(TreeNode root) {

		List<List<Integer>> lever_order = new ArrayList<List<Integer>>();
		if (root == null) {
			return lever_order;
		}

		Queue<TreeNode> level = new LinkedList<TreeNode>();
		Queue<TreeNode> next_level = new LinkedList<TreeNode>();
		level.add(root);
		int levelcnt = 0;
		while (!level.isEmpty()) {
			TreeNode node = level.poll();
			if (node.left != null)
				next_level.offer(node.left);
			if (node.right != null)
				next_level.offer(node.right);
			
			if (lever_order.size() < levelcnt + 1)
				lever_order.add(new ArrayList<Integer>());
			lever_order.get(levelcnt).add(node.val);
			
			if(level.isEmpty() && !next_level.isEmpty()) {
				level.addAll(next_level);
				next_level.clear();
				levelcnt++;
			}
		}
		return lever_order;
	}
}
