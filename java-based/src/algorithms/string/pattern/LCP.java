package algorithms.string.pattern;

import java.util.Scanner;

public class LCP {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String text = sc.next();

		int[] z = Z(text);

		for (int i = 0; i < z.length; i++)
			System.out.print(z[i] + " ");
		System.out.println();
	}

	static int[] zFunction(String text) {
		int[] zarr = new int[text.length()];

		zarr[0] = 0;
		int j = 1;
		int i = 0;
		int zl = j;
		int zr = j + i;
		while (j < text.length()) {
			if ((j + i < text.length()) && (text.charAt(j + i) == text.charAt(i))) {
				zl = j;
				zr = j + i;
				i++;
				zarr[j] = zarr[j] + 1;
			} else {

				if (zr > zl + 1) {
					int k = zl + 1;
					for (; k < zr; k++) {
						if (zarr[k - zl] + k < zr) {
							zarr[k] = zarr[k - zl];
						} else {
							break;
						}
					}
					zl = k;
					
					j = zl;
					if (zl == zr) {
						i = 0;
					} else {
						zarr[zl] = zr - zl + 1;
						i = zr - zl + 1;
					}
				} else {
					i = 0;
					j++;
				}
			}
		}

		return zarr;
	}
	
	static int[] Z(String text){
		int[] z = new int[text.length()];
		
		int L=0, R = 0;
		for(int i=1; i<text.length(); i++){
			if(i>R){
				L=R=i;
				while(R < text.length() && text.charAt(R-L) == text.charAt(R)) R++;
				R--; z[i] = R-L + 1;
			} else {
				int k = i-L;
				if(z[k] < R - i +1) z[i] = z[k];
				else {
					L = i;
					while(R < text.length() && text.charAt(R-L) == text.charAt(R)) R++;
					R--; z[i] = R-L+1;
				}
			}
		}
		
		
		return z;
	}

}
