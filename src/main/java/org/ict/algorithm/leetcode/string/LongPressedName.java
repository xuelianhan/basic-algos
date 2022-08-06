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
        String name = "alex", typed = "aaleex";
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

    public static boolean isLongPressedName(String name, String typed) {
        if (name.equals(typed)) {
            return true;
        }
        if (name.length() > typed.length()) {
            return false;
        }
        /**
         * passed cases:
         * name = "saeed", typed = "ssaaedd"
         * name = "alex", typed = "aaleex"
         *
         * failed cases:
         * name = "leelee", typed = "lleeelee", expected true
         *
         */
        int i = 0;
        int j = 0;
        while( i < name.length()) {
            int crossOutCnt = 0;
            while (j < typed.length() && name.charAt(i) == typed.charAt(j)) {
                j++;
                crossOutCnt++;
            }
            System.out.println("i:" + i + ", j:" + j + ", crossOutCnt:" + crossOutCnt);
            if (crossOutCnt == 0) {
                return false;
            }
            i++;
        }
        return (i == name.length());
    }
}
