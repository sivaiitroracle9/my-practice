package leetcode.company.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L049_GroupAnagrams {

	public List<List<String>> groupAnagrams(String[] strs) {

		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String str : strs) {
			char[] cstr = str.toCharArray();
			Arrays.sort(cstr);
			String sstr = new String(cstr);
			if (!map.containsKey(sstr))
				map.put(sstr, new ArrayList<String>());
			map.get(sstr).add(str);
		}
		
		List<List<String>> result = new ArrayList<List<String>>();
		for(String key : map.keySet()) {
			List<String> anagram_pairs = map.get(key);
			Collections.sort(anagram_pairs);
			result.add(anagram_pairs);
		}
		return result;
	}
}
