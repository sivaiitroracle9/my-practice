package leetcode.questions.basic;

import java.util.ArrayList;
import java.util.List;

public class WordBreakII {
	public static void main(String[] args) {
		String s = "catsanddog";
		String[] dict = { "cat", "cats", "and", "sand", "dog" };

		List<String> sentences = findSentences(s, dict);
		for (String sn : sentences) {
			System.out.println(sn);
		}
	}

	public static List<String> findSentences(String s, String[] dict) {

		List<String> dp[] = new ArrayList[s.length() + 1];
		dp[0] = new ArrayList<String>();

		for (int i = 0; i < s.length(); i++) {

			if (dp[i] == null)
				continue;

			for (String d : dict) {
				int end = i + d.length();

				if (end > s.length())
					continue;

				if (s.substring(i, end).equals(d)) {
					if (dp[end] == null)
						dp[end] = new ArrayList<String>();
					dp[end].add(d);
				}
			}
		}

		if (dp[s.length()] == null)
			return new ArrayList<String>();

		List<String> result = new ArrayList<String>();
		dfs(dp, s.length(), result, new ArrayList<String>());
		return result;
	}

	public static void dfs(List<String>[] dp, int end, List<String> result,
			List<String> temp) {

		if (end <= 0) {
			String p = "";
			for (String sn : temp) {
				p = sn + " " + p;
			}
			if (p != "")
				result.add(p.trim());
			return;
		}

		for (String word : dp[end]) {
			temp.add(word);
			dfs(dp, end - word.length(), result, temp);
			temp.remove(word);
		}

	}
}
