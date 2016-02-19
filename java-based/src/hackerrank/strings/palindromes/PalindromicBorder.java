/**
 Problem Statement

A border of a string is a proper prefix of it that is also a suffix. For example:

a and abra are borders of abracadabra,
kan and kankan are borders of kankankan.
de is a border of decode.
Note that decode is not a border of decode because it's not proper.

A palindromic border is a border that is palindromic. For example,

a and ana are palindromic borders of anabanana,
l, lol and lolol are palindromic borders of lololol.
Let's define P(s) as the number of palindromic borders of string s. For example, if s= lololol, then P(s)=3.

Now, a string of length N has exactly N(N+1)/2 non-empty substrings (we count substrings as distinct if they are of different lengths or are in different positions, even if they are the same string). Given a string s, consisting only of the first 8 lowercase letters of the English alphabet, your task is to find the sum of P(s′) for all the non-empty substrings s′ of s. In other words, you need to find:
∑1≤i≤j≤NP(si…j)
where si…j is the substring of s starting at position i and ending at position j.

Since the answer can be very large, output the answer modulo 109+7.

Input Format

The first line contains a string consisting of N characters.

Output Format

Print a single integer: the remainder of the division of the resulting number by 109+7.

Constraints

1≤N≤105 
All characters in the string can be any of the first 8 lowercase letters of the English alphabet (abcdefgh).
 */

package hackerrank.strings.palindromes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PalindromicBorder {

	private static char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		String input = s.next().trim();

		long sum = 0L;
		for (String p : getPal()) {
			sum += getPalBorderLength(input, p);
		}

		System.out.println((int)(sum % (Math.pow(10, 9) + 7)));

	}

	static long getPalBorderLength(String input, String pattern) {
		long count = 0L;

		int idx = -1;
		while (true) {
			idx++;
			idx = input.indexOf(pattern, idx);
			if (idx == -1)
				break;
			count++;
		}

		return (count * (count - 1)) / 2;

	}

	static Collection<String> getPal() {
		Set<String> palindromes = new HashSet<String>();
		for (int i = 1; i <= 8; i++) {
			palindromes.addAll(getPalindromes(i));
		}
		return palindromes;
	}

	static Collection<String> getPalindromes(int len) {

		Set<String> palindromes = new HashSet<String>();

		if (len == 1) {
			for (int j = 0; j < alphabet.length; j++)
				palindromes.add(String.valueOf(alphabet[j]));
			return palindromes;
		} else if (len == 2) {
			for (int j = 0; j < alphabet.length; j++)
				palindromes.add(String.valueOf(alphabet[j]) + String.valueOf(alphabet[j]));
			palindromes.addAll(getPalindromes(1));
			return palindromes;
		} else {
			Collection<String> pali2 = getPalindromes(len - 2);
			for (String p : pali2) {
				for (int j = 0; j < alphabet.length; j++)
					palindromes.add(String.valueOf(alphabet[j]) + p + String.valueOf(alphabet[j]));
			}
			palindromes.addAll(pali2);
		}
		return palindromes;
	}

}
