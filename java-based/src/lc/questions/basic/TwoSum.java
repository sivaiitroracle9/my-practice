package lc.questions.basic;

import java.util.Arrays;

public class TwoSum {
	public static void main(String[] args) {
		int[] numbers = { 2, 7, 11, 15 };
		int sum = 9;
		
		/**
		 * O(nlongn + n) method
		 */
		Arrays.sort(numbers);
		int left = 0, right = numbers.length - 1;
		while (left < right) {
			if(numbers[left] + numbers[right] == sum) {
				break;
			} else if(numbers[left] + numbers[right]>sum) {
				right--;
			} else {
				left++;
			}
			
			if(left==right) {
				left = -1;
				right = -1;
				break;
			}
		}
		System.out.println("left=" + left + ", right=" + right);
	}
}
