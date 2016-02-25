package interview.worksapp;

import java.util.Arrays;
import java.util.Scanner;

public class LongestSubStrAppearMoreThanOnce {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String word = sc.next();
		System.out.println(LSO(word));
	}

	static String LSO (String word) {
		String[] SA = new String[word.length()];
		for(int i=0; i<word.length(); i++) SA[i] = word.substring(i);
		Arrays.sort(SA);

		int max = 0;
		int maxI = 0;
		for(int i=1; i<SA.length; i++) {
			int j=0;
			while (true) {
				if(j<SA[i].length() && j<SA[i-1].length() && SA[i].charAt(j) == SA[i-1].charAt(j)) {
					j++;
					if(max < j){
						max = j;
						maxI = i;
					}
				} else break;
			}
		}
		return SA[maxI].substring(0, max);
	}
 }
