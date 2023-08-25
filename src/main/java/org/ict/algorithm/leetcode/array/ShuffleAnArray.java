package org.ict.algorithm.leetcode.array;

import java.util.Random;

/**
 * Given an integer array nums, design an algorithm to randomly shuffle the array.
 * All permutations of the array should be equally likely as a result of the shuffling.
 *
 * Implement the Solution class:
 *
 * Solution(int[] nums) Initializes the object with the integer array nums.
 * int[] reset() Resets the array to its original configuration and returns it.
 * int[] shuffle() Returns a random shuffling of the array.
 *
 *
 * Example 1:
 *
 * Input
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * Output
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 *
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // Shuffle the array [1,2,3] and return its result.
 *                        // Any permutation of [1,2,3] must be equally likely to be returned.
 *                        // Example: return [3, 1, 2]
 * solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
 * solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 50
 * -106 <= nums[i] <= 106
 * All the elements of nums are unique.
 * At most 104 calls in total will be made to reset and shuffle.
 * @author sniper
 * @date 21 Sep, 2022
 * LC384, Medium
 */
public class ShuffleAnArray {

    private int[] nums;

    private Random random = new Random();

    public ShuffleAnArray(int[] nums) {
        this.nums = nums;
    }

    /**
     * Resets the array to its original configuration and returns it
     * @return
     */
    public int[] reset() {
        return nums;
    }

    /**
     * Returns a random shuffling of the array
     * @return
     */
    public int[] shuffle() {
        if (nums == null) {
            return null;
        }
        int[] copy = nums.clone();
        /**
         * Why i starts at 1, not 0?
         * If i starts at 0, random.nextInt(i+1) = random.nextInt(1)
         * due to bound is exclusive, random.nextInt(1) always return 0.
         * Tt makes no sense to call random.nextInt(1), so i starts at 1 instead of 0.
         */
        for (int i = 1; i < copy.length; i++) {
            /**
             * the next pseudorandom, uniformly distributed
             * value between zero (inclusive) and bound (exclusive)
             * return j: 0 <= j <= i;
             */
            int j = random.nextInt(i + 1);
            swap(copy, i, j);
        }
        return copy;
    }

    private void swap(int[] copy, int i, int j) {
        int t = copy[i];
        copy[i] = copy[j];
        copy[j] = t;
    }

}
