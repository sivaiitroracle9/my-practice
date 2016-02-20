package interview.worksapp;

import java.util.Scanner;

public class KMPAlgo {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String text = sc.next();
		String pattern = sc.next();

		int[] lps = patternPreprocessing(pattern);

		int i = 0;
		int j = 0;
		while (i < text.length()) {

			if (text.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
			} else {
				if (j == 0) {
					i++;
				} else {
					j = lps[j - 1];
				}

			}
			if (j == pattern.length()) {
				System.out.println("pattern found at " + (i - j) + " to " + i);
				break;
			}

		}

	}

	static int[] patternPreprocessing(String pattern) {

		int[] lps = new int[pattern.length()];

		int j = 0;
		int i = 1;
		lps[0] = 0;
		while (i < pattern.length()) {
			if (pattern.charAt(i) == pattern.charAt(j)) {
				lps[i] = j + 1;
				j++;
				i++;
			} else {
				if (j == 0) {
					lps[i] = 0;
					i++;
				} else {
					j = lps[j - 1];
				}
			}
		}

		return lps;
	}

	static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

}
