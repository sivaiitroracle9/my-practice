package leetcode.company.amazon;

public class IntersectionOfTwoLinkedLists {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		};
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

		if (headA == null || headB == null)
			return null;

		int lengthA = 0;
		ListNode current = headA;
		while (current != null) {
			lengthA++;
			current = current.next;
		}

		int lengthB = 0;
		current = headB;
		while (current != null) {
			lengthB++;
			current = current.next;
		}

		ListNode currA = headA;
		ListNode currB = headB;
		int move = 1;
		if (lengthA > lengthB) {
			while (move <= lengthA - lengthB) {
				currA = currA.next;
				move++;
			}
		} else {
			while (move <= lengthB - lengthA) {
				currB = currB.next;
				move++;
			}
		}

		while (currA != null && currB != null) {
			if (currA == currB)
				return currA;
			currA = currA.next;
			currB = currB.next;
		}
		return null;
	}
}
