package lc.comp.amz;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/subsets/
 * 
 * @author sivae
 * 
 */
public class L78_Subsets {
	public List<List<Integer>> subsets(int[] nums) {

		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		for (int i : nums) {
			if (map.containsKey(i)) {
				map.put(i, map.get(i) + 1);
			} else {
				map.put(i, 1);
			}
		}

		List<List<Integer>> result = new LinkedList<List<Integer>>();
		List<List<Integer>> newresult = new LinkedList<List<Integer>>();
		for (int k : map.keySet()) {
			for (int v = 0; v < map.get(k); v++) {
				if(!result.isEmpty()) {
					newresult.addAll(result);
					for(List<Integer> l : newresult) {
						l.add(k);
					}
					
					for(int i=result.size()-v; i>=0; i++) {
						newresult.add(result.get(i));
					}
					result.clear();
					result.addAll(newresult);
					newresult.clear();
				} else {
					
				}
			}
		}

	}
}
