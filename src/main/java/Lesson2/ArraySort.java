package Lesson2;

import java.util.Arrays;

public class ArraySort {

    public static void bubbleSort(int[] arr) {
        for (int i=arr.length-1; i > 0; i--) {
            for (int j=0; j<i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            int minIx = i;
            for (int j=i+1; j<arr.length; j++) {
                if (arr[j] < arr[minIx]) {
                    minIx = j;
                }
            }
            swap(arr, i, minIx);
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i=1; i<arr.length; i++) {
            int tmp = arr[i];
            int j=i;
            while (j>0 && arr[j-1] > tmp) {
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = tmp;
        }
    }

    // N * Log (N)
    public static void mergeSort(int[] arr) {
        int[] tmp = new int[arr.length];
        mergeSortInt(arr, tmp, 0, arr.length-1);
    }

    private static void mergeSortInt(int[] arr, int[] tmp, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSortInt(arr, tmp, lo, mid);
        mergeSortInt(arr, tmp, mid+1, hi);
        merge(arr, tmp, lo, mid, hi);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void merge(int[] arr, int[] tmp, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            tmp[k] = arr[k];
        }

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              arr[k] = tmp[j++];
            else if (j > hi)               arr[k] = tmp[i++];
            else if (tmp[j] < tmp[i]) arr[k] = tmp[j++];
            else                           arr[k] = tmp[i++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {500, 20, -1, 300, 700, 1, 4, 5, 4, 3, 0};
        //bubbleSort(arr);
        //selectionSort(arr);
        //insertionSort(arr);
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
