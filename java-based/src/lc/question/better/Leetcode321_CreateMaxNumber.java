package leetcode.question.better;

/**
 * https://leetcode.com/problems/create-maximum-number/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 *
 */
public class Leetcode321_CreateMaxNumber {

	public static void main(String[] args) {
		Leetcode321_CreateMaxNumber lc = new Leetcode321_CreateMaxNumber();
		int[] nums1 = { 3, 9 };
		int[] nums2 = { 8, 9 };
		int k = 3;
		int[] result = lc.maxNumber(nums1, nums2, k);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + ", ");
		}

	}

	public int[] maxNumber(int[] nums1, int[] nums2, int k) {

		int M = nums1.length;
		int N = nums2.length;

		int[] result = new int[k];
		if (M + N < k)
			return result;

		for (int i = 0; i <= M && i <= k; i++) {
			if (k - i <= N) {
				int[] res1 = maxArray(nums1, i);
				int[] res2 = maxArray(nums2, k - i);
				int[] res = merge(res1, res2);

				boolean valid = false;
				for (int r = 0; r < k; r++) {
					if (result[r] == res[r])
						continue;
					else if (res[r] > result[r]) {
						valid = true;
					} else {
						valid = false;
					}
					break;
				}
				if (valid)
					result = res;
			}
		}
		return result;
	}

	public int[] merge(int[] res1, int[] res2) {
		int[] result = new int[res1.length + res2.length];

		int i = 0, r1 = 0, r2 = 0;
		while (i < result.length && r1 < res1.length && r2 < res2.length) {
			boolean take_from_res1 = false;
			if (res1[r1] == res2[r2]) {
				// TODO
				int rr1 = r1 + 1, rr2 = r2 + 1;
				while (rr1 < res1.length && rr2 < res2.length) {
					if (res1[rr1] == res2[rr2]) {
						rr1++;
						rr2++;
						continue;
					}

					if (res1[rr1] > res2[rr2]) {
						take_from_res1 = true;
					}
					break;
				}

				if (rr2 == res2.length && rr1 < res1.length) {
					take_from_res1 = true;
				}

			} else if (res1[r1] > res2[r2]) {
				take_from_res1 = true;
			}

			if (take_from_res1) {
				result[i++] = res1[r1++];
			} else {
				result[i++] = res2[r2++];
			}
		}

		while (i < result.length && r1 < res1.length) {
			result[i++] = res1[r1++];
		}

		while (i < result.length && r2 < res2.length) {
			result[i++] = res2[r2++];
		}
		return result;
	}

	public int[] maxArray(int[] nums, int k) {
		int[] res = new int[k];
		int n = nums.length;

		for (int i = 0, j = 0; i < n; i++) {
			while (n - i + j > k && j > 0 && res[j - 1] < nums[i])
				// good logic in adding the new element into the k-window.
				j--;
			if (j < k)
				res[j++] = nums[i];
		}
		return res;
	}

}
