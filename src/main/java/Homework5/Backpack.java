package Homework5;

import java.util.*;

public class Backpack {

    static List<Integer> allValue = new ArrayList<>();
    static int[] weight = {2, 3, 3, 4, 2, 2, 5, 7, 1, 3};
    static int[] val = {1, 2, 5, 9, 4, 9, 11, 40, 4, 90};
    static int w = 12;
    static int allWeight;
    static int allVal;

    public static void main(String[] args) {
        System.out.println("Ценность для случая с наибольшей ценностью: " + recursive(weight, val, w, 0));
    }

    public static int recursive(int[] weight, int[] val, int w, int itemNum) {
        if (w == 0 || itemNum == weight.length) {
            return 0;
        }
        if (weight[itemNum] > w) {
            return recursive(weight, val, w,itemNum + 1);
        }
        // TODO вычислить ценность для случаев, когда мы берем и не берем в рюкзак предмет
        if (allWeight < w && (allWeight + weight[itemNum] <= w)) {
            allWeight += weight[itemNum];
            allVal += val[itemNum];
            if (itemNum + 1 < weight.length) return recursive(weight, val, w,itemNum + 1);
        }
        if ((allWeight < w && (allWeight + weight[itemNum] > w)) || (allWeight == w)) {
            allValue.add(allVal);
            allWeight = allVal = 0;
            return recursive(reduceValue(weight), reduceValue(val), w, 0);
        }
        // TODO вернуть ценность для случая с наибольшей ценностью
        return printBestValue(allValue);
    }

    private static int[] reduceValue(int[] values) {
        List<String> list = new ArrayList<>();
        for (int value : values) {
            list.add(String.valueOf(value));
        }
        list.remove(0);
        int[] newValue = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            newValue[i] = Integer.parseInt(list.get(i));
        }
        return newValue;
    }

    private static int printBestValue(List<Integer> allValue) {
        int result = 0;
        for (Integer val : allValue) {
            if (val > result) result = val;
        }
        return result;
    }
}
