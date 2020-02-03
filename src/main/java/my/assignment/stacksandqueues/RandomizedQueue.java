package my.assignment.assignment.stacksandqueues;

import edu.princeton.cs.algs4.Stack;

public class RandomizedQueue<Item> {

    private Stack<Item> stack;

    // construct an empty randomized queue
    public RandomizedQueue() {
        stack = new Stack<>();
    }

    // return true if the queue is empty, false otherwise
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // insert the item into the queue
    public void add(Item item) {
        stack.push(item);
    }

    // delete and return an item from the queue, uniformly at random
    public Item remove() {

        return null;
    }

    public static void main(String[] args) {

    }
}
