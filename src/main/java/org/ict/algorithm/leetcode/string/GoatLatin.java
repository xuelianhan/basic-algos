package org.ict.algorithm.leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given a string sentence that consist of words separated by spaces.
 * Each word consists of lowercase and uppercase letters only.
 *
 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)
 * The rules of Goat Latin are as follows:
 *
 * If a word begins with a vowel ('a', 'e', 'i', 'o', or 'u'), append "ma" to the end of the word.
 * For example, the word "apple" becomes "applema".
 *
 * If a word begins with a consonant (i.e., not a vowel),
 * remove the first letter and append it to the end, then add "ma".
 * For example, the word "goat" becomes "oatgma".
 *
 * Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 * For example, the first word gets "a" added to the end,
 * the second word gets "aa" added to the end, and so on.
 *
 * Return the final sentence representing the conversion from sentence to Goat Latin.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence = "I speak Goat Latin"
 * Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 * Example 2:
 *
 * Input: sentence = "The quick brown fox jumped over the lazy dog"
 * Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 *
 *
 * Constraints:
 *
 * 1 <= sentence.length <= 150
 * sentence consists of English letters and spaces.
 * sentence has no leading or trailing spaces.
 * All the words in sentence are separated by a single space.
 * @date 2022/8/1
 * lc824
 */
public class GoatLatin {

    public static void main(String[] args) {
        String sentence = "The quick brown fox jumped over the lazy dog";
        String result = toGoatLatin(sentence);
        System.out.println(result);
    }

    public static String toGoatLatin(String sentence) {
        String[] arr = sentence.split("\\s+");
        Set<String> vowel = new HashSet<>();
        vowel.add("a");
        vowel.add("e");
        vowel.add("i");
        vowel.add("o");
        vowel.add("u");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            String item = arr[i];
            String s = item.substring(0, 1).toLowerCase();
            if (vowel.contains(s)) {
                sb.append(item + "ma");
            } else {
                sb.append(item.substring(1, item.length()) + item.substring(0, 1)+  "ma");
            }
            for(int j = 0; j < (i + 1); j++) {
                sb.append("a");
            }
            /**
             * last index not need to append space, so skip it.
             */
            if (i < arr.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
