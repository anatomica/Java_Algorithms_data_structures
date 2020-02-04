package Homework1;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int[] nums = {-7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7};
        System.out.println(sum(nums) + "\n");
        System.out.println(num(nums));
    }

    private static ArrayList<String> sum (int[] numbers) {
        int result = 0;
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<String> allNums = new ArrayList<>();
        for (int i : numbers) {
            for (int j : numbers) {
                for (int k : numbers) {
                    if (i + j + k == result) {
                        list.add(i);
                        list.add(j);
                        list.add(k);
                        allNums.add("\n" + list.toString());
                        list.clear();
                    }
                }
            }
        }
        return allNums;
    }

    private static ArrayList<Integer> num (int[] numbers) {
        ArrayList<Integer> num = new ArrayList<>();
        int tmp = 0;
        int tmp1 = 1;
        for (int i : numbers) {
           if (i > tmp1) {
               tmp = tmp1;
               tmp1 = i;
           }
        }
        num.add(tmp);
        num.add(tmp1);
        return num;
    }
}
