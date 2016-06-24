package org.ict.algorithm.strings;

import java.util.Arrays;
/**
 *
 * 1.3. Given two strings, write ad method to decide if one is a permutation of the other.
 *
 */
public class StringPermutationCheckTest {
    
    public static String sort(String s) {
        char[] array = s.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }
      
    public static boolean permutationV1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        return sort(s).equals(sort(t));
    }

    public static boolean permutationV2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] letters = new int[128];
        for (int i = 0; i < s.length(); i++) {
            letters[s.charAt(i)]++; 
        }

        for (int j = 0; j < t.length();j++) {
            letters[t.charAt(j)]--;
            if (letters[t.charAt(j)] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("abc with cba:" + permutationV1("abc", "cba"));
        System.out.println("abc with cbd:" + permutationV1("abc", "cbd"));
        System.out.println("abc with cb:" + permutationV1("abc", "cb"));
        String[][] pairs = {{"ab", "ba"}, {"apple", "elppad"}, {"qingqing", "gniqgniq"}, {"ab", "ab"}};
        for (String[] pair : pairs) {
            System.out.println(pair[0] + " with " + pair[1] +":" + permutationV2(pair[0], pair[1]));
        }
    }

}
