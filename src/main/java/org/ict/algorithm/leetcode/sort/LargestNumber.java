package org.ict.algorithm.leetcode.sort;

import java.util.*;

/**
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 * Since the result may be very large, so you need to return a string instead of an integer.
 *
 * Example 1:
 * Input: nums = [10,2]
 * Output: "210"
 *
 * Example 2:
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 *
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 *
 * @author sniper
 * @date 28 Mar, 2023
 * LC179, Medium
 */
public class LargestNumber {


    public static void main(String[] args) {
        int[] nums = {3,30,34,5,9};
        LargestNumber instance = new LargestNumber();
        String res = instance.largestNumber(nums);
        System.out.println(res);
    }

    public String largestNumberV2(int[] nums) {
        String[] array = Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new);
        Arrays.sort(array, (String s1, String s2) -> (s2 + s1).compareTo(s1 + s2));
        return Arrays.stream(array).reduce((x, y) -> x.equals("0") ? y : x + y).get();
    }

    public String largestNumberV1(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        /**
         * Process [3, 30] to [30, 3]
         */
        Collections.sort(list, (o1, o2) -> {
            String s1 = o1 + o2;
            String s2 = o2 + o1;
            /**
             * reverse order here
             */
            return s2.compareTo(s1);
        });
        /**
         * Remove leading "0", such as ["0", "0", "0"], convert it to single "0".
         */
        if ("0".equals(list.get(0))) {
            return "0";
        }
        int n = list.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    /**
     * e.g. nums=[0,0], expected:"0"
     * e.g. nums=[1,0,0], expected:"100"
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        /**
         * Process [3, 30] to [30, 3]
         */
        Collections.sort(list, (o1, o2) -> {
            Long x1 = Long.valueOf(o1 + o2);
            Long x2 = Long.valueOf(o2 + o1);
            if (x1 > x2) {
                return 1;
            } else if (x1 < x2) {
                return -1;
            } else {
                return 0;
            }
        });
        StringBuilder sb = new StringBuilder();
        int n = list.size();
        for (int i = n - 1; i >= 0; i--) {
            String cur = list.get(i);
            /**
             * Remove leading "0", such as ["0", "0", "0"]
             */
            if ("0".equals(list.get(n-1))) {
                sb.append("0");
                break;
            }
            sb.append(cur);
        }
        return sb.toString();
    }
}
