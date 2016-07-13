package lc.comp.amz;

public class L200_NumIslands_UnionFind {

	/**
	 * 
	 * This can be done in DFS, BFS, Union Find disjoint sets
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		L200_NumIslands_UnionFind lc = new L200_NumIslands_UnionFind();
		char[][] grid = {{'1', '1'}};
		System.out.println(lc.numIslands(grid));
	}

	class UF {

		int[] parent = null;
		int num_of_sets = 0;

		UF(int m, int n, char[][] grid) {
			for (int i = 0; i < m; i++)
				for (int j = 0; j < n; j++)
					if (grid[i][j] == '1')
						num_of_sets++;

			parent = new int[m * n];
			for (int i = 0; i < m * n; i++)
				parent[i] = i;
		}

		public int find(int p) {
			while (p != parent[p]) {
				// path compression.
				parent[p] = parent[parent[p]];
				p = parent[p];
			}
			return p;
		}

		public boolean isConnected(int p, int q) {
			int pp = find(p);
			int qp = find(q);
			if (pp != qp)
				return false;
			return true;
		}

		public void union(int p, int q) {
			int pp = find(p);
			int qp = find(q);
			if (pp == qp)
				return;
			parent[pp] = qp;
			num_of_sets--;
		}
	}

	public int numIslands(char[][] grid) {

		if(grid.length == 0 || grid[0].length == 0)
			return 0;
		
		int m = grid.length, n = grid[0].length;
		UF uf = new UF(m, n, grid);

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				if (grid[i][j] == '1') {
					int p = n * i + j;
					if (i + 1 < m && grid[i + 1][j] == '1')
						uf.union(p, p + n);

					if (i - 1 >= 0 && grid[i - 1][j] == '1')
						uf.union(p, p - n);

					if (j - 1 >= 0 && grid[i][j - 1] == '1')
						uf.union(p, p - 1);

					if (j + 1 < n && grid[i][j + 1] == '1')
						uf.union(p, p + 1);
				}

			}
		}
		return uf.num_of_sets;
	}
}
