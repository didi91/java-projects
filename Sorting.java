import java.util.Random;

import Plotter.Plotter;

public class Sorting {

	final static int SELECTION_VS_QUICK_LENGTH = 12;
	final static int MERGE_VS_QUICK_LENGTH = 15;
	final static int MERGE_VS_QUICK_SORTED_LENGTH = 12;
	final static int SELECT_VS_MERGE_LENGTH = 16;
	final static double T = 600.0;

	/**
	 * Sorts a given array using the quick sort algorithm. At each stage the
	 * pivot is chosen to be the leftmost element of the subarray.
	 * 
	 * Should run in average complexity of O(nlog(n)), and worst case complexity
	 * of O(n^2)
	 * 
	 * @param arr
	 *            - the array to be sorted
	 */
	public static void quickSort(double[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	private static void sort(double arr[], int i, int j) {
		if (i < j) {
			int q = partition(arr, i, j, arr[j]);
			sort(arr, i, q - 1);
			sort(arr, q + 1, j);
		}
	}

	/**
	 * Sorts a given array using the merge sort algorithm.
	 * 
	 * Should run in complexity O(nlog(n)) in the worst case.
	 * 
	 * @param arr
	 *            - the array to be sorted
	 */
	public static void mergeSort(double[] arr) {
		double[] tmp = new double[arr.length];
		mergeSortHelper(arr, tmp, 0, arr.length - 1);
	}

	private static void mergeSortHelper(double[] arr, double[] tmp, int left, int right) {
		if (left < right) {
			int center = left + (right - left) / 2;
			mergeSortHelper(arr, tmp, left, center);
			mergeSortHelper(arr, tmp, center + 1, right);
			merge(arr, tmp, left, center, right);
		}
	}

	private static void merge(double[] arr, double[] tmp, int left, int middle, int right) {
		for (int i = left; i <= right; i++)
			tmp[i] = arr[i];
		int i = left;
		int j = middle + 1;
		int k = left;
		while (i <= middle && j <= right) {
			if (tmp[i] <= tmp[j]) {
				arr[k] = tmp[i];
				i++;
			} else {
				arr[k] = tmp[j];
				j++;
			}
			k++;

		}
		while (i <= middle) {
			arr[k] = tmp[i];
			k++;
			i++;
		}
	}

	/**
	 * finds the i'th order statistic of a given array.
	 * 
	 * Should run in complexity O(n) in the worst case.
	 * 
	 * @param arr
	 *            - the array.
	 * @param i
	 *            - a number between 0 and arr.length - 1.
	 * @return the number which would be at index i, if the array was to be
	 *         sorted
	 */
	public static double select(double[] arr, int i) {

		return quick_select(arr, 0, arr.length - 1, i);
	}

	private static double quick_select(double[] arr, int left, int right, int k) {
		if (left == right)
			return arr[left];
		double pivot = medianOfMedians(arr, left, right);
		int pivot_index = partition(arr, left, right, pivot);
		int length = (pivot_index - left + 1);
		if (length == k + 1)
			return arr[pivot_index];
		else
			return (k < length) ? quick_select(arr, left, (pivot_index - 1), k)
					: quick_select(arr, (pivot_index + 1), right, (k - length));
	}

	/**
	 * function to split the elements to two sides over the pivot bigger than
	 * pivot and less than pivot
	 * 
	 * @param sort
	 * @param start
	 * @param end
	 * @param pivot
	 * @return the array after the partition
	 */
	private static int partition(double[] arr, int left, int right, double pivot) {
		while (left < right) {
			while (arr[left] < pivot && left < right)
				left++;

			while (arr[right] > pivot && right >= 0)
				right--;

			if (arr[left] == arr[right])
				left++;
			else if (left < right) {
				double tmp = arr[left];
				arr[left] = arr[right];
				arr[right] = tmp;
			}
		}
		return right;
	}

	/**
	 * the function divides the array into groups of at most five elements,
	 * computes the median of each of those groups using some functions, then
	 * recursively computes the true median of the medians found in the previous
	 * step
	 * 
	 * @param sort
	 * @param i
	 * @param j
	 * @return the pivot
	 */
	private static double medianOfMedians(double[] arr, int i, int j) {
		if (j - i < 5)
			return median(arrayMaker(arr, i, j));

		double[] medians = new double[(j - i) / 5 + divideBy5((j - i) % 5)];
		for (int pointer = 0; pointer < medians.length; pointer++) {
			int key = pointer * 5 + i;
			if (key + 4 > arr.length - 1)
				medians[pointer] = median(arrayMaker(arr, key, arr.length - 1));
			else
				medians[pointer] = median(arrayMaker(arr, key, key + 4));
		}
		return medianOfMedians(medians, 0, medians.length - 1);
	}

	/**
	 * checks if the array divided by 5
	 * 
	 * @param i
	 * @return
	 */
	private static int divideBy5(int i) {
		return (i == 0) ? 0 : 1;
	}

	/**
	 * the arrayMaker create arrays of 5 or less doubles
	 * 
	 * @param sort
	 * @param i
	 * @param j
	 * @return the new array
	 */
	private static double[] arrayMaker(double[] arr, int i, int j) {
		if (j == i)
			return arr;
		double[] last = new double[j - i];
		for (int k = 0; k < last.length; k++)
			last[k] = arr[i + k];
		return last;
	}

	/**
	 * sorts a given array using the insertion sort algorithm which is fast sort
	 * for small array
	 * 
	 * @param sort
	 * @return the median of the array
	 */
	private static double median(double[] sort) {
		if (sort.length <= 1)
			return sort[0];
		for (int i = 1; i < sort.length; i++) {
			double key = sort[i];
			int j = i - 1;
			while (j >= 0 && sort[j] > key) {
				sort[j + 1] = sort[j];
				j -= 1;
			}
			sort[j + 1] = key;
		}
		return sort[sort.length / 2];
	}

	/**
	 * Sorts a given array using the selection sort algorithm.
	 * 
	 * Should run in complexity O(n^2) in the worst case.
	 * 
	 * @param arr
	 *            - the array to be sorted
	 */
	public static void selectionSort(double[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < arr.length; j++)
				if (arr[j] < arr[index])
					index = j;

			double smallerNumber = arr[index];
			arr[index] = arr[i];
			arr[i] = smallerNumber;
		}
	}

	public static void main(String[] args) {
		selectionVsQuick();
		mergeVsQuick();
		mergeVsQuickOnSortedArray();
		selectVsMerge();
	}

	/**
	 * Compares the selection sort algorithm against quick sort on random arrays
	 */
	public static void selectionVsQuick() {
		double[] quickTimes = new double[SELECTION_VS_QUICK_LENGTH];
		double[] selectionTimes = new double[SELECTION_VS_QUICK_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < SELECTION_VS_QUICK_LENGTH; i++) {
			long sumQuick = 0;
			long sumSelection = 0;
			for (int k = 0; k < T; k++) {
				int size = (int) Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSort(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				selectionSort(b);
				endTime = System.currentTimeMillis();
				sumSelection += endTime - startTime;
			}
			quickTimes[i] = sumQuick / T;
			selectionTimes[i] = sumSelection / T;
		}
		Plotter.plot("quick sort", quickTimes, "selection sort", selectionTimes);
	}

	/**
	 * Compares the merge sort algorithm against quick sort on random arrays
	 */
	public static void mergeVsQuick() {
		double[] quickTimes = new double[MERGE_VS_QUICK_LENGTH];
		double[] mergeTimes = new double[MERGE_VS_QUICK_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < MERGE_VS_QUICK_LENGTH; i++) {
			long sumQuick = 0;
			long sumMerge = 0;
			for (int k = 0; k < T; k++) {
				int size = (int) Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSort(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				mergeSort(b);
				endTime = System.currentTimeMillis();
				sumMerge += endTime - startTime;
			}
			quickTimes[i] = sumQuick / T;
			mergeTimes[i] = sumMerge / T;
		}
		Plotter.plot("quick sort", quickTimes, "merge sort", mergeTimes);
	}

	/**
	 * Compares the merge sort algorithm against quick sort on pre-sorted arrays
	 */
	public static void mergeVsQuickOnSortedArray() {
		double[] quickTimes = new double[MERGE_VS_QUICK_SORTED_LENGTH];
		double[] mergeTimes = new double[MERGE_VS_QUICK_SORTED_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < MERGE_VS_QUICK_SORTED_LENGTH; i++) {
			long sumQuick = 0;
			long sumMerge = 0;
			for (int k = 0; k < T; k++) {
				int size = (int) Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = j;
					b[j] = j;
				}
				startTime = System.currentTimeMillis();
				quickSort(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				mergeSort(b);
				endTime = System.currentTimeMillis();
				sumMerge += endTime - startTime;
			}
			quickTimes[i] = sumQuick / T;
			mergeTimes[i] = sumMerge / T;
		}
		Plotter.plot("quick sort on sorted array", quickTimes, "merge sort on sorted array", mergeTimes);
	}

	/**
	 * Compares the select algorithm against sorting an array.
	 */
	public static void selectVsMerge() {
		double[] mergeTimes = new double[SELECT_VS_MERGE_LENGTH];
		double[] selectTimes = new double[SELECT_VS_MERGE_LENGTH];
		long startTime, endTime;
		double x;
		Random r = new Random();
		for (int i = 0; i < SELECT_VS_MERGE_LENGTH; i++) {
			long sumMerge = 0;
			long sumSelect = 0;
			for (int k = 0; k < T; k++) {
				int size = (int) Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				int index = (int) (Math.random() * size);
				startTime = System.currentTimeMillis();
				mergeSort(a);
				x = a[index];
				endTime = System.currentTimeMillis();
				sumMerge += endTime - startTime;
				startTime = System.currentTimeMillis();
				x = select(b, index);
				endTime = System.currentTimeMillis();
				sumSelect += endTime - startTime;
			}
			mergeTimes[i] = sumMerge / T;
			selectTimes[i] = sumSelect / T;
		}
		Plotter.plot("merge sort and select", mergeTimes, "select", selectTimes);
	}
}