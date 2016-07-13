/**
 * Rotate a 2D array without using extra space.
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=497
 */
package geeksforgeeks.company.amazon;

import java.util.Scanner;

public class Rotate2DArray {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				arr[i][j] = sc.nextInt();
		System.out.println();
		rotate90(arr);
		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr.length; j++)
				System.out.print(arr[i][j] + " ");
		System.out.println();
	}

	private static void rotate90(int[][] arr) {
		for (int i = 0; i < arr.length; i++)
			shiftCells(arr, i);
		reverseCell(arr);
	}

	private static void reverseCell(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length / 2; j++) {
				int temp = arr[i][j];
				arr[i][j] = arr[i][arr.length - 1 - j];
				arr[i][arr.length - 1 - j] = temp;
			}
		}
	}

	private static void shiftCells(int[][] arr, int r) {
		for (int j = r + 1; j < arr.length; j++) {
			int temp = arr[r][j];
			arr[r][j] = arr[j][r];
			arr[j][r] = temp;
		}
	}
}
