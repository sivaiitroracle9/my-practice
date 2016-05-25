package leetcode.company.amazon;

import java.util.Stack;

public class MinStack {

	Stack<Integer> normal_stack;
	Stack<Integer> min_stack;

	/** initialize your data structure here. */
	public MinStack() {
		normal_stack = new Stack<Integer>();
		min_stack = new Stack<Integer>();
	}

	public void push(int x) {
		int min = Math.min(x, getMin());
		normal_stack.push(x);
		min_stack.push(min);
	}

	public void pop() {
		if (!normal_stack.isEmpty() && !min_stack.isEmpty()) {
			normal_stack.pop();
			min_stack.pop();
		}
	}

	public int top() {
		return normal_stack.peek();
	}

	public int getMin() {
		if (min_stack.isEmpty())
			return Integer.MAX_VALUE;
		return min_stack.peek();
	}
}
