package org.ict.algorithm.sort;

import java.util.Arrays;

import org.ict.algorithm.util.StdOut;

/**
 * Bubble Sort is the simplest sorting algorithm that works by 
 * repeatedly swapping the adjacent elements if they are in wrong order.
 * @see https://www.geeksforgeeks.org/bubble-sort/
 *
 * Example:
 * First Pass:
 * ( 5 1 4 2 8 ) –> ( 1 5 4 2 8 ), Here, algorithm compares the first two elements, and swaps since 5 > 1.
 * ( 1 5 4 2 8 ) –>  ( 1 4 5 2 8 ), Swap since 5 > 4
 * ( 1 4 5 2 8 ) –>  ( 1 4 2 5 8 ), Swap since 5 > 2
 * ( 1 4 2 5 8 ) –> ( 1 4 2 5 8 ), Now, since these elements are already in order (8 > 5), algorithm does not swap them.
 * 
 * Second Pass:
 * ( 1 4 2 5 8 ) –> ( 1 4 2 5 8 )
 * ( 1 4 2 5 8 ) –> ( 1 2 4 5 8 ), Swap since 4 > 2
 * ( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
 * ( 1 2 4 5 8 ) –>  ( 1 2 4 5 8 )
 * Now, the array is already sorted, but our algorithm does not know if it is completed. The algorithm needs one whole pass without any swap to know it is sorted.
 * 
 * Third Pass:
 * ( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
 * ( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
 * ( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
 * ( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
 */
public class Bubble extends AbstractSortHelper {

    public static void sort(Comparable[] a) {
        int n = a.length;
        //put bigest element in last position to serve as sentinel
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (AbstractSortHelper.less(a[j+1], a[j])) {
                    exch(a, j+1, j);
                    StdOut.println(Arrays.toString(a));
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Integer[] array = {64, 34, 25, 12, 22, 11, 90};
        StdOut.println("before sort:" + Arrays.toString(array));
        Bubble.sort(array);
        StdOut.println("after sort:" + Arrays.toString(array));
    }
}
