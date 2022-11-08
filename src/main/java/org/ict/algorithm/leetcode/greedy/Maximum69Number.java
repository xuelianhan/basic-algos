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

    public static void main(String[] args) {
        double x = Math.pow(10, -1);
        double y = Math.pow(10, 0);
        System.out.println("x:" + x);
        System.out.println("y:" + y);
        int intX = (int)x;
        int intY = (int)y;
        System.out.println("intX:" + intX);
        System.out.println("intY:" + intY);
    }

    /**
     * Math solution
     * Cost 0ms
     * @param num
     * @return
     */
    public int maximum69NumberV3(int num) {
        /**
         * 6999 --> 9999, 9999 = 6999 + 3 * 10^3
         * 9999 --> 9999, 9999 = 9999 + 3 * 0
         */
        int firstSix = -1;
        int i = 0;
        int x = num;
        while (x > 0) {
            if (x % 10 == 6) {
                firstSix = i;
            }
            x /= 10;
            i++;
        }
        int add = 3 * (int)Math.pow(10, firstSix);
        return (num + add);
    }

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
     * e.g.6699
     * i:0, 6699 modular 10 = 9(right-first, the lower digit)
     * i:1, 669 modular 10 = 9
     * i:2, 66 modular 10 = 6,
     * i:3, 6 modular 10 = 6(left-first, the higher digit)
     *
     * So the highest digit of 6 is at the index 3
     * result = 6699 + 3 * 3*10^3 = 6699 + 3*1000 = 9699
     *
     * @param num
     * @return
     */
    public int maximum69NumberV1(int num) {
        /**
         * firstSix is used as an exponent digit,
         * So it can'not be initialized with 0.
         * e.g.9999
         * we can't find any 6 in 9999, if we initialize firstSix as 0,
         * then double y = Math.pow(10, 0); y will be 1.0
         *      int intY = (int)y; intY will be 1
         * if we initialize firstSix as -1, then
         *      double x = Math.pow(10, -1); x will be 0.1
         *       int intX = (int)x; intX will be 0
         * In num + 3 * (int)Math.pow(10,firstSix);
         * we need the second part of 3 * (int)Math.pow(10,firstSix) to be 0 while we haven't found the 6 in num digits.
         * So, we initialized firstSix as -1;
         *
         */
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
