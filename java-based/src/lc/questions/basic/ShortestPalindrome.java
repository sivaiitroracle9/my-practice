package lc.questions.basic;

/**
 * 
 * O(n) solution.
 * 
 * https://leetcode.com/problems/regular-expression-matching/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */

public class ShortestPalindrome {

	public static void main(String[] args) {
		String text = "ababbbabbaba";

		String sPal = shortestPal(text);
		System.out.println(sPal);
	}

	public static String shortestPal(String text) {

		// Create Input for kmp processing.
		char[] input = new char[2 * text.length() + 1];
		int writeIndex = 0;
		for (char ch : text.toCharArray())
			input[writeIndex++] = ch;
		input[writeIndex++] = '$';
		for (int i = text.length() - 1; i >= 0; i--)
			input[writeIndex++] = text.charAt(i);

		// KMP value of the last char. to get the longest prefix matched with
		// suffix.
		int remove_length = kmp(input);
		char[] sPal = new char[2 * text.length() - remove_length];
		for (int i = 0; i < text.length() - remove_length; i++)
			sPal[i] = text.charAt(text.length() - 1 - i);

		for (int i = 0; i < text.length(); i++)
			sPal[i + text.length() - remove_length] = text.charAt(i);

		return new String(sPal);
	}

	private static int kmp(char[] input) {

		int[] kmp = new int[input.length];
		kmp[0] = 0;
		int i = 0, j = 1;
		while (j < input.length) {
			if (input[i] == input[j]) {
				kmp[j++] = ++i;
			} else if (i == 0) {
				kmp[j++] = 0;
			} else {
				i = kmp[--i];
			}
		}

		return kmp[input.length - 1];
	}

}
