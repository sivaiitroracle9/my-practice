package leetcode.questions.basic;

public class Leetcode53_MaximumSubarray {

	public static void main(String[] args) {

	}

	private int maxSubArray_Linear(int[] nums) {

		int max = Integer.MIN_VALUE;
		if (isAllNegative(nums)) {
			for (int x : nums) {
				max = Math.max(max, x);
			}
		} else {
			int curr_max = 0;
			for (int x : nums) {
				curr_max += x;
				if (curr_max < 0)
					curr_max = 0;
				if (max < curr_max)
					max = curr_max;
			}
		}
		return max;
	}

	private boolean isAllNegative(int[] nums) {

		boolean notAllNegative = false;
		for (int i : nums) {
			if (i >= 0) {
				notAllNegative = true;
				break;
			}
		}
		return !notAllNegative;
	}

	// O(nlog(n)) Divide and conquer algorithm.
	private int maxSubArray_DC(int[] nums) {
		return max_subarray_dc_helper(nums, 0, nums.length - 1);
	}

	private int max_subarray_dc_helper(int[] nums, int left, int right) {
		if (left == right)
			return nums[left];
		int mid = (left + right) / 2;
		int leftSum = max_subarray_dc_helper(nums, left, mid);
		int rightSum = max_subarray_dc_helper(nums, mid + 1, right);

		int sum = 0, lcross_sum = Integer.MIN_VALUE, rcross_sum = Integer.MIN_VALUE;
		for (int i = mid; i >= 0; i--) {
			sum += nums[i];
			if (lcross_sum < sum)
				lcross_sum = sum;
		}
		sum = 0;
		for (int i = mid + 1; i <= right; i++) {
			sum += nums[i];
			if (rcross_sum < sum)
				rcross_sum = sum;
		}

		int max = lcross_sum + rcross_sum;
		max = Math.max(max, leftSum);
		max = Math.max(max, rightSum);
		return max;
	}
}
