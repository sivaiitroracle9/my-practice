package worksapp.interview.string;

import java.util.Scanner;

public class LongestPalindrome {
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String word = sc.next();
		
		System.out.println(LongestPalindrome(word));
	}

	private static String LongestPalindrome(String word){
		String word1 = reverseWord(word);
		
	}
	
	private static String LongestPalindrome(String word){
		String word1 = reverseWord(word);
		
	}
	
	private static String reverseWord(String word){
		String s = "";
		for(int i = word.length()-1; i>=0; i--) s = s + word.charAt(i);
		return s;
	}
	
	private static String LCSubstring(String word, String word1){
		
	}
	
	
	
	private static List<String> getAllCommonStrings(){
		
	}
}
