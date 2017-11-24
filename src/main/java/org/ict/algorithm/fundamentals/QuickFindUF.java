package org.ict.algorithm.fundamentals;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;



/**
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
 * 3 8
 * 6 5
 * 9 4
 * 2 1
 * 5 0
 * 7 2
 * 6 1
 * 2 components
 *
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
    }


    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(n);

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
