package org.ict.algorithm.fundamentals;

import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
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


    //validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("Index " + p + "is not between 0 and " + (n-1));   
        }
    }
    
    /**
     * Returns the number of components
     * 
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int count() {
        return this.count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}
     * 
     * @param p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     *  
     */
    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    /**
     * Returns true if the two sites are in the same component
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code ture} if the two sites {@code p} and {@code q} are in the same component
     *         {@code false} otherwise
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    
    /**
     * Merges the component containing site {@code p} with the 
     * component containing site {@code q}
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }

        //make root of smaller rank point to root of larger rank
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        this.count--;
    }
    
    /**
     *
     *
     */
    public static void main(String[] args) {
        int n = StdIn.readInt();
        UF uf = new UF(n);
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
