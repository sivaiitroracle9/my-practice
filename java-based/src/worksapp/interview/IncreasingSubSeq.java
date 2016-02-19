package worksapp.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IncreasingSubSeq {
	static Map<Integer, List<List<Integer>>> sizeIncSubSeq = new HashMap<Integer, List<List<Integer>>>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] input = new int[N];
		for (int i = 0; i < N; i++)
			input[i] = sc.nextInt();
		printAllSubSeq(input);
	}

	public static void printAllSubSeq(int[] input) {

		int[] visit = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			recursive(i, input, visit);
		}
		
		for(int k : sizeIncSubSeq.keySet()){
			for(List<Integer> l : sizeIncSubSeq.get(k)){
				for(int v:l) System.out.print(v + " ");
				System.out.println();
			}
		}

	}

	public static void recursive(int i, int[] input, int[] visit) {
		visit[i] = 1;
		int j = i + 1;
		while (j < input.length) {
			if (input[i] < input[j]) {
				recursive(j, input, visit);
			}
			j++;
		}
		if (j == input.length) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int k = 0; k < input.length; k++) {
				if (visit[k] == 1) {
					list.add(input[k]);
				}
			}
			if (sizeIncSubSeq.get(list.size()) == null)
				sizeIncSubSeq.put(list.size(), new ArrayList<List<Integer>>());
			sizeIncSubSeq.get(list.size()).add(list);

		}
		visit[i] = 0;

	}

}
