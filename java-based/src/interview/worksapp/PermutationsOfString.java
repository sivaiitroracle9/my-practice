package interview.worksapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PermutationsOfString {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		Set<String> permutations = new HashSet<String>();
		List<String> permList = new ArrayList<String>(permutations.size());
		for (String a : permutations) {
			permList.add(a);
		}
		Collections.sort(permList);
		permutations("", str, permutations);

		for (String a : permutations) {
			System.out.println(a);
		}

	}

	static void permutations(String prefix, String remain, Set<String> permutations) {

		if (prefix.length() == remain.length()) {
			permutations.add(prefix);
			// System.out.println(prefix);
			return;
		}

		for (int i = 0; i < remain.length(); i++) {
			String nprefix = prefix + remain.substring(i, i + 1);
			// String nremain = remain.substring(0, i) + remain.substring(i+1);
			permutations(nprefix, remain, permutations);
		}
	}
}
