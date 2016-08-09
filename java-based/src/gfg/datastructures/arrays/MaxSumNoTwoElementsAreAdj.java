package gfg.datastructures.arrays;

public class MaxSumNoTwoElementsAreAdj {

	public static void main(String[] args) {

		int[] arr = {5,  5, 10, 40, 50, 35};
		System.out.println(maxSum(arr));
		
	}

	public static int maxSum(int[] arr) {

		if (arr.length == 0)
			return 0;

		int include = arr[0], exclude = 0;

		for (int i = 1; i < arr.length; i++) {
			int newexclude = Math.max(exclude, include);
			include = Math.max(exclude + arr[i], include);
			exclude = newexclude;
		}

		return Math.max(include, exclude);
	}

}
