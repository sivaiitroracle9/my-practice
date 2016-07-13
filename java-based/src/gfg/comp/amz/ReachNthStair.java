/**
 * Count ways to reach the n’th stair.
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=155
 */
package geeksforgeeks.company.amazon;

import java.util.Scanner;

public class ReachNthStair {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int ways = numOfWays(sc.nextInt());
			System.out.println(ways);
		}
	}

	static int numOfWays(int N) {
		if (N == 1)
			return 1;

		if (N == 2)
			return 2;

		return numOfWays(N - 1) + numOfWays(N - 2);
	}
}
