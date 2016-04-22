package algorithms.graph;

public class Graph<T> {
	
	boolean isDirected;
	
	Graph(boolean isDirected) {
		this.isDirected = isDirected;
	}
	
	class Vertex<T> {
		
	}
	
	class Edge<T> {
		
		private Vertex<T> vertex1;
		private Vertex<T> vertex2;
		
		Edge(Vertex<T> v1, Vertex<T> v2) {
			this.vertex1 = v1;
			this.vertex2 = v2;
		}
		
	}

}
