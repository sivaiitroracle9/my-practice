package lc.comp.amz;

public class L238_ProductOfArrayExceptSelf {

	// Constast space
	public int[] productExceptSelf(int[] nums) {

		if (nums.length == 0)
			return new int[0];
		int[] result = new int[nums.length];
		result[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			result[i] = nums[i - 1] * result[i - 1];
		}

		int right = nums[nums.length - 1];
		for (int i = nums.length - 2; i >= 0; i--) {
			result[i] = result[i] * right;
			right = right * nums[i];
		}
		return result;
	}

}
