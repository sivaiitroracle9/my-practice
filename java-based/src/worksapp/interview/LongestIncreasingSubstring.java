package worksapp.interview;

import java.util.Scanner;

public class LongestIncreasingSubstring {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String word = sc.next();
		
		int maxI = 0;
		int maxJ = 0;
		int maxL = 1;
		int i=0;
		while(i<word.length()){
			int i0 = i;

			while(i<word.length()-1){
				if(word.charAt(i) < word.charAt(i+1)){
					i++;
				} else {
					break;
				}
			}
			int j0=i;
			if(j0-i0+1 > maxL){
				maxI = i0;
				maxJ = j0;
			}
			i++;
		}
		System.out.println(word.substring(maxI, maxJ+1));
	}

}
