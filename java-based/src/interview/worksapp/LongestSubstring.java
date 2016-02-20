package interview.worksapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LongestSubstring {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String word1 = sc.next();
		String word2 = sc.next();

		System.out.println("common = " + common(word1, word2));
		System.out.println("moreThanOnce - 1 = " + moreThanOnce(word1));
		System.out.println("moreThanOnce - 2 = " + moreThanOnce(word2));

	}

	private static String common(String word1, String word2) {

		int[][] dp = new int[word2.length() + 1][word1.length() + 1];

		int maxL = 0;
		int maxJ = 0;
		for (int i = 1; i <= word2.length(); i++) {
			for (int j = 1; j <= word1.length(); j++) {
				if (word1.charAt(j - 1) == word2.charAt(i - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					if (dp[i][j] > maxL) {
						maxL = dp[i][j];
						maxJ = j;
					}
				}
			}
		}

		return word1.substring(maxJ - maxL, maxJ);
	}

	private static String moreThanOnce(String word) {

		List<String> subStringArray = new ArrayList<String>();

		for (int i = 0; i < word.length(); i++) {
			subStringArray.add(word.substring(i));
		}
		Collections.sort(subStringArray);

		int max = 0;
		int maxI = -1;
		int maxJ = -1;
		for (int i = 1; i < word.length(); i++) {
			String p = subStringArray.get(i - 1);
			String p1 = subStringArray.get(i);
			int overlap = 0;
			int j = 0;
			while (j < p.length() && j < p1.length()) {
				if (p.charAt(j) == p1.charAt(j)) {
					overlap++;
					j++;
				} else
					break;
			}

			if (overlap > max && j > 0) {
				max = overlap;
				maxI = i;
				maxJ = j;
			}
		}

		if (maxI != -1 && maxJ != -1) {
			return subStringArray.get(maxI).substring(0, maxJ);
		}

		return null;
	}
}
