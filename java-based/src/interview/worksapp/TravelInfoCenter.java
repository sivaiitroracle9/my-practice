package interview.worksapp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TravelInfoCenter {

	private static int n = 0;
	private static int m = 0;

	private static Map<Integer, Set<Integer>> grid = null;
	// private static int[][] distMatrix = null;
	private static boolean[] festiveCities = null;

	// private static Map<Integer, Integer> shortestDistMap = null;

	public static void main(String[] args) {

		TravelInfoCenter tc = new TravelInfoCenter();

		Scanner s = new Scanner(System.in);
		n = s.nextInt(); // rows
		m = s.nextInt(); // columns

		grid = new HashMap<Integer, Set<Integer>>(n);
		festiveCities = new boolean[n];
		tc.setFestive(1);

		for (int i = 1; i < n; i++) {

			int k1 = s.nextInt();
			int k2 = s.nextInt();

			if (grid.get(k1) == null) {
				grid.put(k1, new HashSet<Integer>());
			}

			if (grid.get(k2) == null) {
				grid.put(k2, new HashSet<Integer>());
			}

			grid.get(k1).add(k2);
			grid.get(k2).add(k1);

		}

		for (int i = 1; i <= m; i++) {

			int k1 = s.nextInt();
			int k2 = s.nextInt();

			if (k1 == 1) {
				tc.setFestive(k2);
			}

			if (k1 == 2) {
				System.out.println(tc.getShortestPath(k2));
			}
		}

	}

	private int getShortestPath(int city) {
		boolean[] visited = new boolean[n];

		return bfs(city, visited);
	}

	private int bfs(int city, boolean[] visited) {
		if (festiveCities[city - 1])
			return 0;

		Set<Integer> nextLevel = new HashSet<Integer>();
		Set<Integer> nextNextLevel = new HashSet<Integer>();
		int level = 0;

		nextLevel.addAll(grid.get(city));

		while (!nextLevel.isEmpty()) {
			level++;
			nextNextLevel.clear();

			for (int aCity : nextLevel) {
				if (visited[aCity - 1])
					continue;
				if (festiveCities[aCity - 1])
					return level;
				visited[aCity - 1] = true;
				nextNextLevel.addAll(grid.get(aCity));
			}

			nextLevel.clear();
			nextLevel.addAll(nextNextLevel);
		}

		return 0;

	}

	private void setFestive(int city) {
		festiveCities[city - 1] = true;
	}
}
