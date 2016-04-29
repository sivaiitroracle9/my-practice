package algorithms.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * Closest Pair.
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 *
 */
public class ClosestPair {

	static class Point {
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		int x;
		int y;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}

	static class XSorter implements Comparator<Point> {

		@Override
		public int compare(Point p1, Point p2) {
			return p1.x - p2.x;
		}

	}

	static class YSorter implements Comparator<Point> {

		@Override
		public int compare(Point p1, Point p2) {
			return p1.y - p2.y;
		}

	}

	public static void main(String[] args) {
		ClosestPair cp = new ClosestPair();
		Point[] points = { new Point(6, 2), new Point(4, 6), new Point(5, 4),
				new Point(-8, 2), new Point(0, 2) };
		double distance = cp.closest_pair_of_points(points);
		System.out.println(distance);
	}

	public static double closest_pair_of_points(Point[] points) {
		Arrays.sort(points, new XSorter());
		Point[] pointsX = new Point[points.length];
		for (int i = 0; i < points.length; i++)
			pointsX[i] = points[i];

		return closest_pair_distnace(points, 0, points.length - 1);
	}

	public static double closest_pair_distnace(Point[] points, int left,
			int right) {

		if (right <= left || right - left == 1) {
			return Double.POSITIVE_INFINITY;
		}

		if (right - left == 2) {
			return distance(points[left], points[right]);
		}

		int mid = (left + right) / 2;

		double delta_left = closest_pair_distnace(points, left, mid);
		double delta_right = closest_pair_distnace(points, mid + 1, right);

		double delta = Math.min(delta_left, delta_right);
		Point checkPnt = points[mid];
		if (delta == delta_right)
			checkPnt = points[mid + 1];

		List<Point> delta_left_points = new ArrayList<ClosestPair.Point>();
		List<Point> delta_right_points = new ArrayList<ClosestPair.Point>();
		for (int i = left; i <= right; i++) {
			if (Math.abs(points[i].x - checkPnt.x) <= delta) {
				if (mid >= i) {
					delta_left_points.add(points[i]);
				} else {
					delta_right_points.add(points[i]);
				}
			}
		}

		double closest = closet_delta(delta_left_points, delta_right_points,
				delta);
		return Math.min(closest, delta);
	}

	private static double closet_delta(List<Point> delta_left_points,
			List<Point> delta_right_points, double delta) {
		YSorter y_sorter = new YSorter();
		Collections.sort(delta_left_points, y_sorter);
		Collections.sort(delta_left_points, y_sorter);

		//TODO This should be further improved.
		double min_distance = Double.POSITIVE_INFINITY;
		for (Point pL : delta_left_points) {
			for (Point pR : delta_right_points) {
				if (Math.abs(pL.x - pR.x) <= delta
						|| Math.abs(pL.y - pR.y) <= delta)
					min_distance = Math.min(distance(pL, pR), min_distance);
			}
		}

		return min_distance;
	}

	private static double distance(Point p1, Point p2) {
		double v = Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2);
		return Math.sqrt(v);
	}
}
