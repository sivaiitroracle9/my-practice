package lc.comp.dropbox;

import java.util.HashMap;
import java.util.Map;

public class L290_WordPattern {

	public static void main(String[] args) {
		String pattern = "abba";
		String str = "dog dog dog dog";
		System.out.println(wordPattern(pattern, str));
	}

	public static boolean wordPattern(String pattern, String str) {
		String[] words = str.split(" ");

		if (words.length != pattern.length())
			return false;

		Map<Character, String> char_word_map = new HashMap<Character, String>();
		Map<String, Character> word_char_map = new HashMap<String, Character>();
		for (int i = 0; i < pattern.length(); i++) {
			if (!char_word_map.containsKey(pattern.charAt(i))
					&& !word_char_map.containsKey(words[i])) {
				char_word_map.put(pattern.charAt(i), words[i]);
				word_char_map.put(words[i], pattern.charAt(i));
			} else if ((!char_word_map.containsKey(pattern.charAt(i)) && word_char_map
					.containsKey(words[i]))
					|| (char_word_map.containsKey(pattern.charAt(i)) && !word_char_map
							.containsKey(words[i]))
					|| !char_word_map.get(pattern.charAt(i)).equals(words[i])
					|| !word_char_map.get(words[i]).equals(pattern.charAt(i)))
				return false;
		}
		return true;
	}
}
