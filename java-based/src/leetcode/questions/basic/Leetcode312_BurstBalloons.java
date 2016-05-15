package leetcode.questions.basic;

/**
 * https://leetcode.com/problems/burst-balloons/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 *
 */
public class Leetcode312_BurstBalloons {

	public static void main(String[] args) {
		int[] nums = { 3, 1, 5, 8 };

	}

	public int maxCoins_DP(int[] nums) {
		int[] NUMS = new int[nums.length + 2];
		int r = 1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0)
				NUMS[r++] = nums[i];
		}
		NUMS[0] = NUMS[r] = 1;

		int[][] dp = new int[NUMS.length][NUMS.length];
		for (int len = 2; len < NUMS.length; len++) {
			for (int left = 0; left < NUMS.length - len; left++) {

				int right = left + len;

				int LRans = 0;
				for (int i = left + 1; i < right; i++) {
					LRans = Math.max(LRans, dp[left][i] + NUMS[left] * NUMS[i]
							* NUMS[right] + dp[i][right]);
				}
				dp[left][right] = LRans;

			}
		}
		return dp[0][NUMS.length - 1];
	}

	public int maxCoins_DC(int[] nums) {
		int[] NUMS = new int[nums.length + 2];
		int r = 1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0)
				NUMS[r++] = nums[i];
		}
		NUMS[0] = NUMS[r] = 1;
		int[][] max_table = new int[NUMS.length][NUMS.length];
		return maxCoins_DC(NUMS, max_table, 0, NUMS.length - 1);
	}

	public int maxCoins_DC(int[] NUMS, int[][] max_table, int left, int right) {

		if (NUMS.length <= 2 || left + 1 == right)
			return 0;
		if (max_table[left][right] > 0)
			return max_table[left][right];

		int LRans = 0;
		for (int i = left + 1; i < right; i++) {
			LRans = Math.max(
					LRans,
					maxCoins_DC(NUMS, max_table, left, i) + NUMS[left]
							* NUMS[i] * NUMS[right]
							+ maxCoins_DC(NUMS, max_table, i, right));
		}

		max_table[left][right] = LRans;
		return LRans;
	}
}
