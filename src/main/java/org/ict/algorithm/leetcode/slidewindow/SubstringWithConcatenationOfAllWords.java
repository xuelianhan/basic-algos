package org.ict.algorithm.leetcode.slidewindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a string s and an array of strings words.
 * All the strings of words are of the same length.
 *
 * A concatenated substring in s is a substring that contains all the strings of any permutation of words concatenated.
 * For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd",
 * and "efcdab" are all concatenated strings.
 * "acdbef" is not a concatenated substring because it is not the concatenation of any permutation of words.
 * Return the starting indices of all the concatenated substrings in s.
 * You can return the answer in any order.
 *
 *
 * Example 1:
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Since words.length == 2 and words[i].length == 3, the concatenated substring has to be of length 6.
 * The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
 * The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
 * The output order does not matter. Returning [9,0] is fine too.
 *
 * Example 2:
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * Output: []
 * Explanation: Since words.length == 4 and words[i].length == 4, the concatenated substring has to be of length 16.
 * There is no substring of length 16 is s that is equal to the concatenation of any permutation of words.
 * We return an empty array.
 *
 * Example 3:
 * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * Output: [6,9,12]
 * Explanation: Since words.length == 3 and words[i].length == 3, the concatenated substring has to be of length 9.
 * The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"] which is a permutation of words.
 * The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"] which is a permutation of words.
 * The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"] which is a permutation of words.
 *
 *
 * Constraints:
 * 1 <= s.length <= 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * s and words[i] consist of lowercase English letters.
 * @author sniper
 * @date 17 Jun 2023
 * LC30, Hard
 */
public class SubstringWithConcatenationOfAllWords {

    public List<Integer> findSubstringV1(String s, String[] words) {
        //todo
        return null;
    }

    /**
     * Understanding the following solution
     * Hash + Slide Window
     * --------------------------------------------------------
     * class Solution:
     *     def findSubstring(self, s: str, words: List[str]) -> List[int]:
     *         if len(s) == 0 or words == []:
     *             return []
     *
     *         k = len(words)
     *         n = len(words[0])
     *         res = []
     *         freq = collections.Counter(words)
     *
     *         for i in range(0, len(s) - k * n + 1):
     *             seen = collections.defaultdict(int)
     *             j = 0
     *             while j < k:
     *                 word = s[i + j * n: i + j * n + n]
     *                 seen[word] += 1
     *                 if seen[word] > freq[word]:
     *                     break
     *                 j += 1
     *             if j == k:
     *                 res.append(i)
     *         return res
     * --------------------------------------------------------
     * class Solution {
     * public:
     *     vector<int> findSubstring(string s, vector<string>& words) {
     *         if (s.empty() || words.empty()) {
     *             return {};
     *         }
     *         const int k = words.size();
     *         const int n = words[0].length();
     *         vector<int> res;
     *         unordered_map<string, int> freq;
     *
     *         for (const string& word : words) {
     *             freq[word]++;
     *         }
     *
     *         for (int i = 0; i < s.length() - k * n + 1; i++) {
     *             unordered_map<string, int> seen;
     *             int j = 0;
     *             for (; j < k; j++) {
     *                 const string& word = s.substr(i + j * n, n);
     *                 seen[word]++;
     *                 if (seen[word] > freq[word]) {
     *                     break;
     *                 }
     *             }
     *             if (j == k) {
     *                 res.push_back(i);
     *             }
     *         }
     *
     *         return res;
     *     }
     * };
     * --------------------------------------------------------
     * e.g. s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
     * k:3, n:3, freq:[{"bar", 1}, {"foo", 1}, {"the", 1}]
     * i:0, the length of s is 24, so the index-i ends up with 24 - 9 = 15
     * i:0, j:0, j < k, i + j * n = 0, i + j * n + n = 3, word = s.substring(0, 3) = "bar", seen:[{"bar", 1}], j++
     *      j:1, j < k, i + j * n = 3, i + j * n + n = 6, word = s.substring(3, 6) = "foo", seen:[{"bar", 1}, {"foo", 1}], j++
     *      j:2, j < k, i + j * n = 6, i + j * n + n = 9, word = s.substring(6, 9) = "foo", seen:[{"bar", 1}, {"foo", 2}],
     *          see.get("foo") > freq.get("foo"), inner-for-loop break
     *          j:2, j != k
     * i:1, j:0, ......, not satisfy
     * i:2, j:0, ......, not satisfy
     * i:3, j:0, ......, not satisfy
     * i:4, j:0, ......, not satisfy
     * i:5, j:0, ......, not satisfy
     * i:6, j:0, j < k, i + j * n = 6,  i + j * n + n = 9,  word = s.substring(6, 9) = "foo", seen:[{"foo", 1}], j++
     *      j:1, j < k, i + j * n = 9,  i + j * n + n = 12, word = s.substring(9, 12) = "bar", seen:[{"foo", 1}, {"bar", 1}], j++
     *      j:2, j < k, i + j * n = 12, i + j * n + n = 15, word = s.substring(12, 15) = "the", seen:[{"foo", 1}, {"bar", 1}, {"the", 1}], j++
     *      j:3, j == k, i:6 is added to the res list
     * i:7, j:0, ......, not satisfy
     * i:8, j:0, ......, not satisfy
     * i:9, j:0, j < k, satisfy, 9 is added to the res list
     * .....
     * i:12, j:0, satisfy, 12 is added to the res list
     *
     * @author jianchao-li
     * @see <a href="https://leetcode.com/problems/substring-with-concatenation-of-all-words/solutions/13658/easy-two-map-solution-c-java"></a>
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        if (s.isEmpty() || words.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        int k = words.length;
        int n = words[0].length();

        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        /**
         * The slide window size is k*n, so while i == s.length() - k*n, the for-loop ends.
         */
        for (int i = 0; i <= s.length() - k * n; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            for (; j < k; j++) {
                String word = s.substring(i + j * n, i + j * n + n);
                seen.merge(word, 1, Integer::sum);
                if (seen.get(word) > freq.getOrDefault(word, 0)) {
                    break;
                }
            }
            if (j == k) {
                res.add(i);
            }
        }
        return res;
    }
}
