package gfg.ds.advanced.segmenttree;

import java.util.Scanner;

public class RangeMinimumQuery {

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

		System.out.println("Sum of 1-3 : " + getMinOfRange(1, 3));
	}

	static void constructST(int low, int high, int i) {
		if (low == high) {
			ST[i] = input[low];
			return;
		}

		int mid = (low + high) / 2;
		constructST(low, mid, 2 * i + 1);
		constructST(mid + 1, high, 2 * i + 2);
		ST[i] = Math.min(ST[2 * i + 1], ST[2 * i + 2]);
	}

	private static int getMinOfRange(int qlow, int qhigh) {
		return getMin(qlow, qhigh, 0, (ST.length + 1) / 2 - 1, 0);
	}

	private static int getMin(int qlow, int qhigh, int low, int high, int i) {
		if (qlow == low && qhigh == high)
			return ST[i];
		int mid = (low + high) / 2;
		if (qlow > mid && qhigh > mid)
			return getMin(qlow, qhigh, mid + 1, high, 2 * i + 2);
		else if (qlow <= mid && qhigh <= mid)
			return getMin(qlow, qhigh, low, mid, 2 * i + 1);
		else {
			int left = getMin(qlow, mid, low, mid, 2 * i + 1);
			int right = getMin(mid + 1, qhigh, mid + 1, high, 2 * i + 2);
			return Math.min(left, right);
		}
	}
}
