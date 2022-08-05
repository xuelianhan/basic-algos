package org.ict.algorithm.leetcode.string;

/**
 * Your friend is typing his name into a keyboard.
 * Sometimes, when typing a character c,
 * the key might get long pressed,
 * and the character will be typed 1 or more times.
 *
 * You examine the typed characters of the keyboard.
 * Return True if it is possible that it was your friends name,
 * with some characters (possibly none) being long pressed.
 *
 *
 *
 * Example 1:
 *
 * Input: name = "alex", typed = "aaleex"
 * Output: true
 * Explanation: 'a' and 'e' in 'alex' were long pressed.
 * Example 2:
 *
 * Input: name = "saeed", typed = "ssaaedd"
 * Output: false
 * Explanation: 'e' must have been pressed twice,
 * but it was not in the typed output.
 *
 *
 * Constraints:
 *
 * 1 <= name.length, typed.length <= 1000
 * name and typed consist of only lowercase English letters.
 * @author sniper
 * @date 2022/8/4
 * lc925
 */
public class LongPressedName {

    public static void main(String[] args) {
        //String name = "saeed", typed = "ssaaedd";
        //String name = "alex", typed = "aaleex";
        //String name = "leelee", typed = "lleeelee";
        //String name = "alex", typed = "aaleexa";
        String name = "alex", typed = "aaleexeex";
        //String name = "alex", typed = "aaleelx";
        boolean result = isLongPressedName(name, typed);
        System.out.println(result);
    }

    /**
     * Two Pointers solution provided by lee215
     * @param name
     * @param typed
     * @return
     */
    public static boolean isLongPressedNameV2(String name, String typed) {
        int i = 0, m = name.length(), n = typed.length();
        for (int j = 0; j < n; ++j) {
            if (i < m && name.charAt(i) == typed.charAt(j)) {
                ++i;
            } else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1)) {
                return false;
            }
        }
        return i == m;
    }

    /**
     * passed cases:
     * name = "saeed", typed = "ssaaedd", expected false
     * name = "alex", typed = "aaleex", expected true
     * name = "leelee", typed = "lleeelee", expected true
     * name = "alex", typed = "aaleexa", expected false
     * name = "alex", typed = "aaleexeex", expected false
     * name = "alex", typed = "aaleelx", expected false
     */
    public static boolean isLongPressedName(String name, String typed) {
        if (name.equals(typed)) {
            return true;
        }
        if (name.length() > typed.length()) {
            return false;
        }
        int n = 0;
        int t = 0;
        int lenName = name.length();
        int lenTyped = typed.length();
        while(n < lenName && t < lenTyped) {
            char c = name.charAt(n);
            if(c != typed.charAt(t))  {
                return false;
            }
            int countInName = 0;
            int countInTyped = 0;
            while(n < lenName && name.charAt(n) == c){
                n++;
                countInName++;
            }
            while(t < lenTyped && typed.charAt(t) == c){
                t++;
                countInTyped++;
            }
            if(countInName > countInTyped) {
                return false;
            }
        }
        if(n == lenName && t== lenTyped) {
            return true;
        }
        return false;
    }
}
