package hackerrank;

import java.util.Scanner;

public class LCP {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();

		char[] sarr = str.toCharArray();
		print(process(sarr));
	}

	static int[] process(char[] sarr) {
		int[] zarr = new int[sarr.length];

		zarr[0] = 0;
		int l = 0;
		int j = 1;
		int zl = 1;
		int zr = 1;
		while ((zr < sarr.length) && (l < sarr.length)) {
			if ((j + l < sarr.length) && (sarr[j + l] == sarr[l])) {
				l++;
				zarr[j]++;
			} else {
				

			}
		}

		return zarr;
	}

	static int zboxProcess(int zl, int zr, int[] zarr) {

		for (int k = zl + 1; k < zr; k++) {
			if (zarr[k - zl] + k < zr) {
				zarr[k] = zarr[k - zl];
			} else {
				return k;
			}
		}

		return zl;
	}

	static void print(int[] zarr) {
		for (int i = 0; i < zarr.length; i++)
			System.out.print(zarr[i] + " ");
		System.out.println();
	}
}
