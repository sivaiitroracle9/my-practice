package lc.questions.basic;

import java.util.HashMap;
import java.util.Map;

/**
 * O(n2)
 * https://leetcode.com/problems/minimum-window-substring/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */

public class MinimumWindowSubstring {

	public static void main(String[] args) {
		String S = "ADOBECODEBANC";
		String T = "ABC";

		System.out.println(minWindow("BBA", "AB"));
	}

	public static String minWindow(String S, String T) {

		Map<Character, Integer> TMAP = new HashMap<Character, Integer>();
		for (int i = 0; i < T.length(); i++) {
			if (!TMAP.containsKey(T.charAt(i)))
				TMAP.put(T.charAt(i), 0);
			TMAP.put(T.charAt(i), TMAP.get(T.charAt(i)) + 1);
		}

		Map<Character, Integer> SMAP = new HashMap<Character, Integer>();
		int left = -1, right = -1, start = -1, end = -1;
		for (int i = 0; i < S.length(); i++) {

			if (TMAP.containsKey(S.charAt(i))) {
				if (left == -1)
					left = i;

				if (right == -1) {
					int prev = SMAP.get(S.charAt(i)) != null ? SMAP.get(S
							.charAt(i)) : 0;
					SMAP.put(S.charAt(i), prev + 1);
					boolean invalid = false;
					for (char tc : TMAP.keySet()) {
						if (!SMAP.containsKey(tc)
								|| TMAP.get(tc) > (SMAP.get(tc) == null ? 0
										: SMAP.get(tc))) {
							invalid = true;
							break;
						}
					}
					if (!invalid) {
						right = i;
					}
				} else {
					int prev = SMAP.get(S.charAt(i)) != null ? SMAP.get(S
							.charAt(i)) : 0;
					SMAP.put(S.charAt(i), prev + 1);
					right = i;
				}

				while (left != -1 && right != -1 && left < right) {
					if (TMAP.containsKey(S.charAt(left))) {
						if (SMAP.get(S.charAt(left)) > TMAP.get(S.charAt(left))) {
							int prev = SMAP.get(S.charAt(left)) != null ? SMAP
									.get(S.charAt(left)) : 0;
							SMAP.put(S.charAt(left), prev - 1);
						} else
							break;
					}
					left++;
				}

				if ((left != -1 && right != -1)
						&& ((start == -1 && end == -1) || (start != -1
								&& end != -1 && (right - left + 1 < end - start
								+ 1)))) {
					start = left;
					end = right;
				}
			}

		}

		if (start == -1 || end == -1)
			return "";

		return S.substring(start, end + 1);
	}
}
