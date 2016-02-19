package worksapp.interview;

import java.util.Scanner;

public class BinaryToHex {
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		
		int rem = str.length()%4;
		if(rem == 1){
			str = "000"+str;
		} else if(rem == 2){
			str = "00"+str;
		} else if(rem==3){
			str = "0"+str;
		}
		
		String hex = "";
		for(int i=0; i<str.length(); i=i+4){
			hex += binaryToHex(str.substring(i, i+4));
		}
		
		System.out.println(hex);
		
	}
	
	static String binaryToHex(String binary){
		int k=8;
		int sum = 0;
		for(int i=0; i<4; i++){
			String val = binary.substring(i, i+1);
			//System.out.println(">>" + val);
			sum += Integer.parseInt(val)*k;
			k = k/2;
		}
		
		if(sum>=0 && sum<=9){
			return String.valueOf(sum);
		} else if(sum==10){
			return "A";
		} else if(sum==11){
			return "B";
		} else if(sum==12){
			return "C";
		} else if(sum==13){
			return "D";
		} else if(sum==14){
			return "E";
		} else if(sum==15){
			return "F";
		}
		
		
		return "";
	}

}
