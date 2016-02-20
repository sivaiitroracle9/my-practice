package hackerrank.challenges;

import java.util.Scanner;

public class MorganAndAString {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while (T-- > 0) {
			char[] s1 = sc.next().toCharArray();
			char[] s2 = sc.next().toCharArray();

			char[] s = new char[s1.length + s2.length];
			merge(s, s1, s2);
			System.out.println(new String(s));
		}
	}

	static void merge(char[] s, char[] s1, char[] s2) {
		int p = 0, i1 = 0, i2 = 0;
		for (int i = 0; i < s1.length + s2.length; i++) {
			p = findMinIn(s1, s2, i1, i2);

			if (p == 1) {
				s[i] = s1[i1];
				i1++;
			}
			if (p == 2) {
				s[i] = s2[i2];
				i2++;
			}
		}
	}

	static int findMinIn(char[] s1, char[] s2, int i1, int i2) {
		if (i1 == s1.length)
			return 2;
		if (i2 == s2.length)
			return 1;

		if (s1[i1] == s2[i2]) {
			return findMinIn(s1, s2, i1 + 1, i2 + 1);
		} else if (s1[i1] > s2[i2]) {
			return 2;
		} else {
			return 1;
		}
	}
}
