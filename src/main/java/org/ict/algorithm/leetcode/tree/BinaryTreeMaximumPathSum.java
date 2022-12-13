package org.ict.algorithm.leetcode.tree;

import java.util.*;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
 * A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 *
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 10^4].
 * -1000 <= Node.val <= 1000
 * LC124
 */
public class BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        TreeNode root = test2();
        BinaryTreeMaximumPathSum instance = new BinaryTreeMaximumPathSum();
        int result = instance.maxPathSumWrong(root);
        System.out.println(result);
    }

    /**
     * nums = [1,-2,-3,1,3,-2,null,-1]
     *           1
     *         /   \
     *       -2    -3
     *      / \    /
     *     1  3  -2
     *    /
     *   -1
     *
     *           -3
     *         /   \
     *       1    -5
     *      / \    /
     *     0  3  -2
     *    /
     *   -1
     * @return
     */
    public static TreeNode test2() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(-2);
        TreeNode l1 = new TreeNode(1);
        TreeNode r1 = new TreeNode(3);
        left.left = l1;
        left.right = r1;
        TreeNode l3 = new TreeNode(-1);
        l1.left = l3;
        TreeNode right = new TreeNode(-3);
        TreeNode l2 = new TreeNode(-2);
        right.left = l2;
        root.left = left;
        root.right = right;
        return root;
    }

    public static TreeNode test1() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        return root;
    }



    /**
     * nums = [1,-2,-3,1,3,-2,null,-1]
     * @param root
     * @return
     */
    public int maxPathSumWrong(TreeNode root) {
        List<Integer> list = inOrder(root);
        int n = list.size();
        /**
         * dp[i] means the maximum sub-array ending with nums[i];
         */
        int[] dp = new int[n];
        dp[0] = list.get(0);
        int max = dp[0];

        for(int i = 1; i < n; i++){
            dp[i] = list.get(i) + Math.max(dp[i - 1], 0);
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public List<Integer> inOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }

    /**
     * Time Cost 1ms
     * Recursive Solution without Global Variable.
     * @param root
     * @return
     */
    public int maxPathSumV1(TreeNode root) {
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        helperV1(root, res);
        return res[0];
    }

    public int helperV1(TreeNode root, int[] res) {
        if (null == root) {//1
            return 0;
        }
        int left = Math.max(0, helperV1(root.left, res));//2
        int right = Math.max(0, helperV1(root.right, res));//3
        res[0] = Math.max(res[0], left + right + root.val);//4
        return Math.max(left, right) + root.val;//used in 2 and 3
    }


    private int ans;

    /**
     * Time Cost 0ms
     * Recursive Solution with Global Variable.
     * Time Complexity O(N)
     * Space Complexity O(N)
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        helper(root);
        return ans;
    }

    public int helper(TreeNode root) {
         if (null == root) {//1
             return 0;
         }
         int left = Math.max(0, helper(root.left));//2
         int right = Math.max(0, helper(root.right));//3
         ans = Math.max(ans, left + right + root.val);//4
         return Math.max(left, right) + root.val;//used in 2 and 3
    }



}
