package lc.questions.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Leetcode282_ExpressionAddOperators {

	public static void main(String[] args) {
		Leetcode282_ExpressionAddOperators lc = new Leetcode282_ExpressionAddOperators();
		String num = "123";
		int target = 6;
		lc.print(lc.addOperators(num, target), num, target);
		num = "232";
		target = 8;
		lc.print(lc.addOperators(num, target), num, target);
		num = "105";
		target = 5;
		lc.print(lc.addOperators(num, target), num, target);
		num = "00";
		target = 0;
		lc.print(lc.addOperators(num, target), num, target);
		num = "3456237490";
		target = 9191;
		lc.print(lc.addOperators(num, target), num, target);
	}

	public void print(List<String> list, String num, int target) {
		System.out.println("------- num = " + num + " ---- target = " + target);
		for (String s : list)
			System.out.println(s);
		System.out.println("-------------------------------------------------");
	}

	public List<String> addOperators(String num, int target) {

		return new ArrayList<String>(addOperators_helper(num).get(target));
	}

	public Map<Integer, Set<String>> addOperators_helper(String num) {

		Map<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();
		if (num != "") {
			int x = Integer.parseInt(num);
			if (!map.containsKey(x))
				map.put(x, new HashSet<String>());
			map.get(x).add(num);
		}

		for (int i = 1; i < num.length(); i++) {
			String lstring = num.substring(0, i);
			String rstring = num.substring(i);

			Map<Integer, Set<String>> lmap = addOperators_helper(lstring);
			Map<Integer, Set<String>> rmap = addOperators_helper(rstring);

			for (int l : lmap.keySet()) {
				for (int r : rmap.keySet()) {
					for (String ls : lmap.get(l)) {
						for (String rs : rmap.get(r)) {
							// Adding + operator
							int x = l + r;
							if (!map.containsKey(x))
								map.put(x, new HashSet<String>());
							map.get(x).add(ls + '+' + rs);

							// Adding - operator
							x = l - r;
							if (!map.containsKey(x))
								map.put(x, new HashSet<String>());
							map.get(x).add(ls + '-' + rs);

							// Adding * operator
							x = l * r;
							if (!map.containsKey(x))
								map.put(x, new HashSet<String>());
							map.get(x).add(ls + '*' + rs);
						}
					}
				}
			}
		}
		return map;
	}
}
