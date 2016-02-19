package visa.popularCharecters;

import java.util.Scanner;

public class PopularCharecters {

	
	public static void main(String[] args){
		
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		
		boolean[][] map = new boolean[26][N];
		for(int i=0; i<N; i++){
			for(int j=0; j<26; j++){
				map[j][i]=false;
			}
		}
		
		for(int i=0; i<N; i++){
			String line = s.nextLine().trim();
			char[] array = line.toCharArray();
			for(int j=0; j<array.length; j++){
				int idx = array[j]-'a';
				map[idx][i] = true;
			}
		}
		
		int cnt = 0;
		System.out.println("map.length=" + map.length);
		for(int i=0; i<map.length; i++){
			
			boolean popular = true;
			for(int j=0; j<N; j++){
				if(!map[i][j]) {
					popular = false;
					break;
				}
			}
			
			if(popular) cnt++;
		}
		System.out.println(cnt);
	}
}
