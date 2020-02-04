package my.assignment.stacksandqueues;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> firstNode;
    private Node<Item> lastNode;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
    }

    public int size() {
        return size;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = firstNode;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node<Item> temp = current;
            current = current.next;
            return temp.item;
        }
    }

    // helper linked list class
    private static class Node<Item> {
        private final Item item;
        private Node<Item> next;
        private Node<Item> pre;

        public Node(Item item) {
            this.item = item;
        }
    }


    // return true if the queue is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // insert the item at the front of the queue
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (firstNode == null) {
            firstNode = new Node<>(item);
            lastNode = firstNode;
        } else {
            Node<Item> temp = new Node<>(item);
            temp.next = firstNode;
            firstNode.pre = temp;
            firstNode = temp;
        }
        size++;
    }

    // insert the item at the end of the queue
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (firstNode == null) {
            firstNode = new Node<>(item);
            lastNode = firstNode;
        } else {
            Node<Item> temp = new Node<>(item);
            temp.pre = lastNode;
            lastNode.next = temp;
            lastNode = temp;
        }
        size++;
    }

    // delete and return the first item in the queue
    public Item removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        Node<Item> temp = firstNode;
        if (size == 1) {
            firstNode = null;
            lastNode = null;
        } else {
            firstNode = firstNode.next;
            firstNode.pre = null;
        }
        size--;
        return temp.item;
    }

    // delete and return the last item in the queue
    public Item removeLast() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        Node<Item> temp = lastNode;
        if (size == 1) {
            firstNode = null;
            lastNode = null;
        } else {
            lastNode = lastNode.pre;
            lastNode.next = null;
        }
        size--;
        return temp.item;
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(4);
        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
    }
}
