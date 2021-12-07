package org.ict.algorithm.leetcode.tree;

import java.util.Arrays;

/**
 * Given an integer n,
 * return the number of structurally unique BST's (binary search trees)
 * which has exactly n nodes of unique values from 1 to n.
 * Input: n = 3
 * Output: 5
 *
 * Input: n = 1
 * Output: 1
 *
 * Constraints:
 * 1 <= n <= 19
 *
 * Number of Unique BST with a given key | Dynamic Programming
 * @see <a href="https://www.quora.com/Given-n-how-many-structurally-unique-BSTs-binary-search-trees-that-store-values-1-to-n-are-there-How-would-I-come-up-with-the-solution-Can-you-explain-the-thought-process-that-leads-to-the-solution"></a>
 * @see <a href="https://www.geeksforgeeks.org/number-of-unique-bst-with-a-given-key-dynamic-programming/"></a>
 *
 * @author sniper
 * @date 2021/12/2
 * LC96
 */
public class UniqueBinarySearchTrees {

    /**
     * DP solution
     * @param n
     * @return
     */
    public int numTreesV3(int n) {
        // DP to store the number
        // of unique BST with key i
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 0);

        // Base case
        dp[0] = 1;
        dp[1] = 1;

        // fill the dp table in
        // top-down approach.
        // fill the dp table in top-down approach.
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                // n-i in right * i-1 in left
                dp[i] = dp[i] + (dp[i - j] * dp[j - 1]);
            }
        }
        return dp[n];
    }

    /**
     * The number of binary search trees visualized as
     *
     * Number of binary search trees =
     * (Number of ways root can be choosen)*
     * (Number of Left binary search sub-trees) *
     * (Number of Right binary search sub-trees)
     * Now, since there are "n" nodes in BST and let,
     * the number of BST be represented by C(n) for n elements.
     *
     * We can find the number of BSTs recursively as :
     *
     * choose 1 as root, no element on the left sub-tree. n-1 elements on the right sub-tree.
     * Choose 2 as root, 1 element on the left sub-tree. n-2 elements on the right sub-tree.
     * Choose 3 as root, 2 element on the left sub-tree. n-3 elements on the right sub-tree.
     *
     * Similarly, for i-th element as the root, i-1 elements on the left and n-i on the right.
     *
     * These sub-trees are also BSTs so we can write :
     *
     * C(n) = C(0)C(n-1) + C(1)C(n-2) + .....+ C(i-1)C(n-i)..... + C(n-1)C(0)
     *
     * C(0) = 1, as there is exactly 1 way to make a BST with 0 nodes.
     * C(1) = 1, as there is exactly 1 way to make a BST with 1 node.
     *
     * 𝐶(𝑛)=∑𝑖=1->𝑛:𝐶(𝑖−1)𝐶(𝑛−𝑖)
     *
     * @see <a href="https://en.wikipedia.org/wiki/Binomial_coefficient"></a>
     *
     * The above summation dissolves into Catalan Number.
     * (2n n)/(n+1)
     * @param n
     * @return
     */
    public static int numTreesV2(int n) {
        int nf = factorial(n);
        System.out.println("nf:" + nf);
        int res = 1;
        for (int i = (n + 2); i<= 2*n; i++) {
            res *= i;
        }
        System.out.println("res:" + res);
        return res / nf;
    }

    public static void main(String[] args) {
        int res = numTreesV2(5);
        System.out.println(res);
    }

    public static int factorial(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    /**
     * The following recursive method is too slow
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if(n <= 1) {
            return 1;
        }
        int sum =0;
        for(int i = 1; i <= n; i++) {
            int left = numTrees(i-1);
            int right = numTrees(n - i);
            sum+= left*right;
        }
        return sum;
    }
}
