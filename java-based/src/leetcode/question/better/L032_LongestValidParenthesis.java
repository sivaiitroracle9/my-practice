package leetcode.question.better;

import java.util.Stack;

public class L032_LongestValidParenthesis {

	public static void main(String[] args) {

		String t = ")()())";
		System.out.println(longestValidParentheses(t));

	}

	public static int longestValidParentheses(String s) {
		char[] csarr = s.toCharArray();
		if (csarr.length < 2)
			return 0;

		Stack<Integer> stack = new Stack<Integer>();
		int max_len = 0;
		for (int i = 0; i < csarr.length; i++) {

			if (!stack.isEmpty()) {

			}

		}
		return max_len;
	}
}
