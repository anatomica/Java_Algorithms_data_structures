package Homework2;

import java.util.Random;

public class Main {

    private final static int SIZE = 10000;

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();

        Random rnd = new Random(System.currentTimeMillis());
        for (int i = 0; i < SIZE; i++) {
            array.insert(rnd.nextInt());
        }
        long startTime = System.currentTimeMillis();
        array.selectionSort();
        System.out.println("Selection sort time: " + (System.currentTimeMillis() - startTime) + " ms");

        rnd = new Random(System.currentTimeMillis());
        for (int i = 0; i < SIZE; i++) {
            array.insert(rnd.nextInt());
        }
        startTime = System.currentTimeMillis();
        array.insertionSort();
        System.out.println("Insertion sort time: " + (System.currentTimeMillis() - startTime) + " ms");

        rnd = new Random(System.currentTimeMillis());
        for (int i = 0; i < SIZE; i++) {
            array.insert(rnd.nextInt());
        }
        startTime = System.currentTimeMillis();
        array.bubbleSort();
        System.out.println("Bubble sort time: " + (System.currentTimeMillis() - startTime) + " ms");
    }
} 