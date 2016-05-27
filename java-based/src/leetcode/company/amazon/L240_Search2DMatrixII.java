package leetcode.company.amazon;

public class L240_Search2DMatrixII {
	public boolean searchMatrix(int[][] matrix, int target) {

		int row = 0, col = matrix[0].length - 1;
		while (row < matrix.length && col >= 0) {
			if (matrix[row][col] == target) {
				return true;
			} else if (matrix[row][col] > target) {
				col--;
			} else if (matrix[row][col] < target) {
				row++;
			}
		}
		return false;
	}
}
