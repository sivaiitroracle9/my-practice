package interview.worksapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class IrreducableFunctions {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		List<Fraction> fList = new ArrayList<Fraction>();
		Map<Integer, Set<Integer>> irredFrac = findAllIrreducableFractions(M, N);
		for (int p : irredFrac.keySet()) {
			for (int q : irredFrac.get(p)) {
				fList.add(new Fraction(p, q));
			}
		}
		Collections.sort(fList, Collections.reverseOrder());

		for (Fraction f : fList) {
			f.print();
		}

	}

	static Map<Integer, Set<Integer>> findAllIrreducableFractions(int M, int N) {
		Map<Integer, Set<Integer>> irredFrac = new HashMap<Integer, Set<Integer>>();
		for (int p = M; p <= N; p++) {
			for (int q = 1; q <= p - 1; q++) {
				int d = GCD(p, q);
				int p1 = p / d;
				int q1 = q / d;
				if (irredFrac.get(p1) == null) {
					irredFrac.put(p1, new HashSet<Integer>());
				}
				irredFrac.get(p1).add(q1);
			}
		}
		return irredFrac;
	}

	static int GCD(int p, int q) {

		if (q < p) {
			int rm = p % q;
			if (rm == 0) {
				return q;
			} else {
				return GCD(q, rm);
			}
		} else {
			int rm = q % p;
			if (rm == 0) {
				return p;
			} else {
				return GCD(p, rm);
			}
		}

	}

	private static class Fraction implements Comparable<Fraction> {

		int p = 1;
		int q = 1;

		Fraction(int p, int q) {
			this.p = p;
			this.q = q;
		}

		@Override
		public int compareTo(Fraction that) {
			// TODO Auto-generated method stub
			return this.p * that.q - this.q * that.p;
		}

		public void print() {
			System.out.println(this.p + "/" + this.q);
		}
	}
}
