package lc.questions.basic;

/**
 * 
 * Time Complexity: O(log(N))
 * 
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 */
public class Leetcode4_MedianOfTwoSortedArrays {

	public static void main(String[] args) {

		Leetcode4_MedianOfTwoSortedArrays lc = new Leetcode4_MedianOfTwoSortedArrays();
		int[] nums1 = { 1, 2 };
		int[] nums2 = { 1, 2 };
		System.out.println(lc.findMedianSortedArrays(nums1, nums2));
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;

		if ((m + n) % 2 != 0) {
			return (double) findKthElement(nums1, nums2, 0, m - 1, 0, n - 1,
					(m + n) / 2);
		}
		int l = findKthElement(nums1, nums2, 0, m - 1, 0, n - 1,
				(m + n - 1) / 2);
		int r = findKthElement(nums1, nums2, 0, m - 1, 0, n - 1, (m + n) / 2);
		return (l + r) / 2.0;

	}

	public int findKthElement(int[] nums1, int[] nums2, int l1, int r1, int l2,
			int r2, int k) {

		int len1 = r1 - l1 + 1;
		int len2 = r2 - l2 + 1;

		if (len1 <= 0 && len2 > k)
			return nums2[l2 + k];

		if (len1 > k && len2 <= 0)
			return nums1[l1 + k];

		if (k == 0) {
			if (nums1[l1] > nums2[l2])
				return nums2[l2];
			else
				return nums1[l1];
		}

		int i = (int) (len1 * (k - 1) / ((double) (len1 + len2)));
		int j = k - 1 - i;
		if (l1 + i > r1 || l2 + j > r2) {
			j = (int) (len2 * (k - 1) / ((double) (len1 + len2)));
			i = k - 1 - j;
		}
		if (nums1[l1 + i] == nums2[l2 + j])
			return nums1[l1 + i];

		if (nums1[l1 + i] > nums2[l2 + j]) {
			l2 = l2 + j + 1;
			k = k - (j + 1);
		} else if (nums1[l1 + i] < nums2[l2 + j]) {
			l1 = l1 + i + 1;
			k = k - (i + 1);
		}
		return findKthElement(nums1, nums2, l1, r1, l2, r2, k);
	}
}
