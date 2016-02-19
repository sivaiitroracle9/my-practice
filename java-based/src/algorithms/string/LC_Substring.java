package algorithms.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LC_Substring {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String text1 = sc.next();
		String text2 = sc.next();

		List<String> lcs = lcs_substring(text1, text2);
		for(String l: lcs) System.out.println(l);
	}

	static List<String> lcs_substring(String text1, String text2) {
		int[][] dp = new int[text1.length()+1][text2.length()+1];
		int MAX = 0;
		List<String> lcsList = new ArrayList<String>();
		for(int i=1; i<=text1.length(); i++){
			for(int j=1; j<=text2.length(); j++){
				if(text1.charAt(i-1)==text2.charAt(j-1)){
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = 0;
				}
				
				if(dp[i][j] > MAX) MAX = dp[i][j];
			}
		}
		
		for(int i=1; i<=text1.length(); i++){
			for(int j=1; j<=text2.length(); j++){
				if(dp[i][j]==MAX) {
					lcsList.add(text1.substring(i-MAX, i));
				}
			}
		}
		return lcsList;
	}
}
