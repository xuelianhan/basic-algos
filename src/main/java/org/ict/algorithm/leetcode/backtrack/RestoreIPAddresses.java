package org.ict.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A valid IP address consists of exactly four integers separated by single dots.
 * Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 *
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses,
 * but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s.
 * You are not allowed to reorder or remove any digits in s.
 * You may return the valid IP addresses in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * Example 2:
 *
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * Example 3:
 *
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * s consists of digits only.
 * @author sniper
 * @date 21 Jun 2023
 * LC93, Medium, frequency=7
 */
public class RestoreIPAddresses {

    /**
     * Time Cost 7ms
     * @param s
     * @return
     */
    public List<String> restoreIpAddressesV1(String s) {
        List<String> res = new ArrayList<>();
        LinkedList<String> track = new LinkedList<>();
        backtrackV1(s, 0, track, res);
        return res;
    }

    private void backtrackV1(String s, int start, LinkedList<String> track, List<String> result) {
        if (track.size() == 4 && start == s.length()) {
            result.add(String.join(".", track));
            return;
        }
        for (int len = 1; len < 4; len++) {
            if (start + len > s.length()) {
                return;
            }
            // Leading '0'
            if (len > 1 && s.charAt(start) == '0') {
                return;
            }
            String num = s.substring(start, start + len);
            if (Integer.parseInt(num) > 255) {
                return;
            }
            track.add(num);
            backtrack(s, start + len, track, result);
            track.removeLast();
        }
    }

    /**
     * Understanding the following solution
     * Time Cost 3ms
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        backtrack(s, 0, new LinkedList<String>(), res);
        return res;
    }

    private void backtrack(String s, int start, LinkedList<String> track, List<String> result) {
        if (track.size() == 4 && start == s.length()) {
            result.add(String.join(".", track));
            return;
        }
        /**
         * Remove the following if-condition won't affect the result,
         * but the running time cost 7ms
         */
        if (track.size() == 4 || start == s.length()) {
            return;
        }

        for (int len = 1; len < 4; len++) {
            if (start + len > s.length()) {
                return;
            }
            // Leading '0'
            if (len > 1 && s.charAt(start) == '0') {
                return;
            }
            String num = s.substring(start, start + len);
            if (Integer.parseInt(num) > 255) {
                return;
            }
            track.add(num);
            backtrack(s, start + len, track, result);
            track.removeLast();
        }
    }

}
