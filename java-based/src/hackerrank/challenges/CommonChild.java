package hackerrank.challenges;

import java.util.Scanner;

public class CommonChild {
	static int[][] long_cmn_subseq_tbl;

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		String strA = sc.next();
		String strB = sc.next();

		long_cmn_subseq_tbl = new int[strB.length() + 1][strA.length() + 1];

		for (int i = 0; i < strB.length() + 1; i++) {
			long_cmn_subseq_tbl[i][0] = 0;
		}

		for (int i = 0; i < strA.length() + 1; i++) {
			long_cmn_subseq_tbl[0][i] = 0;
		}

		char[] strA_ch = strA.toCharArray();
		char[] strB_ch = strB.toCharArray();

		for (int i = 1; i < strB.length() + 1; i++) {
			for (int j = 1; j < strA.length() + 1; j++) {

				if (strB_ch[i - 1] == strA_ch[j - 1]) {
					int min = getMin(long_cmn_subseq_tbl[i][j - 1], long_cmn_subseq_tbl[i - 1][j],
							long_cmn_subseq_tbl[i - 1][j - 1]);
					long_cmn_subseq_tbl[i][j] = min + 1;
				} else {

					int max = getMax(long_cmn_subseq_tbl[i][j - 1], long_cmn_subseq_tbl[i - 1][j],
							long_cmn_subseq_tbl[i - 1][j - 1]);
					long_cmn_subseq_tbl[i][j] = max;
				}

				if (long_cmn_subseq_tbl[i][j] > i)
					long_cmn_subseq_tbl[i][j] = i;
			}
		}

		System.out.println(long_cmn_subseq_tbl[strB.length()][strA.length()]);

	}

	static int getMax(int a, int b, int c) {
		int x = Math.max(a, b);
		return Math.max(x, c);
	}

	static int getMin(int a, int b, int c) {
		int x = Math.min(a, b);
		return Math.min(x, c);
	}
}
