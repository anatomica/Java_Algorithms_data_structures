package Lesson2;

public class Arr3sum {

    public static void threeSumInArray(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (arr[i] + arr[j] + arr[k] == 0) {
                        System.out.printf("arr[%d]+arr[%d]+arr[%d] == 0%n", i, j, k);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, -2, 3, -4, 0, 5, -2, -1, 7, 4, 2, 1, 3, -3};
        threeSumInArray(arr);
    }
}
