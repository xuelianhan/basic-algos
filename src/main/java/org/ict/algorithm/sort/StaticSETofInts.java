package org.ict.algorithm.sort;

import java.util.Arrays;

/**
 * @see <a href="http://algs4.cs.princeton.edu/12oop/StaticSETofInts.java.html"></a>
 * javac org/ict/algorithm/sort/StaticSETofInts.java
 * java org/ict/algorithm/sort/StaticSETofInts
 *
 */

public class StaticSETofInts {
    private int[] a;

    public StaticSETofInts(int[] keys) {
        if (keys.length <= 0) {
          throw new IllegalArgumentException("Input int[] keys is empty!");
        }

        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++) {
            a[i] = keys[i];
        }
        
        Arrays.sort(a);

        for (int i = 1; i < a.length; i++) {
            if (a[i-1] == a[i]) {
                throw new IllegalArgumentException("Argument arrays contains duplicated keys.");
            }
        }
    }


    public boolean contains(int key) {
        return rank(key) != -1;
    }


    public int rank(int key) {
        int lo = 0; 
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi -lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] keys = {1,5,2,9,3,8,4};
        StaticSETofInts ssoi = new StaticSETofInts(keys);
        System.out.println(ssoi.contains(3));
    }

}
