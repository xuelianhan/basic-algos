package org.ict.algorithm.hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Consider an array of numeric strings where each string is a positive number with anywhere from  to  digits. Sort the array's elements in non-decreasing, or ascending order of their integer values and return the sorted array.
 *
 * Example
 *
 * Return the array ['1', '3', '150', '200'].
 *
 * Function Description
 *
 * Complete the bigSorting function in the editor below.
 *
 * bigSorting has the following parameter(s):
 *
 * string unsorted[n]: an unsorted array of integers as strings
 * Returns
 *
 * string[n]: the array sorted in numerical order
 * Input Format
 *
 * The first line contains an integer, , the number of strings in .
 * Each of the  subsequent lines contains an integer string, .
 *
 * Constraints
 *
 * Each string is guaranteed to represent a positive integer.
 * There will be no leading zeros.
 * The total number of digits across all strings in  is between  and  (inclusive).
 * Sample Input 0
 *
 * 6
 * 31415926535897932384626433832795
 * 1
 * 3
 * 10
 * 3
 * 5
 * Sample Output 0
 *
 * 1
 * 3
 * 3
 * 5
 * 10
 * 31415926535897932384626433832795
 * Explanation 0
 *
 * The initial array of strings is . When we order each string by the real-world integer value it represents, we get:
 *
 * We then print each value on a new line, from smallest to largest.
 *
 * Sample Input 1
 *
 * 8
 * 1
 * 2
 * 100
 * 12303479849857341718340192371
 * 3084193741082937
 * 3084193741082938
 * 111
 * 200
 * Sample Output 1
 *
 * 1
 * 2
 * 100
 * 111
 * 200
 * 3084193741082937
 * 3084193741082938
 * 12303479849857341718340192371
 * @see <a href="https://www.hackerrank.com/challenges/big-sorting/problem?isFullScreen=true"></a>
 * @author sniper
 * @date 20 Jul 2023
 */
public class BigSorting {

    public static List<String> bigSorting(List<String> unsorted) {
        Collections.sort(unsorted, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() < o2.length()) {
                    return -1;
                } else if (o1.length() > o2.length()) {
                    return 1;
                } else {
                    if (o1.equals(o2)) {
                        return 0;
                    } else {
                        int i = 0;
                        while (i < o1.length() && o1.charAt(i) == o2.charAt(i)) {
                            i++;
                        }
                        return o1.charAt(i) - o2.charAt(i);
                    }
                }
            }
        });
        return unsorted;
    }

}
