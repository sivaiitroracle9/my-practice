package lc.questions.basic;
/**
 * O(n)
 * 
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 */
import java.util.Stack;

public class LargestRectangleInHistogram_Stack {

	public static void main(String[] args) {
		int[] histogram = { 2, 1, 5, 6, 2, 3 };

		System.out.println(findLargeRectangleArea(histogram));
	}

	private static int findLargeRectangleArea(int[] histogram) {

		Stack<Integer> min_hist_stack = new Stack<Integer>();

		int i, max_area = 0, area = 0;

		for (i = 0; i < histogram.length;) {
			if (min_hist_stack.isEmpty()
					|| histogram[min_hist_stack.peek()] <= histogram[i]) {
				min_hist_stack.push(i);
			} else {
				int top = min_hist_stack.pop();

				// no bar lesser than the bar at 'top' and top-bar > i-bar
				if (min_hist_stack.isEmpty()) {
					area = i * histogram[top];
				}
				// peek-bar < top-bar and top-bar > i-bar
				// computing the area of the rectangle including the top-bar
				// completely.
				else {
					area = (i - 1 - (min_hist_stack.peek() + 1) + 1)
							* histogram[top];
				}
			}

			max_area = max_area < area ? area : max_area;
		}

		while (!min_hist_stack.isEmpty()) {
			int top = min_hist_stack.pop();

			if (min_hist_stack.isEmpty()) {
				area = i * histogram[top];
			} else {
				area = (i - 1 - (min_hist_stack.peek() - 1) + 1)
						* histogram[top];
			}
			max_area = max_area < area ? area : max_area;
		}

		return max_area;
	}
}
