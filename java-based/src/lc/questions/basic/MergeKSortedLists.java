package lc.questions.basic;
/**
 * 
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 */
import java.util.HashMap;
import java.util.Map;

public class MergeKSortedLists {

	public static void main(String[] args) {

		MergeKSortedLists ml = new MergeKSortedLists();

		ListNode l1 = ml.new ListNode(-1);
		l1.next = ml.new ListNode(5);
		l1.next.next = ml.new ListNode(11);
		ListNode l2 = ml.new ListNode(6);
		l2.next = ml.new ListNode(10);

		ListNode[] lists = { null, l1, null, l2 };
		ListNode root = ml.mergeKLists(lists);
		System.out.println();
	}

	public ListNode mergeKLists(ListNode[] lists) {

		int INFINITY = Integer.MAX_VALUE;
		ListNode root = null;
		Map<Integer, Integer> key_val = new HashMap<Integer, Integer>(
				lists.length);
		// First K elements.
		for (int i = 0; i < lists.length; i++) {
			ListNode node = lists[i];
			if (node != null) {
				key_val.put(i, node.val);
				lists[i] = node.next;
			} else {
				key_val.put(i, INFINITY);
			}
		}
		// create Priority Queue.
		MinPriorityQueue PQ = new MinPriorityQueue(key_val.size());
		PQ.create(key_val);
		ListNode temp = null;
		while (PQ.getMinPriority() != INFINITY) {
			if (root == null) {
				root = new ListNode(PQ.getMinPriority());
				temp = root;
			} else {
				temp.next = new ListNode(PQ.getMinPriority());
				temp = temp.next;
			}

			int key = PQ.getMinKey();
			ListNode node = lists[key];
			if (node == null) {
				PQ.increaseKey(key, INFINITY);
			} else {
				PQ.increaseKey(key, node.val);
				lists[key] = node.next;
			}
		}
		return root;
	}

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	class Heap {
		private boolean min_heap = false;
		private int curr_index = 0;

		private int[] heap = null;

		public Heap(int capacity, boolean min_heap) {
			heap = new int[capacity];
			this.min_heap = min_heap;
		}

		public void add(int i) {
			if (curr_index < heap.length) {
				heap[curr_index] = i;
				heapify_up(curr_index);
			}
		}

		public int getHeapSize() {
			return curr_index;
		}

		public int extractTop() {
			if (curr_index <= 0)
				return -1;
			int top = heap[0];
			heap[0] = heap[--curr_index];
			heapify_down(0);
			return top;
		}

		private void heapify_up(int i) {
			int parent = i / 2;
			if (min_heap) {
				if (heap[parent] > heap[i]) {
					int temp = heap[i];
					heap[i] = heap[parent];
					heap[parent] = temp;
					heapify_up(parent);
				}
			} else {
				if (heap[parent] < heap[i]) {
					int temp = heap[i];
					heap[i] = heap[parent];
					heap[parent] = temp;
					heapify_up(parent);
				}
			}
		}

		private void heapify_down(int i) {
			int left = 2 * i + 1;
			int right = 2 * i + 2;

			if (min_heap) {
				int MIN = Integer.MAX_VALUE;
				if (left <= curr_index) {
					MIN = Math.min(heap[left], MIN);
				} else
					return;

				if (right <= curr_index) {
					MIN = Math.min(heap[right], MIN);
				}

				if (heap[i] == MIN)
					return;
				else if (MIN == heap[left]) {
					int temp = heap[left];
					heap[left] = heap[i];
					heap[i] = temp;
					heapify_down(left);
				} else {
					int temp = heap[right];
					heap[right] = heap[i];
					heap[i] = temp;
					heapify_down(right);
				}
			} else {

				int MAX = Integer.MIN_VALUE;
				if (left <= curr_index) {
					MAX = Math.max(heap[left], MAX);
				} else
					return;

				if (right <= curr_index) {
					MAX = Math.max(heap[right], MAX);
				}

				if (heap[i] == MAX)
					return;
				else if (MAX == heap[left]) {
					int temp = heap[left];
					heap[left] = heap[i];
					heap[i] = temp;
					heapify_down(left);
				} else {
					int temp = heap[right];
					heap[right] = heap[i];
					heap[i] = temp;
					heapify_down(right);
				}

			}
		}
	}

	class MinPriorityQueue {
		private int curr_index = -1;

		private int[] key_heap = null;
		private Map<Integer, Integer> key_priority = null;

		public MinPriorityQueue(int capacity) {
			key_heap = new int[capacity];
			key_priority = new HashMap<Integer, Integer>(capacity);
		}

		public void create(Map<Integer, Integer> key_prio) {

			for (int key : key_prio.keySet()) {
				key_heap[++curr_index] = key;
			}
			key_priority.putAll(key_prio);
			for (int parent = (curr_index - 1) / 2; parent >= 0; parent--) {
				int left = 2 * parent + 1;
				int right = 2 * parent + 2;

				int MIN = key_priority.get(key_heap[parent]);
				if (left <= curr_index) {
					MIN = Math.min(key_priority.get(key_heap[left]), MIN);
				} else
					continue;

				if (right <= curr_index) {
					MIN = Math.min(key_priority.get(key_heap[right]), MIN);
				}

				if (key_priority.get(key_heap[parent]) == MIN)
					continue;
				else if (MIN == key_priority.get(key_heap[left])) {
					int temp = key_heap[left];
					key_heap[left] = key_heap[parent];
					key_heap[parent] = temp;
					heapify_down(left);
				} else {
					int temp = key_heap[right];
					key_heap[right] = key_heap[parent];
					key_heap[parent] = temp;
					heapify_down(right);
				}
			}
		}

		public int getSize() {
			return curr_index + 1;
		}

		public int getMinKey() {
			if (curr_index < 0)
				return -1;
			return key_heap[0];
		}

		public int getPriority(int key) {
			return key_priority.get(key);
		}

		public int getMinPriority() {
			return key_priority.get(key_heap[0]);
		}

		public void increaseKey(int key, int priority) {
			key_priority.put(key, priority);
			heapify_down(0);
		}

		private void heapify_down(int i) {
			int left = 2 * i + 1;
			int right = 2 * i + 2;
			int MIN = key_priority.get(key_heap[i]);
			if (left <= curr_index) {
				MIN = Math.min(key_priority.get(key_heap[left]), MIN);
			} else
				return;

			if (right <= curr_index) {
				MIN = Math.min(key_priority.get(key_heap[right]), MIN);
			}

			if (key_priority.get(key_heap[i]) == MIN)
				return;
			else if (MIN == key_priority.get(key_heap[left])) {
				int temp = key_heap[left];
				key_heap[left] = key_heap[i];
				key_heap[i] = temp;
				heapify_down(left);
			} else {
				int temp = key_heap[right];
				key_heap[right] = key_heap[i];
				key_heap[i] = temp;
				heapify_down(right);
			}

		}

	}
}
