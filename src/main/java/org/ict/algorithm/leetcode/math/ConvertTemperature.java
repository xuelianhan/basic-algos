package org.ict.algorithm.leetcode.math;

import java.util.Arrays;

/**
 * You are given a non-negative floating point number rounded to two decimal places celsius, that denotes the temperature in Celsius.
 *
 * You should convert Celsius into Kelvin and Fahrenheit and return it as an array ans = [kelvin, fahrenheit].
 *
 * Return the array ans. Answers within 10^-5 of the actual answer will be accepted.
 *
 * Note that:
 *
 * Kelvin = Celsius + 273.15
 * Fahrenheit = Celsius * 1.80 + 32.00
 *
 *
 * Example 1:
 *
 * Input: celsius = 36.50
 * Output: [309.65000,97.70000]
 * Explanation: Temperature at 36.50 Celsius converted in Kelvin is 309.65 and converted in Fahrenheit is 97.70.
 * Example 2:
 *
 * Input: celsius = 122.11
 * Output: [395.26000,251.79800]
 * Explanation: Temperature at 122.11 Celsius converted in Kelvin is 395.26 and converted in Fahrenheit is 251.798.
 *
 *
 * Constraints:
 *
 * 0 <= celsius <= 1000
 * @author sniper
 * @date 11 Dec, 2022
 * LC2469
 */
public class ConvertTemperature {

    public static void main(String[] args) {
        ConvertTemperature instance = new ConvertTemperature();
        double celsius = 122.11;
        double[] res = instance.convertTemperature(celsius);
        System.out.println(Arrays.toString(res));
    }

    public double[] convertTemperature(double celsius) {
        double[] res = new double[2];
        double kelvin = celsius + 273.15;
        double fahrenheit = celsius * 1.80 + 32.00;
        res[0] = (double)Math.round(kelvin * 100000.0) / 100000.0;
        res[1] = (double)Math.round(fahrenheit * 100000.0) / 100000.0;
        return res;
    }
}
