package algorithms.geometry;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * https://leetcode.com/problems/max-points-on-a-line/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 *
 */
public class MaximumPointsOnALine {

	class Point {
		int x;
		int y;

		Point() {
			x = 0;
			y = 0;
		}

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	class Slope {
		int a;
		int b;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + a;
			result = prime * result + b;
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
			Slope other = (Slope) obj;
			if (a != other.a)
				return false;
			if (b != other.b)
				return false;
			return true;
		}
	}

	public static void main(String[] args) {

	}

	public int maxPoints(Point[] points) {
		int result = 0;
		for (int i = 0; i < points.length; i++) {
			Map<Slope, Integer> map = new HashMap<Slope, Integer>();
			int samePoint = 0;
			int verticalLine = 1;
			int i_point_max = 1;
			for (int j = i + 1; j < points.length; j++) {
				if (points[i].x == points[j].x && points[i].y == points[j].y)
					samePoint++;
				else if (points[i].x == points[j].x) {
					verticalLine++;
					i_point_max = Math.max(verticalLine, i_point_max);
				} else {
					int gcd = GCD(points[j].y - points[i].y, points[j].x
							- points[i].x);
					Slope s = new Slope();
					s.a = (points[j].y - points[i].y) / gcd;
					s.b = (points[j].x - points[i].x) / gcd;
					if (map.containsKey(s)) {
						int count = map.get(s);
						count++;
						map.put(s, count);
					} else {
						map.put(s, 2);
					}
					i_point_max = Math.max(i_point_max, map.get(s));
				}
			}
			result = Math.max(result, i_point_max + samePoint);
		}
		return result;
	}

	public int GCD(int a, int b) {

		if (b == 0)
			return a;
		return GCD(b, a % b);
	}

}
