package worksapp.interview;

import java.util.Scanner;

public class StringCompression {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String word = sc.next();
		System.out.println(compression(word));
	}

	private static String compression(String word) {

		String cmp = "";
		int i = 0;
		while (i < word.length()) {
			cmp = cmp + word.charAt(i);
			int j = i + 1;
			while (j < word.length()) {
				if (word.charAt(j) == word.charAt(i)) {
					j++;
				} else {
					break;
				}
			}
			if (j - i > 1)
				cmp = cmp + (j - i);
			i = j;
		}

		return cmp;
	}

}
