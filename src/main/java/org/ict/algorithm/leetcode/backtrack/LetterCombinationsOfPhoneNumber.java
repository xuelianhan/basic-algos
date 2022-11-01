package org.ict.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void main(String[] args) {
        String digits = "23";
        letterCombinations(digits);
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
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
        List<Character> choise = new ArrayList<>();
        for (char digit : digits.toCharArray()) {
            for (char ch : map.get(digit).toCharArray()) {
                choise.add(ch);
            }
        }
        backtrack(result, digits.length(), track, choise);
        return result;
    }

    public static void backtrack(List<String> result, int max, StringBuilder track, List<Character> choise) {
        if (track.length() == max) {
            result.add(track.toString());
            return;
        }
        for (char ch : choise) {
            if (track.toString().contains(Character.toString(ch))) {
                /**
                 * Skip the number which has been accessed.
                 */
                continue;
            }
            track.append(ch);
            backtrack(result, max, track, choise);
            track.deleteCharAt(track.length() - 1);
        }
    }
}
