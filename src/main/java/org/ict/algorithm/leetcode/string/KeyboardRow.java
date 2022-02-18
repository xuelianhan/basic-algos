package org.ict.algorithm.leetcode.string;

import java.util.*;
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

    public static void main(String[] args) {
        String[] words = new String[]{"adsdf","sfd"};
        String[] result = findWords(words);
        System.out.println(Arrays.toString(result));
    }

    public static String[] findWords(String[] words) {
        Set<Character> sc1 = "qwertyuiopQWERTYUIOP".chars().mapToObj(e->(char)e).collect(Collectors.toSet());
        Set<Character> sc2 = "asdfghjklASDFGHJKL".chars().mapToObj(e->(char)e).collect(Collectors.toSet());
        Set<Character> sc3 = "zxcvbnmZXCVBNM".chars().mapToObj(e->(char)e).collect(Collectors.toSet());
        List<String> list = new ArrayList<>();
        for (String s : words) {
            char[] arr = s.toCharArray();
            /**
             * Using set to check s all in one line.
             */
            Set<Integer> set = new HashSet<>();
            for(int i = 0; i < arr.length; i++) {
                if (sc1.contains(arr[i])) {
                    set.add(1);
                } else if (sc2.contains(arr[i])) {
                    set.add(2);
                } else if (sc3.contains(arr[i])) {
                    set.add(3);
                }
                if (set.size() > 1) {
                    break;
                }
            }
            /**
             * if set size is 1, then all character is in one line
             */
            if (set.size() == 1) {
                list.add(s);
            }
        }
        return list.stream().toArray(String[]::new);
    }


}
