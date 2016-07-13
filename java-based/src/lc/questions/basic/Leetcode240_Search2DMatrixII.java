package lc.questions.basic;

/**
 * Time Complexity : O(log(N)) Space Complexity: O(1)
 * 
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 */
public class Leetcode240_Search2DMatrixII {

	public static void main(String[] args) {
		Leetcode240_Search2DMatrixII lc = new Leetcode240_Search2DMatrixII();
		int[][] matrix = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 },
				{ 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 },
				{ 21, 22, 23, 24, 25 } };
		int target = 5;
		System.out.println("target = " + target + " ==> "
				+ lc.searchMatrix(matrix, target));
		target = 20;
		System.out.println("target = " + target + " ==> "
				+ lc.searchMatrix(matrix, target));
	}

	public boolean searchMatrix(int[][] matrix, int target) {

		return search_helper(matrix, 0, 0, matrix.length - 1,
				matrix[0].length - 1, target);
	}

	public boolean search_helper(int[][] matrix, int rl, int cl, int rr,
			int cr, int target) {

		if (matrix[rl][cl] > target)
			return false;
		if (matrix[rl][cl] == target)
			return true;
		int r_mid = (rl + rr) / 2;
		int c_mid = (cl + cr) / 2;

		boolean found = false;
		if (matrix[r_mid][c_mid] < target) {

			if (r_mid + 1 <= rr && !found) {
				found = found
						|| search_helper(matrix, r_mid + 1, cl, rr, c_mid,
								target);
			}

			if (c_mid + 1 <= cr && !found) {
				found = found
						|| search_helper(matrix, rl, c_mid + 1, r_mid, cr,
								target);
			}

			if (r_mid + 1 <= rr && c_mid + 1 <= cr && !found) {
				found = found
						|| search_helper(matrix, r_mid + 1, c_mid + 1, rr, cr,
								target);
			}
		} else {

			found = found
					|| search_helper(matrix, rl, cl, r_mid, c_mid, target);
			if (c_mid + 1 <= cr && !found) {
				found = found
						|| search_helper(matrix, rl, c_mid + 1, r_mid, cr,
								target);
			}

			if (r_mid + 1 <= rr && !found) {
				found = found
						|| search_helper(matrix, r_mid + 1, cl, rr, c_mid,
								target);
			}
		}
		return found;
	}

}
