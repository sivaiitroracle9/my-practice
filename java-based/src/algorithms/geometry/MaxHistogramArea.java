package algorithms.geometry;

/**
 * 
 * Maximum Histogram Area.
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 */
import java.util.Deque;
import java.util.LinkedList;

public class MaxHistogramArea {

	public static void main(String[] args) {
		int[] hist = { 2, 2, 2, 6, 1, 5, 4, 2, 2, 2, 2 };

		int area = maxAreaRectangle(hist);
		System.out.println(area);
		assert area == 12;
	}

	private static int maxAreaRectangle(int[] hist) {

		Deque<Integer> stack = new LinkedList<Integer>();
		int max = 0;
		int area;
		int i = 0;
		while (i < hist.length) {

			if (stack.isEmpty() || hist[stack.peekFirst()] <= hist[i]) {
				stack.offerFirst(i);
				i++;
			}

			else {
				int top = stack.pollFirst();

				if (stack.isEmpty()) {
					area = hist[top] * i;
				} else {
					area = hist[top] * (i - stack.peekFirst() - 1);
				}

				max = Math.max(area, max);
			}

		}

		while (!stack.isEmpty()) {
			int top = stack.pollFirst();

			if (stack.isEmpty()) {
				area = hist[top] * i;
			} else {
				area = hist[top] * (i - stack.peekFirst() - 1);
			}

			max = Math.max(max, area);
		}
		return max;
	}

}
