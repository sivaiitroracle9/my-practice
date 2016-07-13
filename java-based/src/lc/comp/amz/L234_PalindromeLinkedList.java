package lc.comp.amz;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 * 
 * @author sivae
 * 
 */

public class L234_PalindromeLinkedList {

	
	public static void main(String[] args) {
		L234_PalindromeLinkedList p = new L234_PalindromeLinkedList();
		ListNode head = p.new ListNode(1);
		head.next = p.new ListNode(1);
		head.next.next = p.new ListNode(2);
		head.next.next.next = p.new ListNode(1);
		System.out.println(p.isPalindrome(head));
	}
	
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		};
	}

	// find mid pointer and revere the one haf and compare.

	public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null)
			return true;

		ListNode fast = head;
		ListNode slow = head;
		ListNode revhead = null;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		// reverse head node of next half.
		if (fast.next == null || fast.next.next == null) {
			revhead = slow.next;
		}

		// reverse list
		ListNode revprev = null;
		ListNode revcurrent = revhead;
		while(revcurrent!=null) {
			ListNode next = revcurrent.next;
			revcurrent.next = revprev;
			revprev = revcurrent;
			revcurrent = next;
		}
		
		revhead = revprev;
		
		while(revhead!=null && head!=null) {
			if(head.val != revhead.val) return false;
			head = head.next;
			revhead = revhead.next;
		}
		return true;
	}
}
