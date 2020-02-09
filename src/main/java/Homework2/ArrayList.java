package Homework2;

public class ArrayList<T extends Comparable<T>> {
    private int size = 0;
    private Object[] list;

    public ArrayList() {
        list = new Object[1];
    }

    public ArrayList(int capacity) {
        size = capacity;
        list = new Object[capacity];
    }

    public boolean delete(T item) {
        int i = 0;
        while (i < size && !list[i].equals(item)) {
            i++;
        }
        if (size == i) {
            return false;
        }
        for (int j = i; j < size - 1; j++) {
            list[j] = list[j + 1];
        }
        list[size - 1] = null;
        size--;
        if (size  == list.length / 4 && size > 0 ) {
            resize(list.length / 2);
        }
        return true;
    }

    public void insert(T item) {
        if (size == list.length) {
            resize(2 * list.length);
        }
        list[size++] = item;
    }

    private void resize(int capacity) {
        Object[] temp = new Object[capacity];
        System.arraycopy(list, 0 , temp, 0, size);
        list = temp;
    }

    public boolean find(T item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    public void selectionSort() {
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (less((T) list[j], (T) list[min])) {
                    min = j;
                }
            }
            exchange(i, min);
        }
    }

    private boolean less(T item1, T item2) {
        return item1.compareTo(item2)  < 0 ;
    }

    private void exchange(int idx1, int idx2) {
        Object tmp = list[idx1];
        list[idx1] = list[idx2];
        list[idx2] = tmp;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        for (int i = 0; i < size; i++) {
            str.append((T) list[i]);
            str.append(", ");
        }
        return str.toString();
    }

    public void insertionSort() {
        for (int i = 0; i < size; i++) {
            for (int j = i; j > 0; j--) {
                if (less((T) list[j], (T) list[j - 1])) {
                    exchange(j, j - 1);
                }
                else {
                    break;
                }
            }
        }
    }

    public void bubbleSort(){
        for (int i = size - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (less((T) list[j + 1],(T) list[j])){
                    exchange(j+1, j);
                }
            }
        }
    }
}