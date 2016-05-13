package leetcode.questions.basic;
/**
 * 
 * https://leetcode.com/problems/count-of-range-sum/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 */

public class CountRangeSum_mergesort_Leetcode327 {

	public static void main(String[] args) {

		int[] nums = { -2, 5, -1 };
		int lower = -2;
		int upper = 2;
		long[] sums = new long[nums.length + 1];
		for (int i = 0; i < nums.length; i++)
			sums[i + 1] = sums[i] + nums[i];
		System.out.println(count_range_sum(sums, 0, nums.length + 1, upper,
				lower));
	}

	public static int count_range_sum(long[] sums, int left, int right,
			int upper, int lower) {

		// Here the count value for [left, right) i.e [left, right-1]
		// which gives count by checking the values sums(j)-sums(i) for
		// every i, j belongs to [left, right-1]
		// if the right-left <=1, the checkable ranges will be [left, left-1] or
		// [left, left] which will be 0 for checking sum(j) -sum(i);
		if (right - left <= 1) {
			return 0;
		}

		int mid = (left + right) / 2;
		// Here we have to take upto mid.
		// [left, mid) and [mid, right) for sums array access.
		int count = count_range_sum(sums, left, mid, upper, lower)
				+ count_range_sum(sums, mid, right, upper, lower);

		long[] temp_cache = new long[right - left];
		int j = mid, k = mid, r = 0, t = mid;
		for (int i = left; i < mid; i++) {
			while (j < right && sums[j] - sums[i] < lower)
				j++;
			while (k < right && sums[k] - sums[i] <= upper)
				k++;
			count += k - j;

			while (t < right && sums[t] < sums[i])
				temp_cache[r++] = sums[t++];
			temp_cache[r++] = sums[i];
		}
		for (int x = 0; x < r; x++) {
			sums[left + x] = temp_cache[x];
		}
		return count;
	}
}
