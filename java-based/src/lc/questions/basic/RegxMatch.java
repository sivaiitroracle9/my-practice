package lc.questions.basic;

import java.util.Scanner;

/**
 * 
 * Recursive solution.
 * 
 * https://leetcode.com/problems/regular-expression-matching/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */

public class RegxMatch {

	public static void main(String[] args) {
		String s = "aab";
		String p = "c*a*b";

		Scanner sc = new Scanner(System.in);
		String text = sc.next();
		String pattern = sc.next();

		System.out.println(isMatch(text, pattern));
	}

	private static boolean isMatch(String text, String pattern) {

		if (pattern.length() == 0) {
			if (text.length() == 0)
				return true;
		}

		if (pattern.length() == 1) {
			if (text.length() == 0 && pattern.charAt(0) == '*') {
				return true;
			} else if (text.length() == 1
					&& (pattern.charAt(0) == text.charAt(0) || pattern
							.charAt(0) == '?')) {
				return true;
			}
		}

		if (pattern.length() > 1) {

			if (pattern.charAt(1) == '*') {

				if (pattern.charAt(0) != '?' && pattern.charAt(0) != '*') {
					if (text.length() > 0
							&& pattern.charAt(0) != text.charAt(0))
						return false;

					if (text.length() > 0
							&& (isMatch(text.substring(1), pattern.substring(2)) || isMatch(
									text.substring(1), pattern)))
						return true;
				}

				else if (pattern.charAt(0) == '?') {
					if (text.length() > 0
							&& (isMatch(text.substring(1), pattern.substring(2)) || isMatch(
									text.substring(1), pattern)))
						return true;
				}

			} else {

				if (text.length() > 0
						&& ((pattern.charAt(0) != '?' && pattern.charAt(0) != '*') || pattern
								.charAt(0) == '?')) {
					return isMatch(text.substring(1), pattern.substring(1));
				}
			}
		}

		return false;
	}
}
