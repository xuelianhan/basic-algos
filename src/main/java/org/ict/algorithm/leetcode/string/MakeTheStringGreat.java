package org.ict.algorithm.leetcode.string;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Given a string s of lower and upper case English letters.
 *
 * A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
 *
 * 0 <= i <= s.length - 2
 * s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
 * To make the string good, you can choose two adjacent characters that make the string bad and remove them.
 * You can keep doing this until the string becomes good.
 *
 * Return the string after making it good.
 * The answer is guaranteed to be unique under the given constraints.
 *
 * Notice that an empty string is also good.
 *
 *
 * Example 1:
 * Input: s = "leEeetcode"
 * Output: "leetcode"
 * Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".
 *
 * Example 2:
 * Input: s = "abBAcC"
 * Output: ""
 * Explanation: We have many possible scenarios, and all lead to the same answer. For example:
 * "abBAcC" --> "aAcC" --> "cC" --> ""
 * "abBAcC" --> "abBA" --> "aA" --> ""
 *
 * Example 3:
 * Input: s = "s"
 * Output: "s"
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s contains only lower and upper case English letters.
 *
 * Check the ASCII value of each character for the following conditions:
 * If the ASCII value lies in the range of [65, 90], then it is an uppercase letter.
 * If the ASCII value lies in the range of [97, 122], then it is a lowercase letter.
 * If the ASCII value lies in the range of [48, 57], then it is a number.
 * @author sniper
 * @date 13 Aug, 2022
 * LC1544
 */
public class MakeTheStringGreat {

    public static void main(String[] args) {
        String s = "hHcOzoC";
        //String s = "abBAcC";
        //String s = "leEeetcode";
        String result = makeGood(s);
        System.out.println(result);
    }

    public String makeGoodV3(String s) {
        if (null == s || s.length() == 0) {
            return s;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            /**
             * a ascii code is 96, A ascii code is 64, the difference is 32
             */
            if (!stack.isEmpty() && Math.abs(ch - stack.peek()) == 32) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        char[] arr = new char[stack.size()];
        int idx = stack.size() - 1;
        while (!stack.isEmpty()) {
            arr[idx] = stack.pop();
            idx--;
        }
        return new String(arr);
    }

    public String makeGoodV2(String s) {
        Stack<Character> stack = new Stack();
        for (int i = 0;i < s.length(); i++){
            if(!stack.isEmpty() && Math.abs(stack.peek() - s.charAt(i)) == 32) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        char res[] = new char[stack.size()];
        int index = stack.size()-1;
        while(!stack.isEmpty()){
            res[index--] = stack.pop();
        }
        return new String(res);
    }

    /**
     * It should be noted that the difference between the any lowercase and uppercase alphabet is 32.
     * Example - ASCII value of a is 97 and A is 65 , 97-65 = 32
     * Using same trick, we can delete adjacent characters with absolute difference of 32.
     * @param s
     * @return
     */
    public static String makeGood(String s) {
        if (s.length() == 1) {
            return s;
        }
        String result = "";
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            /**
             * The following if-condition is very important
             */
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
                continue;
            }
            char top = stack.peek();
            char ch = s.charAt(i);
            int diff = Math.abs(ch - top);
            if (diff == 32) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        if (stack.isEmpty()) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
