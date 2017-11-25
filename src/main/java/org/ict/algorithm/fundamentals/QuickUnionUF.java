package org.ict.algorithm.fundamentals;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
import java.util.Arrays;

/**
 *  QuickUnionUF.java is based on the same data structure—the site-indexed id[] array
 *  but it uses a different interpretation of the values that leads to more complicated structures 
 *  Specifically, the id[] entry for each site will be the name of another site in the same component (possibly itself) 
 *  To implement find() we start at the given site, follow its link to another site, 
 *  follow that sites link to yet another site, and so forth, following links until reaching a root, 
 *  a site that has a link to itself
 *  Two sites are in the same component if and only if this process leads them to the same root 
 *  To validate this process, we need union() to maintain this invariant, which is easily arranged: 
 *  we follow links to find the roots associated with each of the given sites, then rename one of the components by 
 *  linking one of these roots to the other.
 *  @author Robert Sedgewick  
 *  @author Kevin Wayne
 *
 *  $ javac org/ict/algorithm/fundamentals/QuickUnionUF.java
 *  $ more ../resources/tinyUF.txt
 *  10
 *  4 3
 *  3 8
 *  6 5
 *  9 4
 *  2 1
 *  8 9
 *  5 0
 *  7 2
 *  6 1
 *  1 0
 *  6 7
 *  $ java org/ict/algorithm/fundamentals/QuickUnionUF < ../resources/tinyUF.txt
 *  4 3
 *  [0, 1, 2, 3, 3, 5, 6, 7, 8, 9] ,9
 *  3 8
 *  [0, 1, 2, 8, 3, 5, 6, 7, 8, 9] ,8
 *  6 5
 *  [0, 1, 2, 8, 3, 5, 5, 7, 8, 9] ,7
 *  9 4
 *  [0, 1, 2, 8, 3, 5, 5, 7, 8, 8] ,6
 *  2 1
 *  [0, 1, 1, 8, 3, 5, 5, 7, 8, 8] ,5
 *  8 9
 *  5 0
 *  [0, 1, 1, 8, 3, 0, 5, 7, 8, 8] ,4
 *  7 2
 *  [0, 1, 1, 8, 3, 0, 5, 1, 8, 8] ,3
 *  6 1
 *  [1, 1, 1, 8, 3, 0, 5, 1, 8, 8] ,2
 *  1 0
 *  6 7
 *  2 components
 *
 */
public class QuickUnionUF {
    //parent[i] = parent of i
    private int[] parent;

    private int count;

    public QuickUnionUF(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("input n " + n + "must be greater than or equal to 0");
        }
        parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /**
     * Returns the number of components
     */
    public int count() {
        return this.count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}
     * 
     * @param p the integer representing one object
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p); 
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    private void validate(int p) {
        int n = parent.length; 
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index" + p + " is not between 0 and " + (n-1));
        }
    }

    /**
     * Returns true if the two sites are in the same component
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     *         {@code false} otherwise
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the component containing site{@code q}
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

        parent[rootP] = rootQ;
        count--;
        StdOut.println(Arrays.toString(parent) + " ," + count);
    }

    /**
     * Reads in a sequence of pairs of intergers (between 0 and n-1) from standard input,
     * where each integer represents some object;
     * if the sites are in different components, merge the two components
     * and print the pair to standard output
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int n = StdIn.readInt(); 
        QuickUnionUF uf = new QuickUnionUF(n);
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
