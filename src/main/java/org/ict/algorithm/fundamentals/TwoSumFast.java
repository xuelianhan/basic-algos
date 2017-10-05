package org.ict.algorithm.fundamentals;
import java.util.Arrays;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;
import org.ict.algorithm.search.BinarySearch;


/**
 * Compilation: javac org/ict/algorithm/fundamentals/TwoSumFast.java
 * Execution: java org/ict/algorithm/fundamentals/TwoSumFast  ../resources/1Kints.txt
 *
 *
 */
public class TwoSumFast {

    public static int count(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (BinarySearch.indexOf(a, -a[i]) > i) {
                cnt++;
            }
        }
        return cnt;
    }
    

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] a = in.readAllInts(); 
        StdOut.println(count(a));
    }

}
