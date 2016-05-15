package algorithms.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoyerMoore {

	public static void main(String[] args) {

	}

	/*
	 * private int[] good_suffix(String pattern) {
	 * 
	 * }
	 */

	private Map<Character, Integer> bad_charecter(String pattern) {
		Map<Character, Integer> bcmap = new HashMap<Character, Integer>();
		for (int i = 0; i < pattern.length(); i++) {
			bcmap.put(pattern.charAt(i), i);
		}
		return bcmap;
	}

	private List<Integer> search(String text, String pattern) {
		
		Map<Character, Integer> bcmap = bad_charecter(pattern);
		
		List<Integer> foundList = new ArrayList<Integer>();

		for (int i = 0; i <= text.length()-pattern.length();) {
			boolean found = true;
			for(int j=pattern.length()-1; i>=0;) {
				if(text.charAt(i)!=pattern.charAt(j+i)) {
					found = false;
					break;
				}
			}
			if(found) {
				foundList.add(i);
			} else {
				// TODO
			}
		}

	}
}
