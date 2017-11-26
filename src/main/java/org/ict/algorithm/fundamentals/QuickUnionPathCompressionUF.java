package org.ict.algorithm.fundamentals;

/**
 *
 *  Quick-union with path compression 
 *  Modify QuickUnionUF.java to include path compression, 
 *  by adding a loop to find() that links every site on the path from p to the root
 *  Give a sequence of input pairs that causes this method to produce a path of length 4
 *  Note: the amortized cost per operation for this algorithm is known to be logarithmic Solution 
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


}
