package org.ict.algorithm.leetcode.unionfind;


/**
 * You are given two strings of the same length s1 and s2 and a string baseStr.
 *
 * We say s1[i] and s2[i] are equivalent characters.
 *
 * For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
 * Equivalent characters follow the usual rules of any equivalence relation:
 *
 * Reflexivity: 'a' == 'a'.
 * Symmetry: 'a' == 'b' implies 'b' == 'a'.
 * Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
 * For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" are equivalent strings of baseStr = "eed",
 * and "aab" is the lexicographically smallest equivalent string of baseStr.
 *
 * Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.
 *
 *
 *
 * Example 1:
 * Input: s1 = "parker", s2 = "morris", baseStr = "parser"
 * Output: "makkek"
 * Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
 * The characters in each group are equivalent and sorted in lexicographical order.
 * So the answer is "makkek".
 *
 * Example 2:
 * Input: s1 = "hello", s2 = "world", baseStr = "hold"
 * Output: "hdld"
 * Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [h,w], [d,e,o], [l,r].
 * So only the second letter 'o' in baseStr is changed to 'd', the answer is "hdld".
 *
 * Example 3:
 * Input: s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
 * Output: "aauaaaaada"
 * Explanation: We group the equivalent characters in s1 and s2 as [a,o,e,r,s,c], [l,p], [g,t] and [d,m],
 * thus all letters in baseStr except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".
 *
 * Constraints:
 *
 * 1 <= s1.length, s2.length, baseStr <= 1000
 * s1.length == s2.length
 * s1, s2, and baseStr consist of lowercase English letters.
 * @author sniper
 * @date 14 Jan, 2023
 * LC1061, Medium
 */
public class LexicographicallySmallestEquivalentString {

    public static void main(String[] args) {
        String s1 = "parker", s2 = "morris", baseStr = "parser";
        //String s1 = "abc", s2 = "cde", baseStr = "eed";

        LexicographicallySmallestEquivalentString instance = new  LexicographicallySmallestEquivalentString();
        String res = instance.smallestEquivalentString(s1, s2, baseStr);
        System.out.println(res);
    }

    public String smallestEquivalentStringV2(String s1, String s2, String baseStr) {
        return null;
    }

    public String smallestEquivalentStringV1(String s1, String s2, String baseStr) {
        return null;
    }

    /**
     * Understanding the following solution.
     * Union Find Solution
     * Time Cost 2ms
     * @param s1
     * @param s2
     * @param baseStr
     * @return
     */
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int n = s1.length();
        UnionFind uf = new UnionFind(26);
        for (int i = 0; i < n; i++) {
           int p = s1.charAt(i) - 'a';
           int q = s2.charAt(i) - 'a';
           uf.union(p, q);
        }
        StringBuilder sb = new StringBuilder();
        int m = baseStr.length();
        for (int i = 0; i < m; i++) {
            int x = uf.find(baseStr.charAt(i) - 'a');
            String s = String.valueOf((char)(x + 97));
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * e.g. s1 = "abc", s2 = "cde", baseStr = "eed", expect output:aab
     * 0 1 2 3 4
     * a b c d e
     * 0 1 0 1 2
     */
    class UnionFind {
        int[] parent;
        private int count;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public UnionFind(int start, int end) {
            count = end - start;
            parent = new int[end];
            for (int i = start; i < end; i++) {
                parent[i] = i;
            }
        }

        public int find (int p) {
            while (p != parent[p]) {
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int i = find(p);
            int j = find(q);
            if (i == j) {
                return;
            }
            /**
             * Notice here we change the parent pointer,
             * Let the bigger one point to the smaller one
             */
            if (i < j) {
                parent[j] = i;
            } else {
                parent[i] = j;
            }
            count--;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
    }

}
