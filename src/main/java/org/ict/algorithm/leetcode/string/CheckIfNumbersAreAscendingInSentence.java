package org.ict.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A sentence is a list of tokens separated by a single space with no leading or trailing spaces.
 * Every token is either a positive number consisting of digits 0-9 with no leading zeros,
 * or a word consisting of lowercase English letters.
 *
 * For example, "a puppy has 2 eyes 4 legs" is a sentence with seven tokens:
 * "2" and "4" are numbers and the other tokens such as "puppy" are words.
 * Given a string s representing a sentence,
 * you need to check if all the numbers in s are strictly increasing from left to right
 * (i.e., other than the last number, each number is strictly smaller than the number on its right in s).
 *
 * Return true if so, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * example-1
 * Input: s = "1 box has 3 blue 4 red 6 green and 12 yellow marbles"
 * Output: true
 * Explanation: The numbers in s are: 1, 3, 4, 6, 12.
 * They are strictly increasing from left to right: 1 < 3 < 4 < 6 < 12.
 * Example 2:
 *
 * Input: s = "hello world 5 x 5"
 * Output: false
 * Explanation: The numbers in s are: 5, 5. They are not strictly increasing.
 * Example 3:
 *
 * example-3
 * Input: s = "sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s"
 * Output: false
 * Explanation: The numbers in s are: 7, 51, 50, 60. They are not strictly increasing.
 *
 *
 * Constraints:
 *
 * 3 <= s.length <= 200
 * s consists of lowercase English letters, spaces, and digits from 0 to 9, inclusive.
 * The number of tokens in s is between 2 and 100, inclusive.
 * The tokens in s are separated by a single space.
 * There are at least two numbers in s.
 * Each number in s is a positive number less than 100, with no leading zeros.
 * s contains no leading or trailing spaces.
 *
 * @author sniper
 * @date 2022/8/17
 * LC2042
 */
public class CheckIfNumbersAreAscendingInSentence {

    public static void main(String[] args) {
        //String s = "1 box has 3 blue 4 red 6 green and 12 yellow marbles";
        //String s = "hello world 5 x 5";
        String s = "sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s";
        boolean result = areNumbersAscendingV2(s);
        System.out.println(result);
    }


    public boolean areNumbersAscendingV3(String s) {
        String[] arr = s.split("\\s");
        int prev = -1;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i].charAt(0))) {
                int cur = Integer.valueOf(arr[i]);
                if (prev >= cur) {
                    return false;
                }
                prev = cur;
            }
        }
        return true;
    }



    public static boolean areNumbersAscendingV2(String s) {
        List<Integer> list = new ArrayList<>();
        String[] arr = s.split("\\s");
        for (String item : arr) {
            if (isAllDigits(item)) {
                list.add(Integer.valueOf(item));
            }
        }
        System.out.println(list);
        for (int i = 1; i < list.size(); i++) {
            int prev = list.get(i-1);
            int cur = list.get(i);
            if (prev >= cur) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllDigits(String item) {
        for (char ch : item.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    public static boolean areNumbersAscending(String s) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(s);
        List<Integer> list = new ArrayList<>();
        while(m.find()) {
            list.add(Integer.valueOf(m.group()));
        }
        //System.out.println(list);
        for (int i = 1; i < list.size(); i++) {
            int prev = list.get(i-1);
            int cur = list.get(i);
            if (prev >= cur) {
                return false;
            }
        }
        return true;
    }
}
