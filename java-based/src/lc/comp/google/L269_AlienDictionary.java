package lc.comp.google;

import java.util.HashSet;
import java.util.Set;

public class L269_AlienDictionary {
	
	public static void main(String[] args) {
		L269_AlienDictionary lc = new L269_AlienDictionary();
		String[] words = {"wrt",
				  "wrf",
				  "er",
				  "ett",
				  "rftt"};
		System.out.println(lc.alienOrder(words));
	}
	
	public String alienOrder(String[] words) {

		int maxLenWord = 0;
		for (String word : words) {
			if (maxLenWord < word.length())
				maxLenWord = word.length();
		}

		String order = "";
		Set<Character> orderedSet = new HashSet<Character>();
		for (int i = 0; i < maxLenWord; i++) {
			for (String word : words) {
				if (word.length() > i) {
					if (order.equals("") || (order.charAt(order.length() - 1) != word.charAt(i) && !orderedSet.contains(word.charAt(i)))) {
						order += word.charAt(i);
						orderedSet.add(word.charAt(i));
					}

					
				}
			}
		}

		return order;
	}
}
