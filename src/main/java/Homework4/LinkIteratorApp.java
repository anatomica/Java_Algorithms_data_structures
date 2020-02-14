package Homework4;

import java.util.Iterator;

public class LinkIteratorApp implements Iterator<Object> {
    private Link current;
    private Link previous;
    private LinkedList linkedList;
    private static LinkedList<String> list;

    public static void main(String[] args) {
        list = new LinkedList<>();
        list.insert("test!");
        list.insert("iterator");
        list.insert("linked");
        list.insert("is");
        list.insert("This");

        LinkIteratorApp iterator = new LinkIteratorApp(list);
        Link<String> link = (Link<String>)iterator.current;
        System.out.println(link.getValue());
        while (iterator.hasNext()){
            link = (Link<String>) iterator.next();
            System.out.println(link.getValue());
        }
    }

    public LinkIteratorApp(LinkedList linkedList) {
        this.linkedList = linkedList;
        this.reset();
    }

    public void reset(){
        current = linkedList.getFirst();
        previous = null;
    }

    @Override
    public boolean hasNext(){
        return current.getNext() != null;
    }

    @Override
    public Object next() {
        nextLink();
        return getCurrent();
    }

    @Override
    public void remove() {
        Object object = current.getValue();
        if (previous == null){
            linkedList.setFirst(current.getNext());
            reset();
        } else {
            previous.setNext(current.getNext());
            if (!hasNext()) {
                reset();
            } else {
                current = current.getNext();
            }
        }
        System.out.println(object);
    }

    private void nextLink(){
        previous = current;
        current = current.getNext();
    }

    private Link getCurrent(){
        return current;
    }


    public static class Link<T> {
        private T link;
        private Link<T> next;

        public Link(T link) {
            this.link = link;
        }

        public Link<T> getNext() {
            return next;
        }

        public void setNext(Link<T> next) {
            this.next = next;
        }

        public T getValue() {
            return link;
        }

        @Override
        public String toString() {
            return "Link{" +
                    "link=" + link +
                    '}';
        }
    }


    public static class LinkedList<T> {
        private Link<T> head;

        public LinkedList() {
            head = null;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void insert(T link) {
            Link<T> temp = new Link<>(link);
            temp.setNext(head);
            this.head = temp;
        }

        public Link<T> delete() {
            Link<T> temp = head;
            head = head.getNext();
            return temp;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            Link<T> temp = head;
            while (temp != null) {
                stringBuilder.append(temp.getValue()).append("\n");
                temp = temp.getNext();
            }
            return stringBuilder.toString();
        }

        public Link getFirst() {
            return head;
        }

        public void setFirst(Link link) {
            this.head = link;
        }
    }

}
