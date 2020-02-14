package Homework4;

public class LinkedListTwo {

    private static LinkedListTwo list;
    private Link first;
    private Link last;

    public static void main(String[] args) {
        list = new LinkedListTwo();
        list.insert("Макс", 36);
        list.insert("Оля", 35);
        list.insert("Ника", 9);
        list.insertLast("Глеб", 5);

        System.out.println("List:");
        list.display();
        System.out.println();

        System.out.println("Remove Глеб");
        list.delete("Глеб");
        list.display();
        System.out.println();

        System.out.println("Remove first");
        list.delete();
        list.display();
    }

    public LinkedListTwo() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void display() {
        Link current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }

    public void insert(String name, int age) {
        Link newLink = new Link(name, age);
        if (this.isEmpty()) last = newLink;
        newLink.next = first;
        first = newLink;
    }

    public void insertLast(String name, int age) {
        Link newLink = new Link(name, age);
        if (this.isEmpty()) first = newLink;
        else last.next = newLink;
        last = newLink;
    }

    public Link delete() {
        Link temp = first;
        if (first.next == null) last = null;
        first = first.next;
        return temp;
    }

    public Link delete(String name) {
        Link current = first;
        Link previous = first;
        while (!current.name.equals(name)) {
            if (current.next == null) return null;
            else {
                previous = current;
                current = current.next;
            }
        }
        if (current == first) first = first.next;
        else previous.next = current.next;
        return current;
    }

    public Link find(String name) {
        Link current = first;
        while (!current.name.equals(name)) {
            if (current.next == null) return null;
            else current = current.next;
        }
        return current;
    }

    public static class Link {

        private String name;
        private Link next;
        private int age;

        public Link(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public void display() {
            System.out.println("Имя: " + this.name + ", Возраст: " + this.age);
        }
    }
}