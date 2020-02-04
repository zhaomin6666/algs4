package my.assignment.stacksandqueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

public class Permutation {
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        RandomizedQueue<String> sQueue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        if (k == 0)
            return; // 不需要输出的时候直接返回
        int counter = k;
        for (int i = 0; i != k; ++i) {
            if (!StdIn.isEmpty()) {
                // if (!scanner.hasNext("#")) {
                String str = StdIn.readString();
                sQueue.enqueue(str);
            }
        }
        while (!StdIn.isEmpty()) {
            // while (!scanner.hasNext("#")) {
            String str = StdIn.readString();
            ++counter; // 要先加counter，不然就不是真随机
            if (StdRandom.uniform(counter) < k) {
                sQueue.dequeue();
                sQueue.enqueue(str);
            }
        }
        for (int i = 0; i != k; ++i) {
            StdOut.print(sQueue.dequeue() + "\n");
        }
    }
}
