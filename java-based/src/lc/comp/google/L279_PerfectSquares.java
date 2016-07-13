package lc.comp.google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class L279_PerfectSquares {

	public static void main(String[] args) {

		L279_PerfectSquares lc = new L279_PerfectSquares();
		lc.numSquares(55);
	}

	public int numSquares(int n) {

		if (n < 1)
			return 0;

		boolean[] visit = new boolean[n + 1];
		List<Integer> currlevel = new ArrayList<Integer>(n);
		Set<Integer> nextlevel = new HashSet<Integer>(n);
		currlevel.add(n);
		int level = 0;
		while (!currlevel.isEmpty()) {
			int num = currlevel.remove(0);
			
			if (visit[num])
				continue;
			visit[num] = true;

			for (int i = 1; i * i <= num; i++) {
				int newnum = num - (i * i);
				if (visit[newnum])
					continue;
				if (newnum == 0){
					return level + 1;
				}

				nextlevel.add(newnum);
			}

			if (currlevel.isEmpty() && !nextlevel.isEmpty()) {
				level++;
				currlevel.addAll(nextlevel);
				nextlevel.clear();
			}
		}
		return level;
	}

}
