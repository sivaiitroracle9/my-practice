package algorithms.tree;

public class BinaryIndexedTree {

	int[] BIT = null;

	public static void main(String[] args) {

		BinaryIndexedTree bit = new BinaryIndexedTree();
		int input[] = { 1, 2, 3, 4, 5, 6, 7 };
		bit.create_binary_indexed_tree(input);
		System.out.println(bit.getSum(0));
		System.out.println(bit.getSum(1));
		System.out.println(bit.getSum(2));
		System.out.println(bit.getSum(3));
		System.out.println(bit.getSum(4));
		System.out.println(bit.getSum(5));
		System.out.println(bit.getSum(6));
	}

	public void create_binary_indexed_tree(int[] nums) {
		BIT = new int[nums.length + 1];

		for (int i = 0; i < nums.length; i++)
			updateBIT(i + 1, nums[i]);
	}

	public int getSum(int i) {
		int sum = 0, index = i + 1;
		while (index > 0) {
			sum += BIT[index];
			index = getParent(index);
		}
		return sum;
	}

	private void updateBIT(int index, int val) {
		while (index < BIT.length) {
			BIT[index] += val;
			index = getNext(index);
		}
	}

	/**
	 * Index which gets affected by its change.
	 * 1. take 2's compliment
	 * 2. and with original index
	 * 3. and add the result to original index.
	 * 
	 * @param index
	 * @return
	 */
	
	private int getNext(int index) {
		return index + (index & -index);
	}

	/**
	 * Index which gets parent node.
	 * 1. take 2's compliment
	 * 2. and with original index
	 * 3. and subtract the result from the original index.
	 * 
	 * @param index
	 * @return
	 */
	private int getParent(int index) {
		return index - (index & -index);
	}

}
