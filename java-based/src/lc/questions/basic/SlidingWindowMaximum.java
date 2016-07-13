package lc.questions.basic;
/**
 * 
 * https://leetcode.com/problems/sliding-window-maximum/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 */
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SlidingWindowMaximum {

	public static void main(String[] args) {
		SlidingWindowMaximum swm = new SlidingWindowMaximum();

		int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
		int k = 3;

		for (int i : swm.max_sliding_window_dequeue(nums, k))
			System.out.print(i + " ");
		System.out.println();

		for (int i : swm.max_sliding_window_heap(nums, k))
			System.out.print(i + " ");
		System.out.println();
	}
	
	//TODO SEGMENT TREE - Range Queries.

	public int[] max_sliding_window_dequeue(int[] nums, int k) {
		int[] max_slide = new int[nums.length - k + 1];

		Deque<Integer> dequeue = new LinkedList<Integer>();
		for (int i = 0; i < k; i++) {

			while (!dequeue.isEmpty() && nums[dequeue.peekLast()] < nums[i])
				dequeue.pollLast();

			dequeue.offerLast(i);
		}

		max_slide[0] = nums[dequeue.peekFirst()];

		for (int i = k; i < nums.length; i++) {
			while (!dequeue.isEmpty() && dequeue.peekFirst() < i - (k - 1))
				dequeue.pollFirst();
			while (!dequeue.isEmpty() && nums[dequeue.peekLast()] < nums[i])
				dequeue.pollLast();
			dequeue.offerLast(i);
			max_slide[i - k + 1] = nums[dequeue.peekFirst()];
		}
		return max_slide;
	}

	public int[] max_sliding_window_heap(int[] nums, int k) {
		int[] max_slide = new int[nums.length - k + 1];
		Map<Integer, Integer> key_priority = new HashMap<Integer, Integer>(k);
		for (int i = 0; i < k; i++)
			key_priority.put(i, nums[i]);

		MAX_PQ PQ = new MAX_PQ(k, key_priority);
		max_slide[0] = PQ.getMaxValue();

		for (int i = k; i < nums.length; i++) {
			PQ.removeKey(i - k);
			PQ.add(i, nums[i]);
			max_slide[i - k + 1] = PQ.getMaxValue();
		}

		return max_slide;
	}

	class MAX_PQ {
		private int current_index = -1;
		private int[] key_heap = null;
		private Map<Integer, Integer> key_pos;
		private Map<Integer, Integer> key_priority;

		public MAX_PQ(int k, Map<Integer, Integer> key_prio) {
			key_heap = new int[k];
			key_pos = new HashMap<Integer, Integer>(k);
			key_priority = new HashMap<Integer, Integer>(key_prio);
			for (int key : key_priority.keySet()) {
				current_index++;
				key_pos.put(key, current_index);
				key_heap[current_index] = key;
			}

			for (int p = (k - 1) / 2; p >= 0; p--) {
				heapify_down(p);
			}
		}

		public void add(int key, int priority) {
			if (current_index >= key_heap.length)
				throw new IllegalArgumentException();
			key_heap[++current_index] = key;
			key_priority.put(key, priority);
			key_pos.put(key, current_index);
			heapify_up(current_index);
		}

		public int getMaxKey() {
			return key_heap[0];
		}

		public int getMaxValue() {
			return key_priority.get(key_heap[0]);
		}

		public int extractMax() {

			int max_key = key_heap[0];
			int max_val = key_priority.get(max_key);

			key_priority.remove(max_key);
			key_pos.remove(max_key);

			key_heap[0] = key_heap[current_index--];
			key_pos.put(key_heap[0], 0);
			heapify_down(0);
			return max_val;
		}

		public void removeKey(int key) {
			int keypos = key_pos.get(key);

			key_priority.remove(key);
			key_pos.remove(key);

			// If last node reduce the size of the heap;
			if (keypos == current_index) {
				current_index--;
			}
			// If not the last node.
			else {
				key_heap[keypos] = key_heap[current_index--];
				key_pos.put(key_heap[keypos], keypos);
				heapify_down(keypos);
			}
		}

		private void heapify_up(int i) {
			int parent = i / 2;
			if (key_priority.get(key_heap[parent]) < key_priority
					.get(key_heap[i])) {
				key_pos.put(key_heap[i], parent);
				key_pos.put(key_heap[parent], i);

				int temp = key_heap[i];
				key_heap[i] = key_heap[parent];
				key_heap[parent] = temp;
				heapify_up(parent);
			}
		}

		private void heapify_down(int i) {
			int left = 2 * i + 1;
			int right = 2 * i + 2;

			int MAX = key_priority.get(key_heap[i]);

			if (left <= current_index)
				MAX = Math.max(MAX, key_priority.get(key_heap[left]));
			else
				return;

			if (right <= current_index)
				MAX = Math.max(MAX, key_priority.get(key_heap[right]));

			if (MAX == key_priority.get(key_heap[i]))
				return;
			else if (MAX == key_priority.get(key_heap[left])) {
				key_pos.put(key_heap[i], left);
				key_pos.put(key_heap[left], i);

				int temp = key_heap[i];
				key_heap[i] = key_heap[left];
				key_heap[left] = temp;
				heapify_down(left);
			} else {
				key_pos.put(key_heap[i], right);
				key_pos.put(key_heap[right], i);

				int temp = key_heap[i];
				key_heap[i] = key_heap[right];
				key_heap[right] = temp;
				heapify_down(right);
			}

		}
	}

}
