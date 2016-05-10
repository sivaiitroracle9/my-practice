package FindMedianInDataStream;
/**
 * 
 * https://leetcode.com/problems/find-median-from-data-stream/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 */
import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianInStream_JAVA_BUILT {
	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(5000);
	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(5000,
			Collections.reverseOrder());

	public static void main(String[] args) {
		FindMedianInStream_JAVA_BUILT mds = new FindMedianInStream_JAVA_BUILT();
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
		if (maxHeap.size() == minHeap.size() + 1) {
			if (maxHeap.peek() <= num)
				minHeap.offer(num);
			else {
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(num);
			}
		} else {
			if(maxHeap.size() == 0) maxHeap.offer(num);
			else {
				if(minHeap.peek() >= num) maxHeap.offer(num);
				else {
					maxHeap.offer(minHeap.poll());
					minHeap.offer(num);
				}
			}
		}
	}

	public double findMedian() {

		if (maxHeap.size() > minHeap.size()) {
			return maxHeap.peek();
		}

		return (maxHeap.peek() + minHeap.peek()) / 2.0;
	}
}
