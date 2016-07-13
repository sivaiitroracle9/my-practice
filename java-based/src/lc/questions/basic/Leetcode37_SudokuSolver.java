package leetcode.questions.basic;

import java.util.HashSet;
import java.util.Set;

public class Leetcode37_SudokuSolver {

	public static void main(String[] args) {

	}

	public void solveSudoku(char[][] board) {

		Set<Character>[][] possibilities = new HashSet[board.length][board[0].length];
		char[][] newboard = new char[board.length][board[0].length];
		Set<Character> vals = new HashSet<Character>(9);
		for (int i = 0; i < 9; i++)
			vals.add((char) (i + 1));
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				possibilities[i][j] = new HashSet<Character>(vals);
				newboard[i][j] = board[i][j];
			}
		}

		if (dfs_sudoku(possibilities, board)) {
			return;
		}

	}

	public boolean dfs_sudoku(Set<Character>[][] possibilities,
			char[][] newboard) {

		for (int r = 0; r < newboard.length; r++) {
			for (int c = 0; c < newboard[0].length; c++) {
				if (newboard[r][c] == '.') {
					for (char p : possibilities[r][c]) {
						newboard[r][c] = p;
						if (isValid(newboard, r, c))
							return true;
						newboard[r][c] = '.';
					}
				}
			}
		}
		return false;
	}

	public boolean isValid(char[][] newboard, int r, int c) {

		for (int i = 0; i < newboard.length; i++) {
			if ((i != r && newboard[i][c] == newboard[r][c])
					|| (i != c && newboard[r][i] == newboard[r][c]))
				return false;
		}

		for (int R = r / 3; R < r / 3 + 3; R++) {
			for (int C = c / 3; C < c / 3 + 3; C++) {
				if (R != r && C != c && newboard[R][C] == newboard[r][c])
					return false;
			}
		}

		return true;
	}

	public void fillPossibilities(Set<Character>[][] possibilities,
			char[][] board) {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (board[r][c] != '.') {
					possibilities[r][c].clear();

					for (int R = r / 3; R < r / 3 + 3; R++) {
						for (int C = c / 3; C < c / 3 + 3; C++) {
							if (!possibilities[R][C].isEmpty())
								possibilities[R][C].remove(board[r][c]);
						}
					}

					for (int i = 0; i < 9; i++) {

						if (!possibilities[i][c].isEmpty())
							possibilities[i][c].remove(board[i][c]);

						if (!possibilities[r][i].isEmpty())
							possibilities[r][i].remove(board[r][i]);

					}

				}
			}
		}
	}

}
