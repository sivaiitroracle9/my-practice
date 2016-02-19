package hackerrank;

import java.util.Scanner;

public class MatrixRotation {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int M = s.nextInt();
		int N = s.nextInt();
		int R = s.nextInt();

		int[][] map = new int[M][N];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = s.nextInt();
			}
		}

		print(rotate(map, M, N, R));

	}

	static int[][] rotate(int[][] map, int M, int N, int R) {

		int i1 = 0, i2 = M - 1, j1 = 0, j2 = N - 1;
		int r = 2 * (M + N) - 4;
		while (true) {

			if (i2 > i1 && j2 > j1)
				map = ringRotate(map, i1, i2, j1, j2, R % r);

			if (i2 - i1 < 1 && j2 - j1 < 1)
				break;
			else {
				i1++;
				i2--;
				j1++;
				j2--;
				r = r - 8;
			}
		}

		return map;
	}

	static int[][] ringRotate(int[][] map, int i1, int i2, int j1, int j2, int rotations) {
		int len = 2 * (i2 - i1 + j2 - j1);
		int[] ring_arr = new int[len];

		int idx = 0;
		for (int i = i1; i < i2; i++) {
			ring_arr[idx] = map[i][j1];
			idx++;
		}

		for (int j = j1; j < j2; j++) {
			ring_arr[idx] = map[i2][j];
			idx++;
		}

		for (int i = i2; i > i1; i--) {
			ring_arr[idx] = map[i][j2];
			idx++;
		}

		for (int j = j2; j > j1; j--) {
			ring_arr[idx] = map[i1][j];
			idx++;
		}

		int[] mod_ring_arr = new int[len];

		for (int i = 0; i < len; i++) {
			int pos = i + rotations;

			if (pos > len - 1)
				pos = pos - len;
			mod_ring_arr[pos] = ring_arr[i];
		}

		idx = 0;
		for (int i = i1; i < i2; i++) {
			map[i][j1] = mod_ring_arr[idx];
			idx++;
		}

		for (int j = j1; j < j2; j++) {
			map[i2][j] = mod_ring_arr[idx];
			idx++;
		}

		for (int i = i2; i > i1; i--) {
			map[i][j2] = mod_ring_arr[idx];
			idx++;
		}

		for (int j = j2; j > j1; j--) {
			map[i1][j] = mod_ring_arr[idx];
			idx++;
		}
		return map;
	}

	static void print(int[][] map) {
		int M = map.length;
		int N = map[0].length;
		for (int i = 0; i < M; i++) {
			String str = "";
			for (int j = 0; j < N; j++) {
				str += map[i][j] + " ";
			}
			str = str.substring(0, str.length() - 1);
			System.out.println(str);
		}
	}
}
