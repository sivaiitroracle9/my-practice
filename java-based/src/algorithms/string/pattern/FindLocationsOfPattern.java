package algorithms.string.pattern;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FindLocationsOfPattern {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String text = sc.next();
		String pattern = sc.next();

		String ZString = pattern + "$" + text;
		int[] Z = Z(ZString);

		Set<Integer> indexes = findIndex(Z, ZString, pattern);
		for (int i : indexes)
			System.out.print(i + ", ");
	}

	static Set<Integer> findIndex(int[] Z, String ZString, String pattern) {
		int p = pattern.length();
		Set<Integer> indexes = new HashSet<Integer>();
		for (int i = p + 1; i < ZString.length(); i++) {
			if (Z[i] == p)
				indexes.add((i - p - 1));
		}
		return indexes;
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
