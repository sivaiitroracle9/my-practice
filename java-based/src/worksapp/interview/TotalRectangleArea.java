package worksapp.interview;

import java.util.Scanner;

public class TotalRectangleArea {

	public static void main(String[] main) {
		Scanner sc = new Scanner(System.in);

		int k = sc.nextInt();
		int l = sc.nextInt();
		int m = sc.nextInt();
		int n = sc.nextInt();

		int p = sc.nextInt();
		int q = sc.nextInt();
		int r = sc.nextInt();
		int s = sc.nextInt();

		System.out.println(areaEnclosed(k, l, m, n, p, q, r, s));
	}

	static long areaEnclosed(int k, int l, int m, int n, int p, int q, int r, int s) {
		int left = Math.max(k, p);
		int bottom = Math.max(l, n);
		int right = Math.min(m, r);
		int top = Math.min(n, s);

		return (m - k) * (n - l) + (r - p) * (s - q) - (right - left) * (top - bottom);

	}

}
