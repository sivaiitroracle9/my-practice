/**
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=496
 */
package geeksforgeeks.company.amazon;

import java.util.Scanner;

public class NutsAndBolts {
	public static void main(String[] args) {
		char nuts[] = { '@', '#', '$', '%', '^', '&' };
		char bolts[] = { '$', '%', '&', '^', '@', '#' };
		fit(nuts, bolts);
	}

	private static void fit(char[] nuts, char[] bolts) {
		fit_helper(nuts, 0, nuts.length - 1, bolts, 0, bolts.length - 1);
		for (int i = 0; i < nuts.length; i++)
			System.out.print(nuts[i] + " ");
		System.out.println();
		for (int i = 0; i < bolts.length; i++)
			System.out.print(bolts[i] + " ");
		System.out.println();
	}

	private static void fit_helper(char[] nuts, int nL, int nR, char[] bolts,
			int bL, int bR) {
		int nPivot = nL;

		int bbL = bL;
		int bbR = bR;
		while (bbL < bbR) {
			while (bolts[bbL] < nuts[nPivot] && bbL < bbR)
				bbL++;

			while (bolts[bbR] > nuts[nPivot] && bbL < bbR)
				bbR--;

			char temp = bolts[bbL];
			bolts[bbL] = bolts[bbR];
			bolts[bbR] = temp;

		}

		int bPivot = bbL;
		int nnL = nL;
		int nnR = nR;
		while (nnL < nnR) {
			while (nuts[nnL] < bolts[bPivot] && nnL < nnR)
				nnL++;

			while (nuts[nnR] > bolts[bPivot] && nnL < nnR)
				nnR--;

			char temp = nuts[nnL];
			nuts[nnL] = nuts[nnR];
			nuts[nnR] = temp;

		}
		nPivot = nnL;

		if (nL < nPivot - 1)
			fit_helper(nuts, nL, nPivot - 1, bolts, bL, nPivot - 1);
		if (nR > nPivot + 1)
			fit_helper(nuts, nPivot + 1, nR, bolts, bPivot + 1, bR);
	}
}
