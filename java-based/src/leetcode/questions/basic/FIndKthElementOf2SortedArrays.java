package leetcode.questions.basic;

public class FIndKthElementOf2SortedArrays {

	public static void main(String[] args) {
		int[] A1 = {1};
		int[] A2 = {1};
		int k = 2;
		System.out.println(findKthElement(A1, A2, 0, A1.length - 1, 0,
				A2.length - 1, k));
	}

	public static int findKthElement(int[] A1, int[] A2, int l1, int r1,
			int l2, int r2, int k) {

		if (k==1) return Math.min(A1[0], A2[0]); 
		
		int m1 = (l1 + r1) / 2;
		int m2 = (l2 + r2) / 2;

		if (m1 + m2 == k - 1) {
			if (m1 > 0 && A1[m1 - 1] >= A2[m2] && A2[m2] >= A1[m1]) {
				return A2[m2];
			}

			if (m2 > 0 && A2[m2 - 1] >= A1[m1] && A1[m1] >= A2[m2]) {
				return A1[m1];
			}
		}

		if (m1 + m2 > k - 1) {
			if (A1[m1] < A2[m2]) {
				r2 = m2;
				r1 = A2[m2] <= A1[r1] ? m2 : r1;
			} else {
				r1 = m1;
				r2 = A1[m1] <= A2[r2] ? m1 : r2;
			}
		}

		if (m1 + m2 < k - 1) {
			if (A1[m1] < A2[m2]) {
				l1 = m1;
				l2 = A2[l2] <= A1[m1] ? m1 : l2;
			} else {
				l2 = m2;
				l1 = A1[l1] <= A2[m2] ? m2 : l1;
			}
		}

		return findKthElement(A1, A2, l1, r1, l2, r2, k);
	}
}
