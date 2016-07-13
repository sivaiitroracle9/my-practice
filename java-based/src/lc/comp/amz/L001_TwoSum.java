package leetcode.company.amazon;

import java.util.HashMap;
import java.util.Map;

public class L001_TwoSum {

	public static void main(String[] args) {

	}

	public int[] twoSum(int[] nums, int target) {

		int[] result = new int[2];
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {

			if (map.containsKey(target - nums[i])) {

				result[0] = map.get(target - nums[i]);
				result[1] = i;
				break;
			}
			map.put(nums[i], i);

		}
		return result;
	}
}
