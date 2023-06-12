package org.ict.algorithm.leetcode.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * There is a new alien language that uses the English alphabet.
 * However, the order among the letters is unknown to you.
 * You are given a list of strings words from the alien language's dictionary,
 * where the strings in words are sorted lexicographically by the rules of this new language.
 * Return a string of the unique letters in the new alien language sorted
 * in lexicographically increasing order by the new language's rules.
 * If there is no solution, return "".
 * If there are multiple solutions, return any of them.
 *
 *
 *
 * Example 1:
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 *
 * Example 2:
 * Input: words = ["z","x"]
 * Output: "zx"
 *
 * Example 3:
 * Input: words = ["z","x","z"]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 *
 *
 * Constraints:
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of only lowercase English letters.
 * @author sniper
 * @date 12 Jun 2023
 *
 * This Problem is similar with
 * {@link org.ict.algorithm.leetcode.breadthfirstsearch.CourseSchedule}
 * {@link org.ict.algorithm.leetcode.breadthfirstsearch.CourseScheduleTwo}
 * LC269, Hard, frequency=16
 */
public class AlienDictionary {

    public static void main(String[] args) {
        String[] words = {"wrt","wrf","er","ett","rftt"};
        //String[] words = {"z","x"};
        AlienDictionary instance = new AlienDictionary();
        String res = instance.alienOrderV4(words);
        System.out.println(res);
    }

    public String alienOrderV4(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        int[] inDegrees = new int[26];
        buildGraph(words, graph, inDegrees);
        return topologicalOrder(graph, inDegrees);
    }

    private void buildGraph(String[] words, Map<Character, Set<Character>> graph, int[] inDegrees) {
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                graph.putIfAbsent(ch, new HashSet<>());
            }
        }

        for (int i = 1; i < words.length; i++) {
            String w1 = words[i - 1];
            String w2 = words[i];

            int min = Math.min(w1.length(), w2.length());
            for (int j = 0; j < min; j++) {
                char u = w1.charAt(j);
                char v = w2.charAt(j);
                if (u != v) {
                    if (!graph.get(u).contains(v)) {
                        graph.get(u).add(v);
                        inDegrees[v - 'a']++;
                    }
                    // Later characters' order are meaningless
                    break;
                }
                // w1 = "ab", w2 = "a" -> invalid
                if (j == min - 1 && w1.length() > w2.length()) {
                    graph.clear();
                    return;
                }
            }
        }
    }

    private String topologicalOrder(Map<Character, Set<Character>> graph, int[] inDegrees) {
        StringBuilder res = new StringBuilder();
        Queue<Character> queue = graph.keySet()
                .stream()
                .filter(c -> inDegrees[c - 'a'] == 0)
                .collect(Collectors.toCollection(ArrayDeque::new));

        while (!queue.isEmpty()) {
            char u = queue.poll();
            res.append(u);
            for (char v : graph.get(u)) {
                if (--inDegrees[v - 'a'] == 0) {
                    queue.offer(v);
                }
            }
        }
        return res.length() == graph.size() ? res.toString() : "";
    }

    /**
     * Understanding the following solution
     * @param words
     * @return
     */
    public String alienOrderV3(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        int[] inDegrees = new int[26];
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                graph.putIfAbsent(ch, new HashSet<>());
            }
        }

        for (int i = 1; i < words.length; i++) {
            String w1 = words[i - 1];
            String w2 = words[i];
            /**
             * ["abc", "ab"]
             */
            if (w1.startsWith(w2) && !w1.equals(w2)) {
                return "";
            }

            for (int j = 0; j < w1.length() && j < w2.length(); j++) {
                char ch1 = w1.charAt(j);
                char ch2 = w2.charAt(j);
                if (ch1 != ch2) {
                    /**
                     * ch1 ---> ch2
                     */
                    if (!graph.get(ch1).contains(ch2)) {
                        graph.get(ch1).add(ch2);
                        inDegrees[ch2 - 'a']++;
                    }
                    // Later characters' order are meaningless
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (char ch : graph.keySet()) {
            if (inDegrees[ch - 'a'] == 0) {
                queue.offer(ch);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            res.append(ch);
            for (char next : graph.get(ch)) {
                inDegrees[next - 'a']--;
                if (inDegrees[next - 'a'] == 0) {
                    queue.offer(next);
                }
            }
        }

        return res.length() == graph.size() ? res.toString() : "";
    }


    /**
     * Understanding the following solution
     *
     * Topological-Sort
     * @param words
     * @return
     */
    public String alienOrderV2(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegrees = new HashMap<>();
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                graph.putIfAbsent(ch, new HashSet<>());
                inDegrees.putIfAbsent(ch, 0);
            }
        }

        for (int i = 1; i < words.length; i++) {
            String w1 = words[i - 1];
            String w2 = words[i];
            /**
             * ["abc", "ab"]
             */
            if (w1.startsWith(w2) && !w1.equals(w2)) {
                return "";
            }

            for (int j = 0; j < w1.length() && j < w2.length(); j++) {
                char ch1 = w1.charAt(j);
                char ch2 = w2.charAt(j);
                if (ch1 != ch2) {
                    /**
                     * ch1 ---> ch2
                     */
                    if (!graph.get(ch1).contains(ch2)) {
                        graph.get(ch1).add(ch2);
                        inDegrees.put(ch2, inDegrees.get(ch2) + 1);
                    }
                    // Later characters' order are meaningless
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (char ch : inDegrees.keySet()) {
            if (inDegrees.get(ch) == 0) {
                queue.offer(ch);
            }
        }

        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            res.append(ch);
            for (char next : graph.get(ch)) {
                inDegrees.put(next, inDegrees.get(next) - 1);
                if (inDegrees.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }

        return res.length() == graph.size() ? res.toString() : "";
    }

    /**
     * Depth-First-Search
     * ------------------------
     * To save space, it is not necessary to use a HashSet to record all occurrences of the letters as in the above solution,
     * instead, this two-dimensional array can be used to store this information.
     * At the same time, this two-dimensional array can also hold the information of the order pairs,
     * as long as graph[i][j] = true, we know that the letter in position i is in front of the letter in position j.
     * The method of finding the sequential pairs is exactly the same as the above solution,
     * after which the DFS traversal can be performed.
     * Since the DFS traversal needs to mark the traversal nodes, an array of visited is used.
     * Since it is a depth-first traversal,
     * it is not necessary to start traversing from the node with the entry degree of 0,
     * but from any node.
     * @param words
     * @return
     */
    public String alienOrderV1(String[] words) {
        boolean[][] graph = new boolean[26][26];
        boolean[] visited = new boolean[26];
        int n = words.length;
        StringBuilder res = new StringBuilder();
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                graph[ch - 'a'][ch - 'a'] = true;
            }
        }

        for (int i = 0; i < n - 1; i++) {
            int min = Math.min(words[i].length(), words[i + 1].length());
            int j = 0;
            for (; j < min; j++) {
                char ch1 = words[i].charAt(j);
                char ch2 = words[i + 1].charAt(j);
                if (ch1 != ch2) {
                    graph[ch1 - 'a'][ch2 - 'a'] = true;
                    break;
                }
            }
            if (j == min && words[i].length() > words[i + 1].length()) {
                return "";
            }
        }
        for (int i = 0; i < 26; i++) {
            if (!dfs(graph, i, visited, res)) {
                return "";
            }
        }
        return res.toString();
    }

    private boolean dfs(boolean[][] graph, int idx, boolean[] visited, StringBuilder res) {
        if (!graph[idx][idx]) {
            return true;
        }
        visited[idx] = true;
        for (int i = 0; i < 26; i++) {
            if (i == idx || !graph[i][idx]) {
                continue;
            }
            if (visited[i]) {
                return false;
            }
            if (!dfs(graph, i, visited, res)) {
                return false;
            }
        }
        visited[idx] = false;
        graph[idx][idx] = false;
        res.append((char)('a' + idx));
        return true;
    }

    /**
     * Breadth-First-Search
     * --------------------
     * We need
     * a TreeSet to save these pairs,
     * a HashSet to save all the letters that appear,
     * a one-dimensional array in to save the degree of entry of each letter,
     * and a queue to assist the topological traversal. pair,
     *
     * then according to these pair to assign degree,
     * the letters in the HashSet into the queue with degree 0,
     * and then start traversing,
     * if the letters exist in the TreeSet, the corresponding letters in the pair will be subtracted by 1,
     * if the degree is reduced to 0, then the corresponding letters will be added to the queue and added to the result res,
     * until the traversal is completed.
     * See if the number of elements in the result res and ch are the same,
     * if not, it means there may be a ring,
     * and return the empty string.
     * @param words
     * @return
     */
    public String alienOrder(String[] words) {
        Set<Pair> set = new HashSet<>();
        Set<Character> all = new HashSet<>();
        int[] inDegree = new int[256];
        Queue<Character> queue = new ArrayDeque<>();
        int n = words.length;

        for (String word : words) {
            for (char ch : word.toCharArray()) {
                all.add(ch);
            }
        }

        for (int i = 0; i < n - 1; i++) {
            int min = Math.min(words[i].length(), words[i + 1].length());
            int j = 0;
            for (; j < min; j++) {
                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    set.add(new Pair(words[i].charAt(j), words[i + 1].charAt(j)));
                    break;
                }
            }

            if (j == min && words[i].length() > words[i + 1].length()) {
                return "";
            }
        }

        for (Pair p : set) {
            inDegree[p.to]++;
        }

        StringBuilder res = new StringBuilder();
        for (Character ch : all) {
            if (inDegree[ch] == 0) {
                queue.offer(ch);
                res.append(ch);
            }
        }

        while (!queue.isEmpty()) {
            char cur = queue.poll();
            for (Pair p : set) {
                if (p.from == cur) {
                    inDegree[p.to]--;
                    if (inDegree[p.to] == 0) {
                        queue.offer(p.to);
                        res.append(p.to);
                    }
                }
            }
        }

        return res.length() == all.size() ? res.toString() : "";
    }

    static class Pair {
        private char from;
        private char to;

        public Pair(char from, char to) {
            this.from = from;
            this.to = to;
        }
    }

}
