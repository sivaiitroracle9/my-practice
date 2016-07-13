package geeksforgeeks.datastructures.advanced.trie;

import geeksforgeeks.datastructures.advanced.trie.Trie.TrieNode;

/**
 * 
 * http://www.geeksforgeeks.org/longest-prefix-matching-a-trie-based-solution-in
 * -java/
 * 
 * @email sivaiitroracle9@gmail.com
 * @author Siva Kumar Edupuganti.
 *
 */
public class LongestPrefixMatching_Trie {

	public static void main(String[] args) {

		String[] dict = { "are", "area", "base", "cat", "cater", "basement" };

		Trie trie = new Trie();
		for (String word : dict)
			trie.insert(word);

		String[] inputs = { "caterer", "basement", "are", "arex", "basemexz",
				"xyz" };

		for (String input : inputs)
			System.out.println("Longest Prefix Word for :: " + input + " = "
					+ getMatchingPrefix(input, trie));
	}

	private static String getMatchingPrefix(String input, Trie trie) {

		TrieNode trie_node = trie.getRoot();

		int index = -1;
		for (int i = 0; i < input.length(); i++) {
			if (trie_node.getMap().containsKey(input.charAt(i))) {
				trie_node = trie_node.getMap().get(input.charAt(i));
			} else {
				break;
			}

			if (trie_node.isEnd() && index < i)
				index = i;
		}

		return input.substring(0, index + 1);
	}
}
