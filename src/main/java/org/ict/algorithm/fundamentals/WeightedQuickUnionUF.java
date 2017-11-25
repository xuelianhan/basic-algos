package org.ict.algorithm.fundamentals;
import java.util.Arrays;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 *
 * Weighted quick-union;
 * Rather than arbitrarily connecting the second tree to the first for union() in the quick-union algorithm, 
 * we keep track of the size of each tree and always connect the smaller tree to the larger;
 * Program WeightedQuickUnionUF.java implements this approach.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 * $ javac org/ict/algorithm/fundamentals/WeightedQuickUnionUF.java
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
 * $ java org/ict/algorithm/fundamentals/WeightedQuickUnionUF < ../resources/tinyUF.txt
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
public class WeightedQuickUnionUF {
    //parent[i] = parent of i
    private int[] parent;

    //size[i] = number of sites in subtree rooted at i
    private int[] size;

    //number of components
    private int count;

    /**
     * Initializes an empty union-find data structure with {@code n} sites
     * {@code 0} through {@code n-1};
     * Each site is initially in its own component
     *
     * @param n the number of sites
     * 
     */
    public WeightedQuickUnionUF(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            //at first every site's size is 1
            size[i] = 1;
        }
    }

    public int count() {
        return this.count;
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }


    private void validate(int p) {
        int n = this.parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index" + p + " is not between 0 and " +(n-1));
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        
        if(size[rootP] < size[rootQ]) {
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

    public static void main(String[] args) {
       int n = StdIn.readInt();
       WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);

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
