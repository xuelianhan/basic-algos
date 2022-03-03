package org.ict.algorithm.leetcode.string;

/**
 * You are given a string title consisting of one or more words separated by a single space, where each word consists of English letters. Capitalize the string by changing the capitalization of each word such that:
 *
 * If the length of the word is 1 or 2 letters, change all letters to lowercase.
 * Otherwise, change the first letter to uppercase and the remaining letters to lowercase.
 * Return the capitalized title.
 *
 *
 *
 * Example 1:
 *
 * Input: title = "capiTalIze tHe titLe"
 * Output: "Capitalize The Title"
 * Explanation:
 * Since all the words have a length of at least 3, the first letter of each word is uppercase,
 * and the remaining letters are lowercase.
 * Example 2:
 *
 * Input: title = "First leTTeR of EACH Word"
 * Output: "First Letter of Each Word"
 * Explanation:
 * The word "of" has length 2, so it is all lowercase.
 * The remaining words have a length of at least 3, so the first letter of each remaining word is uppercase,
 * and the remaining letters are lowercase.
 * Example 3:
 *
 * Input: title = "i lOve leetcode"
 * Output: "i Love Leetcode"
 * Explanation:
 * The word "i" has length 1, so it is lowercase.
 * The remaining words have a length of at least 3, so the first letter of each remaining word is uppercase,
 * and the remaining letters are lowercase.
 *
 *
 * Constraints:
 *
 * 1 <= title.length <= 100
 * title consists of words separated by a single space without any leading or trailing spaces.
 * Each word consists of uppercase and lowercase English letters and is non-empty.
 * @author sniper
 * @date 04 Mar, 2022
 * LC2129
 */
public class CapitalizeTheTitle {

    public static void main(String[] args) {
        String title = "capiTalIze tHe titLe";
        String result = capitalizeTitle(title);
        System.out.println(result);

    }

    public static String capitalizeTitle(String title) {
        StringBuffer sb = new StringBuffer();
        String[] arr = title.split("\\s+");
        for (String s : arr) {
            if (s.length() <= 2) {
                sb.append(s.toLowerCase());
                sb.append(' ');
            } else {
                StringBuffer sub = new StringBuffer();
                for (int i = 0; i < s.length(); i++) {
                    Character c = null;
                    if (i == 0 && Character.isLowerCase(s.charAt(i))) {
                        c = Character.toUpperCase(s.charAt(i));
                    } else if (i > 0 && Character.isUpperCase(s.charAt(i))) {
                        c = Character.toLowerCase(s.charAt(i));
                    } else {
                        c = s.charAt(i);
                    }
                    sub.append(c);
                }
                sub.append(' ');
                sb.append(sub.toString());
            }
        }
        return sb.toString().substring(0, sb.length() - 1);
    }
}
