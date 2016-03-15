/**
 * Minimum number of jumps.
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=153
 */
package geeksforgeeks.company.amazon;

import java.util.Scanner;

public class MinimumNumOfJumps {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			for (int i = 0; i < N; i++)
				arr[i] = sc.nextInt();
			System.out.println(minOfJumps(arr));
		}
	}

	static int minOfJumps(int[] arr) {
		int[] T = new int[arr.length];
		outer: for (int i = 0; i < arr.length; i++) {
			int X = arr[i];
			if (X == 0)
				continue;

			for (int j = 1; j <= X; j++) {
				if (T[i+j]==0 || T[i + j] > T[i] + 1)
					T[i + j] = T[i] + 1;
				if (i + j == arr.length - 1)
					break outer;
			}
		}
		if(T[arr.length-1]==0) return -1;
		return T[arr.length-1];
	}
}
