package leetcode.questions.basic;

public class Leetcode329_LongestIncreasingPathMatrix {

	public static void main(String[] args) {
		Leetcode329_LongestIncreasingPathMatrix lc = new Leetcode329_LongestIncreasingPathMatrix();
		int[][] matrix = { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } };
		System.out.println(lc.longestIncreasingPath(matrix));
	}

	public int longestIncreasingPath(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0)
			return 0;

		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		int[][] longestPath = new int[matrix.length][matrix[0].length];

		int length = 0;
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[0].length; c++) {
				if (!visited[r][c])
					length = Math.max(
							length,
							longestIncreasingPath(matrix, visited, longestPath,
									r, c));
			}
		}
		return length;
	}

	public int longestIncreasingPath(int[][] matrix, boolean[][] visited,
			int[][] longestPath, int r, int c) {

		visited[r][c] = true;

		int length = 0;
		if (c + 1 < matrix[0].length && matrix[r][c] > matrix[r][c + 1]) {
			if (!visited[r][c + 1]) {
				length = Math.max(
						length,
						longestIncreasingPath(matrix, visited, longestPath, r,
								c + 1));
			} else {
				length = Math.max(length, longestPath[r][c + 1]);
			}
		}

		if (c - 1 >= 0 && matrix[r][c] > matrix[r][c - 1]) {
			if (!visited[r][c - 1]) {
				length = Math.max(
						length,
						longestIncreasingPath(matrix, visited, longestPath, r,
								c - 1));
			} else {
				length = Math.max(length, longestPath[r][c - 1]);
			}
		}

		if (r + 1 < matrix.length && matrix[r][c] > matrix[r + 1][c]) {
			if (!visited[r + 1][c]) {
				length = Math.max(
						length,
						longestIncreasingPath(matrix, visited, longestPath,
								r + 1, c));
			} else {
				length = Math.max(length, longestPath[r + 1][c]);
			}
		}

		if (r - 1 >= 0 && matrix[r][c] > matrix[r - 1][c]) {
			if (!visited[r - 1][c]) {
				length = Math.max(
						length,
						longestIncreasingPath(matrix, visited, longestPath,
								r - 1, c));
			} else {
				length = Math.max(length, longestPath[r - 1][c]);
			}
		}

		longestPath[r][c] = length + 1;

		return longestPath[r][c];
	}

}
