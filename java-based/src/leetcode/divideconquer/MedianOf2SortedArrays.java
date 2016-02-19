package leetcode.divideconquer;

import java.util.Scanner;

public class MedianOf2SortedArrays {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int[] arr1 = new int[M];
		for (int i = 0; i < M; i++)
			arr1[i] = sc.nextInt();
		int N = sc.nextInt();
		int[] arr2 = new int[N];
		for (int i = 0; i < N; i++)
			arr2[i] = sc.nextInt();
		int k = sc.nextInt();
		System.out.println(smallestKthElement(arr1, arr2, k));
	}

	private static int smallestKthElement(int[] arr1, int[] arr2, int k) {
		return binarySearch(0, arr1.length - 1, 0, arr2.length - 1, k, arr1, arr2);
	}

	private static int binarySearch(int l1, int r1, int l2, int r2, int k, int[] arr1, int[] arr2) {
		if (k == 1) {
			return Math.min(arr1[0], arr2[0]);
		}
		int m1 = (l1 + r1) / 2;
		int m2 = (l2 + r2) / 2;

		if (m1 + m2 == k - 1) {
			if (m2 > 0 && arr1[m1] > arr2[m2 - 1] && arr1[m1] < arr2[m2]) {
				return arr1[m1];
			}

			if (m1 > 0 && arr2[m2] > arr1[m1 - 1] && arr2[m2] < arr1[m1]) {
				return arr2[m2];
			}
		} else if (m1 + m2 < k - 1) {
			if (arr1[m1] <= arr2[m2]) {
				l1 = m1;
				if (arr2[l2] <= arr1[m1])
					l2 = m1;
			} else {
				l2 = m2;
				if (arr1[l1] <= arr2[m2])
					l1 = m2;
			}

		} else {
			if (arr1[m1] <= arr2[m2]) {
				r2 = m2;
				if (arr1[r1] <= arr2[m2])
					r1 = m2;
			} else {
				r1 = m1;
				if (arr2[r2] <= arr1[m1])
					r2 = m1;
			}
		}

		return binarySearch(l1, r1, l2, r2, k, arr1, arr2);
	}
}
