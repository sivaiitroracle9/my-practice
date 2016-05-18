package algorithms.graph;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraShortestPath {

	public static void main(String[] args) {

		Graph<Integer> graph = new Graph<Integer>(false);
		graph.addEdge(1, 2, 5);
		graph.addEdge(2, 3, 2);
		graph.addEdge(1, 4, 9);
		graph.addEdge(1, 5, 3);
		graph.addEdge(5, 6, 2);
		graph.addEdge(6, 4, 2);
		graph.addEdge(3, 4, 3);

		DijkstraShortestPath dsp = new DijkstraShortestPath();
		Vertex<Integer> source = null;
		for (Vertex<Integer> v : graph.getAllVertex()) {
			if (v.getId() == 1) {
				source = v;
				break;
			}
		}
		Map<Vertex<Integer>, Integer> distace = dsp.shortestPath(graph, source);
		System.out.println(distace);
	}

	public Map<Vertex<Integer>, Integer> shortestPath(Graph<Integer> graph,
			Vertex<Integer> source) {

		Map<Vertex<Integer>, Integer> distance = new HashMap<Vertex<Integer>, Integer>();
		Map<Long, Vertex<Integer>> vertices = new HashMap<Long, Vertex<Integer>>();
		Map<Vertex<Integer>, Vertex<Integer>> parent = new HashMap<Vertex<Integer>, Vertex<Integer>>();

		Map<Long, Integer> vertDistance = new HashMap<Long, Integer>();
		for (Vertex<Integer> v : graph.getAllVertex()) {
			vertices.put(v.getId(), v);
			vertDistance.put(v.getId(), Integer.MAX_VALUE);
		}
		BinaryMinHeap minHeap = new BinaryMinHeap(vertDistance);

		minHeap.decreaseKey(source.getId(), 0);

		parent.put(vertices.get(source.getId()), null);

		while (minHeap.getMinKey() != -1) {
			long current = minHeap.getMinKey();
			distance.put(vertices.get(current), minHeap.getMinValue());
			minHeap.extractMinKey();
			for (Edge<Integer> edge : vertices.get(current).getEdges()) {
				Vertex<Integer> adj = edge.getVertex1() == vertices
						.get(current) ? edge.getVertex2() : edge.getVertex1();
				if (!minHeap.containsKey(adj.getId()))
					continue;
				int newdistance = distance.get(vertices.get(current))
						+ (int) edge.getWeight();

				if (newdistance < minHeap.getPriority(adj.getId())) {
					minHeap.decreaseKey(adj.getId(), newdistance);
					parent.put(vertices.get(current), vertices.get(adj.getId()));
				}
			}
		}
		return distance;
	}

	class BinaryMinHeap {

		Map<Long, Integer> key_priority_map = new HashMap<Long, Integer>();
		Map<Long, Integer> key_index_map = new HashMap<Long, Integer>();
		long heap[] = null;
		private int size = 0;

		public BinaryMinHeap(Map<Long, Integer> map) {
			this.key_priority_map.putAll(map);
			size = map.keySet().size();
			heap = new long[size];
			int i = 0;
			for (long key : map.keySet()) {
				key_index_map.put(key, i);
				heap[i++] = key;
			}

			heapifyup(size - 1);
		}

		public long getMinKey() {
			if (size > 0) {
				return heap[0];
			}
			return -1;
		}

		public int getMinValue() {
			if (size > 0) {
				return key_priority_map.get(heap[0]);
			}
			return Integer.MAX_VALUE;
		}

		public boolean containsKey(long key) {
			return key_index_map.containsKey(key);
		}

		public int getPriority(long key) {
			return key_priority_map.get(key);
		}

		public long extractMinKey() {
			long key = -1;
			if (size > 0) {
				key = heap[0];

				if (size == 1) {
					--size;
				} else {
					heap[0] = heap[--size];
					heapifydown(0);
				}
				key_index_map.remove(key);
				key_priority_map.remove(key);
			}

			return key;
		}

		private void decreaseKey(long key, int priority) {
			key_priority_map.put(key, priority);
			heapifyup(key_index_map.get(key));
		}

		private void heapifyup(int i) {
			int parent = i / 2;

			if (key_priority_map.get(heap[parent]) > key_priority_map
					.get(heap[i])) {
				long temp = heap[parent];
				key_index_map.put(temp, i);
				key_index_map.put(heap[i], parent);
				heap[parent] = heap[i];
				heap[i] = temp;
				heapifyup(parent);
			}
		}

		private void heapifydown(int i) {
			int left = 2 * i + 1;
			int right = 2 * i + 2;

			int MIN = key_priority_map.get(heap[i]);
			if (left <= size - 1) {
				MIN = Math.min(MIN, key_priority_map.get(heap[left]));
			} else
				return;

			if (right <= size - 1) {
				MIN = Math.min(MIN, key_priority_map.get(heap[right]));
			}

			if (MIN == key_priority_map.get(heap[i]))
				return;
			else if (MIN == key_priority_map.get(heap[left])) {
				long temp = heap[left];
				key_index_map.put(temp, i);
				key_index_map.put(heap[i], left);
				heap[left] = heap[i];
				heap[i] = temp;
				heapifydown(left);
			} else {
				long temp = heap[right];
				key_index_map.put(temp, i);
				key_index_map.put(heap[i], right);
				heap[right] = heap[i];
				heap[i] = temp;
				heapifydown(right);
			}
		}
	}
}
