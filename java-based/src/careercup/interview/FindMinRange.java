package careercup.interview;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// Given K sorted lists. find min range of k numbers one from each list.
public class FindMinRange {

	class Node {
		int val;
		int listIndex;

		Node(int index, int value) {
			this.listIndex = index;
			this.val = value;
		}
	}

	public int findMinRange(int k, List<List<Integer>> klists) {
		PriorityQueue<Node> minheap = new PriorityQueue<FindMinRange.Node>(k,
				new Comparator<Node>() {
					@Override
					public int compare(Node o1, Node o2) {
						return o1.val - o2.val;
					}
				});
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < k; i++) {
			int val = klists.get(i).remove(0);
			max = max < val ? val : max;
			minheap.add(new Node(i, val));
		}

		int range = max - minheap.peek().val, index = minheap.peek().listIndex;
		while (klists.get(index) != null) {
			minheap.poll();

			int newval = klists.get(index).remove(0);
			if (max < newval)
				max = newval;

			minheap.add(new Node(index, newval));

			if (range > max - minheap.peek().val)
				range = max - minheap.peek().val;

			index = minheap.peek().listIndex;
		}

		return range;
	}

}
