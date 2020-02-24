package Homework5;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exponentiation {

    public static void main(String[] args) {
        exponentiation();
    }

    private static void exponentiation() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите число, возводимое в степень, а после саму степень:");
            double number = scanner.nextDouble();
            int n = scanner.nextInt();
            System.out.println(exponentiationRecursive(number, n));
        } catch (InputMismatchException e) {
            System.out.println("Некорретный формат данных, степень должна быть целым числом!");
            exponentiation();
        }
    }

    public static double exponentiationRecursive(double x, int n){
        if (n == 0)
            return 1;
        else if (n == 1)
            return x;
        else if (n > 1)
            return x * exponentiationRecursive(x, n - 1);
        else
            return 1 / (x * exponentiationRecursive(x, -n - 1));
    }
}
