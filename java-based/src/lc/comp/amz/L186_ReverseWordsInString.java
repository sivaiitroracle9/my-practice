package leetcode.company.amazon;

public class L186_ReverseWordsInString {

	public static void main(String[] args) {
		L186_ReverseWordsInString lc = new L186_ReverseWordsInString();
		char[] s = { 't', 'h', 'e', ' ', 's', 'k', 'y', ' ', 'i', 's', ' ',
				'b', 'l', 'u', 'e' };
		lc.reverseWords(s);
		for (int i = 0; i < s.length; i++)
			System.out.print(s[i]);
	}

	public void reverseWords(char[] s) {

		for (int i = 0; i < s.length / 2; i++) {
			char t = s[i];
			s[i] = s[s.length - 1 - i];
			s[s.length - 1 - i] = t;
		}

		int left = 0, right = 0;
		while (right <= s.length) {
			if (right == s.length || s[right] == ' ') {
				for (int i = left; i < (right + left) / 2; i++) {
					char t = s[i];
					s[i] = s[right + left - 1 - i];
					s[right + left - 1 - i] = t;
				}
				left = right + 1;
			} else {
				if (right == s.length)
					break;
			}
			right++;
		}

	}

}
