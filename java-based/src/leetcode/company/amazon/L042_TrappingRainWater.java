package leetcode.company.amazon;

import java.util.Stack;

public class L042_TrappingRainWater {

	public static void main(String[] args) {

	}

	public int trap_2_ptr(int[] heights) {

		int left = 0, right = heights.length - 1;
		while (left < right && heights[left] < heights[left + 1])
			left++;
		while (right > left && heights[right] < heights[right - 1])
			right--;

		int maxLeft = 0, maxRight = 0, trap = 0;
		while (left < right) {
			if (heights[left] <= heights[right]) {
				if (heights[left] >= maxLeft)
					maxLeft = heights[left];
				trap = trap + maxLeft - heights[left];
				left++;
			} else {
				if (heights[right] >= maxRight)
					maxRight = heights[right];
				trap = trap + maxRight - heights[right];
				right--;
			}
		}

		return trap;
	}

	public int trap_2_stack(int[] heights) {
		int left = 0, right = heights.length - 1;
		while (left < right && heights[left] < heights[left + 1])
			left++;
		while (right > left && heights[right] < heights[right - 1])
			right--;

		Stack<Integer> pos_stack = new Stack<Integer>();
		int top = left;
		for (int i = left + 1; i <= right; i++) {

			while (!pos_stack.isEmpty()
					&& heights[pos_stack.peek()] >= heights[top]
					&& heights[top] <= heights[i]) {
				top = pos_stack.pop();
			}
			pos_stack.push(i);
			top = i;
		}

		int trap = 0;
		while (!pos_stack.isEmpty()) {
			int min = Math.min(heights[pos_stack.peek()], heights[top]);
			trap += min * (top - pos_stack.peek() + 1);
			for (int i = pos_stack.peek() + 1; i < top; i++)
				trap -= heights[i];
		}

		if (top > left) {
			int min = Math.min(heights[left], heights[top]);
			trap += min * (top - left + 1);
			for (int i = left + 1; i < top; i++)
				trap -= heights[i];
		}

		return trap;
	}
}
