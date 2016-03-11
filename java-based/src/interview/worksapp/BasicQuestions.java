/**
 * http://javarevisited.blogspot.sg/2015/01/top-20-string-coding-interview-question-programming-interview.html
 */

package interview.worksapp;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BasicQuestions {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// anagramCheck(sc.next(), sc.next());
		// System.out.println(out);
		// findDuplicateChars(sc.next());
		// firstNonRepeatChar(sc.next());
		// reverseString(sc.next());
		// reverseStringRecursion(sc.next(), "", sc.nextInt());
		// replaceSpace(sc.nextLine());
		// reverseWords(sc.nextLine());
		// reverseWordsInBuilt(sc.nextLine());
		// palindrome(sc.next());
		// searchIndexOf_On2(sc.next(), sc.next());
		// searchIndexOf_On(sc.next(), sc.next());
		interleaving(sc.next(), sc.next(), sc.next());
	}

	public static void interleaving(String word1, String word2, String intWord) {
		char[] cw1 = word1.toCharArray();
		char[] cw2 = word2.toCharArray();
		char[] cw = intWord.toCharArray();

		if (cw.length != cw1.length + cw2.length) {
			System.out.println(false);
			return;
		}

		boolean[][] itable = new boolean[cw1.length + 1][cw2.length + 1];

		itable[0][0] = true;

		for (int i = 1; i <= cw1.length; i++) {
			if (cw1[i - 1] == cw[i - 1] && itable[i - 1][0])
				itable[i][0] = true;
			else
				itable[i][0] = false;
		}

		for (int j = 1; j <= cw2.length; j++) {
			if (cw2[j - 1] == cw[j - 1] && itable[0][j - 1])
				itable[0][j] = true;
			else
				itable[0][j] = false;
		}

		for (int i = 1; i <= cw1.length; i++) {
			for (int j = 1; j <= cw2.length; j++) {
				int k = i + j - 1;
				if (cw1[i - 1] == cw[k] && itable[i - 1][j])
					itable[i][j] = true;
				else if (cw2[j - 1] == cw[k] && itable[i][j - 1])
					itable[i][j] = true;
				else
					itable[i][j] = false;
			}
		}
		System.out.println(itable[cw1.length][cw2.length]);
	}

	public static void searchIndexOf_On(String text, String word) {
		char[] ct = text.toCharArray();
		char[] cw = word.toCharArray();

		// LPS
		int[] lps = new int[cw.length];
		lps[0] = 0;
		int i = 1, j = 0;
		while (i < cw.length) {
			if (cw[i] == cw[j]) {
				lps[i] = j + 1;
				j++;
				i++;
			} else {
				if (j == 0) {
					lps[i] = 0;
					i++;
				} else {
					j = lps[j - 1];
				}
			}
		}

		// KMP
		int index = -1;
		int k = 0, m = 0;
		while (k < ct.length && m < cw.length) {
			if (ct[k] == cw[m]) {
				k++;
				m++;
			} else {
				if (m == 0) {
					k++;
				} else {
					m = lps[m - 1];
				}
			}
			if (m == cw.length) {
				index = k - m;
				break;
			}
		}

		System.out.println(index);
	}

	public static void searchIndexOf_On2(String text, String word) {
		char[] ct = text.toCharArray();
		char[] cw = word.toCharArray();

		int index = -1;
		for (int i = 0; i < ct.length - cw.length; i++) {
			boolean found = true;
			for (int j = 0; j < cw.length; j++) {
				if (ct[i + j] != cw[j]) {
					found = false;
					break;
				}
			}
			if (found) {
				index = i;
				break;
			}
		}
		System.out.println(index);
	}

	public static void palindrome(String word) {
		System.out.println(palrecursion(word.toCharArray(), 0, word.length() - 1));
	}

	public static boolean palrecursion(char[] word, int left, int right) {
		if (left >= right)
			return true;

		if (word[left] != word[right])
			return false;
		else {
			return palrecursion(word, left + 1, right - 1);
		}
	}

	public static void reverseWordsInBuilt(String line) {
		String[] words = line.split(" ");
		List<String> list = Arrays.asList(words);
		Collections.reverse(list);
		String reverse = "";
		for (String word : list)
			reverse += word + " ";
		reverse = reverse.trim();
		System.out.println(reverse);
	}

	public static void reverseWords(String line) {
		String[] words = line.split(" ");
		String reverse = "";
		for (int i = 0; i < words.length; i++)
			reverse = words[i] + " " + reverse;
		System.out.println(reverse.trim());
	}

	public static void replaceSpace(String word) {
		char[] cw = word.toCharArray();
		String s = "";
		for (int i = 0; i < cw.length; i++) {
			if (cw[i] == ' ')
				s += "%20";
			else
				s += cw[i];
		}
		System.out.println(s);
	}

	public static void anagramCheck(String word1, String word2) {
		if (word1.length() != word2.length()) {
			System.out.println(false);
			return;
		}
		char[] cw1 = word1.toCharArray();
		char[] cw2 = word2.toCharArray();
		Arrays.sort(cw1);
		Arrays.sort(cw2);
		System.out.println(Arrays.equals(cw1, cw2));
	}

	public static void findDuplicateChars(String word) {
		char[] cw = word.toCharArray();
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < cw.length; i++) {
			if (map.get(cw[i]) == null)
				map.put(cw[i], 0);
			map.put(cw[i], map.get(cw[i]) + 1);
		}

		for (char ch : map.keySet())
			if (map.get(ch) > 1)
				System.out.println(ch + " : " + map.get(ch));
	}

	public static void firstNonRepeatChar(String word) {
		char[] cw = word.toCharArray();
		LinkedHashMap<Character, Integer> lMap = new LinkedHashMap<Character, Integer>();
		for (int i = 0; i < cw.length; i++) {
			if (lMap.get(cw[i]) == null)
				lMap.put(cw[i], 0);
			lMap.put(cw[i], lMap.get(cw[i]) + 1);
		}

		for (char ch : lMap.keySet())
			if (lMap.get(ch) == 1) {
				System.out.println(ch);
				break;
			}
	}

	public static void reverseString(String s) {
		char[] cs = s.toCharArray();
		char[] rcs = new char[cs.length];
		for (int i = 0; i < cs.length; i++)
			rcs[cs.length - 1 - i] = cs[i];
		System.out.println(new String(rcs));
	}

	public static void reverseStringRecursion(String word, String rev, int len) {
		if (rev.length() == len) {
			System.out.println(rev);
			return;
		}
		rev = word.charAt(0) + rev;
		reverseStringRecursion(word.substring(1), rev, len);
	}
}
