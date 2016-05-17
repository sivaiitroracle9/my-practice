package leetcode.questions.basic;

/**
 * Time complexity O(m*n*m) Space complexity O(m*n)
 * https://leetcode.com/problems/maximal-rectangle/
 * 
 * @author Siva
 *
 */
public class Leetcode85_MaximalRectangle {
	public static void main(String[] args) {

	}

	public int maximalRectangle(char[][] matrix) {
		if (matrix.length == 0)
			return 0;
		int[][] matboard = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = matrix[0].length - 1; j >= 0; j--) {
				if (j == matrix[0].length - 1) {
					matboard[i][j] = (matrix[i][j] == '0') ? 0 : 1;
				} else {
					matboard[i][j] = (matrix[i][j] == '0') ? 0
							: matboard[i][j + 1] + 1;
				}
			}
		}

		int max_area = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {

				int width = Integer.MAX_VALUE;
				for (int k = i; k < matrix.length; k++) {
					width = Math.min(width, matboard[k][j]);
					if (width == 0)
						break;
					max_area = Math.max(max_area, width * (k - i + 1));

				}

			}
		}
		return max_area;
	}
}
