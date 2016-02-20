package interview.worksapp;

import java.util.Scanner;

public class ReverseWordsInString {
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		String output = "";
		while(true){
			int ix = input.lastIndexOf(" ");
			if(ix>=0){
				output = output + input.substring(ix+1) + " ";
				input = input.substring(0, ix);
			} else{
				output += input.substring(ix+1);
				break;
			}
			
		}
		
		System.out.println(output);
		
	}

}
