package org.ict.algorithm.leetcode.backtrack;

import java.util.*;

/**
 * Given a string containing digits from 2-9 inclusive,
 * return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 * mapping relations like the following:
 *
 * 2---abc
 * 3---def
 * 4---ghi
 * 5---jkl
 * 6---mno
 * 7--pqrs
 * 8---tuv
 * 9--wxyz
 *
 *
 * Example 1:
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 *
 * Input: digits = ""
 * Output: []
 * Example 3:
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 *
 * Constraints:
 *
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 * @author sniper
 * @date 01 Nov, 2022
 * LC17
 */
public class LetterCombinationsOfPhoneNumber {

    /**
     * Understanding the following Breadth-First-Search Solution
     * 
     * @param digits
     * @return
     */
    public List<String> letterCombinationsV2(String digits) {
        return null;
    }


    /**
     * Understanding the following Breadth-First-Search Solution
     *
     *
     * @see <a href="https://afteracademy.com/blog/letter-combinations-of-a-phone-number"></a>
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinationsV1(String digits) {
        String[] map = new String[] {"0",   "1",   "abc",  "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        LinkedList<String> queue = new LinkedList<>();
        if (digits.isEmpty()) {
            return queue;
        }
        return queue;
    }


    /**
     * Understanding the following Backtracking Solution
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        /**
         * Corner case digits = "", return expected []
         */
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        StringBuilder track = new StringBuilder();
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        backtrack(result, digits, track, map, 0);
        return result;
    }

    /**
     * digits = "23"
     * 2 --> abc, 3 --> def
     *          root
     *        /  |  \      pos:0, select 2
     *       a   b   c
     *       /   |    \    pos:1, select 3
     *    (def) (def) (def)
     *
     *    ad,ae,af
     *    bd,be,bf
     *    cd,ce,cf
     *
     * @see <a href="https://www.interviewbit.com/blog/letter-combinations-of-a-phone-number/"></a>
     * @param result
     * @param digits
     * @param track
     * @param map
     * @param pos
     */
    public void backtrack(List<String> result, String digits, StringBuilder track, Map<Character, String> map, int pos) {
        if (track.length() == digits.length()) {
            result.add(track.toString());
            return;
        }
        String possibleLetters = map.get(digits.charAt(pos));
        for (char ch : possibleLetters.toCharArray()) {
            track.append(ch);
            backtrack(result, digits, track, map, pos + 1);
            track.deleteCharAt(track.length() - 1);
        }
    }
}
