package algorithms.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TravelingSalesmanHeldKarp {

	private int INFINITY = Integer.MAX_VALUE;

	public static void main(String[] args) {
		TravelingSalesmanHeldKarp ts = new TravelingSalesmanHeldKarp();

		int[][] distance = new int[5][5];

		ts.minCost(distance);
	}

	private static class Index {
		int currentVertex;
		Set<Integer> vertexSet;

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || o.getClass() != getClass())
				return false;

			Index index = (Index) o;

			if (currentVertex != index.currentVertex)
				return false;

			return !(vertexSet != null ? !vertexSet.equals(index.vertexSet)
					: index.vertexSet != null);
		}

		@Override
		public int hashCode() {
			int result = currentVertex;
			result = 31 * result
					+ (vertexSet != null ? vertexSet.hashCode() : 0);
			return result;
		}

		private static Index createIndex(int vertex, Set<Integer> vertexSet) {
			Index i = new Index();
			i.currentVertex = vertex;
			i.vertexSet = vertexSet;
			return i;
		}
	}

	public int minCost(int[][] distance) {

		Map<Index, Integer> minCostDP = new HashMap<TravelingSalesmanHeldKarp.Index, Integer>();
		Map<Index, Integer> parent = new HashMap<TravelingSalesmanHeldKarp.Index, Integer>();

		List<Set<Integer>> allSets = generateCombination(distance.length);

		for (Set<Integer> set : allSets) {
			for (int currentVertix = 1; currentVertix < distance.length; currentVertix++) {
				if(set.contains(currentVertix)) continue;
				
				Index index = Index.createIndex(currentVertix, set);
			}
		}

	}

	private List<Set<Integer>> generateCombination(int n) {
		List<Set<Integer>> allSets = new ArrayList<Set<Integer>>();
		int[] input = new int[n];
		for (int i = 0; i < n; i++) {
			input[i] = i;
		}
		int[] result = new int[input.length];
		generateCombination(input, result, 0, 0, allSets);
		Collections.sort(allSets, new SetSizeComparator());
		return allSets;
	}

	private static class SetSizeComparator implements Comparator<Set<Integer>> {

		@Override
		public int compare(Set<Integer> o1, Set<Integer> o2) {
			return o1.size() - o2.size();
		}

	}

	private void generateCombination(int[] input, int[] result, int start,
			int pos, List<Set<Integer>> allSets) {
		if (pos == input.length)
			return;

		Set<Integer> set = createSet(result, pos);
		allSets.add(set);

		for (int i = start; i < input.length; i++) {
			result[pos] = input[i];
			generateCombination(input, result, i + 1, pos + 1, allSets);
		}
	}

	private Set<Integer> createSet(int[] input, int pos) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < pos; i++) {
			set.add(input[i]);
		}
		return set;
	}
}
