package org.ict.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A pangram is a sentence where every letter of the English alphabet appears at least once.
 *
 * Given a string sentence containing only lowercase English letters,
 * return true if sentence is a pangram, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
 * Output: true
 * Explanation: sentence contains at least one of every letter of the English alphabet.
 * Example 2:
 *
 * Input: sentence = "leetcode"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= sentence.length <= 1000
 * sentence consists of lowercase English letters.
 * @author sniper
 * @date 14 Aug, 2022
 * LC1832
 */
public class CheckSentenceIsPangram {

    /**
     * Solution provides by lee215
     * @param sentence
     * @return
     */
    public boolean checkIfPangramV2(String sentence) {
        Set<Character> s = new HashSet<>();
        for (int i = 0; i < sentence.length(); ++i) {
            s.add(sentence.charAt(i));
        }
        return s.size() == 26;
    }

    public boolean checkIfPangram(String sentence) {
        char[] alpha = new char[26];
        Arrays.fill(alpha, '0');
        for (char c : sentence.toCharArray()) {
            alpha[c - 'a']++;
        }
        for (char c = 'a'; c <= 'z'; c++) {
            if (alpha[c - 'a'] == '0') {
                return false;
            }
        }
        return true;
    }
}
