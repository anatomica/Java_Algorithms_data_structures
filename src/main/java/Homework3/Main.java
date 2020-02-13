package Homework3;

public class Main {
    public static void main(String[] args) {

        String text = "This worlds will be flipped";
        System.out.println(stringRevers(text));
    }

    public static String stringRevers(String str) {
        ArrayStack<Character> mas = new ArrayStack<>();
        StringBuilder sb = new StringBuilder();
        // char[] chars = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            mas.push(str.charAt(i));
        }
        for (int i = 0; i < str.length(); i++) {
            sb.append(mas.pop());
        }
        return sb.toString();
    }
}
