package org.ict.algorithm.hackerrank;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @see <a href="https://www.hackerrank.com/challenges/insertionsort1/problem"></a>
 * @author sniper
 * @date 20 Jul 2023
 */
public class InsertionSort1 {

    public static void insertionSort1(int n, List<Integer> arr) {
        int t = arr.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            if (t >= arr.get(i)) {
                arr.set(i + 1, t);
                System.out.println(convert(arr));
                break;
            }
            arr.set(i + 1, arr.get(i));
            System.out.println(convert(arr));
        }

        if (t < arr.get(0)) {
            arr.set(0, t);
            System.out.println(convert(arr));
        }
    }

    private static String convert(List<Integer> arr) {
        return arr.stream().map(Object::toString).collect(Collectors.joining(" "));
    }
}
