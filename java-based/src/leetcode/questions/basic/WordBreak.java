package leetcode.questions.basic;

public class WordBreak {

	public static void main(String[] args) {
		String s = "leetcode";
		String[] dict = {"leet", "code"};
		
		// System.out.println(matchBreak(s, dict, 0));
		System.out.println(matchBreakDP(s, dict));
	}
	
	public static boolean matchBreak (String s, String[] dict, int start) {
		
		if(s.length()==start)
			return true;
		
		for (String d : dict) {
			int end = start + d.length();
			if(end>s.length())
				continue;
			if(s.substring(start, end).equals(d))
				if(matchBreak(s, dict, end))
					return true;
		}
		
		return false;
	}
	
	public static boolean matchBreakDP (String s, String[] dict) {
		boolean[] t = new boolean[s.length() + 1];
		t[0] = true;
		
		for (int i=0; i<s.length(); i++) {
			if(!t[i])
				continue;
			
			for (String d : dict) {
				int len = d.length();
				int end = i + len;
				
				if(end > s.length()) continue;
				
				if(t[end]) continue;
				
				if(s.substring(i, end).equals(d))
					t[end]=true;
			}
		}
		return true;
	}
}
