package org.ict.algorithm.leetcode.binarysearch;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * All the integers in nums appear only once except for precisely one integer which appears two or more times.
 *
 *
 * Follow up:
 *
 * How can we prove that at least one duplicate number must exist in nums?
 * Can you solve the problem in linear runtime complexity?
 * @author sniper
 * @date 26 Nov, 2022
 * LC287
 */
public class FindTheDuplicateNumber {

    public static void main(String[] args) {
        FindTheDuplicateNumber instance = new FindTheDuplicateNumber();
        int[] nums = {1,3,4,2,2 };
        instance.findDuplicateV2(nums);
        //instance.findDuplicateV3(nums);
        //instance.findDuplicateV4(nums);
        //instance.findDuplicateV5(nums);
    }

    /**
     * Floyd's Tortoise and Hare Solution.
     * Time Cost 14ms
     *
     * The main idea is the same with problem
     * <a href="https://leetcode.com/problems/linked-list-cycle-ii/">LC142</a>
     * Use two pointers the fast and the slow.
     * The fast one goes forward two steps each time, while the slow one goes only step each time.
     * They must meet the same item when slow==fast.
     * In fact, they meet in a circle, the duplicate number must be the entry point of the circle when visiting the array from nums[0].
     * Next we just need to find the entry point.
     * We use a point(we can use the fast one before) to visit form begining with one step each time,
     * do the same job to slow.
     * When fast==slow, they meet at the entry point of the circle.
     * The easy understood code is as follows.
     *
     * e.g. nums=[1,3,4,2,2]
     * slow:1, fast:3
     * slow:3, fast:4
     * slow:2, fast:4
     * slow:4, fast:4
     * slow == fast, first-while-loop-end
     * slow:4, fast:0
     * slow:2, fast:1
     * slow:4, fast:3
     * slow:2, fast:2
     * slow == fast, second-while-loop-end
     * return slow:2
     *
     * @author echoxiaolee
     * @param nums
     * @return
     */
    public int findDuplicateV6(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    /**
     * Time Cost 34ms
     * e.g. nums=[1,3,4,2,2]
     * 0 1 2 3 4
     * 1 3 4 2 2
     * @param nums
     * @return
     */
    public int findDuplicateV5(int[] nums) {
        /**
         * nums containing n + 1 integers where each integer is in the range [1, n] inclusive
         */
        int left = 1, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            System.out.println("left:" + left + ", right:" + right + ", mid:" + mid);
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            System.out.println("cnt:" + cnt  + ", mid:" + mid);
            if (cnt <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }


    /**
     * Time Cost 32ms
     * e.g. nums=[1,3,4,2,2]
     * 0 1 2 3 4
     * 1 3 4 2 2
     * @param nums
     * @return
     */
    public int findDuplicateV4(int[] nums) {
        /**
         * nums containing n + 1 integers where each integer is in the range [1, n] inclusive
         */
        int left = 1, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            System.out.println("left:" + left + ", right:" + right + ", mid:" + mid);
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            System.out.println("cnt:" + cnt  + ", mid:" + mid);
            if (cnt <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    /**
     * Time Cost 26ms
     * e.g. nums=[1,3,4,2,2]
     * 0 1 2 3 4
     * 1 3 4 2 2
     * left:1, right:
     *
     * @param nums
     * @return
     */
    public int findDuplicateV3(int[] nums) {
        /**
         * nums containing n + 1 integers where each integer is in the range [1, n] inclusive
         * You can start with zero: left=0, but this doesn't have any affect to the final result.
         * The only affect may be adding more loops than starting from one.
         */
        int left = 1, right = nums.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println("left:" + left + ", right:" + right + ", mid:" + mid);
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            System.out.println("cnt:" + cnt  + ", mid:" + mid);
            if (cnt <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }


    /**
     * Time Cost 35ms
     *
     * e.g. nums=[1,3,4,2,2]
     * 0 1 2 3 4
     * 1 3 4 2 2
     * left:1, right:5 - 1 = 4, mid = 5 / 2 = 2
     * cnt:0 -> cnt:3, cnt > mid, right = mid - 1 = 2 - 1 = 1
     * left:1, right:1, mid = 2 / 2 = 1
     * cnt:0, cnt:1, cnt == mid, left = mid + 1 = 1 + 1 = 2
     * left:2, right:1, left > right, while-loop-end
     * return left:2
     *
     * @param nums
     * @return
     */
    public int findDuplicateV2(int[] nums) {
        /**
         * nums containing n + 1 integers where each integer is in the range [1, n] inclusive
         * You can start with zero: left=0, but this doesn't have any affect to the final result.
         * The only affect may be adding more loops than starting from one.
         */
        int left = 1, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            //System.out.println("left:" + left + ", right:" + right + ", mid:" + mid);
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            //System.out.println("cnt:" + cnt  + ", mid:" + mid);
            if (cnt <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * Time Cost 84 ms
     * Not Satisfy the constraint of using only constant extra space.
     * @param nums
     * @return
     */
    public int findDuplicateV1(int[] nums) {
        int res = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            if (freq.getOrDefault(nums[i], 0) > 1) {
                res = nums[i];
                break;
            }
        }
        return res;
    }


    /**
     * Time Cost 32ms
     * Not Satisfy the constraint of using only constant extra space.
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int max = 0, sum = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            if (freq.getOrDefault(nums[i], 0) > 1) {
                return nums[i];
            }
            sum += nums[i];
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        for (int i = 1; i <= max; i++) {
            sum -= i;
        }
        return sum;
    }
}
