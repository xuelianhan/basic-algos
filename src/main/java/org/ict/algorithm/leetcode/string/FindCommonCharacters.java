package org.ict.algorithm.leetcode.string;

import java.util.*;

/**
 * Given a string array words,
 * return an array of all characters that show up in all strings within the words (including duplicates).
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["bella","label","roller"]
 * Output: ["e","l","l"]
 * Example 2:
 *
 * Input: words = ["cool","lock","cook"]
 * Output: ["c","o"]
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of lowercase English letters.
 * @author sniper
 * @date 2022/8/8
 */
public class FindCommonCharacters {

    public static void main(String[] args) {
        String[] words = {"bella","label","roller"};
        List<String> result = commonChars(words);
        System.out.println(result);
    }

    /**
     * This problem can be solved in following steps :-
     *
     * 1.Create two integer array of size 26 (Total number of characters in the English language),
     * One array (count array) will be used to store the frequency of each character in the S string of input array
     * and another array (main array) will be used to store the minimum frequency of each character
     * among all strings of input array. Initialize this array with Integer.MAX_VALUE.
     *
     * 2.Iterate through the given input array from start (index 0) to end (n-1, where n is the length of an array)
     * and for each iteration convert the string into a character array
     * and assign a frequency of each character to count array.
     *
     * 3.Iterate through the count array (prepared in the last step)
     * and for each iteration and assign a minimum value between count array
     * and main array to the main array.
     * @param words
     * @return
     */
    public static List<String> commonCharsV2(String[] words) {
        int[] array = new int[26];
        int[] main = new int[26];
        Arrays.fill(main,Integer.MAX_VALUE);

        char[] temp;
        for (String string : words) {
            temp = string.toCharArray();
            for (int i = 0; i < temp.length; i++) {
                array[temp[i] - 'a']++;
            }
            for (int i = 0; i < 26; i++){
                main[i] = Math.min(main[i], array[i]);
                /**
                 * reset array[i] to initialized zero for next loop usage.
                 */
                array[i] = 0;
            }
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            while ((main[i]--) > 0) {
                result.add((((char)('a' + i))+""));
            }
        }
        return result;
    }

    /**
     * We can first use a hashmap,
     * say finalCount to store the counts of characters ranging in [‘a’, ‘z’]
     * to store their minimum common presence in all the strings.
     * For example, if we find there are 2 ‘e’s in every string in the array,
     * we maintain its count as 2.
     * In order to achieve this,
     * we visit through every string in the array and minimize the finalCount for every character in [‘a’, ‘z’].
     * Finally, we push a character according to its final count in a result array/list
     * and return it.
     * @param words
     * @return
     */
    public static List<String> commonChars(String[] words) {
        int MAX_CNT = 100;
        Map<Character, Integer> totalMap = new HashMap<>(26);
        for (char c = 'a'; c <= 'z'; c++) {
            totalMap.put(c, MAX_CNT);
        }
        Map<Character, Integer> cntMap = new HashMap<>(26);
        for (int i = 0; i < words.length; i++) {
            cntMap.clear();
            /**
             * for each character of string, we count the frequency and store it.
             */
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                cntMap.put(c, cntMap.getOrDefault(c, 0) + 1);
            }
            /**
             * calculate the minimum frequency of each little character after accessed one string.
             */
            for (char c = 'a'; c <= 'z'; c++) {
                totalMap.put(c, Math.min(totalMap.get(c), cntMap.getOrDefault(c, 0)));
            }
        }
        /**
         * collect the characters by frequency count.
         */
        List<String> list = new ArrayList<>();
        totalMap.forEach((k, v) -> {
            for (int i = 0; i < v; i++) {
                list.add(Character.toString(k));
            }
        });
        return list;
    }
}