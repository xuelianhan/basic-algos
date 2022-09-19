package org.ict.algorithm.leetcode.array;

/**
 *
 * You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer.
 * The digits are ordered from most significant to least significant in left-to-right order.
 * The large integer does not contain any leading 0's.
 *
 * Increment the large integer by one and return the resulting array of digits.
 *
 *
 *
 * Example 1:
 *
 * Input: digits = [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Incrementing by one gives 123 + 1 = 124.
 * Thus, the result should be [1,2,4].
 * Example 2:
 *
 * Input: digits = [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 * Incrementing by one gives 4321 + 1 = 4322.
 * Thus, the result should be [4,3,2,2].
 * Example 3:
 *
 * Input: digits = [9]
 * Output: [1,0]
 * Explanation: The array represents the integer 9.
 * Incrementing by one gives 9 + 1 = 10.
 * Thus, the result should be [1,0].
 *
 *
 * Constraints:
 *
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 * digits does not contain any leading 0's.
 * @author sniper
 * @date 19 Sep, 2022
 * LC66
 */
public class PlusOne {


    public int[] plusOneV2(int[] digits) {
        return null;
    }

    public int[] plusOne(int[] digits) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int sum = 0;
        /**
         * Iterate the array from high to low.
         */
        for (int i = digits.length - 1; i >= 0; i--) {
            if (i == digits.length - 1) {
                sum = digits[i] + 1 + carry;
            } else {
                sum = digits[i] + carry;
            }
            if (sum >= 10) {
                carry = 1;
                sum = sum - 10;
            } else {
                /**
                 * when no carry, carry must be reset to zero.
                 */
                carry = 0;
            }
            sb.append(sum);
        }
        /**
         * When carry is greater than zero, the highest digit is the carry.
         */
        if (carry > 0) {
            sb.append(carry);
        }
        /**
         * Output in reverse order.
         */
        int[] res = new int[sb.length()];
        for(int i = 0; i < res.length; i++) {
            res[i] = sb.charAt(sb.length() - 1 - i) - '0';
        }
        return res;
    }
}
