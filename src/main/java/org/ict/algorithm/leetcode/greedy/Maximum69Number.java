package org.ict.algorithm.leetcode.greedy;

/**
 * You are given a positive integer num consisting only of digits 6 and 9.
 *
 * Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
 *
 *
 *
 * Example 1:
 *
 * Input: num = 9669
 * Output: 9969
 * Explanation:
 * Changing the first digit results in 6669.
 * Changing the second digit results in 9969.
 * Changing the third digit results in 9699.
 * Changing the fourth digit results in 9666.
 * The maximum number is 9969.
 * Example 2:
 *
 * Input: num = 9996
 * Output: 9999
 * Explanation: Changing the last digit 6 to 9 results in the maximum number.
 * Example 3:
 *
 * Input: num = 9999
 * Output: 9999
 * Explanation: It is better not to apply any change.
 *
 *
 * Constraints:
 *
 * 1 <= num <= 10^4
 * num consists of only 6 and 9 digits.
 * @author sniper
 * @date 08 Nov, 2022
 * LC1323
 */
public class Maximum69Number {

    /**
     * String solution one line
     * Cost 7ms
     * @author tzhenia
     * @param num
     * @return
     */
    public int maximum69NumberV2(int num) {
        return Integer.parseInt(("" + num).replaceFirst("6", "9"));
    }

    /**
     * e.g.9996
     *
     * @param num
     * @return
     */
    public int maximum69NumberV1(int num) {
        int firstSix = -1;
        int number = num;
        for(int i = 0; number > 0; i++){
            if(number % 10 == 6){
                firstSix = i;
            }
            number /= 10;
        }
        return num + 3 * (int)Math.pow(10,firstSix);
    }

    /**
     * String solution with for-loop
     * Cost 1ms
     * @param num
     * @return
     */
    public int maximum69Number (int num) {
        String s = String.valueOf(num);
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (ch == '6') {
                arr[i] = '9';
                break;
            }
        }
        return Integer.valueOf(new String(arr));
    }
}
