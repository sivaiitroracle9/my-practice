package interview.visa;

import java.util.Scanner;

public class IncrementingGrid {

	public static void main(String[] args){
		
		Scanner s = new Scanner(System.in);
		long N = s.nextLong();
		long r = Long.MAX_VALUE;
		long c = Long.MAX_VALUE;
		for(long i=1L; i<=N; i++){
			long r1 = s.nextLong();
			long c1 = s.nextLong();
			r = r1 < r ? r1 : r;
			c = c1 < c ? c1 : c;
		}
		System.out.println(r*c);
	}
	
}
