package leetcode.questions.basic;

public class MedianOfTwoSortedArrays {
	public static void main(String[] args) {
		MedianOfTwoSortedArrays ms = new MedianOfTwoSortedArrays();
		
	}

	// O(log(m+n)) Implementation.
	public int findKth(int[] A, int[] B, int k, int aStart, int aEnd,
			int bStart, int bEnd) {

		int aLen = aEnd - aStart + 1;
		int bLen = bEnd - bStart + 1;

		if (aLen == 0)
			return B[bStart + k];

		if (bLen == 0)
			return A[aStart + k];

		if (k == 0)
			return (A[aStart] < B[bStart]) ? A[aStart] : B[bStart];

		int aMidScale = aLen * k / (aLen + bLen);
		int bMidScale = k - aMidScale - 1;

		int aMid = aStart + aMidScale;
		int bMid = bStart + bMidScale;

		if (A[aMid] > B[bMid]) {
			k = k - (bMid - bStart + 1);
			aEnd = aMid;
			bStart = bMid + 1;
		} else {
			k = k - (aMid - aStart + 1);
			bEnd = bMid;
			aStart = aMid + 1;
		}

		return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
	}
}
