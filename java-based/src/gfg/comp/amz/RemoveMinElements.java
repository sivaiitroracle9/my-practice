/**
 * Remove minimum elements.
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=445
 */
package gfg.comp.amz;

public class RemoveMinElements {
	public static void main(String[] args) {
		int[] arr = { 4, 5, 100, 9, 10, 11, 12, 15, 200 };

		removeMinEle(arr);
	}

	static void removeMinEle(int[] arr) {
		boolean[][] ttable = new boolean[arr.length][arr.length];
		int[][] mintable = new int[arr.length][arr.length];
		int[][] maxtable = new int[arr.length][arr.length];

		for (int l = 1; l <= arr.length; l++) {
			for (int j = 0; j + l - 1 < arr.length; j++) {
				if (l == 1) {
					mintable[j][l + j - 1] = arr[j];
					maxtable[j][l + j - 1] = arr[j];
					ttable[j][l + j - 1] = true;
				} else {
					mintable[j][l + j - 1] = Math.min(mintable[j][l + j - 2],
							mintable[j + 1][l + j - 1]);
					maxtable[j][l + j - 1] = Math.max(maxtable[j][l + j - 2],
							maxtable[j + 1][l + j - 1]);
					if (2 * mintable[j][l + j - 1] > maxtable[j][l + j - 1])
						ttable[j][l + j - 1] = true;
				}
			}
		}

		outer: for (int l = arr.length; l > 0; l--) {
			for (int j = 0; j + l - 1 < arr.length; j++) {
				if (ttable[j][l + j - 1]) {
					System.out.print(arr.length-l);
					break outer;
				}
			}
		}
	}
}
