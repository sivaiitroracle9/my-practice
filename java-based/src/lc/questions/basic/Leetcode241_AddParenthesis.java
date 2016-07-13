package lc.questions.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/different-ways-to-add-parentheses/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 *
 */
public class Leetcode241_AddParenthesis {

	public static void main(String[] args) {

		Leetcode241_AddParenthesis lc = new Leetcode241_AddParenthesis();
		String input = "2*3-4*5";
		for (int out : lc.diffWaysToCompute(input))
			System.out.println(out + ", ");
	}

	public List<Integer> diffWaysToCompute(String input) {
		input = input.replaceAll(" ", "");
		List<Integer> result = diffWaysToCompute(input, 0, input.length() - 1);
		Collections.sort(result);
		return result;
	}

	public List<Integer> diffWaysToCompute(String input, int left, int right) {

		String newinput = input.substring(left, right + 1);
		if (newinput.indexOf('*') == -1 && newinput.indexOf('+') == -1
				&& newinput.indexOf('-') == -1) {

			List<Integer> res = new ArrayList<Integer>();
			res.add(Integer.parseInt(newinput));
			return res;
		}

		List<Integer> result = new ArrayList<Integer>();
		for (int i = left; i <= right; i++) {
			if (input.charAt(i) == '*' || input.charAt(i) == '+'
					|| input.charAt(i) == '-') {
				List<Integer> leftCompute = diffWaysToCompute(input, left,
						i - 1);
				List<Integer> rightCompute = diffWaysToCompute(input, i + 1,
						right);

				for (int lc : leftCompute) {
					for (int rc : rightCompute) {
						if (input.charAt(i) == '+') {
							result.add(lc + rc);
						} else if (input.charAt(i) == '-') {
							result.add(lc - rc);
						} else if (input.charAt(i) == '*') {
							result.add(lc * rc);
						}
					}
				}
			}
		}

		return result;
	}
}
