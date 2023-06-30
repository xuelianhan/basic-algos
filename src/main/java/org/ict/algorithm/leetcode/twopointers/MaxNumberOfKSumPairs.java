package org.ict.algorithm.leetcode.twopointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array nums and an integer k.
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 * Return the maximum number of operations you can perform on the array.
 *
 * Example 1:
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5,
 * hence a total of 2 operations.
 *
 * Example 2:
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 *
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^9
 * @author sniper
 * @date 29 Jun 2023
 * LC1679, Medium
 */
public class MaxNumberOfKSumPairs {

    /**
     * Understanding the following solution
     * HashMap Solution
     * -------------------------------
     * class Solution:
     *     def maxOperations(self, nums: List[int], k: int) -> int:
     *         cnt = Counter()
     *         res = 0
     *         for x in nums:
     *             if cnt[k - x]:
     *                 res += 1
     *                 cnt[k - x] -= 1
     *             else:
     *                 cnt[x] += 1
     *         return res
     * ---------------------------------
     * class Solution {
     * public:
     *     int maxOperations(vector<int>& nums, int k) {
     *         unordered_map<int, int> cnt;
     *         int res = 0;
     *         for (int& x : nums) {
     *             if (cnt[k - x]) {
     *                 cnt[k - x]--;
     *                 res++;
     *             } else {
     *                 cnt[x]++;
     *             }
     *         }
     *         return res;
     *     }
     * };
     * @param nums
     * @param k
     * @return
     */
    public int maxOperationsV2(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) {
            if (freq.containsKey(k - x)) {
                res++;
                if (freq.merge(k - x, -1, Integer::sum) == 0) {
                    freq.remove(k - x);
                }
            } else {
                freq.merge(x, 1, Integer::sum);
            }
        }
        return res;
    }

    /**
     * Understanding the following solution
     * HashMap Solution
     * ----------------------
     * class Solution:
     *     def maxOperations(self, nums: List[int], k: int) -> int:
     *         cnt = Counter()
     *         res = 0
     *         for x in nums:
     *             if cnt[k - x]:
     *                 res += 1
     *                 cnt[k - x] -= 1
     *             else:
     *                 cnt[x] += 1
     *         return res
     * @param nums
     * @param k
     * @return
     */
    public int maxOperationsV1(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) {
            if (freq.containsKey(k - x)) {
                res++;
                int cnt = freq.get(k - x);
                cnt--;
                freq.put(k - x, cnt);
                if (cnt == 0) {
                    freq.remove(k - x);
                }
            } else {
                freq.put(x, freq.getOrDefault(x, 0) + 1);
            }
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Two Pointer Solution
     * ---------------------------------------
     * class Solution:
     *     def maxOperations(self, nums: List[int], k: int) -> int:
     *         nums.sort()
     *         l, r, res = 0, len(nums) - 1, 0
     *         while l < r:
     *             s = nums[l] + nums[r]
     *             if s == k:
     *                 res += 1
     *                 l, r = l + 1, r - 1
     *             elif s > k:
     *                 r -= 1
     *             else:
     *                 l += 1
     *         return res
     * --------------------------------
     * class Solution {
     * public:
     *     int maxOperations(vector<int>& nums, int k) {
     *         sort(nums.begin(), nums.end());
     *         int res = 0;
     *         int i = 0, j = nums.size() - 1;
     *         while (i < j) {
     *             if (nums[i] + nums[j] == k) {
     *                 i++;
     *                 j--;
     *                 res++;
     *             } else if (nums[i] + nums[j] > k) {
     *                 j--;
     *             } else {
     *                 i++;
     *             }
     *         }
     *         return res;
     *     }
     * };
     * @param nums
     * @param k
     * @return
     */
    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 0;
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum == k) {
                res++;
                l++;
                r--;
            } else if (sum > k) {
                r--;
            } else {
                l++;
            }
        }
        return res;
    }
}
