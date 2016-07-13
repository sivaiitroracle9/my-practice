package lc.questions.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * O(n2)
 * https://leetcode.com/problems/word-search/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */

public class WordSearch {

	public static void main(String[] args) {
/*		char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' },
				{ 'A', 'D', 'E', 'E' } };

		System.out.println("ABCCED = " + wordSearch(board, "ABCCED"));
		System.out.println("SEE = " + wordSearch(board, "SEE"));
		System.out.println("ABCB = " + wordSearch(board, "ABCB"));*/
		
		char[][] board = {{'b'}};
		System.out.println(wordSearch(board, "a"));
	}

	private static boolean wordSearch(char[][] board, String word) {
		Map<Character, List<int[]>> char_map = new HashMap<Character, List<int[]>>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (!char_map.containsKey(board[i][j]))
					char_map.put(board[i][j], new ArrayList<int[]>());

				int[] rc = new int[2];
				rc[0] = i;
				rc[1] = j;
				char_map.get(board[i][j]).add(rc);
			}
		}

		// search word.
		char[] cword = word.toCharArray();
		boolean[][] visited = new boolean[board.length][board[0].length];
		if(char_map.containsKey(cword[0]))
		for (int[] rc : char_map.get(cword[0])) {
			if (word_search_helper(board, cword, rc[0], rc[1], 0, visited))
				return true;
		}

		return false;
	}

	private static boolean word_search_helper(char[][] board, char[] cword,
			int r, int c, int i, boolean[][] visited) {

		if (board[r][c] != cword[i])
			return false;
		
		if (i == cword.length - 1)
			return true;

		visited[r][c] = true;
		if (r + 1 < board.length && !visited[r + 1][c]) {
			if (word_search_helper(board, cword, r + 1, c, i + 1, visited))
				return true;
		}

		if (r - 1 >= 0 && !visited[r - 1][c]) {
			if (word_search_helper(board, cword, r - 1, c, i + 1, visited))
				return true;
		}

		if (c + 1 < board[0].length && !visited[r][c + 1]) {
			if (word_search_helper(board, cword, r, c + 1, i + 1, visited))
				return true;
		}

		if (c - 1 >= 0 && !visited[r][c - 1]) {
			if (word_search_helper(board, cword, r, c - 1, i + 1, visited))
				return true;
		}
		visited[r][c] = false;
		return false;
	}
}
