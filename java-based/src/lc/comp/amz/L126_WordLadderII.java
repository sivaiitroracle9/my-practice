package lc.comp.amz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class L126_WordLadderII {

	public static void main(String[] args) {
		L126_WordLadderII lc = new L126_WordLadderII();
		Set<String> wordList = new HashSet<String>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		String beginWord = "hit";
		String endWord = "cog";

		for (List<String> list : lc.findLadders(beginWord, endWord, wordList)) {
			for (String word : list) {
				System.out.print(word + ", ");
			}
			System.out.println();
		}

	}

	public List<List<String>> findLadders(String beginWord, String endWord,
			Set<String> wordList) {

		wordList.add(endWord);
		wordList.add(beginWord);
		Map<String, Integer> distancemap = new HashMap<String, Integer>();
		Map<String, Boolean> visitmap = new HashMap<String, Boolean>();
		Map<String, Set<String>> parent_map = new HashMap<String, Set<String>>();
		for (String word : wordList) {
			distancemap.put(word, Integer.MAX_VALUE);
			visitmap.put(word, false);
		}

		Queue<String> queue = new LinkedList<String>();
		queue.offer(beginWord);
		distancemap.put(beginWord, 0);
		while (!queue.isEmpty()) {
			String word = queue.poll();
			visitmap.put(word, true);
			for (int i = 0; i < word.length(); i++) {
				char[] cbegin = word.toCharArray();
				for (char c = 'a'; c <= 'z'; c++) {
					cbegin[i] = c;
					String newword = new String(cbegin);
					if (distancemap.containsKey(newword)
							&& distancemap.get(word) + 1 <= distancemap
									.get(newword)) {
						distancemap.put(newword, distancemap.get(word) + 1);
						if (!parent_map.containsKey(newword))
							parent_map.put(newword, new HashSet<String>());
						parent_map.get(newword).add(word);

						queue.offer(newword);
					}
				}
			}

		}

		LinkedList<String> resultPath = new LinkedList<String>();
		List<List<String>> paths = new ArrayList<List<String>>();
		dfs(endWord, beginWord, parent_map, resultPath, paths);
		return paths;
	}

	public void dfs(String start, String stopWord,
			Map<String, Set<String>> parent_map, LinkedList<String> resultPath,
			List<List<String>> paths) {

		if (start.equals(stopWord)) {
			resultPath.add(0, stopWord);
			List<String> newpath = new ArrayList<String>(resultPath.size());
			newpath.addAll(resultPath);
			paths.add(newpath);
			resultPath.remove(0);
			return;
		}

		if (parent_map.containsKey(start)) {
			resultPath.add(0, start);
			for (String parent : parent_map.get(start)) {
				dfs(parent, stopWord, parent_map, resultPath, paths);
			}
			resultPath.remove(0);
		}

	}

}
