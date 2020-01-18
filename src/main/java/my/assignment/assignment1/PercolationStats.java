package my.assignment.assignment1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] allResults;
    private final int t;
    private static final double P = 1.96;
    private double mean;
    private double stddev;

    // perform T independent experiments on an n-by-N grid
    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException();
        }
        allResults = new double[t];
        this.t = t;
        for (int i = 0; i < t; i++) {
            allResults[i] = simulate(n);
        }
        mean = StdStats.mean(allResults);
        stddev = StdStats.stddev(allResults);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - P * stddev / Math.sqrt(t);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + P * stddev / Math.sqrt(t);
    }

    private double simulate(int n) {
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            int index = StdRandom.uniform(n * n);
            int i = index / n + 1;
            int j = index % n + 1;
            percolation.open(i, j);
        }
        return (double) percolation.numberOfOpenSites() / (n * n);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(StdIn.readLine());
        int t = Integer.parseInt(StdIn.readLine());
        // int n = 200;
        // int t = 100;
        PercolationStats percolationStats = new PercolationStats(n, t);
        System.out.println("Example values after creating PercolationStats(" + n + ", " + t + ")");
        System.out.println("mean() = " + percolationStats.mean());
        System.out.println("stddev() = " + percolationStats.stddev());
        System.out.println("confidenceLow() = " + percolationStats.confidenceLo());
        System.out.println("confidenceHigh() = " + percolationStats.confidenceHi());
    }
}
