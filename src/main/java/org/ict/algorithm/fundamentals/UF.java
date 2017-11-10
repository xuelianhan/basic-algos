package org.ict.algorithm.fundamentals;

/**
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class UF {
    //parent[i] = parent of i
    private int[] parent; 
    //ranke[i] = rank of subtree rooted at i (never more than 31)
    private byte[] rank;
    //number of components
    private int count;


    /**
     * Initializes an empty union-find data structure with {@code n} sites 
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     *
     * @param n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     *
     */
    public UF(int n) {
        if (n < 0) throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }


    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("Index " + p + "is not between 0 and " + (n-1));   
        }
    }


}
