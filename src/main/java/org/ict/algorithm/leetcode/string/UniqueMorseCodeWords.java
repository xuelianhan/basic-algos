package org.ict.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes,
 * as follows:
 *
 * 'a' maps to ".-",
 * 'b' maps to "-...",
 * 'c' maps to "-.-.", and so on.
 * For convenience, the full table for the 26 letters of the English alphabet is given below:
 *
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 * Given an array of strings words where each word can be written as a concatenation of the Morse code of each letter.
 *
 * For example, "cab" can be written as "-.-..--...", which is the concatenation of "-.-.", ".-", and "-...". We will call such a concatenation the transformation of a word.
 * Return the number of different transformations among all words we have.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["gin","zen","gig","msg"]
 * Output: 2
 * Explanation: The transformation of each word is:
 * "gin" -> "--...-."
 * "zen" -> "--...-."
 * "gig" -> "--...--."
 * "msg" -> "--...--."
 * There are 2 different transformations: "--...-." and "--...--.".
 * Example 2:
 *
 * Input: words = ["a"]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 12
 * words[i] consists of lowercase English letters.
 * @author sniper
 * @date 2022/5/7
 */
public class UniqueMorseCodeWords {
    public static void main(String[] args) {

    }

    /**
     * solution provided by lee215
     * @param words
     * @return
     */
    public int uniqueMorseRepresentationsV2(String[] words) {
        String[] d = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        HashSet<String> s = new HashSet<>();
        for (String w : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < w.length(); ++i) {
                sb.append(d[w.charAt(i) - 'a']);
            }
            s.add(sb.toString());
        }
        return s.size();
    }

    public int uniqueMorseRepresentations(String[] words) {
        if (words.length == 1) {
            return 1;
        }
        Map<Character, String> morseMap = initMorse();
        Set<String> morseSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for(String word : words) {
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                String morse = morseMap.get(c);
                sb.append(morse);
            }
            morseSet.add(sb.toString());
            sb.delete(0, sb.length());
        }
        return morseSet.size();
    }

    public Map<Character, String> initMorse() {
        String[] morseArr = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Map<Character, String> morseMap = new HashMap<>();
        for(int i = 0; i < morseArr.length; i++) {
            morseMap.put((char)('a' + i), morseArr[i]);
        }
        return morseMap;
    }
}
