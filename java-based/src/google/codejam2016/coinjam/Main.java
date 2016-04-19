package google.codejam2016.coinjam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int J = sc.nextInt();
			System.out.println("Case #" + (t+1)+":");
			coinJam(N, J);
		}
	}

	public static void coinJam(int N, int J) {

		Map<String, List<Long>> result = new HashMap<String, List<Long>>();
		long possible = (long) Math.pow(2, N - 2);
		for (long i = 0; i < possible; i++) {

			String binary = Long.toBinaryString(i);
			for(int l=binary.length(); l<N-2; l++) binary = '0' + binary; 
			List<Long> nonTriv = new ArrayList<Long>(9);
			boolean found = true;
			for (int r = 2; r <= 10; r++) {
				long rad = validVal(Long.parseLong(binary, r) + 1 + (long) Math.pow(r, N-1));

				if (rad == -1) {
					found = false;
					break;
				}
				nonTriv.add(rad);
			}

			if (found) {
				result.put("1" + binary + "1", nonTriv);
				if(result.keySet().size()==J) break;
			}

		}

		print(result);
	}

	public static void print(Map<String, List<Long>> result) {
		for (String key : result.keySet()) {
			String out = key + " ";
			for (long l : result.get(key))
				out = out + l + " ";
			System.out.println(out.trim());
		}

	}

	public static long validVal(long val) {
		for (long i = 2; i <= Math.sqrt(val); i++) {
			if (val % i == 0) {
				return i;
			}
		}
		return -1;
	}
}
