package interview.worksapp;

import java.util.Scanner;

public class BaseConversion_V1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
/*		int intype = sc.nextInt();
		int outtype = sc.nextInt();
		String in = sc.next();
		String out = convert(in, intype, outtype);
		System.out.println(out);*/
		int dec = sc.nextInt();
		int decL = dec<0 ? Integer.MAX_VALUE + dec + 1 : dec;
		boolean neg = dec<0;
		System.out.println("BIN = " + decToBin(decL, neg));
		System.out.println("HEX = " + decToHex(decL, neg));
		System.out.println("IN-BIN = " + Integer.toBinaryString(dec));
		System.out.println("IN-HEX = " + Integer.toHexString(dec));
	}

	static String convert(String in, int intype, int outtype) {

		int dec = 0;
		if (intype == 1) {
			dec = Integer.valueOf(in, 2);
		} else if (intype == 2) {
			dec = Integer.valueOf(in, 16);
		} else {
			dec = Integer.valueOf(in, 10);
		}

		String out = "";
		if (outtype == 1) {
			out = Integer.toString(dec, 2);
		} else if (outtype == 2) {
			out = Integer.toString(dec, 16);
		} else {
			out = Integer.toString(dec, 10);
		}
		return out;
	}

	static String decToHex(int dec, boolean neg) {
		char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };

		String str = "";
		if (dec == 0 && !neg)
			return "0";
		while (dec > 0) {
			int rem = dec % 16;
			str = hex[rem] + str;
			dec = dec / 16;
		}
		if(neg) {
			if(str.length()<8) str = "00000000".substring(0, 8-str.length()) + str;
			str = hex[Integer.parseInt(str.substring(0,1))+8] + str.substring(1);
		}
		return str;
	}

	static String decToBin(int dec, boolean neg) {
		String str = "";

		if (dec == 0 && !neg)
			return "0";
		
		while (dec > 0) {
			int rem = dec % 2;
			str = rem + str;
			dec = dec / 2;
		}
		if(neg) {
			if(str.length()<31) str = "0000000000000000000000000000000".substring(0, 31-str.length()) + str;
			str = '1' + str;
		}
		
		return str;
	}

}
