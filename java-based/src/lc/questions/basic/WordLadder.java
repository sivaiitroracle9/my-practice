package lc.questions.basic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class WordLadder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String start = "hit";
		String end = "cog";
		String[] dict = { "hot", "dot", "dog", "lot", "cog" };
		System.out.println((new WordLadder()).ladder(start, end, dict));
	}

	public int ladder(String start, String end, String[] dict) {
		Map<String, Boolean> wordSetVisit = new HashMap<String, Boolean>();
		for (String d : dict)
			wordSetVisit.put(d, false);

		Queue<Word> queue = new LinkedList<WordLadder.Word>();
		queue.add(new Word(start, 1));

		while (!queue.isEmpty()) {
			Word word = queue.poll();
			int level = word.steps;

			if (word.val.equals(end))
				return level;

			for (int i = 0; i < word.val.length(); i++) {
				char[] cw = word.val.toCharArray();
				for (char c = 'a'; c <= 'z'; c++) {
					cw[i] = c;
					String newWord = new String(cw);
					if (wordSetVisit.containsKey(newWord)
							&& !wordSetVisit.get(newWord).booleanValue()) {
						queue.add(new Word(newWord, level + 1));
						wordSetVisit.put(newWord, true);
					}
				}
			}
		}
		return 0;
	}

	class Word {
		String val = "";
		int steps = 0;

		public Word(String val, int steps) {
			this.val = val;
			this.steps = steps;
		}
	}
}
