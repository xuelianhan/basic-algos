package org.ict.algorithm.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * Examples:
 * abcabcbb --> abc, length is 3
 * bbbbb ---> b, length is 1
 * pwwkew --> wke, length is 3
 *
 */
public class LengthOfLongestSubString {

    /**
     * Complexity Analysis
     * Time complexity: O(2n).In the worst case each character will be visited twice by i and j.
     * Space complexity: O(min(m, n))Same as the previous approach.
     * We need O(k)space for the sliding window, where kis the size of the Set. 
     * The size of the Set is upper bounded by the size of the string n and the size of the charset/alphabet m.
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringV1(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        Set<Character> charSet = new HashSet<Character>();
        int n = s.length();
        int result = 0, i = 0, j = 0;
        while (i < n && j < n) {
            char c = s.charAt(j);
            if(!charSet.contains(c)) {
                charSet.add(c);
                j++;
                result = Math.max(result, j - i);
            } else {
                charSet.remove(c);
                i++;
            }
        }
        return result;
    }
    
    /**
     * The above solution requires at most 2n steps. 
     * In fact, it could be optimized to require only n steps. 
     * Instead of using a set to tell if a character exists or not, 
     * we could define a mapping of the characters to its index. 
     * Then we can skip the characters immediately when we found a repeated character.
     * The reason is that if s[j] have a duplicate in the range [i,j) with index j' ​​, 
     * we don't need to increase i little by little. 
     * We can skip all the elements in the range [i, j'] and let i to be j'+1 directly.
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringV2(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        int n = s.length();
        int result = 0;
        //try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            char c = s.charAt(j);
            if (charMap.containsKey(c)) {
                //consider this case: aa, abcabc
                i = Math.max(charMap.get(c), i);
            } 
            result = Math.max(result,  j - i + 1);
            charMap.put(c, j + 1);
        }
        return result;
    }
    
    public static int lengthOfLongestSubstringV3(String s) {
        return 0;
    }
    
    
    public static void main(String[] args) {
        
    }
}
