package lc.comp.amz;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class L127_WordLadder {

	public static void main(String[] args) {

	}

	public int ladderLength(String beginWord, String endWord,
			Set<String> wordList) {

		Map<String, Integer> distancemap = new HashMap<String, Integer>();
		wordList.add(beginWord);
		wordList.add(endWord);
		HashMap<String, Boolean> visitmap = new HashMap<String, Boolean>();
		for (String word : wordList) {
			distancemap.put(word, Integer.MAX_VALUE);
			visitmap.put(word, false);
		}

		Queue<String> queue = new LinkedList<String>();
		queue.offer(beginWord);
		distancemap.put(beginWord, 0);
		while (!queue.isEmpty()) {
			String word = queue.poll();
			if (visitmap.get(word))
				continue;
			visitmap.put(word, true);

			for (int i = 0; i < word.length(); i++) {
				char[] cword = word.toCharArray();
				for (char c = 'a'; c <= 'z'; c++) {
					cword[i] = c;
					String newword = new String(cword);

					if (newword.equals(endWord)) {
						return distancemap.get(word) + 2;
					} else {
						if (visitmap.containsKey(newword)
								&& !visitmap.get(newword)) {
							if (distancemap.get(word) + 1 <= distancemap
									.get(newword))
								distancemap.put(newword,
										distancemap.get(word) + 1);
							queue.offer(newword);
						}
					}
				}
			}
		}

		if (distancemap.get(endWord) == Integer.MAX_VALUE)
			return 0;
		return distancemap.get(endWord) + 2;

	}
}
