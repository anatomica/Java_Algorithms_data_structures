package Homework1;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        int[] nums = {-7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7};
        System.out.println(sum(nums) + "\n");
        System.out.println(num(nums));
        System.out.println(nums(nums));
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

    private static int oneNumber;
    private static int twoNumber;
    private static ArrayList<Integer> num (int[] numbers) {
        ArrayList<Integer> num = new ArrayList<>();
        int score = 0;
        for (int i : numbers) {
           if (score == 0) {
               score = 1;
               oneNumber = i;
               twoNumber = oneNumber;
           }
           if (i > oneNumber) {
               twoNumber = oneNumber;
               oneNumber = i;
           }
           else if (i > twoNumber && i != oneNumber) {
               twoNumber = i;
           }
        }
        num.add(oneNumber);
        num.add(twoNumber);
        return num;
    }

    private static final int number = 5;
    private static int halfNumber;
    private static List<Integer> nums (int[] numbers) {
        List<Integer> newNums = new ArrayList<>(numbers.length);
        for (int i : numbers) newNums.add(i);
        halfNumber = newNums.get(newNums.size() / 2);

        for (int i = newNums.size(); i > 0; i /= 2) {
            if (halfNumber == number) {
                newNums.clear();
                newNums.add(halfNumber);
                break;
            }
            else {
                if (halfNumber < number) {
                    newNums = newNums.stream().filter(num -> num > halfNumber).collect(Collectors.toList());
                    halfNumber = newNums.get(newNums.size() / 2);
                }
                if (halfNumber > number) {
                    newNums = newNums.stream().filter(num -> num < halfNumber).collect(Collectors.toList());
                    halfNumber = newNums.get(newNums.size() / 2);
                }
            }
        }
        return newNums;
    }
}
