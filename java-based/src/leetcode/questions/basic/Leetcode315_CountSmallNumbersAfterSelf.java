package leetcode.questions.basic;

/**
 * 
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Leetcode315_CountSmallNumbersAfterSelf {

	public static void main(String[] args) {
		Leetcode315_CountSmallNumbersAfterSelf l = new Leetcode315_CountSmallNumbersAfterSelf();
		int[] nums = { 5, 2, 6, 1 };
		for (int i : l.countSmaller(nums))
			System.out.print(i + ", ");
	}

	public List<Integer> countSmaller(int[] nums) {

		List<Integer> counts = new ArrayList<Integer>(nums.length);
		Map<Integer, List<Integer>> val_index = new TreeMap<Integer, List<Integer>>();
		for (int i = 0; i < nums.length; i++) {
			counts.add(0);
			if (!val_index.containsKey(nums[i]))
				val_index.put(nums[i], new ArrayList<Integer>());
			val_index.get(nums[i]).add(i);
		}

		int count = 0;
		for (int key : val_index.keySet()) {
			for (int i : val_index.get(key)) {
				counts.set(i, count);
			}
			count += val_index.get(key).size();
		}
		return counts;
	}

}