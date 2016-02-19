package hackerrank;

import java.util.Scanner;

public class AreaOfOverlappingRectangles {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int L = sc.nextInt();
		int M = sc.nextInt();
		int N = sc.nextInt();
		int P = sc.nextInt();
		int Q = sc.nextInt();
		int R = sc.nextInt();
		int S = sc.nextInt();
		
		System.out.println(OverlappingArea(K, L, M, N, P, Q, R, S));
	}
	
	static int area(int xl, int yl, int xr, int yr){
		return Math.abs(xl-xr)*Math.abs(yl-yr);
	}
	
	static boolean onePointInside(int xl, int yl, int xr, int yr, int x, int y){
		
		return false;
	}
	
	static 
	
}
