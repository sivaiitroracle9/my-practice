package leetcode.questions.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/the-skyline-problem/
 * 
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 */
public class Leetcode218_Skyline {

	public static void main(String[] args) {
		Leetcode218_Skyline lc = new Leetcode218_Skyline();
		int[][] buildings = { { 2, 9, 10 }, { 3, 7, 15 }, { 5, 12, 12 },
				{ 15, 20, 10 }, { 19, 24, 8 } };
		lc.getSkyLine(buildings);

	}

	public List<int[]> getSkyLine(int[][] buildings) {

		List<int[]> critical_points = new ArrayList<int[]>();
		if (buildings.length == 0)
			return critical_points;
		BuildingPoint[] building_points = new BuildingPoint[2 * buildings.length];
		for (int i = 0; i < buildings.length; i++) {
			building_points[2 * i] = new BuildingPoint(buildings[i][0],
					buildings[i][2], true);
			building_points[2 * i + 1] = new BuildingPoint(buildings[i][1],
					buildings[i][2], false);
		}

		// Sort according to the processing order.
		Arrays.sort(building_points);

		TreeMap<Integer, Integer> tmap = new TreeMap<Integer, Integer>();
		tmap.put(0, 1);
		int prev_height = 0;
		int m = 2 * buildings.length;
		for (int i = 0; i < m; i++) {
			BuildingPoint bp = building_points[i];
			if (bp.isStart) {
				if (!tmap.containsKey(bp.height))
					tmap.put(bp.height, 1);
				else
					tmap.put(bp.height, tmap.get(bp.height) + 1);
			} else {
				if (tmap.get(bp.height) == 1)
					tmap.remove(bp.height);
				else
					tmap.put(bp.height, tmap.get(bp.height) - 1);
			}

			// After processing the building point. start/end
			int current_max_height = tmap.lastKey();
			if (prev_height != current_max_height) {
				int[] critical_point = new int[2];
				critical_point[0] = bp.x;
				critical_point[1] = current_max_height;
				critical_points.add(critical_point);
				prev_height = current_max_height;
			}
		}
		return critical_points;
	}

	class BuildingPoint implements Comparable<BuildingPoint> {
		int x = 0;
		int height = 0;
		boolean isStart = false;

		public BuildingPoint(int x, int height, boolean isStart) {
			this.x = x;
			this.height = height;
			this.isStart = isStart;
		}

		@Override
		public int compareTo(BuildingPoint that) {

			if (this.x != that.x) {
				return this.x - that.x;
			} else {

				if (this.isStart && that.isStart) {
					return -1 * (this.height - that.height);
				} else if (!this.isStart && !that.isStart) {
					return this.height - that.height;
				} else {
					if (this.isStart && !that.isStart) {
						return -1;
					} else if (!this.isStart && that.isStart) {
						return 1;
					}
				}

			}

			return 0;
		}

	}
}
