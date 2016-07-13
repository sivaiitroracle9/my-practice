package lc.comp.google;

public class L308_RangeSumQuery2D_Mutable {

	public static void main(String[] args) {

		int[][] matrix = { { 3, 0, 1, 4, 2 }, { 5, 6, 3, 2, 1 },
				{ 1, 2, 0, 1, 5 }, { 4, 1, 0, 1, 7 }, { 1, 0, 3, 0, 5 } };

		L308_RangeSumQuery2D_Mutable lc = new L308_RangeSumQuery2D_Mutable(
				matrix);
		System.out.println(lc.sumRegion(2, 1, 4, 3));
		lc.update(3, 2, 2);
		System.out.println(lc.sumRegion(2, 1, 4, 3));
	}

	int[][] bitree;
	int[][] mat;

	public L308_RangeSumQuery2D_Mutable(int[][] matrix) {

		int M = matrix.length, N = matrix[0].length;

		bitree = new int[M + 1][N + 1];
		mat = new int[M][N];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				update(i, j, matrix[i][j]);
			}
		}
	}

	public void update(int row, int col, int val) {

		for (int i = row + 1; i < bitree.length; i = i + (i & (-i))) {
			for (int j = col + 1; j < bitree[0].length; j = j + (j & (-j))) {
				bitree[i][j] += val - mat[row][col];
			}
		}
		mat[row][col] = val;
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {

		return getSum(row2, col2) - getSum(row1 - 1, col2)
				- getSum(row2, col1 - 1) + getSum(row1 - 1, col1 - 1);

	}

	public int getSum(int row, int col) {
		int sum = 0;
		if (row < 0 || col < 0)
			return sum;
		for (int i = row + 1; i > 0; i = i - (i & (-i))) {
			for (int j = col + 1; j > 0; j = j - (j & (-j))) {
				sum += bitree[i][j];
			}
		}
		return sum;
	}
}
