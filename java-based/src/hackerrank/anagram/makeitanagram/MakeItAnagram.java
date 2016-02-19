package hackerrank.anagram.makeitanagram;

import java.util.Scanner;

public class MakeItAnagram {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		String A = s.next().trim();

		String B = s.next().trim();

		System.out.println(noOfDeletions(A, B));

	}

	static int noOfDeletions(String A, String B) {

		char[] A_arr = A.toCharArray();
		char[] B_arr = B.toCharArray();

		int[] A_length = new int[256];
		int[] B_length = new int[256];

		for (char c : A_arr) {
			A_length[c - 'a'] = A_length[c - 'a'] + 1;
		}

		for (char c : B_arr) {
			B_length[c - 'a'] = B_length[c - 'a'] + 1;
		}

		int deletions = 0;
		for (int i = 0; i < 256; i++) {
			deletions += Math.abs(A_length[i] - B_length[i]);
		}

		return deletions;
	}

}
