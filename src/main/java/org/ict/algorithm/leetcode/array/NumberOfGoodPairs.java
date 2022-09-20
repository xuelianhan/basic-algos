package org.ict.algorithm.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums, return the number of good pairs.
 *
 * A pair (i, j) is called good if nums[i] == nums[j] and i < j.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1,1,3]
 * Output: 4
 * Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
 * Example 2:
 *
 * Input: nums = [1,1,1,1]
 * Output: 6
 * Explanation: Each pair in the array are good.
 * Example 3:
 *
 * Input: nums = [1,2,3]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 *
 * @author sniper
 * @date 20 Sep, 2022
 * LC1512
 */
public class NumberOfGoodPairs {

    public static void main(String[] args) {
        int[] nums = {1,2,3,1,1,3};
        int res = numIdenticalPairsV3(nums);
        System.out.println(res);
    }

    /**
     * This solution is same as numIdenticalPairsV3.
     *
     * When we count frequency of each element in the array, we ignore the count at first time.
     * Thereafter, we begin to count frequency of each element accumulatively.
     *
     * e.g.
     * input: 1 2 3 1 1 3
     * freq:  0 0 0 1 2 1
     * ==================
     * e.g.
     * input: 1 1 1 1
     * freq:  0 1 2 3
     * ==================
     * e.g.
     * input: 1 2 3
     * freq:  0 0 0
     *
     * Similar to problem NumberOfArithmeticTriplets of LC2367.
     *
     * @param nums
     * @return
     */
    public int numIdenticalPairsV4(int[] nums) {
        int res = 0;
        int[] count = new int[101];
        for (int a : nums) {
            int freq = count[a];
            res += freq;
            count[a]++;
        }
        return res;
    }

    /**
     * Solution provided by lee215.
     *
     * For each nums[j],
     * count[a] is the frequency where nums[i] = a and i < j.
     *
     * Notice the constraints:
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     *
     * Time O(N)
     * Space O(N)
     *
     * Input: nums = [1,2,3,1,1,3]
     * ------------------
     * index 0 1 2 3 4...
     * ------------------
     * count 0 3 1 2 0...
     *
     * idx:0, a:1, freq:0
     * idx:1, a:2, freq:0
     * idx:2, a:3, freq:0
     * idx:3, a:1, freq:1
     * idx:4, a:1, freq:2
     * idx:5, a:3, freq:1
     * res = count[1] + count[1] + count[3] = 1 + 2 + 1 = 4
     *
     * A trick here is to use count[a]++.
     * This operation makes the counting always later than frequency accumulate for one step.
     *
     * e.g.
     * input: 1 2 3 1 1 3
     * freq:  0 0 0 1 2 1
     * ==================
     * e.g.
     * input: 1 1 1 1
     * freq:  0 1 2 3
     * ==================
     * e.g.
     * input: 1 2 3
     * freq:  0 0 0
     *
     *
     * @param nums
     * @return
     */
    public static int numIdenticalPairsV3(int[] nums) {
        int res = 0;
        int[] count = new int[101];
        for (int a : nums) {
            /**
             * statement:
             * freq = count[a]++;
             * res += freq;
             *
             * its effect equals:
             *
             * freq = count[a];
             * count[a]++;
             * res += freq;
             *
             */
            int freq = count[a]++;
            //System.out.println("a:"+ a + ", freq:"+ freq);
            res += freq;
        }
        //System.out.println(Arrays.toString(count));
        return res;
    }

    public int numIdenticalPairsV2(int[] nums) {
        int res = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            int count = freq.getOrDefault(num, 0);
            res += count;
            freq.put(num, count + 1);
        }
        return res;
    }



    /**
     * Math formula solution.
     * todo
     * Need answer one question:
     * Why the count is n*(n-1)/2 ?
     * @param nums
     * @return
     */
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int cnt = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            Integer v = entry.getValue();
            if (v > 1) {
                cnt += (v - 1) * v / 2;
            }
        }
        return cnt;
    }

    /**
     * Brute-Force Solution
     *
     * Time complexity is O(N^2)
     * Space complexity is O(1).
     *
     * @param nums
     * @return
     */
    public int numIdenticalPairsBruteForce(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    res++;
                }
            }
        }
        return res;
    }
}
