package org.ict.algorithm.leetcode.array;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * Example 2:
 *
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 * 0 <= k <= nums.length
 * @author sniper
 * @date 20 Mar, 2023
 * LC1004, Medium
 */
public class MaxConsecutiveOnesIII {

    public static void main(String[] args) {
        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;
        MaxConsecutiveOnesIII instance = new MaxConsecutiveOnesIII();
        int res = instance.longestOnes(nums, k);
        System.out.println(res);
    }


    /**
     * Understanding the follwing Solution
     * Slide Window Solution
     * e.g. nums = [0,0,0,1], k = 4, expected 4
     * right:0, nums[0]:0, queue:0, queue.size < 4, left:0, res=max(0,0-0+1)=1
     * right:1, nums[1]:0, queue:0,1, queue.size < 4, left:0, res=max(0,1-0+1)=2
     * right:2, nums[2]:0, queue:0,1,2, queue.size < 4, left:0, res=max(0,2-0+1)=3
     * right:3, nums[3]:1, queue:0,1,2, queue.size < 4, left:0, res=max(0,3-0+1)=4
     * return res:4
     *
     * e.g. nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2, expected:6
     * right:0, nums[0]:1, queue:, queue.size=0, left:0, res=max(0,0-0+1)=1
     * right:1, nums[1]:1, queue:, queue.size=0, left:0, res=max(0,1-0+1)=2
     * right:2, nums[2]:1, queue:, queue.size=0, left:0, res=max(0,2-0+1)=3
     * right:3, nums[3]:0, queue:3, queue.size=1, left:0, res=max(0,3-0+1)=4
     * right:4, nums[4]:0, queue:3,4, queue.size=2, left:0, res=max(0,4-0+1)=5
     * right:5, nums[5]:0, queue:3,4,5, queue.size=3, left=3+1=4, res=max(5,5-3+1)=5
     * right:6, nums[6]:1, queue:4,5, queue.size=2, left:4, res=max(5,6-4+1)=5
     * right:7, nums[7]:1, queue:4,5, queue.size=2, left:4, res=max(5,7-4+1)=4
     * right:8, nums[8]:1, queue:4,5, queue.size=2, left:4, res=max(5,8-4+1)=5
     * right:9, nums[9]:1, queue:4,5, queue.size=2, left:4, res=max(5,9-4+1)=6
     * right:10, nums[10]:0, queue:4,5,10, queue.size=3, left:5, res=max(6, 10-5+1)=6
     * return res:6
     *
     * @param nums
     * @param k
     * @return
     */
    public int longestOnesV3(int[] nums, int k) {
        int res = 0;
        int left = 0;
        /**
         * Queue to store the index of value with zero.
         */
        Queue<Integer> queue = new ArrayDeque<>();
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                queue.offer(right);
            }
            /**
             * Two important points:
             * 1.left = queue.poll() + 1, not queue.poll()
             * 2.res = Math.max(res, right - left + 1), not Math.max(res, right - left);
             * e.g. nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2, expected:6
             * Why plus 1? because queue.size greater than k, so the front element of queue
             * is just the previous location before the actual k-plots.
             */
            if (queue.size() > k) {
                left = queue.poll() + 1;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public int longestOnesV2(int[] nums, int k) {
        return 0;
    }

    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int cnt = 0;
        int res = 0;
        for (int right = 0; right < nums.length; right++) {
            /**
             * Count zero numbers.
             */
            if (nums[right] == 0) {
                cnt++;
            }
            /**
             * If zero counts greater than window k,
             * we need to move on pointer left,
             * this may decrease the cnt if nums[left] is zero before we update left pointer.
             */
            while (cnt > k) {
                if (nums[left] == 0) {
                    cnt--;
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
