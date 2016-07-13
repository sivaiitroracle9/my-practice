package lc.comp.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class L310_MinimumHieghtedTrees {

	public static void main(String[] args) {
		L310_MinimumHieghtedTrees lc = new L310_MinimumHieghtedTrees();
		int[][] edges = { { 1, 0 }, { 1, 2 }, { 1, 3 } };
		int n = 4;
		lc.findMinHeightTrees_n(n, edges);
	}

	/**
	 * Remove leaves. the roots can be one or two only.
	 * 
	 * @param n
	 * @param edges
	 * @return
	 */
	public List<Integer> findMinHeightTrees_n(int n, int[][] edges) {

		List<Integer> result = new ArrayList<Integer>();
		if (n <= 0)
			return result;

		if (n == 1) {
			result.add(0);
			return result;
		}

		if (n == 2) {
			result.add(0);
			result.add(1);
			return result;
		}

		Map<Integer, Set<Integer>> edgesmap = new HashMap<Integer, Set<Integer>>(
				n);
		for (int[] edge : edges) {
			if (!edgesmap.containsKey(edge[0]))
				edgesmap.put(edge[0], new HashSet<Integer>());
			if (!edgesmap.containsKey(edge[1]))
				edgesmap.put(edge[1], new HashSet<Integer>());
			edgesmap.get(edge[0]).add(edge[1]);
			edgesmap.get(edge[1]).add(edge[0]);
		}

		Queue<Integer> leaves = new LinkedList<Integer>();
		for (int node : edgesmap.keySet())
			if (edgesmap.get(node).size() == 1)
				leaves.offer(node);
		Set<Integer> newleaves = new HashSet<Integer>();
		while (!leaves.isEmpty()) {
			int leaf = leaves.poll();
			for (int edge : edgesmap.get(leaf)) {
				edgesmap.get(edge).remove(leaf);
				if (edgesmap.get(edge).size() == 1)
					newleaves.add(edge);
			}
			edgesmap.remove(leaf);

			if (leaves.isEmpty() && !newleaves.isEmpty()) {
				if (edgesmap.size() <= 2) {
					result.addAll(edgesmap.keySet());
					return result;
				} else {
					leaves.addAll(newleaves);
					newleaves.clear();
				}
			}
		}

		return result;
	}

	public List<Integer> findMinHeightTrees_n2(int n, int[][] edges) {
		Map<Integer, List<Integer>> edgesmap = new HashMap<Integer, List<Integer>>(
				n);
		for (int[] edge : edges) {
			if (!edgesmap.containsKey(edge[0]))
				edgesmap.put(edge[0], new ArrayList<Integer>());
			if (!edgesmap.containsKey(edge[1]))
				edgesmap.put(edge[1], new ArrayList<Integer>());
			edgesmap.get(edge[0]).add(edge[1]);
			edgesmap.get(edge[1]).add(edge[0]);
		}

		Map<Integer, List<Integer>> heightsmap = new HashMap<Integer, List<Integer>>();
		int minheight = Integer.MAX_VALUE;
		boolean[] visit = new boolean[n];
		for (int i = 0; i < n; i++) {
			visit[i] = true;
			int height = getHeight(i, edgesmap, n);
			visit[i] = false;
			if (!heightsmap.containsKey(height)) {
				heightsmap.put(height, new ArrayList<Integer>());
			}
			heightsmap.get(height).add(i);
			if (height < minheight)
				minheight = height;
		}
		return heightsmap.get(minheight);
	}

	public int getHeight(int root, Map<Integer, List<Integer>> edgesmap, int n) {

		Queue<Integer> currlevel = new LinkedList<Integer>();
		Queue<Integer> nextlevel = new LinkedList<Integer>();
		currlevel.offer(root);
		int level = 1;
		boolean[] visit = new boolean[n];
		while (!currlevel.isEmpty()) {
			int node = currlevel.poll();
			visit[node] = true;
			if (edgesmap.containsKey(node))
				for (int child : edgesmap.get(node)) {
					if (!visit[child])
						nextlevel.offer(child);
				}

			if (currlevel.isEmpty() && !nextlevel.isEmpty()) {
				currlevel.addAll(nextlevel);
				level++;
				nextlevel.clear();
			}
		}
		return level;
	}
}
