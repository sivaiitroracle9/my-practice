package lc.questions.basic;
/**
 * 
 * https://leetcode.com/problems/range-sum-query-mutable/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */
public class RangeSumQuery_BIT {

	int[] BIT = null;
	int[] numarr = null;

	public static void main(String[] args) {

	}

	public RangeSumQuery_BIT(int[] nums) {
		BIT = new int[nums.length + 1];
		numarr = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			update(i, nums[i]);
		}
	}

	public void update(int i, int val) {

		int index = i + 1;
		int prev = numarr[i];
		while (index < BIT.length) {
			BIT[index] = BIT[index] - prev + val;
			index = getNext(index);
		}
		numarr[i] = val;
	}

	public int sumRange(int i, int j) {
        int indexJ = j + 1, sum0J = 0, sum0I = 0;
		while (indexJ > 0) {
			sum0J += BIT[indexJ];
			indexJ = getParent(indexJ);
		}
		
		int indexI = i; // sum from 0 to i-1 not including index i;
		while(indexI > 0) {
		    sum0I += BIT[indexI];
		    indexI = getParent(indexI);
		}
		
		return sum0J-sum0I;
	}

	private int getNext(int index) {
		return index + (index & -index);
	}

	private int getParent(int index) {
		return index - (index & -index);
	}

}
