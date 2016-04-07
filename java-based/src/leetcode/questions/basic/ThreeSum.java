package leetcode.questions.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	public static void main(String[] args) {
		int[] numbers = { -1, 0, 1, 2, -1, -4 };

		// (a + b + c) = 0 and a <= b <= c

		for (List<Integer> res : threeSum(numbers)) {
			for (int i : res) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	private static List<List<Integer>> threeSum(int[] arr) {

		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (arr.length < 3) {
			return result;
		}

		Arrays.sort(arr);

		for (int i = 0; i < arr.length - 2; i++) {

			int neg = -arr[i];

			if (i == 0 || arr[i] > arr[i - 1]) {
				int start = i + 1;
				int end = arr.length - 1;

				while (start < end) {
					if (arr[start] + arr[end] == neg) {
						List<Integer> res = new ArrayList<Integer>();
						res.add(arr[i]);
						res.add(arr[start]);
						res.add(arr[end]);
						result.add(res);
						start++;
						end--;

						while (start < end && arr[start] == arr[start - 1])
							start++;
						while (start < end && arr[end] == arr[end + 1])
							end--;

					} else {
						if (arr[start] + arr[end] > neg) {
							end--;
						} else {
							start++;
						}
					}
				}
			}

		}

		return result;
	}
}
