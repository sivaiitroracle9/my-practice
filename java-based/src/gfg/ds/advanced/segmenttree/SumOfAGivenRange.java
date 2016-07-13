package gfg.ds.advanced.segmenttree;

import java.util.Scanner;

public class SumOfAGivenRange {

	static int[] input = null;
	static int[] ST = null;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		input = new int[N];
		int x = (int) Math.ceil(Math.log(N) / Math.log(2));
		int sTreeSize = 2 * ((int) Math.pow(2, x)) - 1;
		ST = new int[sTreeSize];
		for (int i = 0; i < N; i++)
			input[i] = sc.nextInt();
		constructST(0, N - 1, 0);
		System.out.print("SegmentTree: ");
		for (int i = 0; i < sTreeSize; i++)
			System.out.print(ST[i] + " ");
		System.out.println();

		System.out.println("Sum of 1-3 : " + getSumOfRange(1, 3));
	}

	static void constructST(int low, int high, int i) {
		if (low == high) {
			ST[i] = input[low];
			return;
		}

		int mid = (low + high) / 2;
		constructST(low, mid, 2 * i + 1);
		constructST(mid + 1, high, 2 * i + 2);
		ST[i] = ST[2 * i + 1] + ST[2 * i + 2];
	}

	static int getSumOfRange(int qlow, int qhigh) {
		return getSum(0, input.length - 1, qlow, qhigh, 0);
	}

	static int getSum(int low, int high, int qlow, int qhigh, int i) {

		if (low == qlow && high == qhigh)
			return ST[i];

		int mid = (low + high) / 2;

		if (qlow <= mid && qhigh <= mid)
			return getSum(low, mid, qlow, qhigh, 2 * i + 1);

		if (qlow > mid && qhigh > mid)
			return getSum(mid + 1, high, qlow, qhigh, 2 * i + 2);

		int left = getSum(low, mid, qlow, mid, 2 * i + 1);
		int right = getSum(mid + 1, high, mid + 1, qhigh, 2 * i + 2);

		return left + right;
	}
}
