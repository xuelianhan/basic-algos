package org.ict.algorithm.leetcode.string;

/**
 * You are given a string text of words that are placed among some number of spaces.
 * Each word consists of one or more lowercase English letters and are separated by at least one space.
 * It's guaranteed that text contains at least one word.
 *
 * Rearrange the spaces so that there is an equal number of spaces
 * between every pair of adjacent words and that number is maximized.
 * If you cannot redistribute all the spaces equally,
 * place the extra spaces at the end,
 * meaning the returned string should be the same length as text.
 *
 * Return the string after rearranging the spaces.
 *
 *
 *
 * Example 1:
 *
 * Input: text = "  this   is  a sentence "
 * Output: "this   is   a   sentence"
 * Explanation: There are a total of 9 spaces and 4 words.
 * We can evenly divide the 9 spaces between the words: 9 / (4-1) = 3 spaces.
 * Example 2:
 *
 * Input: text = " practice   makes   perfect"
 * Output: "practice   makes   perfect "
 * Explanation: There are a total of 7 spaces and 3 words. 7 / (3-1) = 3 spaces plus 1 extra space.
 * We place this extra space at the end of the string.
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 100
 * text consists of lowercase English letters and ' '.
 * text contains at least one word.
 * @author sniper
 * @date 2022/8/15
 * LC1592
 */
public class RearrangeSpacesBetweenWords {

    public static void main(String[] args) {
        //String text = "  this   is  a sentence ";
        //String text = " practice   makes   perfect";
        String text = "  hello";
        String result = reorderSpaces(text);
        System.out.println(result);
    }

    public static String reorderSpaces(String text) {
        /**
         * trim is very important here
         */
        String[] words = text.trim().split("\\s+");
        int wordLength = 0;
        for (String word : words) {
            wordLength += word.length();
        }
        int spaceLength = text.length() - wordLength;
        /**
         *  text has (words.length - 1) split points
         *  and each point has (spaceLength / (words.length - 1)) spaces.
         *  The following calculation of spacesOfEachPoint and remainder are easy to make mistakes.
         */
        int spacesOfEachPoint = (words.length == 1 ? 0 : spaceLength / (words.length - 1));
        int remainder = (words.length == 1 ? spaceLength : spaceLength % (words.length - 1));
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for (String word : words) {
            sb.append(word);
            int i = spacesOfEachPoint;
            while (i > 0 && j < words.length - 1) {
                sb.append(" ");
                i--;
            }
            j++;
        }
        if (remainder > 0) {
            int i = remainder;
            while (i > 0) {
                sb.append(" ");
                i--;
            }
        }
        return sb.toString();
    }
}
