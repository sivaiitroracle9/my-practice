package lc.question.better;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NQueens {

	public static void main(String[] args) {
		NQueens nq = new NQueens();
		int N = 4;
		List<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
		LinkedList<Integer> queen = new LinkedList<Integer>();
		for(int i=0; i<N; i++) queen.add(-1);
		nq.nQueens(result, N, 0, queen);

		for (LinkedList<Integer> list : result) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (list.get(r) == c) {
						System.out.print("Q");
					} else
						System.out.print("X");
				}
				System.out.println();
			}
			System.out.println("---------------------------------------");
		}

	}

	private void nQueens(List<LinkedList<Integer>> result, int N, int row,
			LinkedList<Integer> queen) {

		if (row == N) {
			LinkedList<Integer> finalQ = new LinkedList<Integer>();
			finalQ.addAll(queen);
			result.add(finalQ);
			return;
		}

		for (int c = 0; c < N; c++) {
			if (canPut(row, c, queen)) {
				queen.set(row, c);
				nQueens(result, N, row + 1, queen);
				queen.set(row, -1);
			}
		}

	}

	private boolean canPut(int row, int col, List<Integer> queen) {

		if (row - 1 >= 0 && (Math.abs(queen.get(row - 1) - col) == 2)
				|| (row - 2 >= 0 && Math.abs(queen.get(row - 2) - col) == 1))
			return false;

		return true;
	}
}
