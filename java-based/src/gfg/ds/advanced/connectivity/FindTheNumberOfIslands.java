package geeksforgeeks.datastructures.advanced.connectivity;

import java.util.Scanner;

/**
 * 
 * Question Input. 
 * {1, 1, 0, 0, 0}, 
 * {0, 1, 0, 0, 1}, 
 * {1, 0, 0, 1, 1}, 
 * {0, 0, 0,0, 0}, 
 * {1, 0, 1, 0, 1}
 * 
 * @author Siva Edupuganti.
 *
 */
public class FindTheNumberOfIslands {

	static int[][] grid = null;
	static boolean[][] visited = null;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		grid = new int[R][C];
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				grid[i][j] = sc.nextInt();

		System.out.println(countIslands());
	}

	static int countIslands() {
		int count = 0;
		visited = new boolean[grid.length][grid[0].length];
		int mR = grid.length;
		int mC = grid[0].length;
		for (int i = 0; i < mR; i++) {
			for (int j = 0; j < mC; j++) {
				if (grid[i][j] == 1 && !visited[i][j])
					count += solveDFS(i, j, mR, mC);
			}
		}

		return count;
	}

	static int solveDFS(int i, int j, int mR, int mC) {
		visited[i][j] = true;

		boolean canmove = false;
		int island = 0;
		if (i + 1 < mR && grid[i + 1][j] == 1 && !visited[i + 1][j]) {
			island = Math.max(island, solveDFS(i + 1, j, mR, mC));
			canmove = true;
		}

		if (i - 1 >= 0 && grid[i - 1][j] == 1 && !visited[i - 1][j]) {
			island = Math.max(island, solveDFS(i - 1, j, mR, mC));
			canmove = true;
		}

		if (j - 1 >= 0 && grid[i][j - 1] == 1 && !visited[i][j - 1]) {
			island = Math.max(island, solveDFS(i, j - 1, mR, mC));
			canmove = true;
		}

		if (j + 1 < mC && grid[i][j + 1] == 1 && !visited[i][j + 1]) {
			island = Math.max(island, solveDFS(i, j + 1, mR, mC));
			canmove = true;
		}

		if (i - 1 >= 0 && j + 1 < mC && grid[i - 1][j + 1] == 1 && !visited[i - 1][j + 1]) {
			island = Math.max(island, solveDFS(i - 1, j + 1, mR, mC));
			canmove = true;
		}

		if (i - 1 >= 0 && j - 1 >= 0 && grid[i - 1][j - 1] == 1 && !visited[i - 1][j - 1]) {
			island = Math.max(island, solveDFS(i - 1, j - 1, mR, mC));
			canmove = true;
		}

		if (i + 1 < mR && j + 1 < mC && grid[i + 1][j + 1] == 1 && !visited[i + 1][j + 1]) {
			island = Math.max(island, solveDFS(i + 1, j + 1, mR, mC));
			canmove = true;
		}

		if (i + 1 < mR && j - 1 >= 0 && grid[i + 1][j - 1] == 1 && !visited[i + 1][j - 1]) {
			island = Math.max(island, solveDFS(i + 1, j - 1, mR, mC));
			canmove = true;
		}

		if (!canmove)
			return 1;

		return island;
	}

}
