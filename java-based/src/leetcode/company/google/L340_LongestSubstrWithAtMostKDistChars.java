package leetcode.company.google;

import java.util.HashMap;
import java.util.Map;

public class L340_LongestSubstrWithAtMostKDistChars {
	public static void main(String[] args) {

		String str = "ababffzzeee";
		int k = 2;
		System.out.println(lengthOfLongestSubstringKDistinct(str, k));
	}

	public static int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s.length() == 0 || k == 0)
			return 0;
		char[] csarr = s.toCharArray();
		Map<Character, Integer> charcount = new HashMap<Character, Integer>();
		int left = 0, maxlen = 0;
		for (int right = 0; right < csarr.length; right++) {
			char rc = csarr[right];

			if (!charcount.containsKey(rc)) {
				while (charcount.size() == k && left <= right) {
					char lc = csarr[left];
					if (charcount.get(lc) == 1) {
						charcount.remove(lc);
					} else {
						charcount.put(lc, charcount.get(lc) - 1);
					}
					left++;
				}
				charcount.put(rc, 0);
			}
			charcount.put(rc, charcount.get(rc) + 1);

			if (maxlen < right - left + 1)
				maxlen = right - left + 1;
		}

		return maxlen;
	}
}
