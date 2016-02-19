package worksapp.interview;

import java.util.Scanner;

public class BaseConversion {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int type = sc.nextInt();
		String input = sc.next();

		if (type == 1) {
			System.out.println("BIN= " + Dec2Bin(input));
			System.out.println("HEX= " + Dec2Hex(input));
			System.out.println("DEC= " + input);
		} else if (type == 2) {
			System.out.println("BIN= " + input);
			System.out.println("HEX= " + Bin2Hex(input));
			System.out.println("DEC= " + Bin2Dec(input));
		} else {
			System.out.println("BIN= " + Hex2Bin(input));
			System.out.println("HEX= " + input);
			System.out.println("DEC= " + Hex2Dec(input));
		}
	}

	private static String Dec2Bin(String dec) {
		int decV = Integer.parseInt(dec);
		String bin = "";
		int q = decV;
		while (true) {
			int r = q % 2;
			bin = r + bin;
			q = q / 2;
			if (q == 0)
				break;
		}

		return bin;
	}

	private static String Dec2Hex(String dec) {
		int decV = Integer.parseInt(dec);
		String hex = "";
		int q = decV;
		while (true) {
			int r = q % 16;
			if (r == 10)
				hex = 'A' + hex;
			else if (r == 11)
				hex = 'B' + hex;
			else if (r == 12)
				hex = 'C' + hex;
			else if (r == 13)
				hex = 'D' + hex;
			else if (r == 14)
				hex = 'E' + hex;
			else if (r == 15)
				hex = 'F' + hex;
			else
				hex = r + hex;

			q = q / 16;

			if (q == 0)
				break;
		}
		return hex;
	}

	private static String Bin2Dec(String bin) {
		int dec = 0;
		int N = bin.length();
		int i = N - 1;
		int k = 1;
		while (i >= 0) {
			dec += Integer.parseInt(String.valueOf(bin.charAt(i))) * k;
			k *= 2;
			i--;
		}

		return String.valueOf(dec);
	}

	private static String Bin2Hex(String bin) {
		int dec = 0;
		int i = bin.length() - 1;
		int k = 1;
		while (i >= 0) {
			dec += Integer.parseInt(String.valueOf(bin.charAt(i))) * k;
			k *= 2;
			i--;
		}

		String hex = "";
		int q = dec;
		while (true) {
			int r = q % 16;

			if (r == 10)
				hex = 'A' + hex;
			else if (r == 11)
				hex = 'B' + hex;
			else if (r == 12)
				hex = 'C' + hex;
			else if (r == 13)
				hex = 'D' + hex;
			else if (r == 14)
				hex = 'E' + hex;
			else if (r == 15)
				hex = 'F' + hex;
			else
				hex = r + hex;

			q = q / 16;

			if (q == 0)
				break;
		}
		return hex;
	}

	private static String Hex2Dec(String hex) {

		int dec = 0;
		int i = hex.length() - 1;
		int k = 1;
		while (i >= 0) {
			switch (hex.charAt(i)) {
			case 'A':
				dec += 10 * k;
				break;
			case 'B':
				dec += 11 * k;
				break;
			case 'C':
				dec += 12 * k;
				break;
			case 'D':
				dec += 13 * k;
				break;
			case 'E':
				dec += 14 * k;
				break;
			case 'F':
				dec += 15 * k;
				break;

			default:
				dec += Integer.parseInt(String.valueOf(hex.charAt(i))) * k;
				break;
			}

			k *= 16;
			i--;
		}

		return String.valueOf(dec);
	}

	private static String Hex2Bin(String hex) {
		String bin = "";

		String dec = Hex2Dec(hex);
		int decV = Integer.parseInt(dec);

		int q = decV;
		while (true) {
			int r = q % 2;
			bin = r + bin;
			q = q / 2;
			if (q == 0)
				break;
		}
		return bin;
	}
}
