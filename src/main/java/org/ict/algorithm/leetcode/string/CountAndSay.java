package org.ict.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 *
 * countAndSay(1) = "1"
 * countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1),
 * which is then converted into a different digit string.
 * To determine how you "say" a digit string,
 * split it into the minimal number of substrings such that each substring contains exactly one unique digit.
 * Then for each substring, say the number of digits, then say the digit.
 * Finally, concatenate every said digit.
 *
 * For example, the saying and conversion for digit string "3322251":
 *
 *
 * Given a positive integer n, return the nth term of the count-and-say sequence.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: "1"
 * Explanation: This is the base case.
 * Example 2:
 *
 * Input: n = 4
 * Output: "1211"
 * Explanation:
 * countAndSay(1) = "1"
 * countAndSay(2) = say "1" = one 1 = "11"
 * countAndSay(3) = say "11" = two 1's = "21"
 * countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 *
 *
 * Constraints:
 *
 * 1 <= n <= 30
 * @author sniper
 * @date 09 Jan, 2023
 * LC38
 */
public class CountAndSay {

    public static void main(String[] args) {
        CountAndSay instance = new CountAndSay();
        Map<Integer, String> result = instance.generate();
        //System.out.println(result);
    }

    /**
     * Understanding the Following Solution.
     * Time Cost 2ms
     * @param n
     * @return
     */
    public String countAndSayV3(int n) {
        if (n == 1) {
            return "1";
        }
        String prev = countAndSayV3(n - 1);
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < prev.length(); i++) {
            count++;
            if (i == prev.length() - 1 || prev.charAt(i) != prev.charAt(i + 1)) {
                sb.append(count);
                sb.append(prev.charAt(i));
                count = 0;
            }
        }
        return sb.toString();
    }

    /**
     * Time Cost 4ms
     * @param n
     * @return
     */
    public String countAndSayV2(int n) {
        String s = "1";
        if (n == 1) {
            return s;
        }
        Map<Integer, String> map = new HashMap<>();
        map.put(1, s);
        for (int i = 2; i <= 30; i++) {
            map.put(i, say(map.get(i - 1)));
            if (i == n) {
                break;
            }
        }
        return map.get(n);
    }


    /**
     * Time Cost 24ms
     * @param n
     * @return
     */
    public String countAndSayV1(int n) {
        if (n <= 0) {
            return "";
        }
        String res = "1";
        while (--n > 0) {
            String cur = "";
            for (int i = 0; i < res.length(); i++) {
                int count = 1;
                while ((i + 1) < res.length() && (res.charAt(i) == res.charAt(i + 1))) {
                    count++;
                    i++;
                }
                cur += String.valueOf(count) + res.charAt(i);
            }
            res = cur;
        }
        return res;
    }

    /**
     * Time Cost 22ms
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        Map<Integer, String> map = generate();
        return map.get(n);
    }

    private Map<Integer, String> generate() {
        Map<Integer, String> map = new HashMap<>();
        String s = "1";
        map.put(1, s);
        for (int i = 2; i <= 30; i++) {
            map.put(i, say(map.get(i - 1)));
        }
        return map;
    }



    private String say(String s) {
        StringBuilder sb = new StringBuilder();
        if (s.length() == 1) {
            sb.append("1");
            sb.append(s);
            return sb.toString();
        }
        char[] arr = s.toCharArray();
        int i = 0, cnt = 1;
        char cur = arr[0];
        while (i < arr.length) {
            int j = i + 1;
            while (j < arr.length) {
                if (cur == arr[j]) {
                    cnt++;
                    j++;
                } else {
                    break;
                }
            }
            sb.append(String.valueOf(cnt));
            sb.append(Character.toString(cur));
            if (j == arr.length) {
                break;
            }
            if (j < arr.length) {
                i = j;
                cur = arr[j];
                cnt = 1;
            }
        }
        return sb.toString();
    }


}
