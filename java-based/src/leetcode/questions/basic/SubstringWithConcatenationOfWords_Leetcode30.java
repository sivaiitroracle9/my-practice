package leetcode.questions.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfWords_Leetcode30 {

	public static void main(String[] args) {

	}

	private List<Integer> findSubstring(String s, String[] words) {

		List<Integer> foundIndex = new ArrayList<Integer>();

		Map<String, Integer> word_count = new HashMap<String, Integer>();
		int word_length = words[0].length();
		for (String word : words) {
			if (!word_count.containsKey(word)) {
				word_count.put(word, 1);
			} else {
				word_count.put(word, word_count.get(word) + 1);
			}
		}

		Map<String, Integer> currMap = new HashMap<String, Integer>();
		int count_words = 0;
		for (int i = 0; i < word_length; i++) {
			for (int left = i, right = i; right + word_length < s.length(); right += word_length) {
				String kstring = s.substring(right, right + word_length);
				if (word_count.containsKey(kstring)) {

					if (currMap.containsKey(kstring))
						currMap.put(kstring, currMap.get(kstring) + 1);
					else
						currMap.put(kstring, 1);
					count_words++;
					String left_kstring = s.substring(left, left + word_length);
					while (currMap.get(left_kstring) > word_count
							.get(left_kstring)) {
						currMap.put(left_kstring, currMap.get(left_kstring) - 1);
						count_words--;
						left += word_length;
						left_kstring = s.substring(left, left + word_length);
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
		}
		return foundIndex;
	}
}
