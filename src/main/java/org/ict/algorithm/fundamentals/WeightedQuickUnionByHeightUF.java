package org.ict.algorithm.fundamentals;

import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
import java.util.Arrays;

/**
 * Weighted quick-union by height
 * Develop an implementation WeightedQuickUnionByHeightUF.java that uses the same basic strategy as
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
 * $ javac org/ict/algorithm/fundamentals/WeightedQuickUnionByHeightUF.java 
 * $ more ../resources/tinyUF.txt 
 * 10
 * 4 3
 * 3 8
 * 6 5
 * 9 4
 * 2 1
 * 8 9
 * 5 0
 * 7 2
 * 6 1
 * 1 0
 * 6 7
 * $ java org/ict/algorithm/fundamentals/WeightedQuickUnionByHeightUF < ../resources/tinyUF.txt 
 * 4 3
 * parent:[0, 1, 2, 4, 4, 5, 6, 7, 8, 9] ,9
 * height:[0, 0, 0, 0, 1, 0, 0, 0, 0, 0] ,9
 * 3 8
 * parent:[0, 1, 2, 4, 4, 5, 6, 7, 4, 9] ,8
 * height:[0, 0, 0, 0, 1, 0, 0, 0, 0, 0] ,8
 * 6 5
 * parent:[0, 1, 2, 4, 4, 6, 6, 7, 4, 9] ,7
 * height:[0, 0, 0, 0, 1, 0, 1, 0, 0, 0] ,7
 * 9 4
 * parent:[0, 1, 2, 4, 4, 6, 6, 7, 4, 4] ,6
 * height:[0, 0, 0, 0, 1, 0, 1, 0, 0, 0] ,6
 * 2 1
 * parent:[0, 2, 2, 4, 4, 6, 6, 7, 4, 4] ,5
 * height:[0, 0, 1, 0, 1, 0, 1, 0, 0, 0] ,5
 * 8 9
 * 5 0
 * parent:[6, 2, 2, 4, 4, 6, 6, 7, 4, 4] ,4
 * height:[0, 0, 1, 0, 1, 0, 1, 0, 0, 0] ,4
 * 7 2
 * parent:[6, 2, 2, 4, 4, 6, 6, 2, 4, 4] ,3
 * height:[0, 0, 1, 0, 1, 0, 1, 0, 0, 0] ,3
 * 6 1
 * parent:[6, 2, 6, 4, 4, 6, 6, 2, 4, 4] ,2
 * height:[0, 0, 1, 0, 1, 0, 2, 0, 0, 0] ,2
 * 1 0
 * 6 7
 * 2 component
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
       StdOut.println("parent:" + Arrays.toString(parent) + " ," + count);
       StdOut.println("height:" + Arrays.toString(height) + " ," + count);
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionByHeightUF uf = new WeightedQuickUnionByHeightUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            StdOut.println(p + " " + q);
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
        }
        StdOut.println(uf.count() + " components");
    }
}
