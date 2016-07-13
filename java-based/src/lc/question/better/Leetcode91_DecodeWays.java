package lc.question.better;

public class Leetcode91_DecodeWays {

	public static void main(String[] args) {
		Leetcode91_DecodeWays lc = new Leetcode91_DecodeWays();
		String s = "12";
		System.out.println(lc.numDecodings(s));
	}

	public int numDecodings(String s) {
		char[] csarr = s.toCharArray();
		if (csarr.length == 0)
			return 0;

		int prev1 = 0, prev2 = 1, current = 0;
		if (csarr[0] - '0' > 0 && csarr[0] - '0' <= 26)
			prev1 = 1;

		for (int i = 1; i < csarr.length; i++) {
			int curr = csarr[i] - '0';

			if (curr != 0)
				current = current + prev1;

			int prev = (csarr[i - 1] - '0') * 10 + curr;
			if (prev <= 26 && prev >= 0)
				current = current + prev2;

			prev2 = prev1;
			prev1 = current;
		}
		return prev1;
	}
}
