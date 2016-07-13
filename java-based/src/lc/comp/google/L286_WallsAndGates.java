package lc.comp.google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class L286_WallsAndGates {

	public static void main(String[] args) {
		L286_WallsAndGates lc = new L286_WallsAndGates();
		int INF = Integer.MAX_VALUE;
		int[][] rooms = { { INF, -1, 0, INF }, { INF, INF, INF, -1 },
				{ INF, -1, INF, -1 }, { 0, -1, INF, INF } };
		lc.wallsAndGates(rooms);
	}

	public void wallsAndGates(int[][] rooms) {
		
		if(rooms.length == 0 || rooms[0].length == 0) return;
		
		int INF = Integer.MAX_VALUE;
		Map<Integer, Set<Integer>> edgesmap = new HashMap<Integer, Set<Integer>>();
		int m = rooms.length, n = rooms[0].length;
		edgesmap.put(0, new HashSet<Integer>());
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (rooms[i][j] == INF) {
					int room = i * n + j + 1;
					if (!edgesmap.containsKey(room))
						edgesmap.put(room, new HashSet<Integer>());
					if (i + 1 < m && rooms[i + 1][j] != -1) {
						int nextroom = (i + 1) * n + j + 1;
						if (rooms[i + 1][j] == 0) {
							edgesmap.get(room).add(0);
							edgesmap.get(0).add(room);
						} else {
							edgesmap.get(room).add(nextroom);
						}
					}

					if (i - 1 >= 0 && rooms[i - 1][j] != -1) {
						int nextroom = (i - 1) * n + j + 1;
						if (rooms[i - 1][j] == 0) {
							edgesmap.get(room).add(0);
							edgesmap.get(0).add(room);
						} else {
							edgesmap.get(room).add(nextroom);
						}
					}

					if (j + 1 < n && rooms[i][j + 1] != -1) {
						int nextroom = i * n + j + 1 + 1;
						if (rooms[i][j + 1] == 0) {
							edgesmap.get(room).add(0);
							edgesmap.get(0).add(room);
						} else {
							edgesmap.get(room).add(nextroom);
						}
					}

					if (j - 1 >= 0 && rooms[i][j - 1] != -1) {
						int nextroom = i * n + j - 1 + 1;
						if (rooms[i][j - 1] == 0) {
							edgesmap.get(room).add(0);
							edgesmap.get(0).add(room);
						} else {
							edgesmap.get(room).add(nextroom);
						}
					}
				}
			}
		}

		Map<Integer, Integer> distancemap = new HashMap<Integer, Integer>();
		Queue<Integer> currlevel = new LinkedList<Integer>();
		Set<Integer> nextlevel = new HashSet<Integer>();
		currlevel.offer(0);
		int level = 0;
		boolean[] visitednodes = new boolean[m * n + 1];
		visitednodes[0] = true;
		while (!currlevel.isEmpty()) {
			int node = currlevel.poll();
			distancemap.put(node, level);
			if (edgesmap.containsKey(node))
				for (int child : edgesmap.get(node)) {
					if (!visitednodes[child]) {
						visitednodes[child] = true;
						nextlevel.add(child);
					}
				}

			if (currlevel.isEmpty() && !nextlevel.isEmpty()) {
				currlevel.addAll(nextlevel);
				level++;
				nextlevel.clear();
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (rooms[i][j] == INF) {
					int room = i * n + j + 1;
					if(distancemap.containsKey(room))
						rooms[i][j] = distancemap.get(room);
				}
			}
		}
	}
}
