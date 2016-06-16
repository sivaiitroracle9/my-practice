package geeksforgeeks.matrix;

public class SpiralMatrix {

	public static void main(String[] args) {

		int[][] mat = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 },
				{ 11, 12, 13, 14, 15 } };

		int[] spiral = getSpiral(mat);
		for (int i : spiral)
			System.out.print(i + ", ");
	}

	public static int[] getSpiral(int[][] mat) {

		int M = mat.length, N = mat[0].length;

		int r = 0, c = 0, p = 0, i = 0, j = 0;
		int[] rotate = new int[M * N];

		while (r < M / 2 && c < N / 2) {
			i = r;
			for (j = c; j < N - 1 - c; j++)
				rotate[p++] = mat[i][j];

			j = N - 1 - c;
			for (i = r; i < M - 1 - r; i++)
				rotate[p++] = mat[i][j];

			i = M - 1 - r;
			for (j = N - 1 - c; j > c; j--)
				rotate[p++] = mat[i][j];

			j = c;
			for (i = M - 1 - r; i > r; i--)
				rotate[p++] = mat[i][j];

			r++;
			c++;
		}

		for (j = c; j <= N - 1 - c && p < rotate.length; j++)
			rotate[p++] = mat[r][j];
		for (i = r; i <= M - 1 - r && p < rotate.length ; i++)
			rotate[p++] = mat[i][c];

		return rotate;
	}
}
