package org.ict.algorithm.leetcode.array;

/**
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 * Example 2:
 *
 * Input: nums = [1,0,1,1,0,1]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 * @author sniper
 * @date 21 Mar, 2023
 * LC485, Easy
 */
public class MaxConsecutiveOnes {

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1};
        MaxConsecutiveOnes instance = new MaxConsecutiveOnes();
        int res = instance.findMaxConsecutiveOnes(nums);
        System.out.println(res);
    }

    public int findMaxConsecutiveOnesV2(int[] nums) {
        int res = 0;
        int cnt = 0;
        for (int num : nums) {
            cnt = (num == 0 ? 0 : ++cnt);
            res = Math.max(cnt, res);
        }
        return res;
    }

    public int findMaxConsecutiveOnesV1(int[] nums) {
        int res = 0;
        int cnt = 0;
        for (int num : nums) {
            cnt = (num == 0 ? 0 : cnt + 1);
            res = Math.max(cnt, res);
        }
        return res;
    }


    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int cnt = 0;
        for (int num : nums) {
            if (num == 1) {
                cnt++;
            } else {
                res = Math.max(cnt, res);
                cnt = 0;
            }
        }
        return Math.max(res, cnt);
    }
}
