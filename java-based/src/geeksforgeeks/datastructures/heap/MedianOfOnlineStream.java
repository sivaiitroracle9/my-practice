package geeksforgeeks.datastructures.heap;

public class MedianOfOnlineStream {

	int MAX_HEAP_SIZE = 128;
	float median = -1;

	public static void main(String[] args) {

		MedianOfOnlineStream md = new MedianOfOnlineStream();

		int[] arr = { 5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4 };
		Heap minHeap = md.new Heap(0);
		Heap maxHeap = md.new Heap(1);
		for (int in : arr) {
			System.out.print(md.findMedian(in, minHeap, maxHeap) + " ");
		}
		System.out.println();
	}

	private float findMedian(int in, Heap minHeap, Heap maxHeap) {
		if (median < in) {
			minHeap.add(in);
		} else {
			maxHeap.add(in);
		}

		if (maxHeap.size() - minHeap.size() == 2) {
			int m = maxHeap.extractTop();
			minHeap.add(m);
			median = (maxHeap.peek() + minHeap.peek()) / 2f;
		} else if (maxHeap.size() - minHeap.size() == 1) {
			median = maxHeap.peek();
		} else if (minHeap.size() - maxHeap.size() == 2) {
			int m = minHeap.extractTop();
			maxHeap.add(m);
			median = (maxHeap.peek() + minHeap.peek()) / 2f;
		} else if (minHeap.size() - maxHeap.size() == 1) {
			median = minHeap.peek();
		} else {
			median = (maxHeap.peek() + minHeap.peek()) / 2f;
		}
		return median;
	}

	class Heap {
		int[] heap = new int[MAX_HEAP_SIZE];
		int type = 0; // 0 - min, 1 - max
		private int size = -1;

		Heap(int type) {
			this.type = type;
		}

		public void add(int x) {
			size++;
			heap[size] = x;
			heapifyUP(size);
		}

		public int peek() {
			if (size < 0)
				return -1;
			return heap[0];
		}

		public int size() {
			return size + 1;
		}

		public int extractTop() {

			if (size > -1) {
				int top = heap[0];
				heap[0] = heap[size];
				size--;
				heapifyDN(0);
				return top;
			}
			return -1;
		}

		private void heapifyDN(int node) {

			if (node >= size)
				return;

			int left = 2 * node + 1;
			int right = 2 * node + 2;

			if (type == 0) {
				int min = Integer.MAX_VALUE;
				if (size >= left) {
					min = Math.min(heap[left], min);
					if (size >= right)
						min = Math.min(min, heap[right]);
					if (heap[node] > min) {
						int temp = heap[node];
						heap[node] = min;
						if (min == heap[left]) {
							heap[left] = temp;
							heapifyDN(left);
						} else {
							heap[right] = temp;
							heapifyDN(right);
						}
					}
				}
			} else {
				int max = Integer.MIN_VALUE;
				if (size >= left) {
					max = Math.min(heap[left], max);
					if (size >= right)
						max = Math.max(max, heap[right]);
					if (heap[node] < max) {
						int temp = heap[node];
						heap[node] = max;
						if (max == heap[left]) {
							heap[left] = temp;
							heapifyDN(left);
						} else {
							heap[right] = temp;
							heapifyDN(right);
						}
					}
				}
			}
		}

		private void heapifyUP(int node) {
			int left = 2 * node + 1;
			int right = 2 * node + 2;
			if (type == 0) {
				int min = Integer.MAX_VALUE;
				if (size >= left) {
					min = Math.min(heap[left], min);
					if (size >= right)
						min = Math.min(min, heap[right]);
					if (heap[node] > min) {
						int temp = heap[node];
						heap[node] = min;
						if (min == heap[left]) {
							heap[left] = temp;
						} else {
							heap[right] = temp;
						}
					}
				}

				if (node != 0)
					heapifyUP(node / 2);
			} else {
				int max = Integer.MIN_VALUE;
				if (size >= left) {
					max = Math.max(heap[left], max);
					if (size >= right)
						max = Math.max(max, heap[right]);
					if (heap[node] < max) {
						int temp = heap[node];
						heap[node] = max;
						if (max == heap[left]) {
							heap[left] = temp;
						} else {
							heap[right] = temp;
						}
					}
				}
				if (node != 0)
					heapifyUP(node / 2);
			}
		}

	}
}
