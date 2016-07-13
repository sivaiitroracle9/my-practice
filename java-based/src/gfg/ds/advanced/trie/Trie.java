package gfg.ds.advanced.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {

	class TrieNode {
		private Map<Character, TrieNode> map = new HashMap<Character, TrieNode>();
		private boolean isEnd = false;

		public Map<Character, TrieNode> getMap() {
			return map;
		}

		public void setMap(Map<Character, TrieNode> map) {
			this.map = map;
		}

		public boolean isEnd() {
			return isEnd;
		}

		public void setEnd(boolean isEnd) {
			this.isEnd = isEnd;
		}
	}

	private TrieNode root = null;

	public Trie() {
		root = new TrieNode();
	}

	public TrieNode getRoot() {
		return root;
	}

	public void insert(String word) {
		if (word == null)
			return;

		if (word.length() == 0) {
			root.setEnd(true);
			return;
		}

		TrieNode trie = root;
		char[] cword = word.toCharArray();
		for (char c : cword) {
			if (!trie.getMap().containsKey(c)) {
				trie.getMap().put(c, new TrieNode());
			}

			trie = trie.getMap().get(c);
		}

		trie.setEnd(true);
	}

	public boolean search(String word, boolean prefix) {
		if (word == null)
			return false;

		if (word.length() == 0) {
			return root.isEnd();
		}

		TrieNode trie = root;
		char[] cword = word.toCharArray();
		for (char c : cword) {
			if (!trie.getMap().containsKey(c)) {
				return false;
			}

			trie = trie.getMap().get(c);
		}

		if (prefix) {
			return true;
		} else {
			return trie.isEnd();
		}
	}

	public void delete(String word, boolean prefix) {
		if (word == null)
			return;
		if (word.length() == 0 && root.isEnd()) {
			root.setEnd(false);
			return;
		}

		delete_helper(word.toCharArray(), root, 0, prefix);
	}

	public boolean delete_helper(char[] word, TrieNode trie_node, int index,
			boolean prefix) {

		if (!trie_node.getMap().containsKey(word[index]))
			return false;

		TrieNode next_node = trie_node.getMap().get(word[index]);
		if (index == word.length - 1) {
			if (!prefix && next_node.getMap().size() > 0) {
				next_node.setEnd(false);
				return false;
			}

			if ((!prefix && next_node.getMap().size() == 0) || prefix) {
				trie_node.getMap().remove(word[index]);
				return true;
			}
		}

		boolean delete_node = delete_helper(word, next_node, index + 1, prefix);

		if (delete_node) {
			trie_node.getMap().remove(word[index]);
		}
		return delete_node;
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("the");
		trie.insert("a");
		trie.insert("there");
		trie.insert("answer");
		trie.insert("any");
		trie.insert("by");
		trie.insert("bye");
		trie.insert("their");

		System.out.println("prefix - t :" + trie.search("t", true));
		System.out.println("prefix - th :" + trie.search("th", true));
		System.out.println("prefix - an :" + trie.search("an", true));
		System.out.println("prefix - thei :" + trie.search("thei", true));
		System.out.println("whole word - any :" + trie.search("any", false));
		System.out.println("prefix - ab :" + trie.search("ab", true));
		System.out.println("whole word - byex :" + trie.search("byex", false));

		trie.delete("by", false);
		trie.delete("the", true);

		System.out.println("prefix - an :" + trie.search("an", true));
		System.out.println("whole word - any :" + trie.search("any", false));
		System.out.println("prefix - ab :" + trie.search("ab", true));
		System.out.println("whole word - byex :" + trie.search("byex", false));
	}
}
