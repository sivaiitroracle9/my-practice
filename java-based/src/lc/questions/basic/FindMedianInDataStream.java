package lc.questions.basic;
/**
 * 
 * https://leetcode.com/problems/find-median-from-data-stream/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 */
import java.util.ArrayList;
import java.util.List;

public class FindMedianInDataStream {

	Heap left_max_heap = new Heap(false);
	Heap right_min_heap = new Heap(true);

	public static void main(String[] args) {
		FindMedianInDataStream mds = new FindMedianInDataStream();
		mds.addNum(1);
		System.out.println(mds.findMedian());
		mds.addNum(2);
		System.out.println(mds.findMedian());
		mds.addNum(3);
		System.out.println(mds.findMedian());
		mds.addNum(4);
		System.out.println(mds.findMedian());
		mds.addNum(5);
		System.out.println(mds.findMedian());
		mds.addNum(6);
		System.out.println(mds.findMedian());
		mds.addNum(7);
		System.out.println(mds.findMedian());
		mds.addNum(8);
		System.out.println(mds.findMedian());
		mds.addNum(9);
		System.out.println(mds.findMedian());
		mds.addNum(10);
		System.out.println(mds.findMedian());
	}

	public void addNum(int num) {
		left_max_heap.add(num);		
		if(left_max_heap.size() == right_min_heap.size() + 2)
			right_min_heap.add(left_max_heap.extractTop());
		
		
		if (left_max_heap.size() > right_min_heap.size()) {
			if (left_max_heap.peekTop() <= num) {
				right_min_heap.add(num);
			} else {
				int mintop = left_max_heap.extractTop();
				right_min_heap.add(mintop);
				left_max_heap.add(num);
			}
		} else {
			if (right_min_heap.size() != 0 && num >= right_min_heap.peekTop()) {
				int maxtop = right_min_heap.extractTop();
				left_max_heap.add(maxtop);
				right_min_heap.add(num);
			} else
				left_max_heap.add(num);
		}
	}

	public double findMedian() {

		if (left_max_heap.size() > right_min_heap.size()) {
			return left_max_heap.peekTop();
		}

		if (left_max_heap.size() != 0 && right_min_heap.size() != 0)
			return (left_max_heap.peekTop() + right_min_heap.peekTop()) / 2.0;
		return Double.NEGATIVE_INFINITY;
	}

	class Heap {
		private boolean minheap = false;

		List<Integer> heap = null;

		public Heap(boolean minheap) {
			this.minheap = minheap;
			heap = new ArrayList<Integer>();
		}

		public int size() {
			return heap.size();
		}

		public void add(int x) {
			heap.add(x);
			heapify_up(heap.size() - 1);
		}

		public int peekTop() {
			if (heap.size() <= 0)
				return -1;
			return heap.get(0);
		}

		public void changeTop(int x){
			heap.set(0, x);
			heapify_down(0);
		}
		
		public int extractTop() {
			if (heap.size() <= 0)
				return -Integer.MIN_VALUE;
			int top = heap.get(0);
			int last = heap.remove(heap.size() - 1);
			if (heap.size() > 0) {
				heap.set(0, last);
				heapify_down(0);
			}
			return top;
		}

		private void heapify_up(int i) {
			int parent = i / 2;
			if ((minheap && heap.get(parent) > heap.get(i))
					|| (!minheap && heap.get(parent) < heap.get(i))) {
				int temp = heap.get(parent);
				heap.set(parent, heap.get(i));
				heap.set(i, temp);
				heapify_up(parent);
			}
		}

		private void heapify_down(int i) {
			int left = 2 * i + 1;
			int right = 2 * i + 2;

			int MINMAX = heap.get(i);
			if (left <= heap.size() - 1) {
				MINMAX = minheap ? Math.min(MINMAX, heap.get(left)) : Math.max(
						MINMAX, heap.get(left));
			} else
				return;

			if (right <= heap.size() - 1) {
				MINMAX = minheap ? Math.min(MINMAX, heap.get(right)) : Math
						.max(MINMAX, heap.get(right));
			}

			if (MINMAX == heap.get(i))
				return;
			else if (MINMAX == heap.get(left)) {
				int temp = heap.get(left);
				heap.set(left, heap.get(i));
				heap.set(i, temp);
				heapify_down(left);
			} else {
				int temp = heap.get(right);
				heap.set(right, heap.get(i));
				heap.set(i, temp);
				heapify_down(right);
			}

		}
	}
}
