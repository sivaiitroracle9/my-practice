package algorithms.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KruskalsMST {

	class EdgeComparator implements Comparator<Edge<Integer>> {

		@Override
		public int compare(Edge<Integer> edge1, Edge<Integer> edge2) {

			return (int) (edge1.getWeight() - edge2.getWeight());
		}

	}

	public List<Edge<Integer>> getMST(Graph<Integer> graph) {

		DisjointSet disjointSet = new DisjointSet();
		for (Vertex<Integer> v : graph.getAllVertex()) {
			disjointSet.makeSet(v.getId());
		}

		List<Edge<Integer>> edges = graph.getAllEdges();
		Collections.sort(edges, new EdgeComparator());

		List<Edge<Integer>> resultEdges = new ArrayList<Edge<Integer>>();
		for (Edge<Integer> edge : edges) {
			Vertex<Integer> v1 = edge.getVertex1();
			Vertex<Integer> v2 = edge.getVertex2();

			long parent1 = disjointSet.findSet(v1.getId());
			long parent2 = disjointSet.findSet(v2.getId());
			if (parent1 != parent2) {
				disjointSet.union(v1.getId(), v2.getId());
				resultEdges.add(edge);
			}
		}
		return resultEdges;
	}

	public static void main(String args[]) {
		Graph<Integer> graph = new Graph<Integer>(false);
		graph.addEdge(1, 2, 4);
		graph.addEdge(1, 3, 1);
		graph.addEdge(2, 5, 1);
		graph.addEdge(2, 6, 3);
		graph.addEdge(2, 4, 2);
		graph.addEdge(6, 5, 2);
		graph.addEdge(6, 4, 3);
		graph.addEdge(4, 7, 2);
		graph.addEdge(3, 4, 5);
		graph.addEdge(3, 7, 8);
		KruskalsMST mst = new KruskalsMST();
		List<Edge<Integer>> result = mst.getMST(graph);
		for (Edge<Integer> edge : result) {
			System.out.println(edge.getVertex1() + " " + edge.getVertex2());
		}
	}

}
