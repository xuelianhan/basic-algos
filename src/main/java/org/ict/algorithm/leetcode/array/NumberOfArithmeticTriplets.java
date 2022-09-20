package org.ict.algorithm.leetcode.array;

import java.util.*;
import java.util.stream.Stream;

/**
 * You are given a 0-indexed, strictly increasing integer array nums and a positive integer diff.
 * A triplet (i, j, k) is an arithmetic triplet if the following conditions are met:
 *
 * i < j < k,
 * nums[j] - nums[i] == diff, and
 * nums[k] - nums[j] == diff.
 * Return the number of unique arithmetic triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,4,6,7,10], diff = 3
 * Output: 2
 * Explanation:
 * (1, 2, 4) is an arithmetic triplet because both 7 - 4 == 3 and 4 - 1 == 3.
 * (2, 4, 5) is an arithmetic triplet because both 10 - 7 == 3 and 7 - 4 == 3.
 * Example 2:
 *
 * Input: nums = [4,5,6,7,8,9], diff = 2
 * Output: 2
 * Explanation:
 * (0, 2, 4) is an arithmetic triplet because both 8 - 6 == 2 and 6 - 4 == 2.
 * (1, 3, 5) is an arithmetic triplet because both 9 - 7 == 2 and 7 - 5 == 2.
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 200
 * 0 <= nums[i] <= 200
 * 1 <= diff <= 50
 * nums is strictly increasing.
 * @author sniper
 * @date 20 Sep, 2022
 * LC2367
 */
public class NumberOfArithmeticTriplets {

    public static void main(String[] args) {
        /**
         * expected 2
         */
        int[] nums = {0,1,4,6,7,10};
        int diff = 3;

        /**
         * expected 2
         */
        //int[] nums = {4,5,6,7,8,9};
        //int diff = 2;


        /**
         * expected 1
         */
        //int[] nums = {0,1,2};
        //int diff = 1;


        /**
         * expected 0
         */
        //int[] nums = {0, 1, 2};
        //int diff = 2;


        /**
         * expected 2
         */
        //int[] nums = {4,5,6,7,8,9};
        //int diff = 2;


        int res = arithmeticTripletsV1(nums, diff);
        System.out.println(res);
    }

    /**
     * Recommend this solution.
     * 
     * Solution provided by Vlad votrubac.
     * So Brilliant! Amazing!
     * @param nums
     * @param diff
     * @return
     */
    public int arithmeticTripletsV4(int[] nums, int diff) {
        int[] cnt = new int[201];

        int res = 0;
        int twoDiff = 2 * diff;
        for (int num : nums) {
            if (num >= twoDiff) {
                res += cnt[num - diff] & cnt[num - twoDiff];
            }
            cnt[num] = 1;
        }

        return res;
    }

    /**
     * Recommend this solution.
     *
     * @param nums
     * @param diff
     * @return
     */
    public int arithmeticTripletsV3(int[] nums, int diff) {
        int cnt = 0;
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }

        for (int num : nums) {
            int mid = num + diff;
            int thd = num + 2 * diff;
            if (set.contains(mid) && set.contains(thd)) {
                cnt++;
            }
        }
        return cnt;
    }


    public int arithmeticTripletsV2(int[] nums, int diff) {
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int num : nums) {
            int mid = num + diff;
            int thd = num + 2 * diff;
            if (map.containsKey(mid) && map.containsKey(thd)) {
                cnt++;
            }
        }
        return cnt;
    }


    /**
     * Two Pointers Solution
     *
     * Time Complexity O(2N)
     * Space Complexity O(2N)
     *
     * @param nums
     * @param diff
     * @return
     */
    public static int arithmeticTripletsV1(int[] nums, int diff) {
        int first = 0;
        int tail = nums.length - 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        Set<Integer> cnt = new HashSet<>();
        while (first <= tail) {
            if (map.containsKey(nums[first] + diff)) {
                int mid = map.get(nums[first] + diff);
                int end = map.getOrDefault(nums[mid] + diff, -1);
                if (end <= tail && end != -1) {
                    cnt.add(mid);
                }
            }
            if (map.containsKey(nums[tail] - diff)) {
                int mid = map.get(nums[tail] - diff);
                int fro = map.getOrDefault(nums[mid] - diff, -1);
                if (fro >= first && fro != -1) {
                    cnt.add(mid);
                }
            }
            first++;
            tail--;
        }
        return cnt.size();
    }


    /**
     * Brute-Force solution.
     * Time Complexity is O(N^3);
     * @param nums
     * @param diff
     * @return
     */
    public int arithmeticTriplets(int[] nums, int diff) {
        int cnt = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j= i+ 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    int first = nums[j] - nums[i];
                    int second = nums[k] - nums[j];
                    if (first == second && second == diff) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}
