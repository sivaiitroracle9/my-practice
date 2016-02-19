package hackerrank;

import java.util.ArrayList;
import java.util.Scanner;

public class LongestIncSubseq {

	static int[] array = null;
	static int[] jumpArray = null;
	static int[] countArray = null;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		array = new int[N];
		jumpArray = new int[N];
		countArray = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = sc.nextInt();
			jumpArray[i] = -1;
		}

		LIS();
	}

	static void LIS() {

		countArray[0] = 1;
		int max = 1;
		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < i; j++) {
				if (array[i] > array[j] 
						&& countArray[j] +1 > countArray[i]) {
					countArray[i] = countArray[j] + 1;
					if (countArray[j] + 1 >= max) {
						jumpArray[i] = j;
						max = countArray[j] + 1;
						countArray[i] = max;
					}
				}
			}
		}
		int[] lis = new int[max];
		int lastElmt = -1;
		int startElmt = -1;
		int ix = array.length - 1;
		int i = 1;
		while (true) {
			if (jumpArray[ix] != -1 && lastElmt == -1) {
				lastElmt = ix;
				lis[max - i] = array[ix];
				ix = jumpArray[ix];
				i++;
			}

			if (lastElmt != -1 && startElmt == -1) {
				lis[max - i] = array[ix];
				ix = jumpArray[ix];
				i++;
			}

			if (jumpArray[ix] == -1 && startElmt == -1) {
				startElmt = ix;
				lis[max - i] = array[ix];
				break;
			}
		}

		for (int j = 0; j < lis.length; j++)
			System.out.print(lis[j] + " ");
		System.out.println(" Max = " + max);

	}

}
