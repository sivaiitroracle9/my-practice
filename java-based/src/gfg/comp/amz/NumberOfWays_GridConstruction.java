/**
 * Given a tile of size 1 x 4, how many ways you can construct a grid of size N x 4.
 * Input:
 * The first line of input contains an integer T denoting the number of test cases.
 * The first line of each test case is N.
 * Output:
 * Print number of possible ways.
 */
package geeksforgeeks.company.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NumberOfWays_GridConstruction {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while (T-- > 0) {
			int N = sc.nextInt();
			/*
			 * List<List<Integer>> listOfWays = new ArrayList<List<Integer>>();
			 * List<Integer> way = new ArrayList<Integer>(); numOfWays(N,
			 * listOfWays, way, 0); System.out.println(listOfWays.size());
			 */
			numWays(N);
		}
	}

	private static void numWays(int N) {
		int[] dp = new int[N + 1];
		Arrays.fill(dp, -1);
		System.out.println(nums(N, dp));
	}

	private static int nums(int x, int[] dp) {

		if (x == 0) {
			return 1;
		}

		if (dp[x] != -1)
			return dp[x];

		if (x >= 4) {
			dp[x] = nums(x - 1, dp) + nums(x - 4, dp);
		} else if (x > 0) {
			dp[x] = nums(x - 1, dp);
		}

		return dp[x];
	}

	private static void numOfWays(int N, List<List<Integer>> listOfWays,
			List<Integer> way, int sum) {

		if (sum == N) {
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < way.size(); i++)
				list.add(way.get(i));
			listOfWays.add(list);
			return;
		}

		if (sum > N)
			return;

		if (sum + 1 <= N) {
			way.add(1);
			numOfWays(N, listOfWays, way, sum + 1);
			way.remove(way.size() - 1);
		}

		if (sum + 4 <= N) {
			way.add(4);
			numOfWays(N, listOfWays, way, sum + 4);
			way.remove(way.size() - 1);
		}

	}
}
