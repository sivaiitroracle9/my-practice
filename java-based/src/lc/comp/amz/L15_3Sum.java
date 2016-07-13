package lc.comp.amz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L15_3Sum {

	public static void main(String[] args) {

	}

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(nums.length < 3) return result;
		Arrays.sort(nums);

		for (int target = nums.length - 1; target >= 2; target--) {
			int left = 0, right = target - 1;
			while (left < right) {

				int temp = nums[left] + nums[right];
				if(temp == -1*nums[target]) {
					List<Integer> res = new ArrayList<Integer>(3);
					res.add(nums[left]);
					res.add(nums[right]);
					res.add(nums[target]);
					result.add(res);
					break;
				} else if (temp < -1*nums[target])
					left++;
				else if (temp > -1*nums[target])
					right--;
			}
		}

		return result;
	}

}
