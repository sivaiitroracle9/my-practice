package geeksforgeeks.datastructures.advanced.trie;

import geeksforgeeks.datastructures.advanced.trie.Trie.TrieNode;

import java.util.ArrayList;
import java.util.List;

public class UniqueRowsBoolMat {

	public static void main(String[] args) {
		int[][] input = { { 0, 1, 0, 0, 1 }, { 1, 0, 1, 1, 0 },
				{ 0, 1, 0, 0, 1 }, { 1, 0, 1, 0, 0 } };

		for (List<Integer> row : uniqueRows(input)) {
			for (int i : row)
				System.out.print(i + " ");
			System.out.println();
		}
	}

	public static ArrayList<ArrayList<Integer>> uniqueRows(int[][] input) {

		Trie trie = new Trie();
		for (int i = 0; i < input.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < input[0].length; j++) {
				sb.append(input[i][j]);
			}
			trie.insert(sb.toString());
		}

		ArrayList<ArrayList<Integer>> uList = new ArrayList<ArrayList<Integer>>();
		uniqueR(trie.getRoot(), 0, input[0].length, uList,
				new char[input[0].length]);
		return uList;
	}

	public static void uniqueR(TrieNode trie_node, int i, int N,
			ArrayList<ArrayList<Integer>> uList, char[] result) {

		if (i == N) {
			ArrayList<Integer> res = new ArrayList<Integer>();
			for (char c : result) {
				int x = 0;
				if (c == '1')
					x = 1;
				else if (c == '0')
					x = 0;
				res.add(x);
			}
			uList.add(res);
			return;
		}

		for (char c : trie_node.getMap().keySet()) {
			result[i] = c;
			uniqueR(trie_node.getMap().get(c), i + 1, N, uList, result);
		}
	}

}
