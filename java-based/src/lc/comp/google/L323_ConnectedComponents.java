package lc.comp.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class L323_ConnectedComponents {
	public int countComponents(int n, int[][] edges) {

		Map<Integer, List<Integer>> edgesmap = new HashMap<Integer, List<Integer>>();
		for (int[] edge : edges) {
			if (!edgesmap.containsKey(edge[0]))
				edgesmap.put(edge[0], new ArrayList<Integer>());
			if (!edgesmap.containsKey(edge[1]))
				edgesmap.put(edge[1], new ArrayList<Integer>());
			edgesmap.get(edge[0]).add(edge[1]);
			edgesmap.get(edge[1]).add(edge[0]);
		}

		int count = 0;
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {

			if (!visited[i]) {
				count++;
				Queue<Integer> queue = new LinkedList<Integer>();
				queue.offer(i);
				while (!queue.isEmpty()) {
					int node = queue.poll();
					if (visited[node])
						continue;
					visited[node] = true;
					if(edgesmap.containsKey(node))
					for (int child : edgesmap.get(node))
						queue.offer(child);
				}
			}

		}

		return count;
	}

}
