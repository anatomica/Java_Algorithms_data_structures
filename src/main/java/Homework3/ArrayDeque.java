package Homework3;

public class ArrayDeque<Item> {

    private static final int SIZE_INCREASE_RATIO = 2;
    private Object[] queue;
    private int start;
    private int end;
    private int size;

    public ArrayDeque() {
        start = 0;
        end = 0;
        size = 0;
        queue = new Object[1];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void resize () {
        int newSize;
        int tempIndex = 1;

        if (size == 0)
            newSize = SIZE_INCREASE_RATIO;
        else
            newSize = size * SIZE_INCREASE_RATIO;

        Object[] temp = new Object[newSize];

        if (size > 0) {
            if (end < start) {
                for (int i = start; i < queue.length; i++, tempIndex++)
                    temp[tempIndex] = queue[i];

                for (int i = 0; i <= end; i++, tempIndex++)
                    temp[tempIndex] = queue[i];
            } else
                for (int i = start; i < end; i++, tempIndex++)
                    temp[tempIndex] = queue[i];
        } else {
            start = 0;
            end = -1;
        }
        queue = temp;
    }

    public void insertLeft (Item item) {
        if (queue.length == size)
            resize();

        if (start > 0)
            start--;
        else
            start = queue.length - 1;

        queue[start] = item;
        size++;

        if (size == 1)
            end = start;
    }

    public void insertRight (Item item) {
        if (queue.length == size)
            resize();

        if (end == queue.length - 1)
            end = 0;
        else
            end++;

        queue[start] = item;
        size++;

        if (size == 1)
            start = end;
    }

    public Object peekLeft () {
        Object res;

        if (size == 0) {
            System.err.println("Queue empty");
            res = null;
        } else {
            res = queue[start];
        }
        return res;
    }

    public Object peekRight () {
        Object res;

        if (size == 0) {
            System.err.println("Queue empty");
            res = null;
        } else {
            res = queue[end];
        }
        return res;
    }
}