package interview.worksapp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class MaximumInWindow {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int N = sc.nextInt();
		int[] nums = new int[N];
		for (int i = 0; i < N; i++)
			nums[i] = sc.nextInt();

		int[] maxArr = slidingWindow(nums, k);
		for (int i = 0; i < maxArr.length; i++)
			System.out.print(maxArr[i] + " ");
	}

	private static int[] slidingWindow(int[] nums, int k) {
		int[] maxArray;
		if (nums.length <= k) {
			maxArray = new int[1];
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < nums.length; i++)
				max = Math.max(max, nums[i]);
			maxArray[0] = max;
			return maxArray;
		}

		maxArray = new int[nums.length - k + 1];
		Deque<Integer> de = new ArrayDeque<Integer>();
		int maxI = 0;
		for (int i = 1; i < k; i++) {
			if (nums[maxI] < nums[i])
				maxI = i;
		}
		de.add(maxI);
		maxArray[0] = nums[maxI];

		for (int i = k; i < nums.length; i++) {
			while (de.peekFirst() < (i - k + 1))
				de.removeFirst();

			while (!de.isEmpty() && nums[de.peekLast()] < nums[i])
				de.removeLast();
			de.addLast(i);
			maxArray[i - k + 1] = nums[de.peekFirst()];
		}
		return maxArray;
	}
}
