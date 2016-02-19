package algorithms.string.pattern;

import java.util.Scanner;

public class KMP {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String text = sc.next();
		String pattern = sc.next();

		int[] lps = lps_processing(pattern);

		String out = search(text, pattern, lps) ? "Pattern Found" : "Pattern Not Found";
		System.out.println(out);

	}

	static boolean search(String text, String pattern, int[] lps) {

		boolean found = false;
		int i = 0, j = 0;
		while (i < text.length()) {
			if (text.charAt(i) == pattern.charAt(j)) {
				j++;
				i++;
			} else {
				if (j == 0) {
					i++;
				} else {
					j = lps[j - 1];
				}
			}
			if (j == pattern.length()) {
				found = true;
				break;
			}
		}

		return found;

	}

	static int[] lps_processing(String pattern) {
		int[] lps = new int[pattern.length()];

		if (lps.length > 0) {
			lps[0] = 0;
			int i = 0, j = 1;
			while (j < lps.length) {
				if (pattern.charAt(j) == pattern.charAt(i)) {
					lps[j] = i + 1;
					j++;
					i++;
				} else {

					if (i == 0) {
						lps[j] = 0;
						j++;
					} else {
						i = lps[i - 1];
					}
					continue;
				}
			}
		}

		// for (int m = 0; m < lps.length; m++)
		// System.out.print(lps[m] + " ");
		System.out.println();

		return lps;
	}
}
