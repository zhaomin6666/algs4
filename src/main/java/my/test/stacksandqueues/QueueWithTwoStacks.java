package my.test.stacksandqueues;

import edu.princeton.cs.algs4.Stack;

public class QueueWithTwoStacks<T> {
	Stack<T> stackRegular;
	Stack<T> stackReverse;

	public QueueWithTwoStacks() {
		stackRegular = new Stack<>();
		stackReverse = new Stack<>();
	}

	public void enqueue(T item) {
		stackRegular.push(item);
	}

	public T dequeue() throws Exception {
		if (stackReverse.isEmpty()) {
			if (stackRegular.isEmpty()) {
				throw new Exception("Queue is empty");
			}
			while (!stackRegular.isEmpty()) {
				T temp = stackRegular.pop();
				stackReverse.push(temp);
			}
		}
		return stackReverse.pop();
	}

	public static void main(String[] args) throws Exception {
		QueueWithTwoStacks<String> queueWithTwoStacks = new QueueWithTwoStacks<>();
		queueWithTwoStacks.enqueue("I");
		queueWithTwoStacks.enqueue("AM");
		queueWithTwoStacks.enqueue("ROBOT");
		System.out.println(queueWithTwoStacks.dequeue());
		System.out.println(queueWithTwoStacks.dequeue());
		System.out.println(queueWithTwoStacks.dequeue());
		System.out.println(queueWithTwoStacks.dequeue());
	}
}
