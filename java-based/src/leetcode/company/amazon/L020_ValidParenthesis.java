package leetcode.company.amazon;

import java.util.Stack;

public class L020_ValidParenthesis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean isValid(String s) {
		char[] sarr = s.toCharArray();
		Stack<Character> stk = new Stack<Character>();
		for(int i=0; i<sarr.length; i++){
			if(sarr[i] == '(' || sarr[i] == '{' || sarr[i] == '[') {
				stk.push(sarr[i]);
			} else {
				if(stk.isEmpty()) return false;
				char left_parenthesis = stk.pop();
				if((sarr[i] == ')' && left_parenthesis == '(') ||
						(sarr[i] == ']' && left_parenthesis == '[') ||
						(sarr[i] == '}' && left_parenthesis == '{')) {
					continue;
				} else return false;
			}
		}
		return stk.isEmpty();
	}
}
