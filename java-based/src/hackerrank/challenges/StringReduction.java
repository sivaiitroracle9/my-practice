package hackerrank.challenges;

import java.util.Scanner;

public class StringReduction {

	public static void main() {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();

		System.out.println(stringReduction(s));
	}

	static int stringReduction(String s) {

		char[] cs = s.toCharArray();
		int nA = 0, nB = 0, nC = 0;

		for (int i = 0; i < cs.length; i++) {
			if (cs[i] == 'a')
				nA++;
			else if (cs[i] == 'b')
				nB++;
			else if (cs[i] == 'c')
				nC++;
		}

		if ((nA + nB) * (nB + nC) * (nC + nA) == 0) {
			// Any two equal to Zero
			return cs.length;
		} else if ((nA + nB) % 2 == 0 && (nB + nC) % 2 == 0 && (nC + nA) == 0) {
			// All are Odd or All are even.
			return 1;
		} else
			return 2;

	}
}
