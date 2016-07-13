package leetcode.questions.basic;
/**
 * 
 * https://leetcode.com/problems/top-k-frequent-elements/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;


public class TopKFreqElements {

	public static void main(String[] args) {
		int[] in = { 1, 1, 1, 2, 2, 3 };
		int k = 2;
		for(int i : topKFrequent(in, k)) {
			System.out.print(i + ", ");
		}
	}

	public static List<Integer> topKFrequent(int[] in, int k) {

		Map<Integer, Integer> in_cnt = new HashMap<Integer, Integer>();
		TreeMap<Integer, Set<Integer>> freq_in = new TreeMap<Integer, Set<Integer>>();

		for (int i : in) {
			if (!in_cnt.containsKey(i))
				in_cnt.put(i, 0);

			int prev = in_cnt.get(i) == null ? 0 : in_cnt.get(i);
			in_cnt.put(i, prev + 1);

			if (freq_in.containsKey(prev))
				freq_in.get(prev).remove(i);

			if (!freq_in.containsKey(prev + 1))
				freq_in.put(prev + 1, new HashSet<Integer>());

			freq_in.get(prev + 1).add(i);
		}

		NavigableMap<Integer, Set<Integer>> rMap = freq_in.descendingMap();
		List<Integer> topK = new ArrayList<Integer>(k);
		outer: for(int ky : rMap.keySet()) {
			for (int s : rMap.get(ky)) {
				topK.add(s);
				if(--k == 0) {
					break outer;
				}
			}
		}
		
		return topK;
	}
}
