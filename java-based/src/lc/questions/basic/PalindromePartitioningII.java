package leetcode.questions.basic;
/**
 * 
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */
import java.util.Arrays;

public class PalindromePartitioningII {
	public static void main(String[] args) {
		String text = "banana";
		System.out.println(min_cuts(text));
	}

	private static int min_cuts(String text) {

		// Palindrome Validation Table O(n2) and Space O(n2)
		boolean[][] T = new boolean[text.length()][text.length()];
		if (text.length() >= 1)
			for (int i = 0; i < text.length(); i++)
				T[i][i] = true;

		if (text.length() >= 2)
			for (int i = 0; i + 1 < text.length(); i++)
				if(text.charAt(i)==text.charAt(i+1)) T[i][i + 1] = true;

		if (text.length() >= 3)
			for (int l = 3; l <= text.length(); l++) {
				for (int i = 0; i + l - 1 < text.length(); i++) {
					if (text.charAt(i) == text.charAt(i + l - 1)
							&& T[i + 1][i + l - 2])
						T[i][i + l - 1] = true;
				}
			}

		
		// minimum cut table O(n2) time + O(n) space.
		int[] min_cuts = new int[text.length()];
		Arrays.fill(min_cuts, Integer.MAX_VALUE);

		if (T[0][0])
			min_cuts[0] = 0;
		for (int i = 1; i < text.length(); i++) {
			if (T[0][i])
				min_cuts[i] = 0;
			else
				for (int j = 0; j < i; j++) {
					if (T[j + 1][i] && min_cuts[i] > min_cuts[j] + 1)
						min_cuts[i] = min_cuts[j] + 1;
				}
		}
		return min_cuts[text.length() - 1];
	}
}
