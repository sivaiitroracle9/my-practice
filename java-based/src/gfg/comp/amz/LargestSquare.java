/**
 * Largest square formed in a matrix.
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=475
 */

package gfg.comp.amz;

import java.util.Scanner;

public class LargestSquare {
	static int[][] grid = null;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int c = sc.nextInt();

		grid = new int[r][c];
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				grid[i][j] = sc.nextInt();
		System.out.println(maxSquare());
	}

	static int maxSquare() {
		int[][] maxmat = new int[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++)
			maxmat[i][0] = grid[i][0];
		for (int j = 0; j < grid[0].length; j++)
			maxmat[0][j] = grid[0][j];

		int maxSq = 0;

		for (int i = 1; i < grid.length; i++) {
			for (int j = 1; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					int min = Math.min(maxmat[i - 1][j], maxmat[i][j - 1]);
					min = Math.min(min, maxmat[i - 1][j - 1]);
					maxmat[i][j] = min + 1;
				} else
					maxmat[i][j] = 0;

				maxSq = maxSq < maxmat[i][j] ? maxmat[i][j] : maxSq;
			}
		}
		return maxSq;
	}

}
