package org.ict.algorithm.company.coupang;

import java.util.Arrays;
import java.util.HashMap;

/**
 * In the sub-array with the given sum problem,
 * we have given an array containing n positive elements.
 * We have to find the sub-array in which the sum of all the elements of the sub-array equal to a given_sum.
 * Sub-array is obtained from the original array by deleting some elements from the starting or end of the array.
 *
 * a. Input array be: [1, 3, 7, 9, 11, 15, 8, 6]
 * Sum = 19
 * Output will be: 1 and 3 → [3, 7, 9], subarray sum = 19
 *
 * b. Input array be: [1, 3, 7, 9, 11, 15, 8, 6]
 * Sum = 20
 * Output will be: 0 and 3 → [1, 3, 7, 9], subarray sum = 20
 *
 * c. Input array be: [1, 3, 7, 9, 11, 15, 8, 6]
 * Sum = 40
 * Output will be: No subarray with given sum found.
 *
 * d. Input array be: [4, 3, 2, 8, 9, 11]
 * Sum = 8
 * Output will be: 3 and 3 → [8], subarray sum = 8
 *
 * @see <a href="https://tutorialcup.com/interview-experience/coupang-interview-experience.htm"></a>
 * @see <a href="https://tutorialcup.com/interview/array/subarray-with-given-sum.htm"></a>
 * @author sniper
 * @date 15 Aug 2023
 */
public class SubArrayWithGivenSum {


    /**
     * This code first creates a hash table to store the cumulative sums.
     * The hash table stores the sum of all elements up to a particular index.
     *
     * The code then iterates over the elements in the array.
     * For each element, the code does the following:
     *
     * Updates the current sum.
     * Checks if the current sum is equal to the given sum.
     * If it is, then the code stores the start and end indices of the subarray.
     * Checks if the current sum is greater than the given sum.
     * If it is, then the code removes the elements from the beginning of the array to make it equal to the given sum.
     * Updates the hash table with the current sum.
     * The code finally returns the subarray if it is found. Otherwise, the code returns null.
     * @param arr
     * @param sum
     * @return
     */
    public int[] findSubarrayWithGivenSum(int[] arr, int sum) {
        int n = arr.length;

        // Create a hash table to store the cumulative sums.
        HashMap<Integer, Integer> hashTable = new HashMap<>();
        hashTable.put(0, -1);

        int currentSum = 0;
        int start = -1;
        int end = -1;

        for (int i = 0; i < n; i++) {
            currentSum += arr[i];

            // If the current sum is equal to the given sum, then we have found a subarray.
            if (currentSum == sum) {
                start = hashTable.get(currentSum - sum) + 1;
                end = i;
                break;
            }

            // If the current sum is greater than the given sum, then we need to remove the elements from the beginning of the array to make it equal to the given sum.
            if (hashTable.containsKey(currentSum - sum)) {
                start = hashTable.get(currentSum - sum) + 1;
                end = i;
            } else {
                hashTable.put(currentSum, i);
            }
        }

        if (start == -1) {
            return null;
        } else {
            return Arrays.copyOfRange(arr, start, end + 1);
        }
    }
}
    