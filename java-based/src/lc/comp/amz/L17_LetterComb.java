package lc.comp.amz;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
import java.util.ArrayList;
import java.util.List;

public class L17_LetterComb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(letterCombinations("2"));
	}

	public static List<String> letterCombinations(String digits) {

		List<String> result = new ArrayList<String>();
		if (digits.length() == 0)
			return result;

		String[] digitletter = { "", "", "abc", "def", "ghi", "jkl", "mno",
				"pqrs", "tuv", "wxyz" };
		result.add("");
		for (int i = 0; i < digits.length(); i++) {
			char d = digits.charAt(i);
			int x = d - '0';
			if (x > 1) {
				List<String> temp = new ArrayList<String>();
				String letter = digitletter[x];
				for (int j = 0; j < letter.length(); j++) {
					for (String r : result) {
						temp.add(r + letter.charAt(j));
					}
				}
				result = temp;
			}
		}
		return result;
	}
}
