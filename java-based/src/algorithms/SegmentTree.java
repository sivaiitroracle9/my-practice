package algorithms;

import java.util.Scanner;

public class SegmentTree {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] _a = new int[N];
		for (int i = 0; i < N; i++)
			_a[i] = sc.nextInt();
		int[] ST = constructSegmentTree(_a);
		for (int i = 0; i < ST.length; i++)
			System.out.print(ST[i] + " ");
		System.out.println(RMQuery(ST, 0, 3));
		System.out.println(RMQuery(ST, 1, 2));
		System.out.println(RMQuery(ST, 0, 1));
		System.out.println(RMQuery(ST, 2, 3));
	}

	private static int[] constructSegmentTree(int[] a) {
		int N = a.length;
		int x = (int) Math.ceil(Math.log(N) / Math.log(2));
		int sTreeSize = 2 * ((int) Math.pow(2, x)) - 1;
		int[] ST = new int[sTreeSize];
		contructST(ST, a, 0, N - 1, 0);
		return ST;
	}

	private static void contructST(int[] ST, int[] a, int low, int high, int i) {
		if (low == high) {
			ST[i] = a[low];
			return;
		}

		int mid = (low + high) / 2;
		contructST(ST, a, low, mid, 2 * i + 1);
		contructST(ST, a, mid + 1, high, 2 * i + 2);
		ST[i] = Math.min(ST[2 * i + 1], ST[2 * i + 2]);
	}

	private static int RMQuery(int[] ST, int qlow, int qhigh) {
		return RMQ(ST, qlow, qhigh, 0, (ST.length + 1) / 2 - 1, 0);
	}

	private static int RMQ(int[] ST, int qlow, int qhigh, int low, int high, int i) {
		if (qlow == low && qhigh == high)
			return ST[i];
		int mid = (low + high) / 2;
		if (qlow > mid && qhigh > mid)
			return RMQ(ST, qlow, qhigh, mid + 1, high, 2 * i + 2);
		else if (qlow <= mid && qhigh <= mid)
			return RMQ(ST, qlow, qhigh, low, mid, 2 * i + 1);
		else {
			int left = RMQ(ST, qlow, mid, low, mid, 2 * i + 1);
			int right = RMQ(ST, mid + 1, qhigh, mid + 1, high, 2 * i + 2);
			return Math.min(left, right);
		}
	}
}
