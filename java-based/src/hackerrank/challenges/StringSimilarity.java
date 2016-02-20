package hackerrank.challenges;

import java.util.Scanner;

public class StringSimilarity {

	static long stringSimilarity(String a) {

		long sum = a.length();
		int[] z = Z(a);
		for (int i = 1; i < a.length(); i++)
			sum = sum + z[i];
		return sum;
	}

	static int[] Z(String text) {
		int[] z = new int[text.length()];

		int L = 0, R = 0;
		for (int i = 1; i < text.length(); i++) {
			if (i > R) {
				L = R = i;
				while (R < text.length() && text.charAt(R - L) == text.charAt(R))
					R++;
				R--;
				z[i] = R - L + 1;
			} else {
				int k = i - L;
				if (z[k] < R - i + 1)
					z[i] = z[k];
				else {
					L = i;
					while (R < text.length() && text.charAt(R - L) == text.charAt(R))
						R++;
					R--;
					z[i] = R - L + 1;
				}
			}
		}

		return z;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long res;

		int _t_cases = Integer.parseInt(in.nextLine());
		for (int _t_i = 0; _t_i < _t_cases; _t_i++) {
			String _a = in.nextLine();
			res = stringSimilarity(_a);
			System.out.println(res);
		}
	}
}
