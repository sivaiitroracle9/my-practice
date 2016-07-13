package leetcode.questions.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * https://leetcode.com/problems/word-search-ii/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */

public class WordSearchII {
	public static void main(String[] args) {

		WordSearchII ws = new WordSearchII();
		char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' },
				{ 'i', 'h', 'k', 'r' }, { 'i', 'f', 'l', 'v' } };

		String[] words = { "oath", "pea", "eat", "rain" };

		// char[][] b = { { 'a' } };
		// String[] w = { "a" };
		for (String s : ws.findWords(board, words))
			System.out.println(s);
	}

	private class TrieNode {
		boolean isEnd = false;
		Map<Character, TrieNode> cmap = new HashMap<Character, TrieNode>();
	}

	private TrieNode dict_root = null;

	private void find_word_helper(TrieNode trie_node, char[][] board,
			boolean[][] visited, int r, int c, Set<String> result, String res) {

		if (trie_node.isEnd) {
			result.add(res + board[r][c]);
		}

		visited[r][c] = true;

		if (r + 1 < board.length && trie_node.cmap.containsKey(board[r + 1][c])
				&& trie_node.cmap.size() > 0 && !visited[r + 1][c]) {
			find_word_helper(trie_node.cmap.get(board[r + 1][c]), board,
					visited, r + 1, c, result, res + board[r][c]);
		}

		if (r - 1 >= 0 && trie_node.cmap.containsKey(board[r - 1][c])
				&& trie_node.cmap.size() > 0 && !visited[r - 1][c]) {
			find_word_helper(trie_node.cmap.get(board[r - 1][c]), board,
					visited, r - 1, c, result, res + board[r][c]);
		}

		if (c + 1 < board[0].length
				&& trie_node.cmap.containsKey(board[r][c + 1])
				&& trie_node.cmap.size() > 0 && !visited[r][c + 1]) {
			find_word_helper(trie_node.cmap.get(board[r][c + 1]), board,
					visited, r, c + 1, result, res + board[r][c]);
		}

		if (c - 1 >= 0 && trie_node.cmap.containsKey(board[r][c - 1])
				&& trie_node.cmap.size() > 0 && !visited[r][c - 1]) {
			find_word_helper(trie_node.cmap.get(board[r][c - 1]), board,
					visited, r, c - 1, result, res + board[r][c]);
		}

		visited[r][c] = false;
	}

	private void createTrie(String[] words) {
		dict_root = new TrieNode();
		for (String word : words) {
			char[] cword = word.toCharArray();

			TrieNode trie_node = dict_root;
			for (char c : cword) {
				if (!trie_node.cmap.containsKey(c))
					trie_node.cmap.put(c, new TrieNode());
				trie_node = trie_node.cmap.get(c);
			}

			trie_node.isEnd = true;
		}
	}

	public List<String> findWords(char[][] board, String[] words) {

		createTrie(words);

		Map<Character, List<int[]>> board_map = new HashMap<Character, List<int[]>>();
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (!board_map.containsKey(board[r][c]))
					board_map.put(board[r][c], new ArrayList<int[]>());
				int[] rc = new int[2];
				rc[0] = r;
				rc[1] = c;
				board_map.get(board[r][c]).add(rc);
			}
		}
		boolean[][] visited = new boolean[board.length][board[0].length];
		Set<String> result = new HashSet<String>();
		for (char ch : board_map.keySet()) {
			if (board_map.containsKey(ch) && dict_root.cmap.containsKey(ch))
				for (int[] rc : board_map.get(ch)) {
					find_word_helper(dict_root.cmap.get(ch), board, visited,
							rc[0], rc[1], result, "");
				}
		}

		return new ArrayList<String>(result);

	}

}
