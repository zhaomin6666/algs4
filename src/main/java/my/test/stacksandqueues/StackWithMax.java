package my.test.stacksandqueues;

import edu.princeton.cs.algs4.Stack;

public class StackWithMax {
	Stack<Integer> stackRegular;
	Stack<Integer> stackMax;

	public StackWithMax() {
		stackRegular = new Stack<>();
		stackMax = new Stack<>();
	}

	public void enqueue(Integer item) {
		stackRegular.push(item);
		stackMax.push(stackMax.isEmpty() ? item : (item > stackMax.peek() ? item : stackMax.peek()));
	}

	public Integer dequeue() throws Exception {
		if (stackRegular.isEmpty()) {
			throw new Exception("Queue is empty");
		}
		stackMax.pop();
		return stackRegular.pop();
	}

	public Integer getMax() throws Exception {
		if (stackMax.isEmpty()) {
			throw new Exception("Queue is empty");
		}
		return stackMax.peek();
	}

	public static void main(String[] args) throws Exception {
		StackWithMax stackWithMax = new StackWithMax();
		stackWithMax.enqueue(1);
		System.out.println(stackWithMax.getMax());
		stackWithMax.enqueue(3);
		System.out.println(stackWithMax.getMax());
		stackWithMax.enqueue(2);
		System.out.println(stackWithMax.getMax());
		stackWithMax.enqueue(4);
		System.out.println(stackWithMax.getMax());
		stackWithMax.dequeue();
		System.out.println(stackWithMax.getMax());
		stackWithMax.dequeue();
		System.out.println(stackWithMax.getMax());
		stackWithMax.dequeue();
		System.out.println(stackWithMax.getMax());
		stackWithMax.dequeue();
	}
}
