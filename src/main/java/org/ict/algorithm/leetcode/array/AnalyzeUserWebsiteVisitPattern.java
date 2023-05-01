package org.ict.algorithm.leetcode.array;

import java.util.*;

/**
 * Description
 * You are given two string arrays username and website and an integer array timestamp.
 * All the given arrays are of the same length and the tuple [username[i], website[i], timestamp[i]]
 * indicates that the user username[i] visited the website[i] at time timestamp[i].
 * A pattern is a list of three websites (not necessarily distinct).
 *
 * For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"], and ["luffy", "luffy", "luffy"] are all patterns.
 * The score of a pattern is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.
 *
 *
 * For example, if the pattern is ["home", "away", "love"],
 * the score is the number of users x such that x visited "home" then visited "away" and visited "love" after that.
 *
 * Similarly, if the pattern is ["leetcode", "love", "leetcode"],
 * the score is the number of users x such that x visited "leetcode" then visited "love" and visited "leetcode" one more time after that.
 * Also, if the pattern is ["luffy", "luffy", "luffy"],
 * the score is the number of users x such that x visited "luffy" three different times at different timestamps.
 *
 * Return the pattern with the largest score.
 * If there is more than one pattern with the same largest score, return the lexicographically smallest such pattern.
 *
 * Example 1:
 * Input:
 * username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"],
 * timestamp = [1,2,3,4,5,6,7,8,9,10],
 * website = ["home","about","career","home","cart","maps","home","home","about","career"]
 *
 * Output: ["home","about","career"]
 *
 * Explanation: The tuples in this example are:
 * ["joe","home",1],["joe","about",2],["joe","career",3],["james","home",4],
 * ["james","cart",5],["james","maps",6],["james","home",7],["mary","home",8],
 * ["mary","about",9], and ["mary","career",10].
 * The pattern ("home", "about", "career") has score 2 (joe and mary).
 * The pattern ("home", "cart", "maps") has score 1 (james).
 * The pattern ("home", "cart", "home") has score 1 (james).
 * The pattern ("home", "maps", "home") has score 1 (james).
 * The pattern ("cart", "maps", "home") has score 1 (james).
 * The pattern ("home", "home", "home") has score 0 (no user visited home 3 times).
 *
 * Example 2:
 * Input: username = ["ua","ua","ua","ub","ub","ub"],
 * timestamp = [1,2,3,4,5,6], website = ["a","b","a","a","b","c"]
 * Output: ["a","b","a"]
 *
 * Constraints:
 * 3 <= username.length <= 50
 * 1 <= username[i].length <= 10
 * timestamp.length == username.length
 * 1 <= timestamp[i] <= 109
 * website.length == username.length
 * 1 <= website[i].length <= 10
 * username[i] and website[i] consist of lowercase English letters.
 * It is guaranteed that there is at least one user who visited at least three websites.
 * All the tuples [username[i], timestamp[i], website[i]] are unique.
 *
 * @author sniper
 * @date 30 Apr, 2023
 * LC1152, Medium, frequency=56
 * Amazon, Spotify
 */
public class AnalyzeUserWebsiteVisitPattern {

    public static void main(String[] args) {
        String[] username = {"joe","joe","joe","james","james","james","james","mary","mary","mary"};
        int[] timestamp = {1,2,3,4,5,6,7,8,9,10};
        String[] website = {"home","about","career","home","cart","maps","home","home","about","career"};

        AnalyzeUserWebsiteVisitPattern instance = new AnalyzeUserWebsiteVisitPattern();
        List<String> res = instance.mostVisitedPattern(username, timestamp, website);
        System.out.println(res);
    }

    /**
     * e.g.
     * path: a-->b-->c, the number of users access this path is 100
     * path: b-->c-->d, the number of users access this path is 99
     * path: a-->c-->d, the number of users access this path is
     *
     * 1.Group by username using HashMap.
     * 2.Iterate paths for each username, and count the path.
     * 3.Sorting and return.
     * -----------------------
     * class Solution:
     *     def mostVisitedPattern(self, username: List[str], timestamp: List[int], website: List[str]) -> List[str]:
     *         d = defaultdict(list)
     *         for user, _, site in sorted(zip(username, timestamp, website), key=lambda x: x[1]):
     *             d[user].append(site)
     *
     *         cnt = Counter()
     *         for sites in d.values():
     *             m = len(sites)
     *             s = set()
     *             if m > 2:
     *                 for i in range(m - 2):
     *                     for j in range(i + 1, m - 1):
     *                         for k in range(j + 1, m):
     *                             s.add((sites[i], sites[j], sites[k]))
     *             for t in s:
     *                 cnt[t] += 1
     *         return sorted(cnt.items(), key=lambda x: (-x[1], x[0]))[0][0]
     *
     * @param username
     * @param timestamp
     * @param website
     * @return
     */
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int n = username.length;
        Map<String, List<Node>> userNodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String user = username[i];
            int ts = timestamp[i];
            String site = website[i];
            userNodes.computeIfAbsent(user, a -> new ArrayList<>()).add(new Node(user, ts, site));
        }

        Map<String, Integer> counter = new HashMap<>();
        for (List<Node> nodeList : userNodes.values()) {
            int m = nodeList.size();
            Set<String> pathSet = new HashSet<>();
            if (m > 2) {
                Collections.sort(nodeList, (a, b) -> a.ts - b.ts);
                for (int i = 0; i < m - 2; i++) {
                    for (int j = i + 1; j < m - 1; j++) {
                        for (int k = j + 1; k < m; k++) {
                            String path = nodeList.get(i).website + "," + nodeList.get(j).website + "," + nodeList.get(k).website;
                            pathSet.add(path);
                        }
                    }
                }
            }

            for (String path : pathSet) {
                counter.put(path, counter.getOrDefault(path, 0) + 1);
            }
        }

        /**
         * Return the pattern with the largest score.
         * If there is more than one pattern with the same largest score,
         * return the lexicographically smallest such pattern.
         */
        int max = 0;
        String path = "";
        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            if (max < entry.getValue() || (max == entry.getValue() && entry.getKey().compareTo(path) < 0)) {
                max = entry.getValue();
                path = entry.getKey();
            }
        }
        return Arrays.asList(path.split(","));
    }

    static class Node {
        String user;

        int ts;

        String website;

        public Node(String user, int ts, String website) {
            this.user = user;
            this.ts= ts;
            this.website = website;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "user='" + user + '\'' +
                    ", ts=" + ts +
                    ", website='" + website + '\'' +
                    '}';
        }
    }
}
