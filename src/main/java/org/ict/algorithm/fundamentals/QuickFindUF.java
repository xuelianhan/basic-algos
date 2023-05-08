package org.ict.algorithm.fundamentals;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
import java.util.Arrays;




/**
 * QuickFindUF.java maintains the invariant that p and q are connected if and only if id[p] is equal to id[q]. 
 * In other words, all sites in a component must have the same value in id[].
 * $ javac org/ict/algorithm/fundamentals/QuickFindUF.java 
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
 * $ java org/ict/algorithm/fundamentals/QuickFindUF < ../resources/tinyUF.txt 
 * 4 3
 * [0, 1, 2, 3, 3, 5, 6, 7, 8, 9] ,9
 * 3 8
 * [0, 1, 2, 8, 8, 5, 6, 7, 8, 9] ,8
 * 6 5
 * [0, 1, 2, 8, 8, 5, 5, 7, 8, 9] ,7
 * 9 4
 * [0, 1, 2, 8, 8, 5, 5, 7, 8, 8] ,6
 * 2 1
 * [0, 1, 1, 8, 8, 5, 5, 7, 8, 8] ,5
 * 8 9
 * 5 0
 * [0, 1, 1, 8, 8, 0, 0, 7, 8, 8] ,4
 * 7 2
 * [0, 1, 1, 8, 8, 0, 0, 1, 8, 8] ,3
 * 6 1
 * [1, 1, 1, 8, 8, 1, 1, 1, 8, 8] ,2
 * 1 0
 * 6 7
 * 2 components
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 *
 */
public class QuickFindUF {

    //id[id] = component identifier of i
    private int[] id;
    
    private int count;
   
    public QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        validate(p);
        return id[p];
    }

    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException(); 
        }
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);
        int pID = id[p];
        int qID = id[q];
        
        if (pID == qID) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
        count--;
        StdOut.println(Arrays.toString(id) + " ," + count);
    }


    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(n);

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
