package hackerrank.challenges;

import java.util.Arrays;
import java.util.Scanner;

public class SuffixArray_n_logn_logn {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String text = sc.next();

		int[] sa = SA_n_logn_logn(text);
		for (int i = 0; i < sa.length; i++)
			System.out.print(sa[i] + " ");
	}

	static int[] SA_n_logn_logn(String text) {
		char[] t = text.toCharArray();
		int N = t.length;

		Suffix[] suffixes = new Suffix[N];
		int[] indexes = new int[N];
		for (int i = 0; i < N; i++) {
			int rank = t[i] - 'a';
			int nRank = i + 1 >= N ? -1 : t[i + 1] - 'a';
			suffixes[i] = new Suffix(i, rank, nRank);
		}
		Arrays.sort(suffixes);

		int k = 4;
		for (; k < 2 * N; k = 2 * k) {
			// Compute Rank
			int rank = 0;
			int prev_rank = suffixes[0].rank[0];
			suffixes[0].rank[0] = 0;
			indexes[suffixes[0].index] = 0;
			for (int i = 1; i < N; i++) {
				if (suffixes[i].rank[0] == prev_rank && suffixes[i].rank[1] == suffixes[i - 1].rank[1]) {
					prev_rank = suffixes[i].rank[0];
					suffixes[i].rank[0] = rank;
				} else {
					prev_rank = suffixes[i].rank[0];
					rank++;
					suffixes[i].rank[0] = rank;
				}
				indexes[suffixes[i].index] = i;
			}

			// Compute Next Rank.
			for (int i = 0; i < N; i++) {
				int nextIndex = suffixes[i].index + k / 2;
				suffixes[i].rank[1] = (nextIndex >= N) ? -1 : suffixes[indexes[nextIndex]].rank[0];
			}

			// Sort According to Rank and Next Rank.
			Arrays.sort(suffixes);
		}

		int[] SA = new int[N];
		for (int i = 0; i < N; i++)
			SA[i] = suffixes[i].index;

		return SA;
	}

	static int[] suffix_array_D3_N(String text) {

		return null;
	}

}

class Suffix implements Comparable<Suffix> {

	Suffix(int index, int r0, int r1) {
		this.index = index;
		this.rank[0] = r0;
		this.rank[1] = r1;
	}

	int index = -1;
	int[] rank = new int[2];

	@Override
	public int compareTo(Suffix that) {
		if (this.rank[0] < that.rank[0])
			return -1;
		else if (this.rank[0] == that.rank[0] && this.rank[1] < that.rank[1])
			return -1;
		else if (this.rank[0] == that.rank[0] && this.rank[1] == that.rank[1])
			return 0;
		return 1;
	}
}
