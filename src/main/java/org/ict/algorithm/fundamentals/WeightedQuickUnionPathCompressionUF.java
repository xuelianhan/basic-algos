package org.ict.algorithm.fundamentals;

import java.util.Arrays;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

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
 * $ javac org/ict/algorithm/fundamentals/WeightedQuickUnionPathCompressionUF.java 
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
 * $ java org/ict/algorithm/fundamentals/WeightedQuickUnionPathCompressionUF < ../resources/tinyUF.txt 
 * 4 3
 * parent:[0, 1, 2, 4, 4, 5, 6, 7, 8, 9] ,9
 * size:  [1, 1, 1, 1, 2, 1, 1, 1, 1, 1] ,9
 * 3 8
 * parent:[0, 1, 2, 4, 4, 5, 6, 7, 4, 9] ,8
 * size:  [1, 1, 1, 1, 3, 1, 1, 1, 1, 1] ,8
 * 6 5
 * parent:[0, 1, 2, 4, 4, 6, 6, 7, 4, 9] ,7
 * size:  [1, 1, 1, 1, 3, 1, 2, 1, 1, 1] ,7
 * 9 4
 * parent:[0, 1, 2, 4, 4, 6, 6, 7, 4, 4] ,6
 * size:  [1, 1, 1, 1, 4, 1, 2, 1, 1, 1] ,6
 * 2 1
 * parent:[0, 2, 2, 4, 4, 6, 6, 7, 4, 4] ,5
 * size:  [1, 1, 2, 1, 4, 1, 2, 1, 1, 1] ,5
 * 8 9
 * 5 0
 * parent:[6, 2, 2, 4, 4, 6, 6, 7, 4, 4] ,4
 * size:  [1, 1, 2, 1, 4, 1, 3, 1, 1, 1] ,4
 * 7 2
 * parent:[6, 2, 2, 4, 4, 6, 6, 2, 4, 4] ,3
 * size:  [1, 1, 3, 1, 4, 1, 3, 1, 1, 1] ,3
 * 6 1
 * parent:[6, 2, 6, 4, 4, 6, 6, 2, 4, 4] ,2
 * size:  [1, 1, 3, 1, 4, 1, 6, 1, 1, 1] ,2
 * 1 0
 * 6 7
 * 2 components
 *
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
            //be careful here!
            size[i] = 1;
            //be careful here!
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
    
    /**
     * Returns true if the two sites are in the same component
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     *
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * component containing site{@code q}
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     *
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        
        //make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
        StdOut.println("parent:" + Arrays.toString(parent) + " ," + count);
        StdOut.println("size:  " + Arrays.toString(size) + " ," + count);
    }

    /**
     * Reads in a sequence of pairs of integers (between 0 and n-1) from standard input
     * where each integer represents some site;
     * if the sites are in different components, merge the two components
     * and print the pair to standard output
     *
     */
    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            StdOut.println(p + " " + q);

            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
        }
        StdOut.println(uf.count + " components");
    }
}
