package algorithms.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * 
 * The Skyline problem.
 * 
 * https://leetcode.com/problems/the-skyline-problem/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */
public class Skyline {

	public static void main(String[] args) {
		int[][] buildings = { { 1, 3, 4 }, { 3, 4, 4 }, { 2, 6, 2 },
				{ 8, 11, 4 }, { 7, 9, 3 }, { 10, 11, 2 } };
		Skyline sl = new Skyline();
		List<int[]> criticalPointList = sl.getCriticalPoints(buildings);
		for (int[] point : criticalPointList) {
			System.out.println("[ " + point[0] + ", " + point[1] + " ]");
		}
	}

	private static List<int[]> getCriticalPoints(int[][] buildings) {

		BuildPoint[] builing_points = new BuildPoint[buildings.length * 2];
		for (int i = 0; i < buildings.length; i++) {
			BuildPoint bps = new BuildPoint(buildings[i][0], buildings[i][2],
					true);
			BuildPoint bpe = new BuildPoint(buildings[i][1], buildings[i][2],
					false);
			builing_points[2 * i] = bps;
			builing_points[2 * i + 1] = bpe;
		}
		Arrays.sort(builing_points);

		List<int[]> criticalPoints = new ArrayList<int[]>();

		int prevMaxHeight = 0;
		TreeMap<Integer, Integer> tmap = new TreeMap<Integer, Integer>();
		tmap.put(0, 1);

		for (BuildPoint bp : builing_points) {
			if (bp.isStart) {
				if (tmap.get(bp.height) == null) {
					tmap.put(bp.height, 1);
				} else {
					tmap.put(bp.height, tmap.get(bp.height) + 1);
				}
			} else {
				if (tmap.get(bp.height) == 1) {
					tmap.remove(bp.height);
				} else {
					tmap.put(bp.height, tmap.get(bp.height) - 1);
				}
			}

			int height = tmap.lastKey();
			if (prevMaxHeight != height) {
				prevMaxHeight = height;
				int[] cpoint = new int[2];
				cpoint[0] = bp.x;
				cpoint[1] = height;
				criticalPoints.add(cpoint);
			}
		}

		return criticalPoints;
	}

	static class BuildPoint implements Comparable<BuildPoint> {

		public BuildPoint(int x, int height, boolean isStart) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.height = height;
			this.isStart = isStart;
		}

		boolean isStart = false;
		int x;
		int height;

		@Override
		public int compareTo(BuildPoint o) {
			if (this.x != o.x) {
				return this.x - o.x;
			} else {
				/*
				 * return (this.isStart ? -this.height : this.height) -
				 * (o.isStart ? -o.height : o.height);
				 */
				if ((this.isStart && o.isStart)) {
					return -1 * (this.height - o.height);
				}

				if (!this.isStart && !o.isStart) {
					return this.height - o.height;
				}

				if (!this.isStart && o.isStart)
					return 1;
				if (!o.isStart && this.isStart)
					return -1;
			}
			return 0;
		}
	}

}
