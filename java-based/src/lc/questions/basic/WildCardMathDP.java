package lc.questions.basic;

/**
 * 
 * Dynamic Programming solution.
 * 
 * https://leetcode.com/problems/wildcard-matching/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */
public class WildCardMathDP {

	public static void main(String[] args) {
		String text = "aa";
		String pattern = "aa";

		System.out.println(isMatch(text, pattern));
	}

	private static boolean isMatch(String text, String pattern) {

		char[] text_char = text.toCharArray();
		char[] pat_char = pattern.toCharArray();

		// clean pattern string for multiple * to single *
		boolean isFirst = false;
		int writeIndex = 0;
		for (int i = 0; i < pat_char.length; i++) {
			if (isFirst) {
				if (pat_char[i] != '*') {
					isFirst = false;
					pat_char[writeIndex++] = pat_char[i];
				}
			} else {
				if (pat_char[i] == '*') {
					isFirst = true;
				}
				pat_char[writeIndex++] = pat_char[i];
			}

		}

		boolean[][] dp = new boolean[text.length() + 1][writeIndex + 1];
		dp[0][0] = true;
		if (pattern.length() > 0 && pat_char[0] == '*')
			dp[0][1] = true;

		for (int i = 1; i <= text_char.length; i++) {
			for (int j = 1; j <= writeIndex; j++) {
				if (pat_char[j - 1] == '?'
						|| text_char[i - 1] == pat_char[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1];
				} else if (pat_char[j - 1] == '*') {
					dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
				} else
					dp[i][j] = false;
			}
		}

		return dp[text_char.length][writeIndex];
	}
}
