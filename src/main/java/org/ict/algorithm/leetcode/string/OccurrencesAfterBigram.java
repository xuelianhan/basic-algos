package org.ict.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two strings first and second,
 * consider occurrences in some text of the form "first second third",
 * where second comes immediately after first, and third comes immediately after second.
 *
 * Return an array of all the words third for each occurrence of "first second third".
 *
 *
 *
 * Example 1:
 *
 * Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
 * Output: ["girl","student"]
 * Example 2:
 *
 * Input: text = "we will we will rock you", first = "we", second = "will"
 * Output: ["we","rock"]
 *
 * Constraints:
 *
 * 1 <= text.length <= 1000
 * text consists of lowercase English letters and spaces.
 * All the words in text a separated by a single space.
 * 1 <= first.length, second.length <= 10
 * first and second consist of lowercase English letters.
 *
 * @author sniper
 * @date 2022/8/10
 * LC1078
 */
public class OccurrencesAfterBigram {

    public static void main(String[] args) {
        //String text = " alice is a good girl she is a good student ", first = "a", second = "good";
        String text = "we will we will rock you", first = "we", second = "will";
        String[] result = findOcurrences(text, first, second);
        System.out.println(Arrays.toString(result));
    }

    public static String[] findOcurrences(String text, String first, String second) {
        List<String> result = new ArrayList<>();
        String[] arr = text.split("\\s");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                continue;
            }
            if (i >= 2 && arr[i-2].hashCode() == first.hashCode()
                       && arr[i-1].hashCode() == second.hashCode()
                       && arr[i] != null) {
                result.add(arr[i]);
            }
        }
        return result.toArray(new String[result.size()]);
    }
}
