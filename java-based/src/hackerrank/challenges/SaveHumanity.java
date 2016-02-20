package hackerrank.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SaveHumanity {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			String text = sc.next();
			String pattern = sc.next();

			char[] rpat = new char[pattern.length()];
			for (int i = 0; i < pattern.length(); i++)
				rpat[i] = pattern.charAt(pattern.length() - 1 - i);
			char[] rtex = new char[text.length()];
			for (int i = 0; i < text.length(); i++)
				rtex[i] = text.charAt(text.length() - 1 - i);

			String S1 = pattern + "$" + text;
			String S2 = new String(rpat) + "$" + new String(rtex);
			List<Integer> slist = findIndex(Z(S1), Z(S2), pattern);

			if (slist.size() == 0)
				System.out.println("No Match!");
			else {
				String out = "";
				for (int i = 0; i < slist.size(); i++)
					out += slist.get(i) + " ";
				System.out.println(out.trim());
			}
		}
	}

	static List<Integer> findIndex(int[] Z1, int[] Z2, String pattern) {
		int p = pattern.length();
		List<Integer> sl = new ArrayList<Integer>();
		int N = Z1.length - p - 1;
		for (int i = 0; i < N && (N - i - p) >= 0; i++) {
			int match = Z1[i + p + 1] + Z2[N - i + 1];
			if ((match == (p - 1)) || (match == 2 * p))
				sl.add(i);
		}
		Collections.sort(sl);
		return sl;
	}

	static int[] Z(String s) {
		char[] S = s.toCharArray();
		int[] z = new int[s.length()];

		z[0] = 0;
		int L = 0, R = 0;
		for (int i = 1; i < s.length(); i++) {
			if (i > R) {
				L = R = i;
				while (R < s.length() && S[R] == S[R - L])
					R++;
				R--;
				z[i] = R - L + 1;
			} else {
				int k = i - L;
				if (z[k] < R - i + 1)
					z[i] = z[k];
				else {
					L = i;
					while (R < s.length() && S[R] == S[R - L])
						R++;
					R--;
					z[i] = R - L + 1;
				}

			}
		}

		return z;
	}
}
