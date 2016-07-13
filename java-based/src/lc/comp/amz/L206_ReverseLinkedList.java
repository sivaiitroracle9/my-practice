package lc.comp.amz;

public class L206_ReverseLinkedList {

	public static void main(String[] args) {

	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		};
	}

	public ListNode reverseList(ListNode head) {

		ListNode prev = null, current = head, next = null;
		if (current == null)
			return null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}

		return prev;
	}

}
