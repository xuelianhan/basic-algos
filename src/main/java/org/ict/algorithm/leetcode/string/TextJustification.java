package org.ict.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of strings words and a width maxWidth,
 * format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 * You should pack your words in a greedy approach;
 * that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 * Extra spaces between words should be distributed as evenly as possible.
 * If the number of spaces on a line does not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 * Note:
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 *
 * Example 1:
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 *
 * Example 2:
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be",
 * because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified because it contains only one word.
 *
 * Example 3:
 * Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a",
 * "computer.","Art","is","everything","else","we","do"], maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *
 *
 * Constraints:
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] consists of only English letters and symbols.
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 * @author sniper
 * @date 29 Apr, 2023
 * LC68, Hard, frequency=64
 */
public class TextJustification {


    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int j = i;
            /**
             * This len is the length sum of words have been accessed,
             * The legal number of spaces is (maxWidth - len), instead of (j - i)
             * because j may not satisfy condition:(len + words[j].length() + j - i) <= maxWidth
             * e.g. words:["a", "b", "c"], maxWidth = 3
             * i:0, assume that j:0, j:1 is matched, so j++, j increment from 1 to 2,
             * but the value:
             * (len + words[j].length() + j - i) = 2 + 1 + 2 - 0 = 5, it exceeds the maxWidth.
             * j - i = 2 - 0 = 2, but in fact, there are only 1 spaces matches the above condition.
             */
            int len = 0;
            while (j < words.length && len + words[j].length() + j - i <= maxWidth) {
                len += words[j].length();
                j++;
            }
            StringBuilder out = new StringBuilder();
            int spaces = maxWidth - len;
            for (int k = i; k < j; k++) {
                out.append(words[k]);
                if (spaces > 0) {
                    if (j == words.length) {
                        /**
                         * Now we at the last line.
                         */

                    } else {

                    }
                }
            }
            res.add(out.toString());
            i = j;
        }
        return res;
    }
}
