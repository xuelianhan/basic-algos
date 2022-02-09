package org.ict.algorithm.search;

import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
import java.util.Arrays;

/**
 * Compilation: javac org/ict/algorithm/search/BinarySearch.java
 * Execution:   java org/ict/algorithm/search/BinarySearch ../resources/whitelist.log < ../resources/blacklist.log 
 * whitelist.log: 
 * 1
 * 2
 * 3
 * 4
 * 5
 * blacklist.log:
 * 3
 * 6
 * 9
 * Results:
 * 6
 * 9
 * @author kewin-wayne@princeton
 */
public class BinarySearch {
    public static int indexOf(int[] a, int key) {
        int low = 0;
        int high = a.length - 1; 
        while (low <= high) {
            /**
             * To avoid overflow, (low + high)/2 replaced with the
             * following code, and can be accumulated with
             * low + ((high - low) >> 1)
             */
            int mid = low + (high - low) / 2;
            if (key < a[mid]) {
                high = mid - 1;
            } else if (key > a[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int indexOfRecursive(int[] a, int low,  int high, int key) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (key < a[mid]) {
            return indexOfRecursive(a, low,  mid -1, key);
        } else if (key > a[mid]) {
            return indexOfRecursive(a, mid + 1,  high, key);
        } else {
            return mid;
        }
    }

    public static int binaryRecursive(int[] a, int key) {
        return indexOfRecursive(a, 0,  a.length - 1, key);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (BinarySearch.indexOf(whitelist, key) == -1) {
                StdOut.println(key);
            }
        }
    }
}
