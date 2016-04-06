package leetcode.questions.basic;

public class RegxMatch {

	public static void main(String[] args) {
		String s = "aab";
		String p = "c*a*b";
	}

	public static boolean isMatch(String s, String p) {
		if (p.length() == 0)
			return s.length() == 0;

		if (p.length() == 1) {
			if (s.length() < 1)
				return false;
			if (p.charAt(0) == '*')
				return false;
			if (p.charAt(0) == '.')
				return true;
			if (p.charAt(0) != s.charAt(0))
				return false;
			return isMatch(s.substring(1), p.substring(1));
		} else {

			if (p.charAt(0) == '*')
				return false;

			if (p.charAt(1) == '*') {
				
				
				if (p.charAt(0) == '.') {
					if (isMatch(s, p.substring(2))) {
						return true;
					}
				} else {
					int i = 0;
					while (i < s.length() && p.charAt(0) == s.charAt(i)) {
						if (isMatch(s.substring(i), p.substring(2))) {
							return true;
						}
						i++;
					}
				}
			} else {

				if (s.length() < 1)
					return false;
				if (s.charAt(0) != p.charAt(0) && p.charAt(0) != '.')
					return false;

				return isMatch(s.substring(1), p.substring(1));
			}
		}
	}
}
