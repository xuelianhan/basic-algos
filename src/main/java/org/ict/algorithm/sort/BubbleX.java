package org.ict.algorithm.sort;

import java.util.Arrays;

import org.ict.algorithm.util.StdOut;

/**
 * 
 * @see https://www.geeksforgeeks.org/bubble-sort/
 *
 */
public class BubbleX extends AbstractSortHelper {
    
    public static void sort(Comparable[] a) {
        int n = a.length;
        
        int exchanges = 0;
        //put bigest element in last position to serve as sentinel
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (AbstractSortHelper.less(a[j+1], a[j])) {
                    exch(a, j+1, j);
                    exchanges++;
                    StdOut.println(Arrays.toString(a));
                }
            }
            
            // IF no two elements were swapped by inner loop, then break 
            if (exchanges == 0) {
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        Integer[] array = {64, 34, 25, 12, 22, 11, 90};
        StdOut.println("before sort:" + Arrays.toString(array));
        BubbleX.sort(array);
        StdOut.println("after sort:" + Arrays.toString(array));
    }
}
