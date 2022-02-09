package org.ict.algorithm.leetcode.string;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character,
 * but a character may map to itself.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 10^4
 * t.length == s.length
 * s and t consist of any valid ascii character.
 * LC205
 */
public class IsomorphicString {

    public static void main(String[] args) {
        String s = "title";
        String t = "paper";
        System.out.println(isIsomorphicV2(s, t));
    }

    /**
     * The idea is that we need to map a char to another one,
     * for example, "egg" and "add",
     * we need to construct the mapping 'e' -> 'a' and 'g' -> 'd'.
     * Instead of directly mapping 'e' to 'a',
     * another way is to mark them with same value,
     * for example, 'e' -> 1, 'a'-> 1, and 'g' -> 2, 'd' -> 2,
     * this works same.
     *
     * So we use two arrays here m1 and m2,
     * initialized space is 256 (Since the whole ASCII size is 256, 128 also works here).
     * Traverse the character of both s and t on the same position,
     * if their mapping values in m1 and m2 are different,
     * means they are not mapping correctly, return false;
     * else we construct the mapping, since m1 and m2 are both initialized as 0,
     * we want to use a new value when i == 0,
     * so i + 1 works here.
     * @return
     */
    public static boolean isIsomorphicV2(String s, String t) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        for(int i = 0; i < 256; i++) {
            m1[i] = 0;
            m2[i] = 0;
        }
        for(int i = 0; i < s.length(); ++i) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)])  {
                return false;
            }
            /**
             * Due to initialized with zero, so the following use plus one,
             * You can also use plus 2, 3, ..., or other numbers not equals zero.
             */
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }
        System.out.println(Arrays.toString(m1));
        System.out.println(Arrays.toString(m2));
        return true;
    }


    /**
     * Use hash map
     * @param s
     * @param t
     * @return
     */
    public static boolean isIsomorphic(String s, String t) {
        if (s.equalsIgnoreCase(t)) {
            return true;
        }
        Map<Character, Character> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if (map.get(cs) == null) {
                if (map.values().contains(ct)) {
                    return false;
                }
                map.put(cs, ct);
            } else {
                char v = map.get(cs);
                if (v != ct) {
                    return false;
                }
            }
        }
        return true;
    }
}
