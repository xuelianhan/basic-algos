package org.ict.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * You are given an array of unique integers salary where salary[i] is the salary of the ith employee.
 * Return the average salary of employees excluding the minimum and maximum salary.
 * Answers within 10^-5 of the actual answer will be accepted.
 *
 * Example 1:
 * Input: salary = [4000,3000,1000,2000]
 * Output: 2500.00000
 * Explanation: Minimum salary and maximum salary are 1000 and 4000 respectively.
 * Average salary excluding minimum and maximum salary is (2000+3000) / 2 = 2500
 *
 * Example 2:
 * Input: salary = [1000,2000,3000]
 * Output: 2000.00000
 * Explanation: Minimum salary and maximum salary are 1000 and 3000 respectively.
 * Average salary excluding minimum and maximum salary is (2000) / 1 = 2000
 *
 *
 * Constraints:
 * 3 <= salary.length <= 100
 * 1000 <= salary[i] <= 10^6
 * All the integers of salary are unique.
 * @author sniper
 * @date 01 May 2023
 * LC1491, Easy
 */
public class AverageSalaryExcludingMinAndMax {

    /**
     * Because  1000 <= salary[i] <= 10^6
     * class Solution:
     *     def average(self, salary: List[int]) -> float:
     *         n = len(salary)
     *         min = 1000001
     *         max = 0
     *         sum = 0
     *         for s in salary:
     *             sum += s
     *             if s > max:
     *                 max = s
     *             if s < min:
     *                 min = s
     *         return (sum - max - min) / (n - 2)
     * @param salary
     * @return
     */
    public double averageV1(int[] salary) {
        int n = salary.length;
        long sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            sum += salary[i];
            if (salary[i] < min) {
                min = salary[i];
            }
            if (salary[i] > max) {
                max = salary[i];
            }
        }
        return (double)(sum - min - max) / (n - 2);
    }

    /**
     * class Solution:
     *     def average(self, salary: List[int]) -> float:
     *         salary.sort()
     *         n = len(salary)
     *         sum = 0
     *         for s in salary[1:-1]:
     *             sum += s
     *         return sum / (n - 2)
     * @param salary
     * @return
     */
    public double average(int[] salary) {
        Arrays.sort(salary);
        int n = salary.length;
        long sum = 0;
        for (int i = 1; i < n - 1; i++) {
            sum += salary[i];
        }
        return (double)sum / (n - 2);
    }
}
