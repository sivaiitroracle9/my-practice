package worksapp.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LongestSubStrAppearMoreThanOnce {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String word = sc.next();
		System.out.println(LSO(word));
	}

	static String LSO(String word) {
		String lto = "";
		int N = word.length();

		List<String> sufixArray = new ArrayList<String>(N);
		for (int i = 0; i < N; i++) {
			sufixArray.add(word.substring(N - 1 - i));
		}

		Collections.sort(sufixArray);
		int maxL = 0;
		for (int i = 0; i <= N - 2; i++) {
			String sbi = sufixArray.get(i);
			String sbi1 = sufixArray.get(i + 1);
			if (sbi.length() > maxL || sbi1.length() > maxL) {
				int k = 0;
				int numL = 0;
				while (sbi.length() > k && sbi1.length() > k) {
					if (sbi.charAt(k) == sbi1.charAt(k)) {
						k++;
						numL++;
					} else {
						break;
					}
				}

				if (numL > maxL) {
					maxL = numL;
					lto = sbi.substring(0, numL);
				}

			}
		}

		return lto;
	}

}
