package lc.comp.google;

import java.util.ArrayList;
import java.util.List;

public class L022_GenerateParenthesis {

	public static void main(String[] args) {
		for(String s : generateParenthesis(3)) {
			System.out.println(s);
		}
	}

	public static List<String> generateParenthesis(int n) {

		List<String> result = new ArrayList<String>();
		if (n == 0)
			return result;

		char[] path = new char[2 * n];
		gen_parenthesis_helper(0, 0, result, path);
		return result;
	}

	public static void gen_parenthesis_helper(int open, int close,
			List<String> result, char[] path) {

		if (close > open || open > path.length/2 || close > path.length/2)
			return;

		if (close == open && close + open == path.length) {
			String newres = new String(path);
			result.add(newres);
		}

		if (open + close < path.length && open <= path.length/2 && close <= path.length/2 ) {
			path[open + close] = '(';
			gen_parenthesis_helper(open + 1, close, result, path);
			path[open + close] = ')';
			gen_parenthesis_helper(open, close + 1, result, path);
		}

	}

}
