package interview.worksapp;

import java.util.Scanner;
import java.util.Stack;

public class ParenthesisCheck {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String parentheis = sc.next();
		char[] arr = parentheis.toCharArray();

		Stack<Character> pStack = new Stack<Character>();
		boolean invalid = false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '(') {
				pStack.push(arr[i]);
			} else {
				if (!pStack.isEmpty()) {
					pStack.pop();
				} else {
					invalid = true;
					break;
				}
			}
		}

		if (invalid || !pStack.isEmpty()) {
			System.out.println("INVALID");
		} else {
			System.out.println("VALID");
		}

	}

}
