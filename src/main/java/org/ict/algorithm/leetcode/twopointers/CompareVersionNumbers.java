package org.ict.algorithm.leetcode.twopointers;

/**
 * Given two version numbers, version1 and version2, compare them.
 *
 * Version numbers consist of one or more revisions joined by a dot '.'.
 * Each revision consists of digits and may contain leading zeros.
 * Every revision contains at least one character.
 * Revisions are 0-indexed from left to right,
 * with the leftmost revision being revision 0,
 * the next revision being revision 1, and so on.
 * For example 2.5.33 and 0.1 are valid version numbers.
 *
 * To compare version numbers, compare their revisions in left-to-right order.
 * Revisions are compared using their integer value ignoring any leading zeros.
 * This means that revisions 1 and 001 are considered equal.
 * If a version number does not specify a revision at an index, then treat the revision as 0.
 * For example, version 1.0 is less than version 1.1 because their revision 0s are the same,
 * but their revision 1s are 0 and 1 respectively, and 0 < 1.
 *
 * Return the following:
 *
 * If version1 < version2, return -1.
 * If version1 > version2, return 1.
 * Otherwise, return 0.
 *
 *
 * Example 1:
 *
 * Input: version1 = "1.01", version2 = "1.001"
 * Output: 0
 * Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".
 * Example 2:
 *
 * Input: version1 = "1.0", version2 = "1.0.0"
 * Output: 0
 * Explanation: version1 does not specify revision 2, which means it is treated as "0".
 * Example 3:
 *
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1
 * Explanation: version1's revision 0 is "0", while version2's revision 0 is "1". 0 < 1, so version1 < version2.
 *
 *
 * Constraints:
 *
 * 1 <= version1.length, version2.length <= 500
 * version1 and version2 only contain digits and '.'.
 * version1 and version2 are valid version numbers.
 * All the given revisions in version1 and version2 can be stored in a 32-bit integer.
 *
 * @author sniper
 * @date 27 Aug 2023
 * LC165, Medium, abroad frequency=4, mainland frequency:89
 *
 */
public class CompareVersionNumbers {

    /**
     * Time Cost 1ms
     * Time Complexity O(N)
     * Space Complexity O(1)
     * --------------------------------------------
     * class Solution:
     *     def compareVersion(self, version1: str, version2: str) -> int:
     *         arr1, arr2 = version1.split('.'), version2.split('.')
     *         length = max(len(arr1), len(arr2))
     *         for i in range(length):
     *             v1 = int(arr1[i]) if i < len(arr1) else 0
     *             v2 = int(arr2[i]) if i < len(arr2) else 0
     *             if v1 < v2:
     *                 return -1
     *             if v1 > v2:
     *                 return 1
     *         return 0
     * --------------------------------------------
     * object Solution {
     *     def compareVersion(version1: String, version2: String): Int = {
     *         val arr1 = version1.split("\\.")
     *         val arr2 = version2.split("\\.")
     *
     *         var length = math.max(arr1.length, arr2.length)
     *         var res = 0
     *         var i = 0
     *         while (i < length) {
     *             var v1 = if (i < arr1.length) Integer.parseInt(arr1(i)) else 0
     *             var v2 = if (i < arr2.length) Integer.parseInt(arr2(i)) else 0
     *             if (v1 < v2) {
     *                 return -1
     *             } else if (v1 > v2) {
     *                 return 1
     *             }
     *             i += 1
     *         }
     *         res
     *     }
     * }
     * ---------------------------------------------
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersionV1(String version1, String version2) {
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");

        int length = Math.max(arr1.length, arr2.length);
        for (int i = 0; i < length; i++) {
            /**
             * If a version number does not specify a revision at an index,
             * then treat the revision as 0.
             */
            Integer v1 = (i < arr1.length ? Integer.parseInt(arr1[i]) : 0);
            Integer v2 = (i < arr2.length ? Integer.parseInt(arr2[i]) : 0);
            int cur = v1.compareTo(v2);
            if (cur != 0) {
                return cur;
            }
        }
        return 0;
    }

    /**
     * Time Cost 1ms
     * Time Complexity O(N)
     * Space Complexity O(1)
     *
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");

        int length = Math.max(arr1.length, arr2.length);
        for (int i = 0; i < length; i++) {
            /**
             * If a version number does not specify a revision at an index,
             * then treat the revision as 0.
             */
            Integer v1 = (i < arr1.length ? Integer.parseInt(arr1[i]) : 0);
            Integer v2 = (i < arr2.length ? Integer.parseInt(arr2[i]) : 0);
            int cur = v1.compareTo(v2);
            if (cur == 0) {
                continue;
            }
            return cur;
        }
        return 0;
    }
}
