package org.ict.algorithm.leetcode.coupang;

import java.util.*;

/**
 * Finding missing and repeating elements example: 1, 2, 2, 5, 6 missing is 3, 4, repeating is 2.
 *
 * @author sniper
 * @date 17 Aug 2023
 */
public class MissingAndRepeatingElements {

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 5, 6};
        MissingAndRepeatingElements instance = new MissingAndRepeatingElements();
        instance.findMissingAndRepeatingElements(arr);
    }


    public void findMissingAndRepeatingElements(int[] arr) {
        int n = arr.length;

        // Create a hash table to store the elements of the array.
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        List<Integer> missing = new ArrayList<>();
        List<Integer>  repeating = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > 1) {
                repeating.add(entry.getKey());
            }
        }

        // Iterate over the array.
        for (int i = min; i <= max; i++) {
            // If the element is not in the hash table, then it is missing.
            if (!freq.containsKey(i)) {
                missing.add(i);
            }
        }

        // Print the missing and repeating elements.
        System.out.println("Missing element: " + missing);
        System.out.println("Repeating element: " + repeating);
    }
}
