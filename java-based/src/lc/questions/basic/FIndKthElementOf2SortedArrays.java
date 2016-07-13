package lc.questions.basic;

public class FIndKthElementOf2SortedArrays {

	public static void main(String[] args) {
		int[] A1 = { 1, 3, 5, 19, 35, 54 };
		int[] A2 = { 1, 5, 20, 53, 54 };
		int k = 2;
		System.out.println(findKthElement(A1, A2, 0, A1.length - 1, 0,
				A2.length - 1, k));
	}

	public static int findKthElement(int[] A1, int[] A2, int l1, int r1,
			int l2, int r2, int k) {

		if(k==0) {
			if(l1 <= r1 && l2<=r2) {
				if(A1[l1] < A2[l2]) return A1[l1];
				else return A2[l2];
			}
		}
		
		int A1_len = r1 - l1 + 1;
		int A2_len = r2 - l2 + 1;

		if (A1_len == 0) {
			return A2[l2 + k];
		}

		if (A2_len == 0) {
			return A1[l1 + k];
		}

		int A1_i = (int) (A1_len / ((A1_len + A2_len) * 1.0) * (k - 1));
		int A2_i = k - 1 - A1_i;
		if (A1_i + l1 > A1.length - 1 || A2_i + l2 > A2.length - 1) {
			A2_i = (int) (A2_len / ((A1_len + A2_len) * 1.0) * (k - 1));
			A1_i = k - 1 - A2_i;
		}

		if (A1[l1 + A1_i] == A2[l2 + A2_i]) {
			return A1[l1 + A1_i];
		} else if (A1[l1 + A1_i] < A2[l2 + A2_i]) {
			l1 = l1 + A1_i + 1;
			k = k - A1_i - 1;
		} else {
			l2 = l2 + A2_i + 1;
			k = k - A2_i - 1;
		}

		return findKthElement(A1, A2, l1, r1, l2, r2, k);
	}
}
