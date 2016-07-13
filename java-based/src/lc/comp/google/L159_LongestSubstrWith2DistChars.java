package lc.comp.google;

import java.util.HashMap;
import java.util.Map;

public class L159_LongestSubstrWith2DistChars {

	public static void main(String[] args) {

		String str = "ababffzzeee";
		System.out.println(lengthOfLongestSubstringTwoDistinct(str));
	}

	public static int lengthOfLongestSubstringTwoDistinct(String s) {

		char[] csarr = s.toCharArray();

		Map<Character, Integer> charcount = new HashMap<Character, Integer>();
		int left = 0, maxlen = 0;
		for (int right = 0; right < csarr.length; right++) {
			char rc = csarr[right];

			if (!charcount.containsKey(rc)) {
				while (charcount.size() == 2 && left <= right) {
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
