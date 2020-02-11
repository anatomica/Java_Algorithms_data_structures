package Homework3;

import java.util.NoSuchElementException;

public class ArrayStack<Item> {
    private Object[] stack;
    private int size = 0;

    public ArrayStack() {
        stack = new Object[1];
    }

    public int getSize() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    // помещает элемент на вершину стека
    public void push(Item item) {
        if (size == stack.length) {
            resize(2 * stack.length);
        }
        stack[size++] = item;
    }

    // удаляет элемент с вершины стека
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = (Item) stack[size - 1];
        stack[size - 1] = null;
        size--;
        if (size > 0 && size == stack.length / 4) {
            resize(stack.length / 2);
        }
        return item;
    }

    // получает элемент стека, находящийся на его вершине
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (Item) stack[size - 1];
    }

    // изменяет размер стека до значения capacity
    private void resize(int capacity) {
        Object[] temp = new Object[capacity];
        if (size >= 0) System.arraycopy(stack, 0, temp, 0, size);
        stack = temp;
    }

    // стек на печать
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(stack[i]).append(", ");
        }
        return s.toString();
    }
}
