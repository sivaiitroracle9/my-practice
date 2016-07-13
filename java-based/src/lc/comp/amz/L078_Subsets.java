package lc.comp.amz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class L078_Subsets {

	public static void main(String[] args) {
		L078_Subsets lc = new L078_Subsets();
		int[] nums = { 0 };
		lc.subsets(nums);
	}

	public List<List<Integer>> subsets(int[] nums) {

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(new LinkedList<Integer>());
		if (nums.length == 0)
			return result;

		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			List<List<Integer>> temp = new ArrayList<List<Integer>>();
			for (List<Integer> list : result) {
				List<Integer> newlist = new LinkedList<Integer>(list);
				newlist.add(nums[i]);
				temp.add(newlist);
			}
			result.addAll(temp);
		}
		return result;
	}

}
