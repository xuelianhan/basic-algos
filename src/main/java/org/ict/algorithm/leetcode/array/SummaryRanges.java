package org.ict.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a sorted unique integer array nums.
 * A range [a,b] is the set of all integers from a to b (inclusive).
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
 * That is, each element of nums is covered by exactly one of the ranges,
 * and there is no integer x such that x is in one of the ranges but not in nums.
 *
 * Each range [a,b] in the list should be output as:
 * "a->b" if a != b
 * "a" if a == b
 *
 * Example 1:
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 *
 *
 * Example 2:
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 *
 * Constraints:
 * 0 <= nums.length <= 20
 * -2^31 <= nums[i] <= 2^31 - 1
 * All the values of nums are unique.
 * nums is sorted in ascending order.
 * @author sniper
 * @date 17 Jun 2023
 * LC228, Easy, frequency=8
 */
public class SummaryRanges {

    /**
     * Understanding the following solution
     * Time Complexity O(N)
     * Space Complexity O(N)
     * ------------------------------------
     * class Solution:
     *     def summaryRanges(self, nums: List[int]) -> List[str]:
     *         res = []
     *         n = len(nums)
     *         i = 0
     *         while i < n:
     *             begin = nums[i]
     *             while i < n - 1 and nums[i] + 1 == nums[i + 1]:
     *                 i += 1
     *             end = nums[i]
     *             if begin == end:
     *                 res.append(str(begin))
     *             else:
     *                 res.append(str(begin) + "->" + str(end))
     *             i += 1
     *         return res
     * --------------------------------------
     * class Solution {
     * public:
     *     vector<string> summaryRanges(vector<int>& nums) {
     *         vector<string> res;
     *         int n = nums.size();
     *         for (int i = 0; i < n; ++i) {
     *             const int begin = nums[i];
     *             while (i < n - 1 && nums[i] + 1 == nums[i + 1]) {
     *                 i++;
     *             }
     *             const int end = nums[i];
     *             if (begin == end) {
     *                 res.push_back(to_string(begin));
     *             } else {
     *                 res.push_back(to_string(begin) + "->" + to_string(end));
     *             }
     *         }
     *         return res;
     *     }
     * };
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int begin = nums[i];
            while (i < n - 1 && nums[i] + 1 == nums[i + 1]) {
                i++;
            }
            int end = nums[i];
            if (begin == end) {
                res.add("" + begin);
            } else {
                res.add("" + begin + "->" + end);
            }
        }

        return res;
    }

}
