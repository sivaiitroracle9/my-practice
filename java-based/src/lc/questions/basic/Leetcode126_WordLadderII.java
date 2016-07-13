package lc.questions.basic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Leetcode126_WordLadderII {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String start = "hit";
		String end = "cog";
		String[] dict = { "hot", "dot", "dog", "lot", "cog" };
		System.out.println((new WordLadder()).ladder(start, end, dict));
	}

	public List<List<String>> findLadders(String beginWord, String endWord,
			Set<String> wordList) {

		HashMap<String, Boolean> wordListVisit = new HashMap<String, Boolean>();
		for (String word : wordList) {
			wordListVisit.put(word, false);
		}
		Map<Integer, Set<Word>> levelwords = new HashMap<Integer, Set<Word>>();

		Queue<Word> bfsqueue = new LinkedList<Leetcode126_WordLadderII.Word>();
		bfsqueue.offer(new Word(beginWord, 0));
		while (!bfsqueue.isEmpty()) {
			Word currword = bfsqueue.poll();

			if (currword.data.equals(endWord)) {

			}

			for (int i = 0; i < currword.data.length(); i++) {
				char[] curr = currword.data.toCharArray();
				for (char ch = 'a'; ch <= 'z'; ch++) {
					if (ch != currword.data.charAt(i)) {
						curr[i] = ch;
						String newcurr = new String(curr);
						if (wordListVisit.containsKey(newcurr)
								&& !wordListVisit.get(newcurr)) {
							
							Word newword = new Word(newcurr, currword.level + 1);
							
							if (levelwords.containsKey(newword.level)) {
								levelwords.get(newword.level).add(newword);
							}
							
							bfsqueue.offer(newword);
						}
					}
				}
			}
		}

	}

	class Ladder {
		List<Word> ladder = new LinkedList<Word>();

		public int addWord(Word word) {
			ladder.add(word);
			return ladder.size();
		}

		public int removeLast() {
			int size = ladder.size();
			if (size > 0)
				ladder.remove(size - 1);
			return ladder.size();
		}
	}

	class Word {
		Word(String word, int level) {
			data = word;
			this.level = level;
		}

		Set<Word> children = new HashSet<Word>();

		String data;
		int level = -1;
	}
}
