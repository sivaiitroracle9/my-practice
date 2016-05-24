package leetcode.question.better;

public class Leetcode321_CreateMaxNumber {

	public static void main(String[] args) {
		Leetcode321_CreateMaxNumber lc = new Leetcode321_CreateMaxNumber();
		int[] nums1 = { 6, 7 };
		int[] nums2 = { 6, 0, 4 };
		int k = 5;
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
			if (res1[r1] == res2[r2]) {
				//TODO
			} else if (res1[r1] > res2[r2]) {
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
