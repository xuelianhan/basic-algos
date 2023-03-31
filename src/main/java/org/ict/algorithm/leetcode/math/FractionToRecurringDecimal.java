package org.ict.algorithm.leetcode.math;


import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * If multiple answers are possible, return any of them.
 * It is guaranteed that the length of the answer string is less than 10^4 for all the given inputs.
 *
 * Example 1:
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 *
 * Example 2:
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 *
 * Example 3:
 * Input: numerator = 4, denominator = 333
 * Output: "0.(012)"
 *
 *
 * Constraints:
 * -2^31 <= numerator, denominator <= 2^31 - 1
 * denominator != 0
 *
 * @author sniper
 * @date 30 Mar, 2023
 * LC166, Medium
 */
public class FractionToRecurringDecimal {

    public static void main(String[] args) {
        int numerator = 4;
        int denominator = 333;
        FractionToRecurringDecimal instance = new FractionToRecurringDecimal();
        instance.fractionToDecimal(numerator, denominator);
    }

    /**
     * e.g. 4/333
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimalV1(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        /**
         * Sign bit
         */
        String sign = ((numerator > 0) ^ (denominator > 0) ? "-" : "");
        StringBuilder res = new StringBuilder();
        res.append(sign);

        /**
         * Integral part
         */
        long num = Math.abs((long)numerator);
        long deno = Math.abs((long)denominator);
        res.append(num / deno);
        long remainder = num % deno;
        if (remainder == 0) {
            return res.toString();
        }

        /**
         * Fractional part, if remainder is not zero, we need to process the remainder.
         */
        res.append(".");
        Map<Long, Integer> indexMap = new HashMap<>();
        while (remainder != 0) {
            indexMap.put(remainder, res.length());
            remainder *= 10;
            res.append(remainder / deno);
            remainder %= deno;

            if (indexMap.get(remainder) == null) {
                indexMap.put(remainder, res.length());
            } else {
                int index = indexMap.get(remainder);
                res.insert(index, "(");
                res.append(")");
                break;
            }
        }
        return res.toString();
    }

    /**
     * No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
     * Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
     * Notice that once the remainder starts repeating, so does the divided result.
     * Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.
     * e.g. 4/333 =0.012012012...
     * num:4, deno:333, res:4/333=0, remainder:4%333=4, res:"0.", remainder:4, indexMap:(4,2)
     * remainder:4, remainder=4*10=40, 40/333=0, res:"0.0", remainder=40%333=40, indexMap:(4,2),(40,3)
     * remainder:40, remainder=40*10=400, 400/333=1, res:"0.01", remainder=400%333=67, indexMap:(4,2),(40,3),(67, 4)
     * remainder:67, remainder=67*10=670, 670/333=2, res:"0.012", remainder=670%333=4, indexMap.containsKey(4), index:2
     *   res.insert(2,"("), res:"0.(012", res.append(")"), res:"0.(012)", while-loop-break
     * return res:"0.(012)"
     *
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        String sign = ((numerator > 0) ^ (denominator > 0) ? "-" : "");
        res.append(sign);

        /**
         * Integral part
         */
        long num = Math.abs((long)numerator);
        long deno = Math.abs((long)denominator);
        res.append(num / deno);
        long remainder = num % deno;
        if (remainder == 0) {
            return res.toString();
        }
        /**
         * Fractional part, if remainder is not zero, we need to process the remainder.
         */
        res.append(".");
        Map<Long, Integer> indexMap = new HashMap<>();
        indexMap.put(remainder, res.length());
        while (remainder != 0) {
            remainder *= 10;
            res.append(remainder / deno);
            remainder %= deno;
            if (indexMap.containsKey(remainder)) {
                int index = indexMap.get(remainder);
                res.insert(index, "(");
                res.append(")");
                break;
            } else {
                indexMap.put(remainder, res.length());
            }
        }
        return res.toString();
    }

}
