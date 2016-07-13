package lc.comp.amz;

public class L167_TwoSumII {
	public int[] twoSum(int[] numbers, int target) {

		int left = 0, right = numbers.length - 1;
		int[] result = new int[0];
		while (left < right) {
			if (numbers[left] + numbers[right] == target) {
				result = new int[2];
				result[0] = left + 1;
				result[1] = right + 1;
				break;
			} else if (numbers[left] + numbers[right] < target) {
				left++;
			} else {
				right--;
			}
		}
		return result;
	}
}
