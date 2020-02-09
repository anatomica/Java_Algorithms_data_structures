package Lesson2;

public class Algo {

    public static void main(String[] args) {
        int i = 1;
        int[] arr = new int[10];
        arr[0] = 1;
        arr[1] = 2;
        System.out.println(i);

        if (i == 1) {
            System.out.println("i == 1");
        }

        Person p1 = new Person("name", "surname", 25);
        Person p2 = new Person("name1", "surname1", 27);

        if (p1.equals(p2)) {

        }
    }
}
