package org.ict.algorithm.leetcode.binarysearch;

/**
 * You are given a positive integer array grades which represents the grades of students in a university.
 * You would like to enter all these students into a competition in ordered non-empty groups,
 * such that the ordering meets the following conditions:
 *
 * The sum of the grades of students in the ith group is less than the sum of the grades of students in the (i + 1)th group,
 * for all groups (except the last).
 * The total number of students in the ith group is less than the total number of students in the (i + 1)th group,
 * for all groups (except the last).
 * Return the maximum number of groups that can be formed.
 *
 *
 *
 * Example 1:
 *
 * Input: grades = [10,6,12,7,3,5]
 * Output: 3
 * Explanation: The following is a possible way to form 3 groups of students:
 * - 1st group has the students with grades = [12]. Sum of grades: 12. Student count: 1
 * - 2nd group has the students with grades = [6,7]. Sum of grades: 6 + 7 = 13. Student count: 2
 * - 3rd group has the students with grades = [10,3,5]. Sum of grades: 10 + 3 + 5 = 18. Student count: 3
 * It can be shown that it is not possible to form more than 3 groups.
 * Example 2:
 *
 * Input: grades = [8,8]
 * Output: 1
 * Explanation: We can only form 1 group, since forming 2 groups would lead to an equal number of students in both groups.
 *
 *
 * Constraints:
 *
 * 1 <= grades.length <= 10^5
 * 1 <= grades[i] <= 10^5
 * @author sniper
 * @date 16 Mar, 2023
 * LC2358, Medium
 */
public class MaximumNumberGroupsEnteringCompetition {


    /**
     * Math Solution
     * 1 + 2 + ... + k <= n
     * ==> k(k + 1) / 2 <= n
     * ==> k^2 + k <= 2*n
     * ==> k^2 + k + (1/2)^2 - (1/2)^2 <= 2*n
     * ==> (k + 0.5)^2 <= 2*n + (1/2)^2
     * ==> (k + 0.5)(k + 0.5) <= n * 2 + 0.25
     * ==> k + 0.5 <= sqrt(n * 2 + 0.25)
     * ==> k <= sqrt(n * 2 + 0.25) - 0.5
     *
     * Time O(1)
     * Space O(1)
     * @author lee215
     * @see <a href="https://leetcode.com/problems/maximum-number-of-groups-entering-a-competition/solutions/2357789/java-c-python-one-line-o-1/?orderBy=most_votes"></a>
     * @param grades
     * @return
     */
    public int maximumGroupsV4(int[] grades) {
        int N = grades.length;
        return (int)(Math.sqrt(N * 2 + 0.25) - 0.5);
    }

    /**
     * Find the maximum number k that satisfy with k * (k + 1) / 2 <= N
     * @param grades
     * @return
     */
    public int maximumGroupsV3(int[] grades) {
        int lo = 0;
        /**
         * Any value greater than or equals to 447 is enough, e.g. hi = 1000
         */
        int hi = 447;
        int N = grades.length;
        while (lo < hi) {
            int k = (lo + hi + 1) / 2;
            if (k * (k + 1) / 2 <= N) {
                lo = k;
            } else {
                hi = k - 1;
            }
        }
        return lo;
    }

    /**
     * 1 + 2 + ... + k <= n
     * k(k + 1) / 2 <= n
     * We can binary search the biggest k that k(k + 1) / 2 <= n
     *
     * Time O(log1000)
     * Space O(1)
     *
     * 1 <= grades.length <= 10^5
     * N = 100000, 2*N = 200000
     * 1+2+...+1000 = 1000*(1+1000)/2 = 1001*500 = 500500
     * 1+2+...+500 = 500*(1+500)/2 = 250*501 = 125250
     * ==> 100000 = k*(k+1)/2
     * ==> 200000 = k*(k+1)
     * ==> k nearly equals to 447, 447*448 = 200256 > 200000
     * @see <a href="https://leetcode.com/problems/maximum-number-of-groups-entering-a-competition/solutions/2357789/java-c-python-one-line-o-1/?orderBy=most_votes"></a>
     * @author lee215
     * @param grades
     * @return
     */
    public int maximumGroupsV2(int[] grades) {
        int lo = 0;
        /**
         * Any value greater than or equals to 447 is OK, e.g. hi = 1000
         */
        int hi = 447;
        int N = grades.length;
        while (lo < hi) {
            int k = (lo + hi + 1) / 2;
            if (k * (k + 1) / 2 > N) {
                hi = k - 1;
            } else {
                lo = k;
            }
        }
        return lo;
    }

    /**
     * Sort all grades,
     * assign 1 student to the first group,
     * assign 2 students to the second group...
     * This can satisfy ith group < (i+1)th group for both size and sum.
     *
     * So we need to find out the maximum result k that
     * 1 + 2 + ... + k <= n
     *
     * Prove:
     * Sort groups by size,
     * so the first group has size at least 1
     * so the second group has size at least 2
     * kth group has size at least k...
     *
     * So we proved the upper bound of k and I gave a solution to achieve it.
     * @see <a href="https://leetcode.com/problems/maximum-number-of-groups-entering-a-competition/solutions/2357789/java-c-python-one-line-o-1/?orderBy=most_votes"></a>
     * @author lee215
     * @param grades
     * @return
     */
    public int maximumGroupsV1(int[] grades) {
        /**
         * e.g. grades = [8,8]
         * Two points need to notice:
         * 1. k starts with 0;
         * 2. sum + k + 1 <= N, not sum + k + 1 < N, not sum <= N
         * k represents the number of elements for each group, it's fixed value:1,2,3,...
         *   it also means the k-th step.
         * sum represents the total number of elements at the k-th step.
         */
        int k = 0;
        int sum = 0;
        int N = grades.length;
        while (sum + k + 1 <= N) {
            k++;
            sum += k;
        }
        return k;
    }

    /**
     *
     * We don't need to care about the contents within the nums array, but only the length of array matters.
     * If you care about the numbers in the array, you will be stuck.
     *
     * e.g. grades = [10,6,12,7,3,5]
     * If we sort the array, we can pick up the smaller one from the array using greedy strategy.
     * 3
     * 5,6
     * 7,10,12
     * there are three groups in total.
     * @param grades
     * @return
     */
    public int maximumGroups(int[] grades) {
        int N = grades.length;
        int best = 0;
        for (int i = 0; i < N + 1; i++) {
            int sum = i * (i + 1) / 2;
            if (sum <= N) {
                best = i;
            } else {
                return best;
            }
        }
        return best;
    }

}
