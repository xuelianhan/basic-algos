package org.ict.algorithm.leetcode.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given an array of integers nums and an integer k,
 * return the total number of sub-arrays whose sum equals to k.
 * A sub-array is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 * @author sniper
 * @date 12 Apr, 2023
 * LC560, Medium, frequency=185
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;
        SubarraySumEqualsK instance = new SubarraySumEqualsK();
        int res = instance.subarraySumV1(nums, k);
        System.out.println(res);
    }

    /**
     * Understanding the following solution
     * -----------------------------------
     * class Solution:
     *     def subarraySum(self, nums: List[int], k: int) -> int:
     *         prefixSum, res = 0, 0
     *         counter = Counter({0: 1})
     *         for x in nums:
     *             prefixSum += x
     *             res += counter[prefixSum - k]
     *             counter[prefixSum] += 1
     *         return res
     *  ------------------------------------
     *  class Solution {
     * public:
     *     int subarraySum(vector<int>& nums, int k) {
     *         int res = 0;
     *         int sum = 0;
     *         int n = nums.size();
     *         unordered_map<int, int> map{{0, 1}};
     *         for (int i = 0; i < n; i++) {
     *             sum += nums[i];
     *             res += map[sum - k];
     *             map[sum] += 1;
     *         }
     *         return res;
     *     }
     * };
     * -------------------------------------
     * object Solution {
     *     def subarraySum(nums: Array[Int], k: Int): Int = {
     *         var res = 0
     *         var prefixSum = 0
     *         val freq = scala.collection.mutable.HashMap[Int, Int]()
     *         freq.put(0, 1)
     *         for (num <- nums) {
     *             prefixSum += num
     *             res += freq.getOrElse(prefixSum - k, 0)
     *             freq.put(prefixSum, freq.getOrElse(prefixSum, 0) + 1)
     *         }
     *         res
     *     }
     * }
     * ----------------------------------
     * use std::collections::HashMap;
     *
     * impl Solution {
     *     pub fn subarray_sum(nums: Vec<i32>, k: i32) -> i32 {
     *         let mut res = 0;
     *         let mut sum = 0;
     *         let mut map = HashMap::new();
     *         map.insert(0, 1);
     *         nums.iter().for_each(|num| {
     *             sum += num;
     *             res += map.get(&(sum - k)).unwrap_or(&0);
     *             map.insert(sum, map.get(&sum).unwrap_or(&0) + 1);
     *         });
     *         res
     *     }
     * }
     * @param nums
     * @param k
     * @return
     */
    public int subarraySumV2(int[] nums, int k) {
        int res = 0;
        int prefixSum = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);
        for (int num : nums) {
            prefixSum += num;
            /**
             * The accumulation of res should be put before updating of freq.
             * e.g. nums = [1], k = 0, expected 0
             * If you exchange the following two lines, you will get a wrong answer.
             */
            res += freq.getOrDefault(prefixSum - k, 0);
            freq.put(prefixSum, freq.getOrDefault(prefixSum, 0) + 1);
        }
        return res;
    }


    /**
     * Understanding the following solution.
     * We can get intuition from problem two sum:
     * @see org.ict.algorithm.leetcode.array.TwoSum
     *
     * We use hash map to store the cumulative sum, sum[i] up to index-i,
     * if sum[i]−sum[j]=k, then it means the sum between indices i and j is k
     * Therefore, we store the cumulative sum, search for sum−k
     * in the hash map and add the occurrences if it is found to the answer.
     *
     * e.g. nums = [1,2,3], k = 3, expected 2
     * map:{(0, 1)}
     * i:0, sum:1, sum-k=-2, map not contains -2, res:0, map:{(1, 1)},
     * i:1, sum:3, sum-k= 0, map contains 0, res:1, map:{(1, 1), (3, 1)}, nums[0]~nums[1] is a valid sub-array.
     * i:2, sum:6, sum-k= 3, map contains 3, res:2, map:{(1, 1), (3, 1), (6, 1)}, nums[2] is a valid sub-array.
     *
     * e.g. nums = [1], k = 0, expected 0, if you use res + 1 instead of putting (0, 1), you will get the wrong answer 1.
     *
     * def subarraySum(self, nums: List[int], k: int) -> int:
     *         sum = 0
     *         res = 0
     *         dict = {0:1}
     *         for x in nums:
     *             sum += x
     *             if sum - k in dict:
     *                 res += dict[sum - k]
     *             if sum not in dict:
     *                 dict[sum] = 1
     *             else:
     *                 dict[sum] += 1
     *         return res
     * ---------------------------------------------
     * e.g. nums = [1,1,1], k = 2, expected 2
     * map:{(0, 1)}
     * i:0, sum:1, sum-k=-1, map not contains -1, map:{(0, 1), (1, 1)}
     * i:1, sum:2, sum-k= 0, map contains 0, res = res + 1 = 1, map:{(0, 1), (1, 1), (2, 1)}, nums[0]~nums[1] is a valid sub-array.
     * i:2, sum:3, sum-k= 1, map contains 1, res = res + 1 = 2, map:{(0, 1), (1, 1), (2, 1), (3, 1)}, nums[1]~nums2] is a valid sub-array.
     * ----------|-----|
     *           ^     ^
     *           k    sum
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySumV1(int[] nums, int k) {
        int prefixSum = 0;
        int res = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        /**
         * Assume that prefix prefixSum equals k at index-i, the range from index-0 to this index-i is an answer.
         * So while prefixSum == k, the prefixSum-k is 0, the value should be 1.
         * e.g. nums = [1, 2, 3], k = 3
         * while 1 + 2 = 3, i:1, so range from index-0 to index-1 should be an answer.
         */
        freq.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (freq.containsKey(prefixSum - k)) {
                res += freq.get(prefixSum - k);
            }
            freq.put(prefixSum, freq.getOrDefault(prefixSum, 0) + 1);
        }
        return res;
    }


    /**
     * Time Cost 1991ms, very slow.
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (prefix[i] == k) {
                res++;
            }
            for (int j = i - 1; j >= 0; j--) {
                if (prefix[i] - prefix[j] == k) {
                    res++;
                }
            }
        }
        return res;
    }
}
