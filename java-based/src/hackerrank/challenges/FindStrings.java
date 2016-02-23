package hackerrank.challenges;

import java.util.Arrays;
import java.util.Scanner;

public class FindStrings {

	static long max_dist_substring = Long.MAX_VALUE;

	/* Head ends here */
	static void findStrings(String[] a, long[] querry) {

		// Arrays.sort(a);
		int N = a.length;
		String s = "";
		for (int i = 0; i < N; i++)
			s = s + a[i] + '$';
		int[] limit = new int[s.length()];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			int ln = a[i].length();
			for (int j = 0; j <= ln; j++) {
				limit[cnt + j] = cnt + ln;
			}
			cnt += ln + 1;
		}
		// for(int i=0; i<limit.length; i++) System.out.print(limit[i] + " ");

		int[] SA = SA(s);
		int[] lcp = LCP(SA, s);

		int T = querry.length;
		for (int i = 0; i < T; i++) {
			System.out.println(solve(SA, lcp, limit, querry[i], s));
		}
	}

	static String solve(int[] SA, int[] lcp, int[] limit, long K, String s) {
		if (max_dist_substring < K)
			return "INVALID";
		long cnt = 0;
		for (int sa = 0; sa < SA.length; sa++) {
			for (int j = SA[sa] + lcp[sa]; j < limit[SA[sa]]; j++) {
				cnt++;
				if (K == cnt)
					return s.substring(SA[sa], j + 1);
			}
		}
		if (max_dist_substring > cnt)
			max_dist_substring = cnt;
		return "INVALID";
	}

	static int[] SA(String text) {
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

	static int[] LCP(int[] SA, String text) {
		int N = text.length();
		int[] invSA = new int[N];
		for (int i = 0; i < N; i++)
			invSA[SA[i]] = i;
		char[] t = text.toCharArray();
		int[] LCP = new int[N];
		int L = 0;
		for (int i = 0; i < N; i++) {
			int rank = invSA[i];
			if (rank > 0) {
				int j = SA[rank - 1];
				while (i + L < N && j + L < N && t[i + L] == t[j + L])
					L++;
			}
			LCP[rank] = L;
			if (L > 0)
				L--;
		}
		return LCP;
	}

	/* Tail starts here */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int nStrings = in.nextInt();
		String[] _a = new String[nStrings];

		for (int _a_i = 0; _a_i < nStrings; _a_i++) {
			_a[_a_i] = in.next();
		}
		int _query = in.nextInt();
		in.nextLine();
		long[] query = new long[_query];
		for (int _a_i = 0; _a_i < _query; _a_i++) {
			query[_a_i] = in.nextLong();
		}

		findStrings(_a, query);
	}

}
