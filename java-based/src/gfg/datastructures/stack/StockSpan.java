package gfg.datastructures.stack;

import java.util.Stack;

public class StockSpan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] price = { 10, 4, 5, 90, 120, 80 };
		int[] span = new int[price.length];
		stockspan(price, span);
		for (int i : span)
			System.out.print(i + ", ");
	}

	public static void stockspan(int[] price, int[] span) {

		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < price.length; i++) {

			if (!stack.isEmpty()) {
				while (!stack.isEmpty() && price[stack.peek()] <= price[i]) {
					stack.pop();
				}
			}

			if (stack.isEmpty()) {
				span[i] = i + 1;
			} else {
				span[i] = i - stack.peek();
			}

			stack.push(i);
		}

	}
}
