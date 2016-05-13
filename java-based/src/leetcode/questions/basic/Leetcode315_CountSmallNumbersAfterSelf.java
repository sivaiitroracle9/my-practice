package leetcode.questions.basic;

/**
 * 
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 */
import java.util.ArrayList;
import java.util.List;

public class Leetcode315_CountSmallNumbersAfterSelf {

	public static void main(String[] args) {
		Leetcode315_CountSmallNumbersAfterSelf l = new Leetcode315_CountSmallNumbersAfterSelf();
		int[] nums = { 5, 2, 6, 1 };
		for (int i : l.countSmaller(nums))
			System.out.print(i + ", ");
	}

	public List<Integer> countSmaller(int[] nums) {
		if (nums.length == 0)
			return new ArrayList<Integer>();
		int[] sorted = new int[nums.length];
		int[] indexes = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			sorted[i] = nums[i];
			indexes[i] = i;
		}
		int[] result = new int[nums.length];
		count_smaller_helper(sorted, indexes, nums, result, 0, nums.length - 1);
		List<Integer> res = new ArrayList<Integer>(result.length);
		for (int r = 0; r < nums.length; r++)
			res.add(result[r]);
		return res;
	}

	public void count_smaller_helper(int[] sorted, int[] indexes, int[] nums,
			int[] result, int left, int right) {

		if (left == right) {
			result[left] = 0;
			return;
		}

		int mid = (left + right) / 2;
		count_smaller_helper(sorted, indexes, nums, result, left, mid);
		count_smaller_helper(sorted, indexes, nums, result, mid + 1, right);

		int[] temp_sorted = new int[right - left + 1];
		int[] temp_indexes = new int[right - left + 1];

		int j = right, ts = right - left, ti = right - left;
		for (int i = mid; i >= left;) {
			while (j >= mid + 1 && sorted[j] >= sorted[i]) {
				temp_sorted[ts--] = sorted[j];
				temp_indexes[ti--] = indexes[j];
				j--;
			}
			result[indexes[i]] += j - mid;

			temp_sorted[ts--] = sorted[i];
			temp_indexes[ti--] = indexes[i];
			i--;
		}

		for (; j >= mid + 1; j--) {
			temp_sorted[ts--] = sorted[j];
			temp_indexes[ti--] = indexes[j];
		}

		for (int i = left; i <= right; i++) {
			sorted[i] = temp_sorted[i - left];
			indexes[i] = temp_indexes[i - left];
		}
	}
}