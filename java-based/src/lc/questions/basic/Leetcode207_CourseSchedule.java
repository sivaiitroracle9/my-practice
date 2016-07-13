package lc.questions.basic;
/**
 * Time Complexity : O(V+E)
 * Space Conplexity: O(V)
 * 
 * https://leetcode.com/problems/course-schedule/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode207_CourseSchedule {

	public static void main(String[] args) {
		Leetcode207_CourseSchedule lc = new Leetcode207_CourseSchedule();

		int numCourses = 8;
		int[][] prerequisites = { { 1, 0 }, { 2, 6 }, { 1, 7 }, { 6, 4 },
				{ 7, 0 }, { 0, 5 } };
		System.out.println(lc.canFinish(numCourses, prerequisites));
	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {

		Map<Integer, List<Integer>> prereq_courses = new HashMap<Integer, List<Integer>>();

		for (int[] prereq : prerequisites) {
			if (!prereq_courses.containsKey(prereq[1])) {
				prereq_courses.put(prereq[1], new ArrayList<Integer>());
			}
			prereq_courses.get(prereq[1]).add(prereq[0]);
		}

		boolean[] visited = new boolean[numCourses];
		boolean[] recursiveStack = new boolean[numCourses];
		for (int node : prereq_courses.keySet()) {
			if (isCycleExists(prereq_courses, node, visited, recursiveStack)) {
				return false;
			}
		}

		return true;
	}

	private boolean isCycleExists(Map<Integer, List<Integer>> pre, int node,
			boolean[] visited, boolean[] recursiveStack) {

		if (!visited[node]) {
			visited[node] = true;
			recursiveStack[node] = true;
			if (pre.get(node) != null) {
				for (int child : pre.get(node)) {
					if (!visited[child]
							&& isCycleExists(pre, child, visited,
									recursiveStack)) {
						return true;
					} else if (recursiveStack[child]) {
						return true;
					}
				}
			}
		}
		recursiveStack[node] = false;
		return false;
	}

}
