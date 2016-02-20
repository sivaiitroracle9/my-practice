package interview.worksapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LongestIncSubseq_NlogN {

	static int[] array = null;
	static Map<Integer, List<Integer>> subSeqMap = new HashMap<Integer, List<Integer>>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		array = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = sc.nextInt();
		}

		LIS();
	}

	private static void LIS() {
		int max = -1;
		for (int i = 0; i < array.length; i++) {

			if (subSeqMap.isEmpty()) {
				subSeqMap.put(1, new ArrayList<Integer>());
				subSeqMap.get(1).add(array[i]);
				max = 1;
			} else {
				List<Integer> sq = subSeqMap.get(max);
				if (sq.get(max - 1) < array[i]) {
					max++;
					subSeqMap.put(max, new ArrayList<Integer>());
					subSeqMap.get(max).addAll(sq);
					subSeqMap.get(max).add(array[i]);
				} else {
					for (int k = max; k >= 1; k--) {
						sq = subSeqMap.get(k);
						if (k == 1 && sq.get(k - 1) > array[i]) {
							sq.set(0, array[i]);
							subSeqMap.put(k, sq);
							break;
						}
						if (sq.get(k - 1) > array[i] && sq.get(k - 2) < array[i]) {
							sq.set(k - 1, array[i]);
							subSeqMap.put(k, sq);
							break;
						}

					}
				}
			}

		}
		for (int j = 1; j <= max; j++) {
			for (int i = 0; i < j; i++) {
				System.out.print(subSeqMap.get(j).get(i) + " ");
			}
			System.out.println("");
		}
		System.out.println(" Max = " + max);
	}
}
