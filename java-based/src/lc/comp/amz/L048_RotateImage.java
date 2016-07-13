package lc.comp.amz;

public class L048_RotateImage {

	public static void main(String[] args) {
		L048_RotateImage lc = new L048_RotateImage();
		int[][] matrix = { { 1, 2 }, { 3, 4 } };
		lc.rotate(matrix);
	}

	/**
	 * mat --> transpose --> invert columns // clockwise 1 2 3 4 1 5 9 13 13 5 9
	 * 1 13 9 5 1 5 6 7 8 --> 2 6 10 14 --> 14 6 10 2 --> 14 10 6 2 9 10 11 12 3
	 * 7 11 15 15 7 11 3 15 11 7 3 13 14 15 16 4 8 12 16 16 8 12 4 16 12 8 4
	 * 
	 * @param matrix
	 */

	public void rotate(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0)
			return;
		int n = matrix.length;

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int t = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = t;
			}
		}

		for (int j = 0; j < n / 2; j++) {
			for (int i = 0; i < n; i++) {
				int t = matrix[i][j];
				matrix[i][j] = matrix[i][n - 1 - j];
				matrix[i][n - 1 - j] = t;
			}
		}
	}
}
