package org.ict.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given an array of strings words, return the words that can be typed using letters of the alphabet
 * on only one row of American keyboard like the image below.
 *
 * In the American keyboard:
 *
 * the first row consists of the characters "qwertyuiop",
 * the second row consists of the characters "asdfghjkl", and
 * the third row consists of the characters "zxcvbnm".
 *
 * Example 1:
 *
 * Input: words = ["Hello","Alaska","Dad","Peace"]
 * Output: ["Alaska","Dad"]
 * Example 2:
 *
 * Input: words = ["omk"]
 * Output: []
 * Example 3:
 *
 * Input: words = ["adsdf","sfd"]
 * Output: ["adsdf","sfd"]
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 100
 * words[i] consists of English letters (both lowercase and uppercase).
 *
 *
 * @author sniper
 * @date 17 Feb, 2022
 * LC500
 */
public class KeyboardRow {

    public String[] findWords(String[] words) {
        Set<Character> sc1 = "qwertyuiop".chars().mapToObj(e->(char)e).collect(Collectors.toSet());
        Set<Character> sc2 = "asdfghjkl".chars().mapToObj(e->(char)e).collect(Collectors.toSet());
        Set<Character> sc3 = "zxcvbnm".chars().mapToObj(e->(char)e).collect(Collectors.toSet());
        List<String> list = new ArrayList<>();
        for (String s : words) {

        }
        if (list.size() == 0){
            return new String[0];
        }
        String[] arr = new String[list.size()];
        int i = 0;
        for(String s : list) {
            arr[i] = s;
        }
        return arr;
    }
}
