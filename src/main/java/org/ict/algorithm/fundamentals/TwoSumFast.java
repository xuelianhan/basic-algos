package org.ict.algorithm.fundamentals;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
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

    /**
     * To improve our running time complexity, we need a more efficient way to check if 
     * the complement exists in the array. If the complement exists, we need to look up
     * its index. Using hash table to reduce the look up time from O(n) to O(1) by trading
     * space for speed.
     */
    public static int[] twoSumPair(int[] a, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < a.length; i++) {
            int complement = target - a[i];
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
            map.put(a[i], i);
        }
        throw new IllegalArgumentException("No two sum pair solution");
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] a = in.readAllInts(); 
        StdOut.println(count(a));
        int[] result = twoSumPair(a, 0);
        StdOut.println(Arrays.toString(result));
    }

}
