package org.ict.algorithm.leetcode.breadthfirstsearch;

/**
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 *
 * Suppose we need to investigate a mutation from a gene string start to a gene string end where one mutation is defined as one single character changed in the gene string.
 *
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
 *
 * Given the two gene strings start and end and the gene bank bank,
 * return the minimum number of mutations needed to mutate from start to end.
 * If there is no such a mutation, return -1.
 *
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 *
 *
 *
 * Example 1:
 *
 * Input: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 * Output: 1
 * because AACCGGTT --> AACCGGTA
 *
 * Example 2:
 *
 * Input: start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * Output: 2
 * because AACCGGTT --> AACCGGTA --> AAACGGTA
 *
 * Example 3:
 *
 * Input: start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
 * Output: 3
 *
 *
 * Constraints:
 *
 * start.length == 8
 * end.length == 8
 * 0 <= bank.length <= 10
 * bank[i].length == 8
 * start, end, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
 *
 * This problem is same with WordLadder of LC127
 * @author sniper
 * @date 02 Nov, 2022
 *
 * LC433
 */
public class MinimumGeneticMutation {
    /**
     * Intuition: we can see each string as a node, and we can connect them if
     * 1. there is only one single character different
     * 2. the target node is available in `bank`
     * the problem is now to find the shortest path from the starting point to the ending point,
     * so we can use BFS.
     * @see <a href="https://leetcode.com/problems/minimum-genetic-mutation/discuss/2768872/LeetCode-The-Hard-Way-Explained-Line-By-Line"></a>
     */
    public int minMutationV1(String start, String end, String[] bank) {
        if (null == bank || bank.length == 0) {
            return -1;
        }
        //todo
        return 0;
    }

    /**
     * Solution is similar with PermutationI LC46
     * @see <a href="https://grandyang.com/leetcode/433"></a>
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation(String start, String end, String[] bank) {
        if (null == bank || bank.length == 0) {
            return -1;
        }
        boolean[] visited = new boolean[bank.length];
        return backtrack(start, end, bank, visited);
    }

    public int backtrackV1(String current, String end, String[] bank, boolean[] visited) {
        /**
         * while current is same as end, it means no need to do any transform between current and end.
         * So the transform step count is zero.
         */
        if (current.equals(end)) {
            return 0;
        }
        int n = bank.length;
        /**
         * e.g. start:abc --> end:def, bank:[dbc, dec, def]
         * The path length from start to end via bank array is following:
         * abc --> dbc --> dec --> def, we need 3 steps to transform abc to def.
         * In the worst case, we need to traverse all the nodes in the bank array.
         * So the variable res here means the minimum steps to transform.
         * It is initialized as n + 1 only for subsequent processing.
         * Because steps at most n, never greater than n + 1
         * we can use Integer.MAX_VALUE to replace n + 1.
         */
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            /**
             * skip bank items has been visited.
             * skip bank items differ more than one character with the current node string.
             */
            if (visited[i] || !isDiffOne(bank[i], current)) {
                continue;
            }
            /**
             * If current node bank[i] has not been visited,
             * marked it as visited and go to the next layer from the current node bank[i].
             */
            visited[i] = true;
            int total = backtrack(bank[i], end, bank, visited);
            /**
             * unmarked the current node bank[i] when above recursion return.
             */
            visited[i] = false;
            /**
             * Calculate the minimum between current transform steps and the value from recursion above.
             * We should to skip -1 here because while no such a mutation, this function returns -1.
             * See the last return statement of this function to understand the line: total != -1.
             */
            if (total != -1) {
                res = Math.min(res, total);
            }
        }
        /**
         *
         */
        return (res == Integer.MAX_VALUE ? -1 : res + 1);
    }

    /**
     * 1.filter repeated choices or not matched choices.
     * 2.mark the choice.
     * 3.recursion backtrack.
     * 4.unmark the choice.
     * 5.other process like calculate minimum or maximum,
     * this step is not necessary sometimes but is needed here.
     */
    public int backtrack(String current, String end, String[] bank, boolean[] visited) {
        if (current.equals(end)) {
            return 0;
        }
        int n = bank.length;
        int res = n + 1;
        for (int i = 0; i < n; i++) {
            if (visited[i] || !isDiffOne(bank[i], current)) {
                continue;
            }
            visited[i] = true;
            int total = backtrack(bank[i], end, bank, visited);
            visited[i] = false;
            if (total != -1) {
                res = Math.min(res, total);
            }
        }
        return (res == n + 1 ? -1 : res + 1);
    }

    public boolean isDiffOne(String a, String b) {
        if (a == null || b == null) {
            return false;
        }
        if (a.length() != b.length()) {
            return false;
        }
        // to store count of differences
        int differ = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                differ++;
            }
            if (differ > 1) {
                return false;
            }
        }
        // If we assume no duplicates in the word list, then we can return true directly.
        // The following return statement is equivalent on above assumption.
        //return true
        return (differ== 1 ? true : false);
    }

}
