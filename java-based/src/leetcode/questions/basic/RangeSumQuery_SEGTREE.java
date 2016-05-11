package leetcode.questions.basic;

/**
 * 
 * https://leetcode.com/problems/range-sum-query-mutable/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */
public class RangeSumQuery_SEGTREE {

	int[] SEGTREE = null;
	int[] numarr = null;

	public static void main(String[] args) {

		int[] nums = { -28, -39, 53, 65, 11, -56, -65, -39, -43, 97 };
		RangeSumQuery_SEGTREE rsq = new RangeSumQuery_SEGTREE(nums);
		System.out.println(rsq.sumRange(5, 6));
		rsq.update(9, 27);
		System.out.println(rsq.sumRange(2, 3));
		System.out.println(rsq.sumRange(6, 7));
		rsq.update(1, -82);
		rsq.update(3, -72);
		System.out.println(rsq.sumRange(3, 7));
		System.out.println(rsq.sumRange(1, 8));
		rsq.update(5, 13);
		rsq.update(4, -67);
	}

	public RangeSumQuery_SEGTREE(int[] nums) {
		// SEG-TREE can also be of length 2 pow h+1 -1;
		SEGTREE = new int[2 * nums.length - 1];

		numarr = new int[nums.length];
		for (int i = 0; i < nums.length; i++)
			numarr[i] = nums[i];
		createSTREE(0, numarr.length - 1, numarr, 0);
	}

	private int createSTREE(int left, int right, int[] arr, int i) {

		if (left == right) {
			SEGTREE[i] = arr[left];
		} else {
			int mid = (left + right) / 2;
			int lsum = 2 * i + 1 < SEGTREE.length ? createSTREE(left, mid, arr,
					2 * i + 1) : 0;
			int rsum = 2 * i + 2 < SEGTREE.length ? createSTREE(mid + 1, right,
					arr, 2 * i + 2) : 0;
			SEGTREE[i] = lsum + rsum;
		}

		return SEGTREE[i];
	}

	public void update(int i, int val) {
		updateSTREE(0, i, val, 0, numarr.length - 1);
		numarr[i] = val;
	}

	public int updateSTREE(int current, int pos, int val, int left, int right) {

		if (left == right && left == pos) {
			SEGTREE[current] = val;
		} else {
			int mid = (left + right) / 2;
			if (pos <= mid) {
				updateSTREE(2 * current + 1, pos, val, left, mid);
			} else {
				updateSTREE(2 * current + 2, pos, val, mid + 1, right);
			}
			int lsum = 2 * current + 1 < SEGTREE.length ? SEGTREE[2 * current + 1]
					: 0;
			int rsum = 2 * current + 2 < SEGTREE.length ? SEGTREE[2 * current + 2]
					: 0;
			SEGTREE[current] = lsum + rsum;
		}

		return SEGTREE[current];
	}

	public int sumRange(int i, int j) {
		return getSum(0, numarr.length - 1, i, j, 0);
	}

	private int getSum(int left, int right, int i, int j, int index) {

		if (i == left && j == right)
			return SEGTREE[index];

		int mid = (left + right) / 2;
		if (mid < i) {
			return getSum(mid + 1, right, i, j, 2 * index + 2);
		} else if (j <= mid) {
			return getSum(left, mid, i, j, 2 * index + 1);
		} else {
			return getSum(left, mid, i, mid, 2 * index + 1)
					+ getSum(mid + 1, right, mid + 1, j, 2 * index + 2);
		}
	}

}
