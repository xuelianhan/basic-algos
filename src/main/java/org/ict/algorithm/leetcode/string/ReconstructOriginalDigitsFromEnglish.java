package org.ict.algorithm.leetcode.string;

/**
 * Given a string s containing an out-of-order English representation of digits 0-9,
 * return the digits in ascending order.
 *
 * Example 1:
 * Input: s = "owoztneoer"
 * Output: "012"
 *
 * Example 2:
 * Input: s = "fviefuro"
 * Output: "45"
 *
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is one of the characters ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"].
 * s is guaranteed to be valid.
 * @author sniper
 * @date 26 Apr, 2023
 * LC423, Medium, frequency=69
 */
public class ReconstructOriginalDigitsFromEnglish {

    public static void main(String[] args) {
        String s = "owoztneoer";
        ReconstructOriginalDigitsFromEnglish instance = new ReconstructOriginalDigitsFromEnglish();
        String res = instance.originalDigits(s);
        System.out.println(res);
    }


    /**
     * Understanding the following solution
     *
     * "zero", <-- 'z'
     * "two",  <-- 'w'
     * "four", <-- 'u'
     * "six",  <-- 'x'
     * "eight",<-- 'g'
     *
     * "three", 'h', 3-8
     * "five",  'f', 5-4
     * "seven", 's', 7-6
     *
     * "one",  'o', 1-0-2-4
     * "nine", 'i', 9-5-6-8
     * @param s
     * @return
     */
    public String originalDigitsV1(String s) {
        int[] cnt = new int[10];
        for (char c : s.toCharArray()) {
            if (c == 'z') cnt[0]++;
            if (c == 'w') cnt[2]++;
            if (c == 'u') cnt[4]++;
            if (c == 'x') cnt[6]++;
            if (c == 'g') cnt[8]++;

            if (c == 'h') cnt[3]++;//3-8
            if (c == 'f') cnt[5]++;//5-4
            if (c == 's') cnt[7]++;//7-6

            if (c == 'o') cnt[1]++;// 1-0-2-4
            if (c == 'i') cnt[9]++;//9-5-6-8
        }
        cnt[3] -= cnt[8];
        cnt[5] -= cnt[4];
        cnt[7] -= cnt[6];

        cnt[1] -= (cnt[0] + cnt[2] + cnt[4]);
        cnt[9] -= (cnt[5] + cnt[6] + cnt[8]);

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < cnt[i]; k++) {
                res.append(i);
            }
        }
        return res.toString();
    }

    /**
     * "zero", <-- 'z'
     * "two",  <-- 'w'
     * "four", <-- 'u'
     * "six",  <-- 'x'
     * "eight",<-- 'g'
     *
     * "three",
     * "five",
     * "seven",
     *
     * "one",
     * "nine"
     * --------------------------------
     * class Solution:
     *     def originalDigits(self, s: str) -> str:
     *         counter = Counter(s)
     *         cnt = [0] * 10
     *
     *         cnt[0] = counter['z']
     *         cnt[2] = counter['w']
     *         cnt[4] = counter['u']
     *         cnt[6] = counter['x']
     *         cnt[8] = counter['g']
     *
     *         cnt[3] = counter['h'] - cnt[8]
     *         cnt[5] = counter['f'] - cnt[4]
     *         cnt[7] = counter['s'] - cnt[6]
     *
     *         cnt[1] = counter['o'] - cnt[0] - cnt[2] - cnt[4]
     *         cnt[9] = counter['i'] - cnt[5] - cnt[6] - cnt[8]
     *
     *         return ''.join(cnt[i] * str(i) for i in range(10))
     * @param s
     * @return
     */
    public String originalDigits(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            counter[c - 'a']++;
        }

        int[] cnt = new int[10];
        cnt[0] = counter['z' - 'a'];
        cnt[2] = counter['w' - 'a'];
        cnt[4] = counter['u' - 'a'];
        cnt[6] = counter['x' - 'a'];
        cnt[8] = counter['g' - 'a'];

        cnt[3] = counter['h' - 'a'] - cnt[8];
        cnt[5] = counter['f' - 'a'] - cnt[4];
        cnt[7] = counter['s' - 'a'] - cnt[6];

        cnt[1] = counter['o' - 'a'] - cnt[0] - cnt[2] - cnt[4];
        cnt[9] = counter['i' - 'a'] - cnt[5] - cnt[6] - cnt[8];

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < cnt[i]; k++) {
                res.append(i);
            }
        }
        return res.toString();
    }

}
