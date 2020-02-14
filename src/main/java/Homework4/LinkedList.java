package Homework4;

public class LinkedList {

    private static LinkedList list;
    private Link first;

    public static void main(String[] args) {
        list = new LinkedList();
        list.insert("Макс", 36);
        list.insert("Оля", 35);
        list.insert("Ника", 9);

        list.display();
        System.out.println();
        if (!list.isEmpty()) list.delete();
        list.display();
    }

    public LinkedList() {
        first = null;
    }

    public void setFirst(Link first) {
        this.first = first;
    }

    public Link getFirst() {
        return first;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insert(String name, int age) {
        Link link = new Link(name, age);
        link.next = first;
        first = link;
    }

    public Link delete() {
        Link tmp = first;
        first = first.next;
        return tmp;

    }

    public void display() {
        Link current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }

    static class Link {

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
