package org.ict.algorithm.leetcode.string;

import java.util.Arrays;

/**
 * You are given a string s of lowercase English letters and an array widths denoting how many pixels wide each lowercase English letter is.
 * Specifically, widths[0] is the width of 'a', widths[1] is the width of 'b', and so on.
 *
 * You are trying to write s across several lines, where each line is no longer than 100 pixels.
 * Starting at the beginning of s, write as many letters on the first line such that the total width does not exceed 100 pixels.
 * Then, from where you stopped in s, continue writing as many letters as you can on the second line.
 * Continue this process until you have written all of s.
 *
 * Return an array result of length 2 where:
 *
 * result[0] is the total number of lines.
 * result[1] is the width of the last line in pixels.
 *
 *
 * Example 1:
 *
 * Input: widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10], s = "abcdefghijklmnopqrstuvwxyz"
 * Output: [3,60]
 * Explanation: You can write s as follows:
 * abcdefghij  // 100 pixels wide
 * klmnopqrst  // 100 pixels wide
 * uvwxyz      // 60 pixels wide
 * There are a total of 3 lines, and the last line is 60 pixels wide.
 * Example 2:
 *
 * Input: widths = [4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10], s = "bbbcccdddaaa"
 * Output: [2,4]
 * Explanation: You can write s as follows:
 * bbbcccdddaa  // 98 pixels wide
 * a            // 4 pixels wide
 * There are a total of 2 lines, and the last line is 4 pixels wide.
 *
 *
 * Constraints:
 *
 * widths.length == 26
 * 2 <= widths[i] <= 10
 * 1 <= s.length <= 1000
 * s contains only lowercase English letters.
 * @author sniper
 * @date 12 May, 2022
 * LC806
 */
public class NumberOfLinesToWriteString {

    public static void main(String[] args) {
        //int[] width = {10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
        //String s = "abcdefghijklmnopqrstuvwxyz";
        int[] width = {3,4,10,4,8,7,3,3,4,9,8,2,9,6,2,8,4,9,9,10,2,4,9,10,8,2};
        String s = "mqblbtpvicqhbrejb";
        int[] result = numberOfLines(width, s);
        System.out.println(Arrays.toString(result));
    }

    /**
     * @author lee215
     * @param widths
     * @param s
     * @return
     */
    public static int[] numberOfLines(int[] widths, String s) {
        int lines = 1, sum = 0;
        for (char c : s.toCharArray()) {
            int width = widths[c - 'a'];
            lines = (sum + width) > 100 ? lines + 1 : lines;
            sum = (sum + width) > 100 ? width : (sum + width);
        }
        return new int[] {lines, sum};
    }

    public static int[] numberOfLinesV1(int[] widths, String s) {
        int[] result = new int[2];
        char[] arr = s.toCharArray();
        int lines = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int width = widths[arr[i] - 'a'];
            if (sum < 100){
                sum += width;
            }
            if (sum == 100) {
                lines++;
                sum = 0;
            }
            if (sum > 100) {
                lines++;
                sum = width;
            }
            System.out.println("i:" + i + ", lines:" + lines + ", width:" + width  + ", sum:" + sum);
        }
        if (lines == 1 && sum == 0) {
            result[0] = lines;
            result[1] = 100;
        } else {
            result[0] = ++lines;
            result[1] = sum;
        }

        return result;
    }
}