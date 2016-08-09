package gfg.datastructures.stack;

import java.util.Stack;

public class NextGreaterElement {

	public static void main(String[] args) {

		int[] arr = { 13, 7, 6, 12};

		for (int in : nextGreaterEle(arr)) {
			System.out.print(in + ", ");
		}

	}

	public static int[] nextGreaterEle(int[] arr) {

		int[] NGE = new int[arr.length];

		Stack<Integer> next = new Stack<Integer>();
		for (int i = 0; i < NGE.length; i++) {
			if (!next.isEmpty()) {
				if (arr[i] <= arr[next.peek()]) {
					next.push(i);
				} else {
					while (!next.isEmpty() && arr[i] > arr[next.peek()]) {
						NGE[next.pop()] = arr[i];
					}
				}
			}
			next.push(i);
		}
		while (!next.isEmpty()) {
			NGE[next.pop()] = -1;
		}
		return NGE;
	}

}
