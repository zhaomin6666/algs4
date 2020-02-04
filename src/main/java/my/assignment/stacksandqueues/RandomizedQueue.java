package my.assignment.stacksandqueues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] s;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // return true if the queue is empty, false otherwise
    public boolean isEmpty() {
        return s == null || size == 0;
    }

    // insert the item into the queue
    public void enqueue(Item item) {
        if (s == null) {
            s = (Item[]) new Object[1];
        }
        if (size == s.length) {
            resize(s.length << 1);
        }
        s[size++] = item;
    }

    // resize the array
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size(); i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    // return the size of array
    public int size() {
        return size;
    }


    // delete and return an item from the queue, uniformly at random
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int randomNum = StdRandom.uniform(size());
        Item returnItem = s[randomNum];
        s[randomNum] = s[--size];
        if (size <= s.length >> 2) {
            resize(s.length >> 1 == 0 ? 1 : s.length >> 1);
        }
        return returnItem;
    }

    // return a random item
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int randomNum = StdRandom.uniform(size());
        return s[randomNum];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current;
        private final int[] order;

        public RandomizedQueueIterator() {
            current = 0;
            order = StdRandom.permutation(size);
        }

        @Override
        public boolean hasNext() {
            return current != size;
        }

        @Override
        public Item next() {
            if (current == size) {
                throw new NoSuchElementException();
            }
            return s[order[current++]];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        System.out.println("=====enqueue======");
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        Iterator<Integer> iterator = randomizedQueue.iterator();
        System.out.println("====iterator====");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("=====sample=====");
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println("=====dequeue======");
        System.out.println(randomizedQueue.dequeue() + "  " + randomizedQueue.size());
        System.out.println(randomizedQueue.dequeue() + "  " + randomizedQueue.size());
        System.out.println(randomizedQueue.dequeue() + "  " + randomizedQueue.size());
        System.out.println(randomizedQueue.dequeue() + "  " + randomizedQueue.size());
        System.out.println("=====enqueue======");
        randomizedQueue.enqueue(1);
        System.out.println("====iterator====");
        Iterator<Integer> iterator2 = randomizedQueue.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }
}
