package lc.questions.basic;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
		LongestSubstringWithoutRepeatingCharacters o = new LongestSubstringWithoutRepeatingCharacters();
		System.out.println(o.lengthOfLongestSubstring("abcabcbb"));
		System.out.println(o.lengthOfLongestSubstring("bbbbb"));
		System.out.println(o.lengthOfLongestSubstring("pwwkew"));
	}

	public int lengthOfLongestSubstring(String s) {
		Set<Character> set = new HashSet<Character>();
		int max = 0;
		for (int left = 0, right = 0; right < s.length() && left < s.length();) {
			char ch = s.charAt(right);
			if (!set.contains(ch)) {
				set.add(ch);
				right++;
				continue;
			} else {
				while (set.contains(ch)) {
					set.remove(s.charAt(left));
					left++;
				}
			}
			max = Math.max(max, right - left + 1);
		}
		return max;
	}

}
