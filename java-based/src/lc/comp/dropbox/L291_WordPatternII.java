package lc.comp.dropbox;

import java.util.HashMap;
import java.util.Map;

public class L291_WordPatternII {
	public static void main(String[] args) {
		String pattern = "abba";
		String str = "dogcatcatdog";
		System.out.println(wordPatternMatch(pattern, str));
	}

	public static boolean wordPatternMatch(String pattern, String str) {
		int N = pattern.length();
		int L = str.length();
		if (L % N != 0)
			return false;
		int wordlen = L / N;

		Map<Character, String> char_word_map = new HashMap<Character, String>();
		Map<String, Character> word_char_map = new HashMap<String, Character>();
		for (int i = 0; i < N; i++) {
			String word = str.substring(i * wordlen, (i + 1) * wordlen);
			if (!char_word_map.containsKey(pattern.charAt(i))
					&& !word_char_map.containsKey(word)) {
				char_word_map.put(pattern.charAt(i), word);
				word_char_map.put(word, pattern.charAt(i));
			} else if ((!char_word_map.containsKey(pattern.charAt(i)) && word_char_map
					.containsKey(word))
					|| (char_word_map.containsKey(pattern.charAt(i)) && !word_char_map
							.containsKey(word))
					|| !char_word_map.get(pattern.charAt(i)).equals(word)
					|| !word_char_map.get(word).equals(pattern.charAt(i)))
				return false;
		}
		return true;

	}
}
