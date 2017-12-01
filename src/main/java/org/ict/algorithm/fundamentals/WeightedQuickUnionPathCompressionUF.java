package org.ict.algorithm.fundamentals;

import java.util.Arrays;
import org.ict.algorithm.fundamentals.StdIn;
import org.ict.algorithm.fundamentals.StdOut;

/**
 *
 * Weighted quick-union with path compression
 * Modify WeightedQuickUnionUF.java to implement path compression, as described in Exercise 1.5.12
 * Give a sequence of input pairs that causes this method to produce a tree of height 4
 * Note: The amortized cost per operation for this algorithm is known to be bounded by a function known as 
 * the inverse Ackermann function and is less than 5 for any conceivable value of N that arises in practice.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class WeightedQuickUnionPathCompressionUF {

    // parent[i] = parent of i
    private int[] parent;

    //size[i] = number of sites in tree rooted at i
    //Note: not necessarily correct if i is not a root node
    private int[] size;
    
    //number of components
    private int count;

    /**
     * Initializes an empty union-find data structure with {@code n}
     * sites {@code 0} through {@code n-1} 
     * Each site is initially in its own component
     *
     * @param n the number of sites
     *
     */
    public WeightedQuickUnionPathCompressionUF(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = i;
        }
    }

    public int count() {
        return this.count;
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    /**
     * Returns the component identifier for the component containing site {@code p}
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     */
    public int find(int p) {
        validate(p);
        int root = p;
        while (root != parent[root]) {
            root = parent[root];
        }
        while (p != root) {
            int newp = parent[p];
            parent[p] = root;
            p = newp;
        }
        return root;
    }
}
