package lc.questions.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode30_SubstringWithConcatenationOfWords {

	public static void main(String[] args) {
		Leetcode30_SubstringWithConcatenationOfWords lc = new Leetcode30_SubstringWithConcatenationOfWords();
		String s = "aaaaaaaa";
		String[] words = { "aa", "aa", "aa" };
		for (int out : lc.findSubstring(s, words)) {
			System.out.print(out + ", ");
		}
	}

	private List<Integer> findSubstring(String s, String[] words) {

		List<Integer> foundIndex = new ArrayList<Integer>();

		Map<String, Integer> word_count = new HashMap<String, Integer>();
		int word_length = words[0].length();
		if (s.length() < (words.length * word_length))
			return foundIndex;
		for (String word : words) {
			if (!word_count.containsKey(word)) {
				word_count.put(word, 1);
			} else {
				word_count.put(word, word_count.get(word) + 1);
			}
		}

		Map<String, Integer> currMap = new HashMap<String, Integer>();
		String kstring = null, temp = null;
		for (int i = 0; i < word_length; i++) {
			int count_words = 0;
			for (int left = i, right = i; right + word_length <= s.length(); right += word_length) {
				kstring = s.substring(right, right + word_length);
				if (word_count.containsKey(kstring)) {

					if (currMap.containsKey(kstring))
						currMap.put(kstring, currMap.get(kstring) + 1);
					else
						currMap.put(kstring, 1);
					if (currMap.get(kstring) <= word_count.get(kstring))
						count_words++;

					while (currMap.get(kstring) > word_count.get(kstring)) {
						temp = s.substring(left, left + word_length);
						currMap.put(temp, currMap.get(temp) - 1);
						left += word_length;
						if (currMap.get(temp) < word_count.get(temp))
							count_words--;
					}

					// This is the valid substring.
					if (count_words == words.length) {
						foundIndex.add(left);
					}

				} else {
					currMap.clear();
					count_words = 0;
					left = right + word_length;
				}
			}
			currMap.clear();
		}
		return foundIndex;
	}
}
