package leetcode.company.amazon;

public class MergeTwoSortedLists {

	public class ListNode {
		int val;
		ListNode next;

		public ListNode(int x) {
			// TODO Auto-generated constructor stub
			val = x;
		}
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		ListNode head = null, current = null;

		if (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				head = l1;
				l1 = l1.next;
			} else {
				head = l2;
				l2 = l2.next;
			}
		} else if (l1 != null) {
			head = l1;
			l1 = l1.next;
		} else if (l2 != null) {
			head = l2;
			l2 = l2.next;
		} else
			return null;

		current = head;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				current.next = l1;
				l1 = l1.next;
			} else {
				current.next = l2;
				l2 = l2.next;
			}
			current = current.next;
		}

		while (l1 != null) {
			current.next = l1;
			l1 = l1.next;
			current = current.next;
		}

		while (l2 != null) {
			current.next = l2;
			l2 = l2.next;
			current = current.next;
		}

		return head;
	}

}
