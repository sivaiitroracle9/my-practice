package interview.worksapp;

import java.util.Scanner;

public class EditDistance {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String word1 = sc.next();
		String word2 = sc.next();

		System.out.println(editDistance(word1, word2));
	}

	static int editDistance(String word1, String word2) {

		int len1 = word1.length();
		int len2 = word2.length();

		int[][] dp = new int[len2 + 1][len1 + 1];

		for (int i = 0; i <= len1; i++)
			dp[0][i] = i;
		for (int i = 0; i <= len2; i++)
			dp[i][0] = i;

		for (int i = 1; i <= len2; i++) {
			for (int j = 1; j <= len1; j++) {

				if (word1.charAt(j - 1) == word2.charAt(i - 1))
					dp[i][j] = dp[i - 1][j - 1];
				else {
					int delete = dp[i][j - 1] + 1;
					int insert = dp[i - 1][j] + 1;
					int replace = dp[i - 1][j - 1] + 1;

					int min = Math.min(insert, delete);
					min = Math.min(min, replace);
					dp[i][j] = min;
				}
			}
		}

		return dp[len2][len1];
	}
}
