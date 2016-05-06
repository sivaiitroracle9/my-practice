package leetcode.questions.basic;

import java.util.Stack;

/**
 * O(n)
 * https://leetcode.com/problems/trapping-rain-water/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */
public class TrippingRainWater {

	public static void main(String[] args) {
		TrippingRainWater tr = new TrippingRainWater();
		int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(tr.trap(height));
	}

	public int trap(int[] height) {

		Stack<Integer> height_stack = new Stack<Integer>();
		int top = 0;
		for (int i = 1; i < height.length; i++) {

			while (!height_stack.isEmpty()
					&& height[height_stack.peek()] >= height[top]
					&& height[top] <= height[i]) {
				top = height_stack.pop();
			}

			height_stack.push(top);
			top = i;
		}

		int water = 0;
		while (!height_stack.isEmpty()) {
			int min_height = Math.min(height[height_stack.peek()], height[top]);
			water += min_height * (top - height_stack.peek() - 1);
			for (int i = height_stack.peek() + 1; i < top; i++) {
				water -= height[i];
			}
			top = height_stack.pop();
		}

		if (top > 0) {
			int min_height = Math.min(height[0], height[top]);
			water += min_height * (top - 1);
			for (int i = 1; i < top; i++) {
				water -= height[i];
			}
		}
		return water;
	}
}
