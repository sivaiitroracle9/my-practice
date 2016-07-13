package geeksforgeeks.matrix;

public class MinPointsToReach {

	public static void main(String[] args) {

		int[][] mat = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } };
		System.out.println(minPoints(mat));

	}

	public static int minPoints(int[][] mat) {

		int[][] dp = new int[mat.length][mat[0].length];

		int M = mat.length, N = mat[0].length;

		dp[M - 1][N - 1] = mat[M - 1][N - 1] > 0 ? 1 : Math
				.abs(mat[M - 1][N - 1]) + 1;

		for (int i = M - 2; i >= 0; i--)
			dp[i][N - 1] = Math.max(dp[i + 1][N - 1] - mat[i][N - 1], 1);

		for (int j = N - 2; j >= 0; j--)
			dp[M - 1][j] = Math.max(dp[M - 1][j] - mat[M - 1][j], 1);

		for (int i = M - 2; i >= 0; i--) {
			for (int j = N - 2; j >= 0; j--) {

				int min_points_on_exit = Math.min(dp[i + 1][j], dp[i][j + 1]);

				dp[i][j] = Math.max(min_points_on_exit - mat[i][j], 1);
			}
		}

		return dp[0][0];
	}
}
