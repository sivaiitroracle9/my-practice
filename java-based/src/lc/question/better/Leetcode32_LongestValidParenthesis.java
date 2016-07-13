package lc.question.better;

public class Leetcode32_LongestValidParenthesis {

	public static void main(String[] args) {
		Leetcode32_LongestValidParenthesis lc = new Leetcode32_LongestValidParenthesis();
		String s = "()(()";
		System.out.println(lc.longestValidParentheses(s));
	}

	public int longestValidParentheses(String s) {
		char[] sarr = s.toCharArray();
		int left = 0, right = 0, max_length = 0;

		for (int i = 0; i < sarr.length; i++) {
			if (sarr[i] == '(') {
				left++;
			} else {
				right++;
			}

			if (left - right < 0) {
				left = 0;
				right = 0;
			}

			if (max_length < 2 * right)
				max_length = 2 * right;
		}
		return max_length;
	}
}
