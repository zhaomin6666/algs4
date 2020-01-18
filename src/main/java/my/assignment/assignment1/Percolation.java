package my.assignment.assignment1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] grid;
    private final int n;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private int openedSites;

    // create N-by-N grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        grid = new boolean[n + 1][n + 1];
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        openedSites = 0;
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        if (!isOpen(i,j)){
            int index = helper(i, j);
            // get top
            if (i == 1) {
                weightedQuickUnionUF.union(index, n * n);
            } else if (isOpen(i - 1, j)) {
                weightedQuickUnionUF.union(index, helper(i - 1, j));
            }
            // get bottom
            if (i == n) {
                weightedQuickUnionUF.union(index, n * n + 1);
            } else if (isOpen(i + 1, j)) {
                weightedQuickUnionUF.union(index, helper(i + 1, j));
            }
            // get left
            if (j != 1 && isOpen(i, j - 1)) {
                weightedQuickUnionUF.union(index, helper(i, j - 1));
            }
            // get right
            if (j != n && isOpen(i, j + 1)) {
                weightedQuickUnionUF.union(index, helper(i, j + 1));
            }
            grid[i - 1][j - 1] = true;
            openedSites++;
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        validate(i, j);
        return grid[i - 1][j - 1];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        validate(i, j);
        return weightedQuickUnionUF.find(helper(i, j)) == weightedQuickUnionUF.find(n * n);
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.find(n * n) == weightedQuickUnionUF.find(n * n + 1);
    }

    private int helper(int i, int j) {
        validate(i, j);
        return (i - 1) * n + j - 1;
    }

    private void validate(int i, int j) {
        if (i > n || j > n || i <= 0 || j <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public int numberOfOpenSites() {
        return openedSites;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(1, 5);
        System.out.println(percolation.isOpen(1, 5));
        System.out.println(percolation.isFull(1, 5));
        System.out.println(percolation.numberOfOpenSites());
        percolation.open(1, 5);
        System.out.println(percolation.numberOfOpenSites());
    }
}
