package Lesson2;

public class MyArray {

    private int[] arr;

    private int len;

    public MyArray(int len) {
        this.arr = new int[len];
        this.len = len;
    }

    public void set(int ix, int value) {
        arr[ix] = value;
    }

    public int get(int ix) {
        return arr[ix];
    }

    public void delete(int ix) {
        for (int i=ix; i<len - 1; i++) {
            arr[i] = arr[i+1];
        }
        if (len > 0) {
            len--;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<len; i++) {
            sb.append(arr[i]);
            sb.append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    public static void main(String[] args) {
        MyArray arr = new MyArray(10);
        arr.set(0, 1);
        arr.set(1, 2);
        arr.set(2, 3);
        arr.set(3, 4);
        arr.set(4, 5);
        System.out.println(arr);
        arr.delete(1);
        arr.delete(3);
        System.out.println(arr);
    }
}
