package lc.questions.basic;

/**
 * Time Complexity : O(V+E)
 * Space Conplexity: O(V)
 * 
 * https://leetcode.com/problems/course-schedule-ii/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Leetcode210_CourseScheduleII {

	public static void main(String[] args) {
		Leetcode210_CourseScheduleII lc = new Leetcode210_CourseScheduleII();
		int numCourses = 8;
		int[][] prerequisites = { { 1, 0 }, { 2, 6 }, { 1, 7 }, { 6, 4 },
				{ 7, 0 }, { 0, 5 } };
		for (int i : lc.findOrder(numCourses, prerequisites)) {
			System.out.print(i + ", ");
		}
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {

		Map<Integer, List<Integer>> prereqmap = new HashMap<Integer, List<Integer>>();

		for (int[] pre : prerequisites) {
			if (!prereqmap.containsKey(pre[1]))
				prereqmap.put(pre[1], new ArrayList<Integer>());
			prereqmap.get(pre[1]).add(pre[0]);
		}

		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[numCourses];
		boolean[] recursionStack = new boolean[numCourses];
		for (int i = 0; i < numCourses; i++) {
			if (!findOrder(prereqmap, i, visited, recursionStack, stack)) {
				return null;
			}
		}

		int[] res = new int[numCourses];
		int i = 0;
		while (!stack.isEmpty() && i < numCourses) {
			res[i++] = stack.pop();
		}
		return res;
	}

	private boolean findOrder(Map<Integer, List<Integer>> premap, int node,
			boolean[] visited, boolean[] rec_stack, Stack<Integer> result) {

		if (!visited[node]) {
			visited[node] = true;
			rec_stack[node] = true;

			if (premap.get(node) != null) {
				for (int child : premap.get(node)) {
					if (!visited[child]
							&& !findOrder(premap, child, visited, rec_stack,
									result)) {
						return false;
					} else if (rec_stack[child])
						return false;
				}
			}
			result.push(node);
		}

		rec_stack[node] = false;
		return true;
	}

	private boolean isCycleExists(Map<Integer, List<Integer>> premap, int node,
			boolean[] visited, boolean[] rec_stack) {

		if (!visited[node]) {
			visited[node] = true;
			rec_stack[node] = true;

			if (premap.get(node) != null) {
				for (int child : premap.get(node)) {
					if (!visited[child]
							&& isCycleExists(premap, child, visited, rec_stack)) {
						return true;
					}

					if (rec_stack[child])
						return true;
				}
			}
		}

		rec_stack[node] = false;
		return false;
	}
}
