package algorithms.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimsMST {

	public static void main(String[] args) {
		Graph<Integer> graph = new Graph<Integer>(false);
	     /* graph.addEdge(0, 1, 4);
	        graph.addEdge(1, 2, 8);
	        graph.addEdge(2, 3, 7);
	        graph.addEdge(3, 4, 9);
	        graph.addEdge(4, 5, 10);
	        graph.addEdge(2, 5, 4);
	        graph.addEdge(1, 7, 11);
	        graph.addEdge(0, 7, 8);
	        graph.addEdge(2, 8, 2);
	        graph.addEdge(3, 5, 14);
	        graph.addEdge(5, 6, 2);
	        graph.addEdge(6, 8, 6);
	        graph.addEdge(6, 7, 1);
	        graph.addEdge(7, 8, 7);*/

	        graph.addEdge(1, 2, 3);
	        graph.addEdge(2, 3, 1);
	        graph.addEdge(3, 1, 1);
	        graph.addEdge(1, 4, 1);
	        graph.addEdge(2, 4, 3);
	        graph.addEdge(4, 5, 6);
	        graph.addEdge(5, 6, 2);
	        graph.addEdge(3, 5, 5);
	        graph.addEdge(3, 6, 4);

	        PrimsMST prims = new PrimsMST();
	        Collection<Edge<Integer>> edges = prims.primMST(graph);
	        for(Edge<Integer> edge : edges){
	            System.out.println(edge);
	        }
	}
	
	public List<Edge<Integer>> primMST(Graph<Integer> graph) {

		List<Edge<Integer>> result = new ArrayList<Edge<Integer>>();

		BinaryMinHeap<Vertex<Integer>> minheap = new BinaryMinHeap<Vertex<Integer>>();
		for (Vertex<Integer> v : graph.getAllVertex())
			minheap.add(Integer.MAX_VALUE, v);

		Map<Vertex<Integer>, Edge<Integer>> vertexToEdge = new HashMap<Vertex<Integer>, Edge<Integer>>();
		Vertex<Integer> start = graph.getAllVertex().iterator().next();
		minheap.decrease(start, 0);

		while (!minheap.empty()) {
			Vertex<Integer> current = minheap.extractMin();

			Edge<Integer> spanningTreeEdge = vertexToEdge.get(current);
			if (spanningTreeEdge != null) {
				result.add(spanningTreeEdge);
			}

			for (Edge<Integer> edge : current.getEdges()) {
				Vertex<Integer> adjVertex = (edge.getVertex1() == current) ? edge
						.getVertex2() : edge.getVertex1();

				if (minheap.containsData(adjVertex)
						&& minheap.getWeight(adjVertex) > (int) edge
								.getWeight()) {
					minheap.decrease(adjVertex, (int) edge.getWeight());
					vertexToEdge.put(adjVertex, edge);
				}
			}
		}
		return result;
	}
}
