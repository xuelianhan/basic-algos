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
     * 
     * ***********start***********
     * j  i  c  result   map
     * 0  0  a   0       (,)
     * ***********loop************
     * 0  0  a   1       (a, 1)
     * 1  0  b   2       (a, 1),(b, 2)
     * 2  0  c   3       (a, 1),(b, 2),(c, 3)
     * 3  1  a   3       (a, 4),(b, 2),(c, 3)
     * 4  2  b   3       (a, 4),(b, 5),(c, 3)
     * 5  3  c   3       (a, 4),(b, 5),(c, 6)
     * ***********end*************
     * 
     */
    public static int lengthOfLongestSubstringV2(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        int n = s.length();
        int result = 0;
        //try to extend the range [i, j]
        //draw a graph to show the process will be helpful to understand this method
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
    
    /**
     * The previouse implements all have no assumption on the charset of the string s.
     * If we know that the charset is rather small, we can replace the Map with an 
     * Integer array as direct access table.
     * Commonly used tables are:
     * int[26] for Letters 'a'~'z' or 'A'~'Z'
     * int[128] for ASCII
     * int[256] for Extended ASCII
     * Complexity Analysis
     * Time complexity:O(n).Index j will iterate n times.
     * Space complexity:
     * HashTable:O(min(m,n)).Same as the previous approach.
     * Table:O(m), m is the size of the charset
     * 
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringV3(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] index = new int[128];
        int result = 0;
        //try to extend range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            char c = s.charAt(j);
            i = Math.max(index[c], i);
            result = Math.max(result, j - i + 1);
            index[c] = j + 1;
        }
        return result;
    }
    
    
    public static void main(String[] args) {
        
    }
}
