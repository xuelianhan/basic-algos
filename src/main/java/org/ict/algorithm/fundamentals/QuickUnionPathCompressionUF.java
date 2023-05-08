package org.ict.algorithm.fundamentals;
import java.util.Arrays;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;


/**
 *
 *  Quick-union with path compression 
 *  Modify QuickUnionUF.java to include path compression, 
 *  by adding a loop to find() that links every site on the path from p to the root
 *  Give a sequence of input pairs that causes this method to produce a path of length 4
 *  Note: the amortized cost per operation for this algorithm is known to be logarithmic Solution 
 *
 *  $ javac org/ict/algorithm/fundamentals/QuickUnionPathCompressionUF.java 
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
 *  $ java org/ict/algorithm/fundamentals/QuickUnionPathCompressionUF < ../resources/tinyUF.txt 
 *  4 3
 *  [0, 1, 2, 3, 3, 5, 6, 7, 8, 9] ,9
 *  3 8
 *  [0, 1, 2, 8, 3, 5, 6, 7, 8, 9] ,8
 *  6 5
 *  [0, 1, 2, 8, 3, 5, 5, 7, 8, 9] ,7
 *  9 4
 *  [0, 1, 2, 8, 8, 5, 5, 7, 8, 8] ,6
 *  2 1
 *  [0, 1, 1, 8, 8, 5, 5, 7, 8, 8] ,5
 *  8 9
 *  5 0
 *  [0, 1, 1, 8, 8, 0, 5, 7, 8, 8] ,4
 *  7 2
 *  [0, 1, 1, 8, 8, 0, 5, 1, 8, 8] ,3
 *  6 1
 *  [1, 1, 1, 8, 8, 0, 0, 1, 8, 8] ,2
 *  1 0
 *  6 7
 *  2 components
 *
 *
 */
public class QuickUnionPathCompressionUF {
    //id[i] = parent of i
    private int[] id;
    //number of components
    private int count;

    public QuickUnionPathCompressionUF (int n) {
        this.count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return this.count;
    }
    
    /**
     * Returns the component identifier for the component containing site{@code p}
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     */
    public int find(int p) {
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }
        while (root != p) {
            int newp = id[p];
            id[p] = root;
            p = newp;
        }
        return root;
    }

    /**
     * Returns true if the two sites are in the same component.
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
        id[rootP] = rootQ;
        count--;
        StdOut.println(Arrays.toString(id) + " ," + count);
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            StdOut.println(p + " " + q);
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
        }
        StdOut.println(uf.count()+ " components");
    }
}
