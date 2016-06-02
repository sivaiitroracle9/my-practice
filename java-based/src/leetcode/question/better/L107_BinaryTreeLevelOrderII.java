package leetcode.question.better;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class L107_BinaryTreeLevelOrderII {

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {

		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (root == null)
			return result;

		Map<Integer, List<TreeNode>> levelorder = new HashMap<Integer, List<TreeNode>>();
		Queue<TreeNode> currlevel = new LinkedList<TreeNode>();
		Queue<TreeNode> nextlevel = new LinkedList<TreeNode>();
		currlevel.offer(root);
		int level = 1;
		levelorder.put(level, new LinkedList<TreeNode>(currlevel));
		while (!currlevel.isEmpty()) {
			TreeNode node = currlevel.poll();

			if (node.left != null)
				nextlevel.offer(node.left);
			if (node.right != null)
				nextlevel.offer(node.right);

			if (currlevel.isEmpty()) {
				level++;
				currlevel.addAll(nextlevel);
				nextlevel.clear();
				if (!currlevel.isEmpty())
					levelorder.put(level, new LinkedList<TreeNode>(currlevel));
			}

		}

		for (int i = levelorder.size(); i >= 1; i--) {
			if(!levelorder.isEmpty()) {
				List<Integer> res = new ArrayList<Integer>(levelorder.get(i).size());
				for(TreeNode node : levelorder.get(i)) res.add(node.val);
				result.add(res);
			}
		}
		return result;
	}

}
