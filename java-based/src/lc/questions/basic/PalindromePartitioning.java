package leetcode.questions.basic;

/**
 * 
 * https://leetcode.com/problems/palindrome-partitioning/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

	public static void main(String[] args) {
		String text = "banana";

		for (List<String> list : partition(text)) {
			System.out.print("[ ");
			for (String l : list) {
				System.out.print(l + ", ");
			}
			System.out.println(" ]");
		}
	}

	private static List<List<String>> partition(String text) {

		// Palindrome Validation Table O(n2) and Space O(n2)
		boolean[][] T = new boolean[text.length()][text.length()];
		if (text.length() >= 1)
			for (int i = 0; i < text.length(); i++)
				T[i][i] = true;

		if (text.length() >= 2)
			for (int i = 0; i + 1 < text.length(); i++)
				if (text.charAt(i) == text.charAt(i + 1))
					T[i][i + 1] = true;

		if (text.length() >= 3)
			for (int l = 3; l <= text.length(); l++) {
				for (int i = 0; i + l - 1 < text.length(); i++) {
					if (text.charAt(i) == text.charAt(i + l - 1)
							&& T[i + 1][i + l - 2])
						T[i][i + l - 1] = true;
				}
			}

		List<String> pal_part_list[] = new ArrayList[text.length() + 1];
		pal_part_list[0] = new ArrayList<String>();
		for (int l = 1; l <= text.length(); l++) {
			for (int i = 0; i + l - 1 < text.length(); i++) {
				if (T[i][i + l - 1]) {
					if (pal_part_list[i + l] == null)
						pal_part_list[i + l] = new ArrayList<String>();
					pal_part_list[i + l].add(text.substring(i, i + l));
				}
			}
		}

		List<List<String>> result = new ArrayList<List<String>>();
		dfs(pal_part_list, pal_part_list.length - 1, result,
				new ArrayList<String>(text.length()));

		return result;
	}

	private static void dfs(List<String>[] pal_part_list, int n,
			List<List<String>> result, List<String> list) {

		if (n == 0) {
			result.add(new ArrayList<String>(list));
			return;
		}

		for (String pal : pal_part_list[n]) {
			list.add(0, pal);
			dfs(pal_part_list, n - pal.length(), result, list);
			list.remove(0);
		}
	}
}
