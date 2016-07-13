package lc.questions.basic;
/**
 * Recursive Solution.
 * 
 * https://leetcode.com/problems/wildcard-matching/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */
public class WildCardMatch {

	public static void main(String[] args) {

		String text = "abbcdgiiide";
		String pattern = "ab*cd?i*de";

		System.out.println(isMatch(text, pattern));
	}

	public static boolean isMatch(String text, String pattern) {
		int[][] match = new int[pattern.length() + 1][text.length() + 1];
		for (int i = 0; i <= pattern.length(); i++) {
			for (int j = 0; j <= text.length(); j++) {
				match[i][j] = -1;
			}
		}
		return isMatch(text, pattern, match, pattern.length(), text.length());
	}

	public static boolean isMatch(String text, String pattern, int[][] dp,
			int pat, int tex) {

		if (dp[pat - pattern.length()][tex - text.length()] != -1)
			return dp[pat - pattern.length()][tex - text.length()] == 1 ? true
					: false;

		if (pattern.length() == 0 && text.length() == 0) {
			dp[pat - pattern.length()][tex - text.length()] = 1;
			return true;
		}

		if (pattern.length() == 1) {

			if (text.length() >= 0) {
				if ((text.length() == 1 && (pattern.charAt(0) == '?' || pattern
						.charAt(0) == text.charAt(0)))
						|| pattern.charAt(0) == '*') {
					dp[pat - pattern.length()][tex - text.length()] = 1;
					return true;
				}
			}
			
			return false;
		}

		if (pattern.length() > 1) {

			if (text.length() > 0) {
				if (pattern.charAt(0) != '?' && pattern.charAt(0) != '*') {
					if (isMatch(text.substring(1), pattern.substring(1), dp, pat, tex)) {
						dp[pat - pattern.length()][tex - text.length()] = 1;
						return true;
					}

				} else if (pattern.charAt(0) == '?') {
					if (isMatch(text.substring(1), pattern.substring(1), dp, pat, tex)) {
						dp[pat - pattern.length()][tex - text.length()] = 1;
						return true;
					}
				}
				// char at 0 is *
				else {
					int m = 0;
					while (m < text.length()) {
						if (text.charAt(m) == pattern.charAt(1)
								|| pattern.charAt(1) == '?') {
							if (isMatch(text.substring(m + 1),
									pattern.substring(2), dp, pat, tex)) {
								dp[pat - pattern.length()][tex - text.length()] = 1;
								return true;
							}
						}
						m++;
					}
				}
			}
		}
		dp[pat - pattern.length()][tex - text.length()] = 0;
		return false;
	}
}