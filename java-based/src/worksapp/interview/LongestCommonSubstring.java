package worksapp.interview;

import java.util.Scanner;

public class LongestCommonSubstring {
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String word1 = sc.next();
		String word2 = sc.next();
		
		String lcs = LCSubstring(word1, word2);
		System.out.println(lcs);
	}
	
	private static String LCSubstring(String word1, String word2){
		
		String lcs = "";
		
		int len1 = word1.length();
		int len2 = word2.length();
		
		int[][] dp = new int[len2+1][len1+1];
		
		int maxI = 0;
		int maxJ = 0;
		int maxL = 0;
		for(int i=1; i<=len2; i++ ){
			for(int j=1; j<=len1; j++){
				if(word1.charAt(j-1)==word2.charAt(i-1)){
					dp[i][j]=dp[i-1][j-1]+1;
					if(dp[i][j]>maxL){
						maxI = i; maxJ=j; maxL=dp[i][j];
					}
				}
			}
		}
		
		while(dp[maxI][maxJ]!=0){
			lcs = word1.charAt(maxJ-1) + lcs;
			maxI--;
			maxJ--;
		}
		
		return lcs;
	}

}
