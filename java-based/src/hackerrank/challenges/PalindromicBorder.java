package hackerrank.challenges;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PalindromicBorder {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String text = sc.next();

		Map<String, Integer> palMap = findAllPalidromes(text);
		long sum = 0L;
		for (String key : palMap.keySet()) {
			int k = palMap.get(key);
			sum = sum + (k * (k - 1) / 2);
		}
		long mod = (long) Math.pow(10, 9) + 7;
		long rem = sum % mod;
		System.out.println(rem);
	}

	static Map<String, Integer> findAllPalidromes(String text) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		char[] tarr = text.toCharArray();
		boolean[][] T = new boolean[text.length()][text.length()];
		int l = 1;
		while (l <= text.length()) {
			for (int i = 0; i < text.length() && i + l - 1 < text.length(); i++) {
				int j = i + l - 1;
				if (l == 1)
					T[i][j] = true;

				else if (l == 2 && tarr[i] == tarr[j])
					T[i][j] = true;

				else if (T[i + 1][j - 1] && tarr[i] == tarr[j])
					T[i][j] = true;

				if (T[i][j]) {
					String pal = text.substring(i, j + 1);
					if (map.get(pal) == null)
						map.put(pal, 0);
					map.put(pal, map.get(pal) + 1);
				}

			}
			l++;
		}
		return map;
	}

}
