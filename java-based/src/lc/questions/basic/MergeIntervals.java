package leetcode.questions.basic;

import java.util.ArrayList;
import java.util.List;

public class MergeIntervals {

	public static void main(String[] args) {
		int[] L = {1, 2, 8, 15};
		int[] U = {3, 6, 10, 18};

		MergeIntervals mg = new MergeIntervals();
		List<Interval> ivlList = new ArrayList<MergeIntervals.Interval>();
		for (int i = 0; i < L.length; i++) {
			ivlList.add(mg.new Interval(L[i], U[i]));
		}

		for (Interval ivl : mg.merge(ivlList)) {
			System.out.println(ivl.low + ", " + ivl.up);
		}
	}

	public List<Interval> merge(List<Interval> ivlList) {

		List<Interval> result = new ArrayList<MergeIntervals.Interval>();
		for (Interval ivl : ivlList) {
			if (result.size() == 0)
				result.add(ivl);
			else {
				Interval prev = result.get(result.size() - 1);
				if (prev.up < ivl.low) {
					result.add(ivl);
				} else {
					Interval merged = new Interval(Math.min(prev.low, ivl.low),
							Math.max(prev.up, ivl.up));
					result.remove(result.size() - 1);
					result.add(merged);
				}
			}
		}

		return result;
	}

	class Interval implements Comparable<Interval> {
		int low = 0;
		int up = 0;

		public Interval(int low, int up) {
			// TODO Auto-generated constructor stub
			this.low = low;
			this.up = up;
		}

		@Override
		public int compareTo(Interval that) {
			// TODO Auto-generated method stub
			return this.low - that.low;
		}

	}
}
