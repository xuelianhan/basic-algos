package org.ict.algorithm.leetcode.array;

import java.util.*;

/**
 * Given an array of strings strs, group the anagrams together.
 * You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Example 2:
 *
 * Input: strs = [""]
 * Output: [[""]]
 * Example 3:
 *
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 * @author sniper
 * @date 24 Nov, 2022
 * LC49, Medium, frequency=138
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        GroupAnagrams instance = new  GroupAnagrams();
        List<List<String>> result = instance.groupAnagrams(strs);
        System.out.println(result);
    }

    /**
     * Time Cost 13ms
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagramsV2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] cnt = new int[26];
            for (int i = 0; i < s.length(); i++) {
                cnt[s.charAt(i) - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (cnt[i] > 0) {
                    sb.append((char)'a' + i).append(cnt[i]);
                }
            }
            map.computeIfAbsent(sb.toString(), k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * Understanding the following solution
     * ----------------------------------------
     * class Solution {
     * public:
     *     vector<vector<string>> groupAnagrams(vector<string>& strs) {
     *         vector<vector<string>> res;
     *         unordered_map<string, int> map;
     *         for (string s : strs) {
     *             string t = s;
     *             sort(t.begin(), t.end());
     *             if (!map.count(t)) {
     *                 map[t] = res.size();
     *                 res.push_back({});
     *             }
     *             res[map[t]].push_back(s);
     *         }
     *         return res;
     *     }
     * };
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagramsV1(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            if (!map.containsKey(key)) {
                /**
                 * A trick here is we store the list size other than string itself,
                 * this operation can avoid copy string from map to res list.
                 */
                map.put(key, res.size());
                res.add(new ArrayList<>());
            }
            res.get(map.get(key)).add(str);
        }
        return res;
    }

    /**
     * Understanding the following solution
     * --------------------------------------
     * class Solution:
     *     def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
     *         d = defaultdict(list)
     *         for s in strs:
     *             k = ''.join(sorted(s))
     *             d[k].append(s)
     *         return list(d.values())
     * -----------------------------------------
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
