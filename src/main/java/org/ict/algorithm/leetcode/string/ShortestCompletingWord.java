package org.ict.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string licensePlate and an array of strings words, find the shortest completing word in words.
 *
 * A completing word is a word that contains all the letters in licensePlate.
 * Ignore numbers and spaces in licensePlate, and treat letters as case insensitive.
 * If a letter appears more than once in licensePlate,
 * then it must appear in the word the same number of times or more.
 *
 * For example, if licensePlate = "aBc 12c",
 * then it contains letters 'a', 'b' (ignoring case), and 'c' twice.
 * Possible completing words are "abccdef", "caaacab", and "cbca".
 *
 * Return the shortest completing word in words.
 * It is guaranteed an answer exists.
 * If there are multiple shortest completing words,
 * return the first one that occurs in words.
 *
 *
 *
 * Example 1:
 *
 * Input: licensePlate = "1s3 PSt", words = ["step","steps","stripe","stepple"]
 * Output: "steps"
 * Explanation: licensePlate contains letters 's', 'p', 's' (ignoring case), and 't'.
 * "step" contains 't' and 'p', but only contains 1 's'.
 * "steps" contains 't', 'p', and both 's' characters.
 * "stripe" is missing an 's'.
 * "stepple" is missing an 's'.
 * Since "steps" is the only word containing all the letters, that is the answer.
 * Example 2:
 *
 * Input: licensePlate = "1s3 456", words = ["looks","pest","stew","show"]
 * Output: "pest"
 * Explanation: licensePlate only contains the letter 's'.
 * All the words contain 's', but among these "pest", "stew", and "show" are shortest.
 * The answer is "pest" because it is the word that appears earliest of the 3.
 *
 *
 * Constraints:
 *
 * 1 <= licensePlate.length <= 7
 * licensePlate contains digits, letters (uppercase or lowercase), or space ' '.
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 15
 * words[i] consists of lower case English letters.
 *
 * @author sniper
 * @date 2022/5/7
 * LC748
 */
public class ShortestCompletingWord {

    /**
     * "GrC8950"
     * ["measure","other","every","base","according","level","meeting","none","marriage","rest"]
     * @param args
     */
    public static void main(String[] args) {
        String licensePlate = "GrC8950";
        String[] words = {"measure","other","every","base","according","level","meeting","none","marriage","rest"};
        String res = shortestCompletingWord(licensePlate, words);
        System.out.println(res);
    }

    public static String shortestCompletingWord(String licensePlate, String[] words) {
        String res = null;
        Map<Character, Integer> plateFrequency = count(licensePlate);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Map<Character, Integer> wordFrequency = count(word);
            boolean matched = true;
            for (Character fc : plateFrequency.keySet()) {
                Integer pCnt = plateFrequency.get(fc);
                Integer wCnt = wordFrequency.get(fc);
                /**
                 * The following condition need to be concerned carefully
                 */
                if (wCnt != null && pCnt.compareTo(wCnt) <= 0) {
                    continue;
                } else {
                    matched = false;
                    break;
                }
            }
            if (matched) {
                System.out.println("matched" + word);
                if (res == null) {
                    res = word;
                } else {
                    if (word.length() < res.length()) {
                        res = word;
                    }
                }
            }
        }
        return res;
    }

    private static Map<Character, Integer> count(String s) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : s.toCharArray())  {
            if (Character.isLetter(c)) {
                Character lowerCaseC = Character.toLowerCase(c);
                frequency.put(lowerCaseC, frequency.getOrDefault(lowerCaseC,0) + 1);
            }
        }
        return frequency;
    }
}
