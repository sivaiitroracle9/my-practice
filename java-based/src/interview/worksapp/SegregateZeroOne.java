package interview.worksapp;

import java.util.Scanner;

public class SegregateZeroOne {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] input = new int[N];
		for (int i = 0; i < N; i++) input[i] = sc.nextInt();
		int[] out = segregate(input);
		for (int i = 0; i < N; i++)
			System.out.print(out[i] + " ");
	}

	static int[] segregate(int[] input) {
		int N = input.length;

		int left = 0, right = N - 1;
		while (left < right) {
			while (input[left] == 0 && left < right) 
				left++;

			while (input[right] == 1 && left < right) 
				right--;
			

			if (left < right) {
				int temp = input[left];
				input[left] = input[right];
				input[right] = temp;
				left++;
				right--;
			}
		}
		return input;
	}
}
