package org.ict.algorithm.fundamentals;

import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
import java.util.Arrays;

/**
 * Weighted quick-union by height
 * Develop a implementation WeightedQuickUnionByHeightUF.java that uses the same basic strategy as 
 * weighted quick-union but keeps track of tree height and always links the shorter tree to the taller one 
 * Prove a logarithmic upper bound on the height of the trees for N sites with your algorithm
 * 
 * A union operation between elements in different trees either leaves the height unchanged(if the two tree
 * have different heights) or increase the height by one (if the two tree are the same height)
 * You can prove by induction that the size of the tree is at least 2^height
 * Therefore, the height can increase at most lg N times
 *
 * @author Robert sedgewick
 * @author Kevin Wayne 
 *
 */
public class WeightedQuickUnionByHeightUF {
    //parent[i] = parent of i
    private int[] parent; 
    //height[i] = height of subtree rooted at i
    private int[] height;
    //number of components
    private int count;

    /**
     * Initializes an empty union-find data structure with {@code n} sites
     * {@code 0} through {@code n-1} 
     * Each site is initially in its own component
     *
     * @param n the number of sites
     */
    public WeightedQuickUnionByHeightUF(int n) {
        this.count = n;
        this.parent = new int[n];
        this.height = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            //becareful here
            height[i] = 0;
            //becareful here
        }
    }

    public int count() {
        return this.count;
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p > n) {
            throw new IllegalArgumentException("index" + p + " is not between o and " + (n-1));
        }
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site{@code p} with the
     * component containing site {@code q}
     * 
     * @param p the integer representing one site
     * @param q the integer representing the other site
     */
    public void union(int p, int q) {
       int i = find(p); 
       int j = find(q);
       if (i == j) {
           return;
       }
       //make shorter root point to taller one
       if (height[i] < height[j]) {
            parent[i] = j;
       } else if (height[i] > height[j]) {
            parent[j] = i;
       } else {
            parent[j] = i;
            height[i]++;
       }
       count--;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionByHeightUF uf = new WeightedQuickUnionByHeightUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
