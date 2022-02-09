package org.ict.algorithm.leetcode.string;

/**
 * Given two strings ransomNote and magazine,
 * return true if ransomNote can be constructed from magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 *
 *
 *
 * Example 1:
 *
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * Example 2:
 *
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * Example 3:
 *
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= ransomNote.length, magazine.length <= 10^5
 * ransomNote and magazine consist of lowercase English letters.
 * @author sniper
 * @date 2022/2/9
 *
 * LC383
 */
public class RansomNote {

    public static void main(String[] args) {
        String ransomNote = "a";
        String magazine = "b";
        boolean result = canConstruct(ransomNote, magazine);
        System.out.println(result);
    }

    public boolean canConstructV2(String ransomNote, String magazine) {
        int[] arr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            arr[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if(--arr[ransomNote.charAt(i)-'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * The basic idea is to calculate the characters of frequency for the two
     * input strings
     * @param ransomNote
     * @param magazine
     * @return
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) {
            return false;
        }
        int len = 128;
        int[] m1 = new int[len];
        int[] m2 = new int[len];
        for(int i = 0; i < len; i++) {
            m1[i] = 0;
            m2[i] = 0;
        }
        int len1 = ransomNote.length();
        int len2 = magazine.length();
        int min = Math.min(len1, len2);
        int i = 0;
        for(;i < min; i++) {
            m1[ransomNote.charAt(i)]++;
            m2[magazine.charAt(i)]++;
        }
        while (i < len1) {
            m1[ransomNote.charAt(i)]++;
            i++;
        }
        while (i < len2) {
            m2[magazine.charAt(i)]++;
            i++;
        }
        for(int j = 0; j < len; j++) {
            if (m1[j] > m2[j]) {
                return false;
            }
        }
        return true;
    }
}
