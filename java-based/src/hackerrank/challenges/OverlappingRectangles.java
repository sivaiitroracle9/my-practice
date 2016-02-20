package hackerrank.challenges;

import java.util.Scanner;

public class OverlappingRectangles {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		// Rectangle 1
		int k = sc.nextInt();
		int l = sc.nextInt();
		int m = sc.nextInt();
		int n = sc.nextInt();
		// Rectangle 2
		int p = sc.nextInt();
		int q = sc.nextInt();
		int r = sc.nextInt();
		int s = sc.nextInt();
		
		System.out.println(overlapping(k, l, m, n, p, q, r, s) ? 1 : 0);
	}
	
	static boolean overlapping(int x11, int y11, int x12, int y12, 
			int x21, int y21, int x22, int y22){
		
		if(x11>x22 &&
				x21>x12 &&
				y21>y12 &&
				y11>y22){
			return false;
		}
		
		return true;
	}
	
}
