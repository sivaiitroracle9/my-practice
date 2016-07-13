package lc.comp.google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L261_GraphValidTree {

	public static void main(String[] args) {
		L261_GraphValidTree lc = new L261_GraphValidTree();
		int n = 5;
		int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 1, 3 }, { 1, 4 } };
		System.out.println(lc.validTree(n, edges));
	}

	public boolean validTree(int n, int[][] edges) {

		if (n <= 0)
			return false;
		if (n == 1)
			return true;

		Map<Integer, Set<Integer>> edgesmap = new HashMap<Integer, Set<Integer>>();
		for (int[] edge : edges) {
			if (!edgesmap.containsKey(edge[0]))
				edgesmap.put(edge[0], new HashSet<Integer>());
			if (!edgesmap.containsKey(edge[1]))
				edgesmap.put(edge[1], new HashSet<Integer>());
			edgesmap.get(edge[0]).add(edge[1]);
			edgesmap.get(edge[1]).add(edge[0]);
		}

		boolean[] travesedpath = new boolean[n];
		boolean[] visitednodes = new boolean[n];

		if (isCycle(edgesmap, travesedpath, visitednodes, 0, -1))
			return false;
		for (int i = 0; i < n; i++) {
			if (!visitednodes[i])
				return false;
		}

		return true;
	}

	public boolean isCycle(Map<Integer, Set<Integer>> edgesmap,
			boolean[] travesedpath, boolean[] visitednodes, int start,
			int parent) {
		visitednodes[start] = true;
		if (edgesmap.containsKey(start)) {
			travesedpath[start] = true;
			for (int child : edgesmap.get(start)) {
				if (child == parent)
					continue;
				if (!visitednodes[child]
						&& isCycle(edgesmap, travesedpath, visitednodes, child,
								start))
					return true;
				else if (travesedpath[child])
					return true;
			}
			travesedpath[start] = false;
		}

		return false;
	}
}
