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
     * --------------------------------------
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
     * --------------------------------------
     * class Solution:
     *     def originalDigits(self, s: str) -> str:
     *         counter = [0] * 26
     *         for c in s:
     *             counter[ord(c) - ord('a')] += 1
     *
     *         cnt = [0] * 10
     *         cnt[0] = counter[ord('z') - ord('a')]
     *         cnt[2] = counter[ord('w') - ord('a')]
     *         cnt[4] = counter[ord('u') - ord('a')]
     *         cnt[6] = counter[ord('x') - ord('a')]
     *         cnt[8] = counter[ord('g') - ord('a')]
     *
     *         cnt[3] = counter[ord('h') - ord('a')] - cnt[8]
     *         cnt[5] = counter[ord('f') - ord('a')] - cnt[4]
     *         cnt[7] = counter[ord('s') - ord('a')] - cnt[6]
     *
     *         cnt[1] = counter[ord('o') - ord('a')] - cnt[0] - cnt[2] - cnt[4]
     *         cnt[9] = counter[ord('i') - ord('a')] - cnt[5] - cnt[6] - cnt[8]
     *
     *         res = []
     *         for i in range(10):
     *             for _ in range(cnt[i]):
     *                 res.append(str(i))
     *         return ''.join(res)
     * --------------------------------------
     * impl Solution {
     *     pub fn original_digits(s: String) -> String {
     *         let mut counter: Vec<i32> = vec![0; 26];
     *         for c in s.chars() {
     *             counter[(c as u8 - b'a') as usize] += 1;
     *         }
     *
     *         let mut cnt: Vec<i32> = vec![0; 10];
     *         cnt[0] = counter[(b'z' - b'a') as usize];
     *         cnt[2] = counter[(b'w' - b'a') as usize];
     *         cnt[4] = counter[(b'u' - b'a') as usize];
     *         cnt[6] = counter[(b'x' - b'a') as usize];
     *         cnt[8] = counter[(b'g' - b'a') as usize];
     *
     *         cnt[3] = counter[(b'h' - b'a') as usize] - cnt[8];
     *         cnt[5] = counter[(b'f' - b'a') as usize] - cnt[4];
     *         cnt[7] = counter[(b's' - b'a') as usize] - cnt[6];
     *
     *         cnt[1] = counter[(b'o' - b'a') as usize] - cnt[0] - cnt[2] - cnt[4];
     *         cnt[9] = counter[(b'i' - b'a') as usize] - cnt[5] - cnt[6] - cnt[8];
     *
     *         let mut res = String::new();
     *         for i in 0..10 {
     *             for _ in 0..cnt[i] {
     *                 res.push((i as u8 + b'0') as char);
     *             }
     *         }
     *         res
     *     }
     * }
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
