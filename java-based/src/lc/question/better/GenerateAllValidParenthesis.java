package lc.question.better;

import java.util.ArrayList;
import java.util.List;

public class GenerateAllValidParenthesis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenerateAllValidParenthesis gV = new GenerateAllValidParenthesis();
		for (String s : gV.genParenthesis(3)) {
			System.out.print(s + ", ");
		}
	}

	private List<String> genParenthesis(int n) {
		List<String> result = new ArrayList<String>();
		gen(result, 0, 0, n, "");
		return result;
	}

	private void gen(List<String> result, int left, int right, int n,
			String temp) {

		if (left == right && left == n) {
			result.add(temp);
			return;
		}

		if (left + 1 <= n && right <= left + 1)
			gen(result, left + 1, right, n, temp + '(');
		if (right + 1 <= n && right+1 <= left)
			gen(result, left, right + 1, n, temp + ')');

	}
}
