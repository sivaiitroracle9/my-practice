package geeksforgeeks.sorting;

import java.util.Scanner;

public class SelectionSort {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = sc.nextInt();

		// selectionsort(arr);
		// bubblesort(arr);
		// insertionsort(arr);
		// mergesort(arr);
		// heapsort(arr);
		quicksort(arr);
		
		for (int i = 0; i < N; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		System.out.println(x);
	}
	static int x = 0;
	static void quicksort(int[] arr) {
		quicksort_helper(arr, 0, arr.length - 1);
	}

	static void quicksort_helper(int[] arr, int left, int right) {

		if(left >= right) return;
		
		int pivot = left;
		for (int i = left; i <= right; i++) {
			if (arr[i] < arr[pivot]) {
				int temp = arr[i];
				arr[i] = arr[pivot];
				arr[pivot] = temp;
				pivot = i;
				x++;
			}
		}
		quicksort_helper(arr, left, pivot-1);
		quicksort_helper(arr, pivot+1, right);
	}

	static void selectionsort(int[] arr) {
		int N = arr.length;
		for (int i = 0; i < N; i++) {
			int min = arr[i];
			for (int j = i + 1; j < N; j++) {
				if (min > arr[j]) {
					min = arr[j];
					arr[j] = arr[i];
					arr[i] = min;
				}
			}
		}
	}

	static void bubblesort(int[] arr) {
		int N = arr.length;
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if (arr[j] < arr[j - 1]) {
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				}
			}
		}
	}

	static void insertionsort(int[] arr) {
		int N = arr.length;
		int j = 0;
		for (int i = 1; i < N; i++) {
			j = i - 1;
			int temp = arr[i];
			while (j >= 0) {
				if (arr[j] > temp) {
					arr[j + 1] = arr[j];
					j--;
				} else
					break;
			}
			arr[j + 1] = temp;
		}
	}

	static void mergesort(int[] arr) {
		int N = arr.length;
		mergesort_helper(arr, 0, N - 1);
	}

	static void mergesort_helper(int[] arr, int left, int right) {

		if (left == right) {
			return;
		}

		int mid = (left + right) / 2;
		mergesort_helper(arr, left, mid);
		mergesort_helper(arr, mid + 1, right);
		int[] l = new int[mid - left + 1];
		int i = 0;
		for (i = left; i <= mid; i++)
			l[i - left] = arr[i];
		int[] r = new int[right - mid - 1 + 1];
		for (i = mid + 1; i <= right; i++)
			r[i - mid - 1] = arr[i];

		i = left;
		int li = 0;
		int ri = 0;
		while (li < l.length && ri < r.length) {
			if (l[li] < r[ri]) {
				arr[i] = l[li];
				i++;
				li++;
			} else {
				arr[i] = r[ri];
				i++;
				ri++;
			}
		}

		while (li < l.length) {
			arr[i] = l[li];
			i++;
			li++;
		}

		while (ri < r.length) {
			arr[i] = r[ri];
			i++;
			ri++;
		}

	}

	static void heapsort(int[] arr) {
		buildheap(arr);

		for (int i = arr.length - 1; i >= 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			heapifyDOWN(arr, i, 0);
		}
	}

	static void buildheap(int[] arr) {
		int N = arr.length;

		int parents = (N - 1) / 2;
		for (int i = parents; i >= 0; i--) {
			heapifyDOWN(arr, N, i);
		}
	}

	static void heapifyDOWN(int[] arr, int size, int ix) {
		int left = 2 * ix + 1;
		int right = 2 * ix + 2;
		int max = arr[ix];
		if (left < size - 1) {
			max = Math.max(max, arr[left]);
		}

		if (right < size - 1) {
			max = Math.max(max, arr[right]);
		}

		if (max == arr[ix])
			return;
		else if (left < size - 1 && max == arr[left]) {
			arr[left] = arr[ix];
			arr[ix] = max;
			heapifyDOWN(arr, size, left);
		} else if (right < size - 1 && max == arr[right]) {
			arr[right] = arr[ix];
			arr[ix] = max;
			heapifyDOWN(arr, size, right);
		}
	}

	static void heapifyUP(int[] arr, int size, int ix) {
		int parent = ix / 2;
		if (arr[parent] < arr[ix]) {
			int temp = arr[parent];
			arr[parent] = arr[ix];
			arr[ix] = temp;
			heapifyUP(arr, size, parent);
		}
	}
}
