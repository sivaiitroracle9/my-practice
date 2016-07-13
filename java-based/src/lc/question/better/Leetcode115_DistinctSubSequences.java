package lc.question.better;

public class Leetcode115_DistinctSubSequences {

	public static void main(String[] args) {
		Leetcode115_DistinctSubSequences lc = new Leetcode115_DistinctSubSequences();
		String s = "rabbbit";
		String t = "rabbit";
		System.out.println(lc.numDistinct_DP_MN_basic(s, t));
	}

	public int numDistinct(String s, String t) {
		int[][] dp = new int[t.length() + 1][s.length() + 1];
		for (int j = 0; j < s.length() + 1; j++)
			dp[0][j] = 1;
		for (int i = 0; i < t.length(); i++) {
			for (int j = 0; j < s.length(); j++) {
				if (t.charAt(i) == s.charAt(j)) {
					dp[i + 1][j + 1] = dp[i][j] + dp[i + 1][j];
				} else {
					dp[i + 1][j + 1] = dp[i + 1][j];
				}
			}
		}
		return dp[t.length()][s.length()];
	}

	public int numDistinct_DP_MN_basic(String s, String t) {
		int[][] dp = new int[t.length() + 1][s.length() + 1];
		for (int i = 0; i < t.length(); i++) {
			for (int j = 0; j < s.length(); j++) {
				if (t.charAt(i) == s.charAt(j)) {
					dp[i + 1][j + 1] = dp[i][j] + 1;
				} else {
					int max = Math.max(dp[i + 1][j], dp[i][j + 1]);
					max = Math.max(dp[i][j], max);
					dp[i + 1][j + 1] = max;
				}
			}
		}
		if (dp[t.length()][s.length()] < t.length())
			return 0;

		return distinctPaths(s.toCharArray(), t.toCharArray(), dp, t.length(),
				s.length());
	}

	public int distinctPaths(char[] s, char[] t, int[][] dp, int r, int c) {

		if (r == 0 || c == 0) {
			return 1;
		}

		int paths = 0;
		if (s[c - 1] == t[r - 1]) {
			// Max Path
			if (dp[r][c] == dp[r - 1][c - 1] + 1) {
				paths += distinctPaths(s, t, dp, r - 1, c - 1);
			}
		}

		if (dp[r][c] == dp[r][c - 1]) {
			paths += distinctPaths(s, t, dp, r, c - 1);
		}

		if (dp[r][c] == dp[r - 1][c]) {
			paths += distinctPaths(s, t, dp, r - 1, c);
		}

		return paths;
	}
}
