package gfg.ds.graphs.mst;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class MST_Prims {

	public void mst1(int[][] graph) {

	}

	public void mst2(final Map<Integer, Map<Integer, Integer>> graph) {
		final PriorityQueue<Node> pq = new PriorityQueue<>(graph.size(),
				new Comparator<Node>() {

					@Override
					public int compare(Node o1, Node o2) {
						return o1.distance - o2.distance;
					}

				});
		final Map<Integer, Node> vertex = new HashMap<>();
		final Map<Integer, Integer> parents = new HashMap<>(graph.size());
		graph.keySet().stream().forEach(v -> parents.put(v, null));

		vertex.put(0, new Node(0, 0));
		pq.offer(vertex.get(0));
		while (!pq.isEmpty()) {
			final Node pn = pq.poll();
			for (int e : graph.get(pn.vertex).keySet()) {
				if (vertex.get(e) == null) {
					vertex.put(e, new Node(e, Integer.MAX_VALUE));
					pq.offer(vertex.get(e));
				}

				if (vertex.get(e).distance > graph.get(pn.vertex).get(e)) {
					vertex.get(e).distance = graph.get(pn.vertex).get(e);
					parents.put(e, pn.vertex);
				}
			}
		}
		for (Entry<Integer, Integer> p : parents.entrySet())
			System.out.println(p.getKey() + " - " + p.getValue());
	}

	class Node {
		int vertex;
		int distance;

		Node(int vertex, int distance) {
			this.vertex = vertex;
			this.distance = distance;
		}
	}

	public static void main(String[] args) {
		MST_Prims t = new MST_Prims();
		int graph[][] = new int[][] { { 0, 2, 0, 6, 0 }, { 2, 0, 3, 8, 5 },
				{ 0, 3, 0, 0, 7 }, { 6, 8, 0, 0, 9 }, { 0, 5, 7, 9, 0 }, };
		// t.mst1(graph);

		Map<Integer, Map<Integer, Integer>> g = new HashMap<Integer, Map<Integer, Integer>>();
		for (int i = 0; i < graph.length; i++) {
			Map<Integer, Integer> map = new HashMap<>();
			for (int j = 0; j < graph.length; j++) {
				if (graph[i][j] != 0)
					map.put(j, graph[i][j]);
			}
			g.put(i, map);
		}
		t.mst2(g);
	}
}
