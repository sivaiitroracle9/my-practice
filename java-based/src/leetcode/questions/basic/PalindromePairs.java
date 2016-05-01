package leetcode.questions.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * https://leetcode.com/problems/palindrome-pairs/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */
public class PalindromePairs {

	public static void main(String[] args) {
		String[] words = { "abcd", "dcba", "lls", "s", "sssll" };

		for (List<Integer> indx : pal_pairs_good(words)) {
			System.out.println("[ " + indx.get(0) + ", " + indx.get(1) + " ]");
		}
	}

	// O(k*2m*d) d<=m {m=length of word, k=size of dict}
	// This can also be implemented using KMP to find the non-palindromes 
	// and lookup in the map.
	private static List<List<Integer>> pal_pairs_good(String[] words) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		for (int i = 0; i < words.length; i++)
			wordMap.put(words[i], i);
		boolean[][] valid = new boolean[words.length][words.length];
		for(int i=0; i<words.length; i++) valid[i][i] = true;
		
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j <= words[i].length(); j++) {
				String ws1 = words[i].substring(0, j);
				String ws2 = words[i].substring(j);

				if (isPalindrome(ws1)) {
					String rev = new StringBuilder(ws2).reverse().toString();
					if (wordMap.get(rev) != null && !valid[wordMap.get(rev)][i]) {
						result.add(Arrays.asList(wordMap.get(rev), i));
						valid[wordMap.get(rev)][i] = true;
					}
				}

				if (isPalindrome(ws2)) {
					String rev = new StringBuilder(ws1).reverse().toString();
					if (wordMap.get(rev) != null && !valid[i][wordMap.get(rev)]) {
						result.add(Arrays.asList(i, wordMap.get(rev)));
						valid[i][wordMap.get(rev)] = true;
					}
				}
			}
		}
		return result;
	}

	private static boolean isPalindrome(String word) {
		int left = 0, right = word.length() - 1;
		while (left <= right) {
			if (word.charAt(left) != word.charAt(right))
				return false;
			left++;
			right--;
		}
		return true;
	}

	private static List<int[]> palindrome_pairs_bruteforce(String[] words) {

		List<int[]> palList = new ArrayList<int[]>();

		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words.length; j++) {
				if (i != j) {
					String pal = words[i] + words[j];
					int len = pal.length();
					boolean valid = true;
					int start = len / 2 - 1;
					while (start >= 0) {
						if (pal.charAt(start) != pal.charAt(len - start - 1)) {
							valid = false;
							break;
						}
						start--;
					}
					if (valid) {
						int[] indx = new int[2];
						indx[0] = i;
						indx[1] = j;
						palList.add(indx);
					}
				}
			}
		}

		return palList;
	}
}
