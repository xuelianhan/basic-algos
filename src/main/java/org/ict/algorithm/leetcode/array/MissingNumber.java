package org.ict.algorithm.leetcode.array;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3].
 * 2 is the missing number in the range since it does not appear in nums.
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2].
 * 2 is the missing number in the range since it does not appear in nums.
 * Example 3:
 *
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9].
 * 8 is the missing number in the range since it does not appear in nums.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 10^4
 * 0 <= nums[i] <= n
 * All the numbers of nums are unique.
 *
 *
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 * @author sniper
 * @date 21 Sep, 2022
 * LC268
 */
public class MissingNumber {

    /**
     * The basic idea is to use XOR operation.
     * We all know that a^b^b = a,
     * which means two xor operations with the same number will eliminate the number and reveal the original number.
     * In this solution, I apply XOR operation to both the index and value of the array.
     * In a complete array with no missing numbers,
     * the index and value should be perfectly corresponding( nums[index] = index),
     * so in a missing array, what left finally is the missing number.
     *
     * @param nums
     * @return
     */
    public int missingNumberV5(int[] nums) {
        int xor = 0, i = 0;
        for (i = 0; i < nums.length; i++) {
            xor = xor ^ i ^ nums[i];
        }

        return xor ^ i;
    }


    /**
     * XOR Solution.
     * @param nums
     * @return
     */
    public int missingNumberV4(int[] nums) {
        int res = nums.length;
        for(int i = 0; i < nums.length; i++){
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }

    /**
     * Sum subtract Solution.
     * @param nums
     * @return
     */
    public int missingNumberV3(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }

    /**
     * Time Complexity O(N)
     * Space Complexity O(1)
     *
     * @param nums
     * @return
     */
    public int missingNumberV2(int[] nums) {
        long n = nums.length;
        return (int)(n * (n+1) / 2 - IntStream.of(nums).sum());
    }

    /**
     * Pretty simple since we are told that we are missing only one number in [1,n],
     * we just need to look at the difference between the sum([1,n]) = n * (n+1) / 2 and the sum of nums in our array.
     *
     *
     * @param nums
     * @return
     */
    public int missingNumberV1(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int nSum = n * (n + 1) / 2;
        return (nSum - sum);
    }

    public int missingNumberV0(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length;
        int mid= (left + right)/2;
        while(left < right){
            mid = (left + right)/2;
            if(nums[mid]>mid) {
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return left;
    }

    /**
     * Input: nums = [3,0,1]
     *
     * 0 1 2
     * 0 1 3
     *
     * Input: nums = [9,6,4,2,3,5,7,0,1]
     * 0 1 2 3 4 5 6 7 8 9
     * 0 1 2 3 4 5 6 7 9
     *
     * Input: nums = [0, 1]
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        /**
         * Process the case that missing number is the last one.
         */
        if (nums[len - 1] != len) {
            return len;
        }
        int res = 0;
        for (int i = 1; i < nums.length;) {
            if ((nums[i-1]+1) == nums[i]) {
                i++;
            } else {
                res = nums[i-1] + 1;
                break;
            }
        }
        return res;
    }
}
