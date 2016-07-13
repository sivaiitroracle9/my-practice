package lc.comp.amz;

public class L141_LinkedListCycle {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		};
	}

	public boolean hasCycle(ListNode head) {

		if (head == null)
			return false;

		ListNode slowpointer, fastpointer;
		slowpointer = head;
		fastpointer = head.next;
		while (slowpointer != null && fastpointer != null) {
			if (slowpointer == fastpointer)
				return true;

			slowpointer = slowpointer.next;
			if (fastpointer.next == null)
				return false;
			fastpointer = fastpointer.next.next;
		}
		return false;
	}
}
