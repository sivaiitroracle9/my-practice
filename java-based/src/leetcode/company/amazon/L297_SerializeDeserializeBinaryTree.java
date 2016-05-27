package leetcode.company.amazon;

public class L297_SerializeDeserializeBinaryTree {

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		public TreeNode(int x) {
			// TODO Auto-generated constructor stub
			val = x;
		}
	}

	public static void main(String[] args) {
		L297_SerializeDeserializeBinaryTree lc = new L297_SerializeDeserializeBinaryTree();

		TreeNode root = lc.new TreeNode(1);
		root.left = lc.new TreeNode(2);
		root.right = lc.new TreeNode(3);
		String data = lc.serialize(root);
		TreeNode newroot = lc.deserialize(data);
	}

	private String NNODE = "X";

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		serialize(root, sb);
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public void serialize(TreeNode root, StringBuilder sb) {
		if (root == null) {
			sb.append(NNODE + ",");
			return;
		}

		sb.append(root.val + ",");
		serialize(root.left, sb);
		serialize(root.right, sb);

	}

	class Counter {
		int val = 0;
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		String[] datastr = data.split(",");
		return deserialize(datastr, new Counter());
	}

	public TreeNode deserialize(String[] datastr, Counter index) {

		if (index.val >= datastr.length || datastr[index.val].equals("X")) {
			return null;
		}

		TreeNode root = new TreeNode(Integer.parseInt(datastr[index.val]));
		++index.val;
		TreeNode left = deserialize(datastr, index);
		++index.val;
		TreeNode right = deserialize(datastr, index);

		root.left = left;
		root.right = right;
		return root;
	}

}
